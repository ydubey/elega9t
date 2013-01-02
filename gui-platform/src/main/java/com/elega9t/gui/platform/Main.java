/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform;

import com.elega9t.gui.platform.actions.menu.file.ExitAction;
import com.elega9t.gui.platform.dock.DockLocation;
import com.elega9t.gui.platform.dock.DockPanel;
import com.elega9t.gui.platform.mgr.PluginProcessor;
import com.elega9t.platform.binding.plugin.Action;
import com.elega9t.platform.binding.plugin.ActionGroup;
import com.elega9t.platform.binding.plugin.Plugin;

import javax.swing.*;

public class Main extends javax.swing.JFrame implements PluginProcessor {

    private javax.swing.Action exitAction = new ExitAction();

    private final Context CONTEXT = Context.getInstance();
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        ((DockPanel)dockPanel).addDock(DockLocation.LEFT, "Connections", null, new JPanel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarBasePanel = new javax.swing.JPanel();
        dockPanel = new DockPanel();
        mainMenu = new javax.swing.JMenuBar();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle(CONTEXT.getApplicationName());
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(CONTEXT.getApplicationIcon().getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        toolBarBasePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        getContentPane().add(toolBarBasePanel, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(dockPanel, java.awt.BorderLayout.CENTER);
        setJMenuBar(mainMenu);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exitAction.actionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dockPanel;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JPanel toolBarBasePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void process(Plugin plugin) {
        if(plugin.getActions() != null) {
            for (ActionGroup actionGroup : plugin.getActions().getGroups()) {
                javax.swing.JMenu menuGroup = new javax.swing.JMenu(actionGroup.getName());
                menuGroup.setToolTipText(actionGroup.getDescription());
                if(actionGroup.getAddToGroup().getGroupId().equals("MainMenu")) {
                    mainMenu.add(menuGroup);
                }
                for (Action action : actionGroup.getAction()) {
                    try {
                        javax.swing.Action actionInstance = (javax.swing.Action) Class.forName(action.getClazz()).newInstance();
                        javax.swing.JMenuItem actionItem = new javax.swing.JMenuItem();
                        actionItem.setAction(actionInstance);
                        actionItem.setText(action.getName());
                        actionItem.setToolTipText(action.getDescription());
                        menuGroup.add(actionItem);
                        if("ExitApplication".equals(action.getId())) {
                            exitAction = actionInstance;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
}
