/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.core;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
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
        try {
            if (this.icons == null) {
                final Map<String, ImageIcon> resourceMap = new HashMap<>();
                final URL url = getClass().getClassLoader().getResource("resources/images");
                if (url != null) {
                    if (url.getProtocol().equals("jar")) {
                        final JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
                        final JarFile jarFile = jarConnection.getJarFile();
                        final Enumeration<JarEntry> entries = jarFile.entries();
                        
                        while (entries.hasMoreElements()) {
                            final JarEntry entry = entries.nextElement();
                            if (entry.getName().startsWith("resources/images/") && !entry.isDirectory()) {
                                final String name = entry.getName().substring("resources/images/".length());
                                final ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(entry.getName()));
                                resourceMap.put(name.substring(0, name.lastIndexOf(".")), icon);
                            }
                        }
                    } else {
                        final File dir = new File(url.getPath());
                        for (File f : dir.listFiles()) {
                            try {
                                resourceMap.put(f.getName().substring(0, f.getName().lastIndexOf(".")), new ImageIcon(ImageIO.read(f)));
                            } catch (IOException ex) {
                                Logger.getLogger(ResourcesReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    this.icons = resourceMap;
                }
            }
            return this.icons;
        } catch (Exception e) {
        }
        return null;
    }
}
