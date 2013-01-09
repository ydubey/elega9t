/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform;

import com.elega9t.gui.platform.mgr.event.Event;
import com.elega9t.gui.platform.mgr.event.EventListener;
import com.elega9t.gui.platform.mgr.event.EventManager;
import com.elega9t.gui.platform.mgr.plugin.PluginManager;
import com.elega9t.gui.platform.splsh.SplashScreen;
import com.elega9t.platform.binding.plugin.Plugin;

public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        EventManager.getInstance().addListener(PluginManager.PLUGIN_LOAD_EVENT_TYPE, new EventListener() {
            @Override
            public void pastEvent(Event event) {
            }

            @Override
            public void eventOccurred(Event event) {
                Plugin plugin = (Plugin) event.getSource();
                if("Application".equalsIgnoreCase(plugin.getInfo().getCategory())) {
                    System.setProperty("com.apple.mrj.application.apple.menu.about.name", plugin.getInfo().getName() + " v" + plugin.getInfo().getVersion());
                }
            }
        });
        SplashScreen splashScreen = new SplashScreen(new javax.swing.ImageIcon(Launcher.class.getResource("/com/elega9t/platform/images/splashscr.png")).getImage(), 230);
        splashScreen.setVisible(true);
    }

}
