/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui;

import com.elega9t.elixir.gui.form.Main;

import javax.swing.*;

public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", ResourceStrings.main.getString("doc.name"));
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
                main.setVisible(true);
                main.setExtendedState(JFrame.MAXIMIZED_BOTH);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
