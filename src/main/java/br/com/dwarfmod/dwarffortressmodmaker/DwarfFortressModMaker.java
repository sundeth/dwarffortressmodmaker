/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.dwarfmod.dwarffortressmodmaker;

import br.com.dwarfmod.dwarffortressmodmaker.core.ModManager;
import br.com.dwarfmod.dwarffortressmodmaker.gui.MainWindow;
import br.com.dwarfmod.dwarffortressmodmaker.utils.Constants;
import br.com.dwarfmod.dwarffortressmodmaker.utils.StringLibrary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Ander
 */
public class DwarfFortressModMaker {

    public static void main(String[] args) {
        try {
            // Set the look and feel to FlatDarkLaf
            UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf");
        }
        
        initialize();
    }

    private static void initialize() {
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(Paths.get(Constants.INIT_FILE).toFile(), Map.class);
            if (map.containsKey("dfFolder")) {
                new MainWindow(ModManager.builder().path(map.get("dfFolder")).build()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, StringLibrary.INIT_ERROR_JSON, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle(StringLibrary.INIT_INSTALLATION_TITLE);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }
            final File df = fc.getSelectedFile();
            if (df == null || !df.exists()) {
                JOptionPane.showMessageDialog(null, StringLibrary.INIT_INVALID_PATH, StringLibrary.GENERAL_ERROR, JOptionPane.ERROR_MESSAGE);
            } else {
                boolean found = false;
                for (File file : df.listFiles()) {
                    if (file.getName().equalsIgnoreCase("Dwarf Fortress.exe")) {
                        final Map<String, String> map = new HashMap<>();
                        map.put("dfFolder", df.getAbsolutePath());
                        try {
                            mapper.writeValue(Paths.get(Constants.INIT_FILE).toFile(), map);
                            found = true;
                        } catch (IOException ex1) {
                            JOptionPane.showMessageDialog(null, StringLibrary.INIT_ERROR_SAVE_DATA, StringLibrary.GENERAL_ERROR, JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, StringLibrary.INIT_GAME_PATH, StringLibrary.GENERAL_ERROR, JOptionPane.ERROR_MESSAGE);
                } else {
                    initialize();
                }
            }
        }
    }
}
