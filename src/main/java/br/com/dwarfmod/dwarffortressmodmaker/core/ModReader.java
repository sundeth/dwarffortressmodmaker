package br.com.dwarfmod.dwarffortressmodmaker.core;

import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.Mod.ModBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JOptionPane;

public class ModReader {
    public static Mod readRecordsFromFile(final String filename) throws IOException {
        Mod record = null;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[ID:")) {
                    String id = line.substring(line.indexOf(":") + 1, line.indexOf("]"));
                    ModBuilder builder = Mod.builder().id(id);
                    
                    TreeSet<String> requiredIds = new TreeSet<>();
                    TreeSet<String> steamTags = new TreeSet<>();
                    
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        if (line.startsWith("[NUMERIC_VERSION:")) {
                            builder.numericVersion(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[DISPLAYED_VERSION:")) {
                            builder.displayedVersion(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[EARLIEST_COMPATIBLE_NUMERIC_VERSION:")) {
                            builder.earliestCompatibleNumericVersion(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[EARLIEST_COMPATIBLE_DISPLAYED_VERSION:")) {
                            builder.earliestCompatibleDisplayedVersion(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[AUTHOR:")) {
                            builder.author(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[NAME:")) {
                            builder.name(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[DESCRIPTION:")) {
                            StringBuilder description = new StringBuilder(line.substring(line.indexOf(":") + 1).replace("]", ""));
                            while ((line = reader.readLine()) != null && !line.startsWith("[")) {
                                description.append("\n").append(line.replace("]", ""));
                            }
                            builder.description(description.toString());
                        } else if (line.startsWith("[REQUIRES_ID_BEFORE_ME:")) {
                            requiredIds.addAll(Set.of(line.substring(line.indexOf(":") + 1, line.indexOf("]"))));
                        } else if (line.startsWith("[STEAM_TITLE:")) {
                            builder.steamTitle(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[STEAM_TAG:")) {
                            steamTags.addAll(Set.of(line.substring(line.indexOf(":") + 1, line.indexOf("]"))));
                        } else if (line.startsWith("[STEAM_CHANGELOG:")) {
                            builder.steamChangelog(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        } else if (line.startsWith("[STEAM_FILE_ID:")) {
                            builder.steamFileId(line.substring(line.indexOf(":") + 1, line.indexOf("]")));
                        }
                    }
                    builder.requiresIdBeforeMe(requiredIds);
                    builder.steamTags(steamTags);
                    record = builder.objects(new TreeSet<>()).build();
                }
            }
        } catch( Exception e) {
            JOptionPane.showMessageDialog(null, "Error on reading file " + line + "\nFix file " + filename + "\nFix it and try again", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        reader.close();
        return record;
    }
}
