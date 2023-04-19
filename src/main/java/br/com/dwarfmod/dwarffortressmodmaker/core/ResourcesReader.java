/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Ander
 */
public class ResourcesReader {
    
    private static ResourcesReader instance;
    
    private ResourcesReader() {
        
    }
    
    public static ResourcesReader getInstance() {
        if (instance == null) {
            instance = new ResourcesReader();
        }
        return instance;
    }
    
    private Map<String, ImageIcon> icons;
    
    public Map<String, ImageIcon> getIcons() {
        if (this.icons == null) {
            final Map<String, ImageIcon> resourceMap = new HashMap<>();
            final File dir = new File(getClass().getClassLoader().getResource("resources/images").getPath());

            for (File f : dir.listFiles()) {
                try {
                    resourceMap.put(f.getName().substring(0, f.getName().lastIndexOf(".")), new ImageIcon(ImageIO.read(f)));
                } catch (IOException ex) {
                    Logger.getLogger(ResourcesReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.icons = resourceMap;
        }
        return this.icons;
    }
}
