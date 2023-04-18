/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.core;

import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.ModType;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFileTypeEnum;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.OccurrenceLibrary;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.TokenLibrary;
import br.com.dwarfmod.dwarffortressmodmaker.utils.Constants;
import br.com.dwarfmod.dwarffortressmodmaker.utils.StringLibrary;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Ander
 */
@Data
@Builder
public class ModManager {

    private String path;

    private List<Mod> modList;
    
    private TokenLibrary tokenLibrary;
    private OccurrenceLibrary occurrenceLibrary;

    public void initialize() {
        this.modList = new ArrayList();
        this.occurrenceLibrary = OccurrenceLibrary.builder().occurrenceList(new HashMap<>()).build();
        
        this.readMods(new File(this.path + Constants.VANILLA_FOLDER), ModType.VANILLA);
        this.readMods(new File(this.path + Constants.MODS_FOLDER), ModType.CUSTOM);
    }
    
    private void readMods(final File target, final ModType type) {
        for (File file : target.listFiles()) {
            if (file.getName().equalsIgnoreCase("examples and notes") || file.getName().equalsIgnoreCase("interaction examples") || file.getName().equalsIgnoreCase("mod_upload") || file.isFile()) {
                continue;
            }
            try {
                final Mod mod = ModReader.readRecordsFromFile(file.getAbsolutePath() + "//info.txt");
                mod.setType(type);
                mod.setModPath(file.getAbsolutePath());

                if (new File(file.getAbsolutePath() + "//" + Constants.MOD_MAKER_FILE).exists()) {
                    mod.setType(ModType.MODMAKER);
                }
                this.readObjects(mod, Constants.OBJECTS_FOLDER);
                this.readObjects(mod, Constants.GRAPHICS_FOLDER);
                this.modList.add(mod);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, StringLibrary.INIT_PARSE_ERROR.replace("#MOD", file.getName()), StringLibrary.GENERAL_ERROR, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void readObjects(final Mod mod, final String rootFolder) throws IOException {
        final File objects = new File(mod.getModPath() + rootFolder);
        if (objects.exists() && objects.isDirectory()) {
            for (File obj : objects.listFiles()) {
                try {
                    if (mod.getObjects() == null) {
                        mod.setObjects(new TreeSet<>());
                    }
                    if (!obj.getName().endsWith(".txt")) {
                        continue;
                    }
                    mod.getObjects().add(RawFileReader.read(mod, obj.getAbsolutePath(), this.occurrenceLibrary));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, StringLibrary.INIT_OBJECT_PARSE_ERROR.replace("#MOD", obj.getName()), StringLibrary.GENERAL_ERROR, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void readTokenLibrary() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.tokenLibrary = mapper.readValue(Paths.get(Constants.TOKEN_LIBRARY_FILE).toFile(), TokenLibrary.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            this.tokenLibrary = null;
        }
    }

    public void updateTokenLibrary() throws IOException {
        final TokenLibrary newLibrary = TokenLibrary.builder().tokenMap(new HashMap<>()).build();
        for (RawFileTypeEnum type : RawFileTypeEnum.values()) {
            if (!type.getUrl().isEmpty()) {
                newLibrary.getTokenMap().put(type, TableParser.parseHtmlForTokens(type, type.getUrl(), type.getTitles()));
            }
        }
        this.tokenLibrary = newLibrary;
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Constants.TOKEN_LIBRARY_FILE).toFile(), this.tokenLibrary);
    }
}