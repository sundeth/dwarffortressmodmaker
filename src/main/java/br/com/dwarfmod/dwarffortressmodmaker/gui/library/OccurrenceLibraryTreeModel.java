/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.gui.library;

/**
 *
 * @author Ander
 */
import br.com.dwarfmod.dwarffortressmodmaker.data.library.Occurrence;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.OccurrenceLibrary;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;
import java.util.Map;

public class OccurrenceLibraryTreeModel extends DefaultTreeModel {

    public OccurrenceLibraryTreeModel(OccurrenceLibrary occurrenceLibrary) {
        super(new DefaultMutableTreeNode("Occurrence Library"));

        Map<String, Occurrence> occurrenceList = occurrenceLibrary.getOccurrenceList();

        for (String key : occurrenceList.keySet()) {
            DefaultMutableTreeNode keyNode = new DefaultMutableTreeNode(occurrenceList.get(key));
            addNodeToRoot(keyNode);

            Occurrence occurrence = occurrenceList.get(key);
            //DefaultMutableTreeNode occurrenceNode = new DefaultMutableTreeNode(occurrence);
            //addNodeToParent(occurrenceNode, keyNode);
            buildOccurrenceTree(occurrence, keyNode);
        }
    }

    private void buildOccurrenceTree(Occurrence occurrence, DefaultMutableTreeNode occurrenceNode) {
        Map<String, Occurrence> occurrenceList = occurrence.getOccurrenceList();

        for (String key : occurrenceList.keySet()) {
            DefaultMutableTreeNode keyNode = new DefaultMutableTreeNode(occurrenceList.get(key));
            addNodeToParent(keyNode, occurrenceNode);

            for (Occurrence subOccurrence : occurrenceList.get(key).getOccurrenceList().values()) {
                DefaultMutableTreeNode subOccurrenceNode = new DefaultMutableTreeNode(subOccurrence);
                addNodeToParent(subOccurrenceNode, occurrenceNode);

                buildOccurrenceTree(subOccurrence, subOccurrenceNode);
            }
        }
    }

    private void addNodeToRoot(DefaultMutableTreeNode node) {
        ((DefaultMutableTreeNode) root).add(node);
        nodesWereInserted(root, new int[]{getChildCount(root) - 1});
    }

    private void addNodeToParent(DefaultMutableTreeNode childNode, DefaultMutableTreeNode parentNode) {
        parentNode.add(childNode);
        nodesWereInserted(parentNode, new int[]{getChildCount(parentNode) - 1});
    }
}
