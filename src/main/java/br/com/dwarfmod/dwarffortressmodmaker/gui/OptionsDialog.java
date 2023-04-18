/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.gui;

import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Ander
 */
public class OptionsDialog extends JDialog implements ActionListener {
    private ArrayList<JCheckBox> checkboxes;
    private JPanel panel;
    private JButton confirmButton, cancelButton;
    private String result;

    public OptionsDialog(JFrame parent, String title, String optionsString, String[] options) {
        super(parent, title, true);
        checkboxes = new ArrayList<>();
        result = optionsString;
        
        // create the panel and set its layout
        panel = new JPanel(new GridLayout(0, 1));
        
        // create the checkboxes
        if (options == null) {
            options = new String[]{"total conversion", "balance", "difficulty", "qol", "graphics", "fix", "cheat", "tweak", "ui", "megabeast", "entity", "creature", "material", "inorganic", "item", "descriptor", "building"};
        }
        for (String option : options) {
            JCheckBox checkbox = new JCheckBox(option);
            if (optionsString.contains(option)) {
                checkbox.setSelected(true);
            }
            checkboxes.add(checkbox);
            panel.add(checkbox);
        }
        
        // create the confirm and cancel buttons
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        // add the buttons to the panel
        panel.add(confirmButton);
        panel.add(cancelButton);
        
        // add the panel to a scroll pane and then to the dialog
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
        
        // set dialog properties
        pack();
        this.setMaximumSize(new Dimension(250, 550));
        this.setSize(new Dimension(250, 550));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    result += checkbox.getText() + ", ";
                }
            }
            result = result.trim();
            if (result.endsWith(",")) {
                result = result.substring(0, result.length() - 1);
            }
            dispose();
        } else {
            result = null;
            dispose();
        }
    }

    public String getResult() {
        return result;
    }
}