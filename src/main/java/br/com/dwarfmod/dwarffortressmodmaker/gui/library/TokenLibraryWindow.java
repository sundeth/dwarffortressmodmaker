/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.gui.library;

import br.com.dwarfmod.dwarffortressmodmaker.core.ModManager;
import br.com.dwarfmod.dwarffortressmodmaker.data.RawFileTypeEnum;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.Token;
import br.com.dwarfmod.dwarffortressmodmaker.gui.MainWindow;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ander
 */
public class TokenLibraryWindow extends javax.swing.JFrame {

    private final MainWindow mainWindow;
    private final ModManager manager;

    /**
     * Creates new form TokenLibraryWindow
     * @param mainWindow
     * @param manager
     */
    public TokenLibraryWindow(final MainWindow mainWindow, final ModManager manager) {
        this.mainWindow = mainWindow;
        this.manager = manager;
        this.initComponents();
        
        this.tokenTable.getColumnModel().getColumn(4).setMaxWidth(0);
        this.tokenTable.getColumnModel().getColumn(4).setMaxWidth(0);
        this.tokenTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        this.setRelatedRender();        
        
        this.manager.readTokenLibrary();
        this.fillClassCombo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        classCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tokenTable = new javax.swing.JTable();
        relatedBox = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        addTokenButton = new javax.swing.JButton();

        setTitle("Token Library");

        jLabel1.setText("Object Class");

        classCombo.setModel(new DefaultComboBoxModel<RawFileTypeEnum>());
        classCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classComboActionPerformed(evt);
            }
        });

        tokenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Arguments", "Usage", "Description", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tokenTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(tokenTable);

        relatedBox.setSelected(true);
        relatedBox.setText("Related");
        relatedBox.setToolTipText("Sets if the token table will be filled with token related to the selected class. Ex ITEM tokens on ITEM_ARMOR");
        relatedBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatedBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Open Wiki");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addTokenButton.setText("Add");
        addTokenButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(classCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(relatedBox))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addTokenButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(classCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relatedBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTokenButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void classComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classComboActionPerformed
        this.updateTokenTable();
    }//GEN-LAST:event_classComboActionPerformed

    private void relatedBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatedBoxActionPerformed
        this.updateTokenTable();
    }//GEN-LAST:event_relatedBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final RawFileTypeEnum selected = (RawFileTypeEnum) this.classCombo.getSelectedItem();
        if (selected != null) {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(selected.getUrl()));
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTokenButton;
    private javax.swing.JComboBox<RawFileTypeEnum> classCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox relatedBox;
    private javax.swing.JTable tokenTable;
    // End of variables declaration//GEN-END:variables

    public void fillClassCombo() {
        final DefaultComboBoxModel<RawFileTypeEnum> model = (DefaultComboBoxModel<RawFileTypeEnum>) this.classCombo.getModel();
        model.removeAllElements();
        
        if (this.manager.getTokenLibrary() != null) {
            for (RawFileTypeEnum value : RawFileTypeEnum.values()) {
                if (!value.isSubType()) {
                    model.addElement(value);
                }
            }
        }
    }

    private void updateTokenTable() {
        final DefaultTableModel model = (DefaultTableModel) this.tokenTable.getModel();
        model.setRowCount(0);
        
        final RawFileTypeEnum selectedType = (RawFileTypeEnum) this.classCombo.getSelectedItem();
        
        if (selectedType != null && this.manager.getTokenLibrary() != null && this.manager.getTokenLibrary().getTokenMap() != null && this.manager.getTokenLibrary().getTokenMap().get(selectedType) != null) {
            for (Token token : this.manager.getTokenLibrary().getTokenMap().get(selectedType)) {
                model.addRow(new String[] { token.getToken(), token.getArguments(), token.getContext(), token.getDescription(), "" });
            }
            
            if (selectedType.getRelated() != null && relatedBox.isSelected()) {
                for (RawFileTypeEnum related : selectedType.getRelated()) {
                    if (this.manager.getTokenLibrary().getTokenMap().get(related) != null) {
                        model.addRow(new String[] { related.name() + " Tokens", "", "", "", related.name() });
                        for (Token token : this.manager.getTokenLibrary().getTokenMap().get(related)) {
                            model.addRow(new String[] { token.getToken(), token.getArguments(), token.getContext(), token.getDescription(), "" });
                        }
                    }
                }
            }
        }
    }

    private void setRelatedRender() {
        this.tokenTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!table.getValueAt(row, 4).toString().isEmpty() && !isSelected) {
                    int hash = table.getValueAt(row, 4).toString().hashCode();
                    
                    int red = (hash & 0xFF0000) >> 16;
                    int green = (hash & 0xFF00) >> 8;
                    int blue = (hash & 0xFF);
                    
                    red += red < 100 ? 100 : 0;
                    green += green < 100 ? 100 : 0;
                    blue += blue < 100 ? 100 : 0;
                        
                    Color color = new Color(red, green, blue);
                            
                    c.setBackground(color);
                } else if (!isSelected) {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

    }
}