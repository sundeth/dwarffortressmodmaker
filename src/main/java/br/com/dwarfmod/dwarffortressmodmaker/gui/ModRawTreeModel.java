/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.gui;

import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFile;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawObject;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Ander
 */
//***********************************************************************************************************************************************************************
// Mod Entity JTree Model
//***********************************************************************************************************************************************************************
public class ModRawTreeModel extends DefaultTreeModel {

    private Mod mod;

    public ModRawTreeModel(Mod mod) {
        super(new DefaultMutableTreeNode(mod));
        this.mod = mod;
        addNodes((DefaultMutableTreeNode) getRoot(), mod);
    }

    private void addNodes(DefaultMutableTreeNode node, Object obj) {
        if (obj instanceof Mod) {
            Mod mod = (Mod) obj;
            for (RawFile file : mod.getObjects()) {
                addNodes(node, file);
            }
            for (RawFile file : mod.getGraphics()) {
                addNodes(node, file);
            }
        } else if (obj instanceof RawFile) {
            RawFile file = (RawFile) obj;
            DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
            node.add(fileNode);
            for (RawObject entry : file.getEntries()) {
                addNodes(fileNode, entry);
            }
        } else if (obj instanceof RawObject) {
            RawObject rawObject = (RawObject) obj;
            DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(rawObject);
            node.add(objectNode);
            for (RawObject entry : rawObject.getEntries()) {
                addNodes(objectNode, entry);
            }
        }
    }
}