/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.core;

import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFile;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawObject;
import br.com.dwarfmod.dwarffortressmodmaker.utils.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ander
 */
public class ModWritter {
    public static void writeInfo(final ModManager install, final Mod mod) throws IOException {
        File file = new File(mod.getModPath() + "//info.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("[ID:" + mod.getId() + "]");
        bw.newLine();
        bw.write("[NUMERIC_VERSION:" + mod.getNumericVersion() + "]");
        bw.newLine();
        bw.write("[DISPLAYED_VERSION:" + mod.getDisplayedVersion() + "]");
        bw.newLine();
        bw.write("[EARLIEST_COMPATIBLE_NUMERIC_VERSION:" + mod.getEarliestCompatibleNumericVersion() + "]");
        bw.newLine();
        bw.write("[EARLIEST_COMPATIBLE_DISPLAYED_VERSION:" + mod.getEarliestCompatibleDisplayedVersion() + "]");
        bw.newLine();
        bw.write("[AUTHOR:" + mod.getAuthor() + "]");
        bw.newLine();
        bw.write("[NAME:" + mod.getName() + "]");
        bw.newLine();
        bw.write("[DESCRIPTION:" + mod.getDescription() + "]");
        bw.newLine();
        
        if (mod.getRequiresIdBeforeMe() != null) {
            for (String require : mod.getRequiresIdBeforeMe()) {
                bw.write("[REQUIRES_ID_BEFORE_ME:" + require + "]");
                bw.newLine();
            }
        }
        bw.newLine();
        bw.write("[STEAM_TITLE:" + mod.getSteamTitle() + "]");
        bw.newLine();
        
        if (mod.getSteamTags() != null) {
            for (String tag : mod.getSteamTags()) {
                bw.write("[STEAM_TAG:" + tag + "]");
                bw.newLine();
            }
        }
        
        bw.write("[STEAM_CHANGELOG:" + mod.getSteamChangelog() + "]");
        bw.newLine();
        bw.write("[STEAM_FILE_ID:" + mod.getSteamFileId() + "]");
        bw.newLine();
        
        bw.close();
        fw.close();
    }
    
    public static void writeEntity(final ModManager install, final Mod mod) throws IOException {
        for (RawFile entity : mod.getObjects()) {
            ModWritter.saveEntity(install, entity, mod);
        }
        for (RawFile entity : mod.getGraphics()) {
            ModWritter.saveGraphics(install, entity, mod);
        }
    }

    private static void saveEntity(final ModManager install, final RawFile root, final Mod mod) throws IOException {
        new File(mod.getModPath() + Constants.OBJECTS_FOLDER).mkdir();
        
        File file = new File(mod.getModPath() + Constants.OBJECTS_FOLDER + "//" + root.getName() + ".txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(root.getName());
        bw.newLine();
        bw.newLine();
        
        if (root.getDescription() != null && !root.getDescription().isEmpty()) {
            bw.write(root.getDescription());
            bw.newLine();
            bw.newLine();
        }
        
        final String objClass = root.getType().name();
        bw.write(objClass.contains("]") ? objClass : "[OBJECT:" + root.getType().name() + "]");
        bw.newLine();
        bw.newLine();
        
        int level = 0;
        for (RawObject ent : root.getEntries()) {
            ModWritter.saveChildrenEntities(bw, ent, level);
            bw.newLine();
        }
        
        bw.close();
        fw.close();
    }
    
    private static void saveGraphics(final ModManager install, final RawFile root, final Mod mod) throws IOException {
        new File(mod.getModPath() + Constants.GRAPHICS_FOLDER).mkdir();
        
        File file = new File(mod.getModPath() + Constants.GRAPHICS_FOLDER + "//" + root.getName() + ".txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(root.getName());
        bw.newLine();
        bw.newLine();
        
        if (root.getDescription() != null && !root.getDescription().isEmpty()) {
            bw.write(root.getDescription());
            bw.newLine();
            bw.newLine();
        }
        
        final String objClass = root.getType().name();
        bw.write(objClass.contains("]") ? objClass : "[OBJECT:" + root.getType().name() + "]");
        bw.newLine();
        bw.newLine();
        
        int level = 0;
        for (RawObject ent : root.getEntries()) {
            ModWritter.saveChildrenEntities(bw, ent, level);
            bw.newLine();
        }
        
        bw.close();
        fw.close();
    }

    private static void saveChildrenEntities(BufferedWriter bw, RawObject entity, int level) throws IOException {
        int levelAux = level;
        String line = "";
        while (levelAux > 0) {
            line += "   ";
            levelAux--;
        }
        if (entity.getDescription() != null && !entity.getDescription().isEmpty()) {
            bw.write(line + entity.getDescription());
            bw.newLine();
        }
        bw.write(line + entity.getName());
        bw.newLine();
        if (entity.getEntries() != null && !entity.getEntries().isEmpty()) {
            level += 1;
            for (RawObject ent : entity.getEntries()) {
                ModWritter.saveChildrenEntities(bw, ent, level);
            }
        }
    }
}
