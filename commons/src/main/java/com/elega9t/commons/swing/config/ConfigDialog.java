/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.config;

import com.elega9t.commons.entity.impl.GuiEntityTreeNode;
import com.elega9t.commons.swing.SwingUtilities;
import java.awt.CardLayout;

public class ConfigDialog extends javax.swing.JDialog {

    private final String title;
    private final String applyButtonLabel;
    private final String cancelButtonLabel;
    private final String helpButtonLabel;
    private final ConfigPanel[] configPanels;
    
    private GuiEntityTreeNode<GuiEntityTreeNode> configTreeRootNode;

    /**
     * Creates new form ConfigDialog
     */
    public ConfigDialog(java.awt.Frame parent, boolean modal, String title, String applyButtonLabel, String cancelButtonLabel, String helpButtonLabel, ConfigPanel... configPanels) {
        super(parent, modal);
        configTreeRootNode = new GuiEntityTreeNode("Config");
        this.title = title;
        this.applyButtonLabel = applyButtonLabel;
        this.cancelButtonLabel = cancelButtonLabel;
        this.helpButtonLabel = helpButtonLabel;
        this.configPanels = configPanels;
        initComponents();
        initConfigDialog();
        setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new javax.swing.JPanel();
        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        bodyPanel = new javax.swing.JPanel();
        bodySplitPane = new javax.swing.JSplitPane();
        configElementsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        configTree = new javax.swing.JTree(configTreeRootNode);
        configBasePanel = new javax.swing.JPanel();
        settingBreadcrumbPanel = new SettingsBreadcrumbPanel();
        configPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);
        setSize(new java.awt.Dimension(800, 500));

        buttonsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        applyButton.setText(applyButtonLabel);
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(applyButton);

        cancelButton.setText(cancelButtonLabel);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelButton);

        helpButton.setText(helpButtonLabel);
        buttonsPanel.add(helpButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.PAGE_END);

        bodyPanel.setLayout(new java.awt.BorderLayout());

        bodySplitPane.setDividerLocation(200);

        configElementsPanel.setLayout(new java.awt.BorderLayout());

        configTree.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        configTree.setRootVisible(false);
        configTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                configTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(configTree);

        configElementsPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bodySplitPane.setLeftComponent(configElementsPanel);

        configBasePanel.setLayout(new java.awt.BorderLayout());

        settingBreadcrumbPanel.setPreferredSize(new java.awt.Dimension(0, 40));
        settingBreadcrumbPanel.setSize(new java.awt.Dimension(0, 50));
        settingBreadcrumbPanel.setLayout(new javax.swing.BoxLayout(settingBreadcrumbPanel, javax.swing.BoxLayout.LINE_AXIS));
        configBasePanel.add(settingBreadcrumbPanel, java.awt.BorderLayout.PAGE_START);

        configPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 10, 10));
        configPanel.setLayout(new java.awt.CardLayout());
        configBasePanel.add(configPanel, java.awt.BorderLayout.CENTER);

        bodySplitPane.setRightComponent(configBasePanel);

        bodyPanel.add(bodySplitPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void configTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_configTreeValueChanged
        GuiEntityTreeNode selectedNode = (GuiEntityTreeNode) configTree.getLastSelectedPathComponent();
        CardLayout cardLayout = (CardLayout) configPanel.getLayout();
        cardLayout.show(configPanel, selectedNode.getName());
        updateBreadcrumb();
    }//GEN-LAST:event_configTreeValueChanged

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        for(java.awt.Component component: configPanel.getComponents()) {
            ((ConfigPanel) component).applyConfigChange(getParent(), new java.awt.event.ActionEvent(this, evt.getModifiers(), evt.getActionCommand()));
        }
    }//GEN-LAST:event_applyButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JSplitPane bodySplitPane;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel configBasePanel;
    private javax.swing.JPanel configElementsPanel;
    private javax.swing.JPanel configPanel;
    private javax.swing.JTree configTree;
    private javax.swing.JButton helpButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel settingBreadcrumbPanel;
    // End of variables declaration//GEN-END:variables

    private void initConfigDialog() {
        SwingUtilities.escapeToDispose(this);
        for(ConfigPanel cfgPanel: configPanels) {
            String[] parentCategories = cfgPanel.getParentCategory();
            GuiEntityTreeNode<GuiEntityTreeNode> currentNode = configTreeRootNode;
            for(String parentCategory: parentCategories) {
                for(int index=0; index<currentNode.getChildCount(); index++) {
                    GuiEntityTreeNode<GuiEntityTreeNode> node = currentNode.getChildAt(index);
                    if(node.getName().equals(parentCategory)) {
                        currentNode = node;
                        break;
                    }
                }
                if(!currentNode.getName().equals(parentCategory)) {
                    GuiEntityTreeNode<GuiEntityTreeNode> node = new GuiEntityTreeNode<GuiEntityTreeNode>(parentCategory);
                    currentNode.addChild(node);
                    currentNode = node;
                }
            }
            cfgPanel.initConfig();
            configPanel.add(cfgPanel, currentNode.getName());
        }
        SwingUtilities.expandAll(configTree, configTreeRootNode);
        updateBreadcrumb();
    }

    private void updateBreadcrumb() {
        for(java.awt.Component component: configPanel.getComponents()) {
            if(component.isVisible()) {
                ((SettingsBreadcrumbPanel)settingBreadcrumbPanel).setBreadcrumb(((ConfigPanel)component).getParentCategory());
            }
        }
    }
    
}