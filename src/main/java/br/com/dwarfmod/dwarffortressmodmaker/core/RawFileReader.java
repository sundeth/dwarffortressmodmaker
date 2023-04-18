/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.core;

import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFile;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFileTypeEnum;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawObject;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.Occurrence;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.OccurrenceLibrary;
import br.com.dwarfmod.dwarffortressmodmaker.utils.ModFileUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class RawFileReader {

    public static RawFile read(final Mod mod, final String filePath, final OccurrenceLibrary occurrenceLibrary) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        final RawFile rawFile = RawFile.builder().build();
        rawFile.setFullPath(filePath);
        
        String line = null;
        int lineCount = 0;
        int structureCount = 0;

        final RawObject object = RawObject.builder().build();
        object.setEntries(new TreeSet<>());
        
        int level = 0;
        boolean firstObj = true;
        final RawObject[] hier = new RawObject[10];
        hier[0] = object;
        RawObject lastObj = object;
        
        Occurrence rootOcur = Occurrence.builder().occurrenceList(new HashMap<>()).values(new ArrayList<>()).build();
        final Occurrence[] hierOcur = new Occurrence[10];
        hierOcur[0] = rootOcur;
        Occurrence lastocur = rootOcur;
        
        try {
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (lineCount == 0) {
                    object.setName(line);
                    structureCount = 1;
                } else if (structureCount == 1 && !line.isEmpty() && !line.startsWith("[")) {
                    object.setDescription(line);
                    structureCount = 2;
                } else if (structureCount == 2 && !line.isEmpty() && !line.startsWith("[")) {
                    object.setDescription(object.getDescription() + "\n" + line);

                } else if (structureCount <= 2 && line.trim().replaceAll("\t", "").startsWith("[")) {
                    structureCount = 3;
                    object.setObjectClass(line.replace("\t", "").trim());
                    
                    // Add file object class to library
                    final String token = splitParameter(object.getObjectClass())[1];
                    rootOcur.setToken(token);
                    if (!occurrenceLibrary.getOccurrenceList().containsKey(rootOcur.getToken())) {
                        occurrenceLibrary.getOccurrenceList().put(rootOcur.getToken(), rootOcur);
                    } else {
                        rootOcur = occurrenceLibrary.getOccurrenceList().get(rootOcur.getToken());
                    }
                    hierOcur[0] = rootOcur;
                } else if (structureCount == 3) {
                    if (line.isEmpty()) {
                        level = 1;
                        firstObj = true;
                        continue;
                    } else if (!line.contains("[") || line.startsWith("=")) {
                        hier[level-1].setDescription(hier[level-1].getDescription() + "/n" + line.replace("    ", "").replace("\t", ""));
                        continue;
                    }
                    
                    int currentLevel = line.contains("\t") ?  StringUtils.countMatches(line.substring(0, line.indexOf("[")), "\t") + 1 : 1;
                    if (currentLevel > level) {
                        level = currentLevel;
                        hier[level-1] = lastObj;
                        hierOcur[level-1] = lastocur;
                    }
                    
                    if (line.contains("_GRAPHICS:") && level > 1) {
                        level = 1;
                    }

                    final RawObject subObj = RawObject.builder()
                        .entries(new TreeSet<>())
                        .name(line.replace("    ", "").replace("\t", ""))
                        .description("")
                        .objectClass(object.getObjectClass())
                        .build();

                    hier[level-1].getEntries().add(subObj);
                    
                    //Add value to library
                    final Occurrence parentOccur = hierOcur[level-1];
                    final String token = splitParameter(subObj.getName())[0];
                    
                    Occurrence entry;
                    if (parentOccur.getToken().equals(token)) {
                        entry = parentOccur;
                    } else {
                        if (!parentOccur.getOccurrenceList().containsKey(token)) {
                            parentOccur.getOccurrenceList().put(token, Occurrence.builder().token(token).occurrenceList(new HashMap<>()).values(new ArrayList<>()).build());
                        } 
                        entry = parentOccur.getOccurrenceList().get(token);
                    }
                    if (!entry.getValues().contains(line.trim())) {
                        entry.getValues().add(line.trim());
                    }
                    
                    
                    //-------------------------------------------------
                    
                    if (firstObj) {
                        level = 2;
                        firstObj = false;
                        hier[level-1] = subObj;
                        hierOcur[level-1] = entry;
                    }
                    
                    lastocur = entry;
                    lastObj = subObj;
                    
                }
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println(rawFile.getFileName());
            System.out.println(mod.getId());
            System.out.println(line);
        }
        reader.close();
        
        rawFile.setId(hier[0].getName());
        rawFile.setDescription(hier[0].getDescription());
        rawFile.setType(RawFileTypeEnum.valueOf(hier[0].getObjectClass().substring(hier[0].getObjectClass().indexOf(":")+1, hier[0].getObjectClass().length()-1)));
        rawFile.setObjects(hier[0].getEntries());

        return rawFile;
    }

    public static String[] splitParameter(String par) {
        if (par == null) {
            return null;
        }
        String key;
        String value = null;
        if (par.contains("]")) {
            par = par.substring(0, par.lastIndexOf("]")+1);
        }
        
        if (par.split("]").length > 1) {
            key = par.substring(0, par.indexOf("]")+1).split(":")[0].substring(1);
            return new String[] { key, par.trim() };
        } if (par.split(":").length > 1) {
            key = par.split(":")[0];
            value = par.split(":")[1];
        } else {
            key = par;
        }
        
        if (value != null && value.trim().startsWith(":")) {
            value = value.substring(1);
        }
         
        return value == null ? new String[] {key.replace("[", "").replace("]", "").replace("    ", "").replace("\t", "")}
                : new String[] {key.replace("[", "").replace("]", "").replace("    ", "").replace("\t", ""), value.replace("[", "").replace("]", "").replace("    ", "").replace("\t", "")};
    }
}