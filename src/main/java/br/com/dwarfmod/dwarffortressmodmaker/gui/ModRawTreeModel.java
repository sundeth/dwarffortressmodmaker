/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.gui;

import br.com.dwarfmod.dwarffortressmodmaker.data.Mod;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFile;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Ander
 */
//***********************************************************************************************************************************************************************
// Mod Entity JTree Model
//***********************************************************************************************************************************************************************
public class ModRawTreeModel implements TreeModel {

    private final Mod root;

    public ModRawTreeModel(Mod root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof Mod) {
            Mod mod = (Mod) parent;
            List<Object> childNodes = new LinkedList<>();

            if (mod.getObjects() != null) {
                childNodes.addAll(mod.getObjects());
            }

            return childNodes.get(index);
        } else if (parent instanceof RawFile) {
            RawFile rawFile = (RawFile) parent;
            List<Object> childNodes = new LinkedList<>();

            if (rawFile.getObjects() != null) {
                childNodes.addAll(rawFile.getObjects());
            }

            return childNodes.get(index);
        } else if (parent instanceof RawObject) {
            RawObject rawFile = (RawObject) parent;
            List<Object> childNodes = new LinkedList<>();

            if (rawFile.getEntries() != null) {
                childNodes.addAll(rawFile.getEntries());
            }

            return childNodes.get(index);
        } else {
            return null;
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Mod) {
            Mod modEntity = (Mod) parent;

            int childCount = 0;
            if (modEntity.getObjects() != null) {
                childCount += modEntity.getObjects().size();
            }

            return childCount;
        } else if (parent instanceof RawFile) {
            RawFile modEntity = (RawFile) parent;

            int childCount = 0;
            if (modEntity.getObjects() != null) {
                childCount += modEntity.getObjects().size();
            }

            return childCount;
        } else if (parent instanceof RawObject) {
            RawObject modEntity = (RawObject) parent;

            int childCount = 0;
            if (modEntity.getEntries() != null) {
                childCount += modEntity.getEntries().size();
            }

            return childCount;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return (node instanceof RawObject && ((RawObject) node).getEntries().isEmpty()) || 
                (node instanceof RawFile && ((RawFile) node).getObjects().isEmpty()) || 
                (node instanceof Mod && ((Mod) node).getObjects().isEmpty()) || 
                node == null;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // Not implemented
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof Mod) {
            Mod mod = (Mod) parent;

            List<Object> childNodes = new LinkedList<>();

            if (mod.getObjects() != null) {
                childNodes.addAll(mod.getObjects());
            }
            return childNodes.indexOf(child);
        } else if (parent instanceof RawFile) {
            RawFile rawFile = (RawFile) parent;

            List<Object> childNodes = new LinkedList<>();

            if (rawFile.getObjects() != null) {
                childNodes.addAll(rawFile.getObjects());
            }
            return childNodes.indexOf(child);
        } else if (parent instanceof RawObject) {
            RawObject rawFile = (RawObject) parent;

            List<Object> childNodes = new LinkedList<>();

            if (rawFile.getEntries() != null) {
                childNodes.addAll(rawFile.getEntries());
            }
            return childNodes.indexOf(child);
        } else {
            return -1;
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        // Not implemented
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        // Not implemented
    }
}
