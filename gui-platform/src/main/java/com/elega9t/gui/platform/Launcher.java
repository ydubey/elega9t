/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.gui.platform.mgr.PluginManager;

import javax.swing.*;

public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        final Context context = Context.getInstance();
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        //System.setProperty("com.apple.mrj.application.apple.menu.about.name", context.getApplicationName());
        final PluginManager pluginManager = PluginManager.getInstance();
        pluginManager.addPluginProcessor(context.getMain());
        try {
            pluginManager.load();
        } catch (EntityLoadException e) {
            e.printStackTrace();
            //EventManager.getInstance().error(e);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = context.getMain();
                main.setVisible(true);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    SwingUtilities.updateComponentTreeUI(main);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
