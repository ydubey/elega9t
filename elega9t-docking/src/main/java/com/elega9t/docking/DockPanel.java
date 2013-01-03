/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.docking;

import javax.swing.*;
import java.awt.*;

public class DockPanel extends javax.swing.JPanel {

    private final ComponentResizer componentResizer = new ComponentResizer();

    /**
     * Creates new form DockPanel
     */
    public DockPanel() {
        initComponents();
        componentResizer.registerComponent(leftBodyPanel, rightBodyPanel, bottomBodyPanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftDockButtonHolderPanel = new DockButtonHolderPanel(DockLocation.LEFT_FIRST);
        rightDockButtonHolderPanel = new DockButtonHolderPanel(DockLocation.RIGHT_FIRST);
        bottomDockButtonHolderPanel = new DockButtonHolderPanel(DockLocation.BOTTOM_FIRST);
        bodyPanel = new javax.swing.JPanel();
        leftBodyPanel = new DockRegionPanel();
        rightBodyPanel = new DockRegionPanel();
        bottomBodyPanel = new DockRegionPanel();
        centerBodyPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        leftDockButtonHolderPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 1)));
        leftDockButtonHolderPanel.setLayout(new javax.swing.BoxLayout(leftDockButtonHolderPanel, javax.swing.BoxLayout.PAGE_AXIS));
        add(leftDockButtonHolderPanel, java.awt.BorderLayout.LINE_START);

        rightDockButtonHolderPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 1)));
        rightDockButtonHolderPanel.setLayout(new javax.swing.BoxLayout(rightDockButtonHolderPanel, javax.swing.BoxLayout.PAGE_AXIS));
        add(rightDockButtonHolderPanel, java.awt.BorderLayout.LINE_END);

        bottomDockButtonHolderPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 1)));
        bottomDockButtonHolderPanel.setLayout(new javax.swing.BoxLayout(bottomDockButtonHolderPanel, javax.swing.BoxLayout.LINE_AXIS));
        add(bottomDockButtonHolderPanel, java.awt.BorderLayout.PAGE_END);

        bodyPanel.setLayout(new java.awt.BorderLayout());

        leftBodyPanel.setLayout(new javax.swing.BoxLayout(leftBodyPanel, javax.swing.BoxLayout.PAGE_AXIS));
        bodyPanel.add(leftBodyPanel, java.awt.BorderLayout.LINE_START);

        rightBodyPanel.setLayout(new javax.swing.BoxLayout(rightBodyPanel, javax.swing.BoxLayout.PAGE_AXIS));
        bodyPanel.add(rightBodyPanel, java.awt.BorderLayout.LINE_END);

        bottomBodyPanel.setLayout(new javax.swing.BoxLayout(bottomBodyPanel, javax.swing.BoxLayout.LINE_AXIS));
        bodyPanel.add(bottomBodyPanel, java.awt.BorderLayout.PAGE_END);

        centerBodyPanel.setLayout(new java.awt.BorderLayout());
        bodyPanel.add(centerBodyPanel, java.awt.BorderLayout.CENTER);

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public void addDock(final DockLocation location, String name, javax.swing.Icon icon, java.awt.Component component) {
        final DockButton dockButton = new DockButton(location, name, icon);
        final DockablePanel dockablePanel = new DockablePanel(name, location);
        dockablePanel.setVisible(false);
        dockablePanel.setPreferredSize(new Dimension(300, 100));
        dockablePanel.add(component, java.awt.BorderLayout.CENTER);
        DockStateAction.install(dockButton, dockablePanel);
        switch (location) {
            case LEFT_FIRST:
            case LEFT_LAST:
                if(leftDockButtonHolderPanel.getComponentCount() > 0) {
                    leftDockButtonHolderPanel.add(Box.createVerticalStrut(10));
                }
                ((DockButtonHolderPanel)leftDockButtonHolderPanel).add(dockButton, location);
                leftBodyPanel.add(dockablePanel);
                break;
            case RIGHT_FIRST:
            case RIGHT_LAST:
                if(rightDockButtonHolderPanel.getComponentCount() > 0) {
                    rightDockButtonHolderPanel.add(Box.createVerticalStrut(10));
                }
                ((DockButtonHolderPanel)rightDockButtonHolderPanel).add(dockButton, location);
                rightBodyPanel.add(dockablePanel);
                break;
            case BOTTOM_FIRST:
            case BOTTOM_LAST:
                if(bottomDockButtonHolderPanel.getComponentCount() > 0) {
                    bottomDockButtonHolderPanel.add(Box.createHorizontalStrut(10));
                }
                ((DockButtonHolderPanel)bottomDockButtonHolderPanel).add(dockButton, location);
                bottomBodyPanel.add(dockablePanel);
                break;
            case CENTER:
                centerBodyPanel.add(component, java.awt.BorderLayout.CENTER);
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel bottomBodyPanel;
    private javax.swing.JPanel bottomDockButtonHolderPanel;
    private javax.swing.JPanel centerBodyPanel;
    private javax.swing.JPanel leftBodyPanel;
    private javax.swing.JPanel leftDockButtonHolderPanel;
    private javax.swing.JPanel rightBodyPanel;
    private javax.swing.JPanel rightDockButtonHolderPanel;
    // End of variables declaration//GEN-END:variables

}
