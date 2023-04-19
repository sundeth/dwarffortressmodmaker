/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.gui;

import br.com.dwarfmod.dwarffortressmodmaker.core.ResourcesReader;
import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.ModType;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFile;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFileTypeEnum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Ander
 */
public class ModFileTreeCellRenderer extends DefaultTreeCellRenderer {

    private Map<String, ImageIcon> iconMap;
    
    public ModFileTreeCellRenderer() {
        final ResourcesReader reader = ResourcesReader.getInstance();
        this.iconMap = reader.getIcons();
    }
    
    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected,
            final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {

        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        
        
        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object object = node.getUserObject();
            if (object instanceof Mod) {
                Mod mod = (Mod) object;
                JPanel panel = new JPanel(new BorderLayout());
                JLabel label = new JLabel(mod.getName());
                label.setForeground(mod.getType() == ModType.VANILLA ? Color.BLACK
                        : mod.getType() == ModType.MODMAKER ? new Color(51, 0, 204) : new Color(59, 128, 64));
                panel.add(label, BorderLayout.CENTER);
                component = panel;
            } else if (object instanceof RawFile) {
                RawFile file = (RawFile) object;
                JPanel panel = new JPanel(new BorderLayout());
                JLabel label = new JLabel(file.getFileName());
                label.setForeground(file.getType() == RawFileTypeEnum.DESCRIPTOR_PATTERN ? Color.RED
                        : Color.BLACK);
                panel.add(label, BorderLayout.CENTER);
                component = panel;
            }
        }

        return component;
    }
}
