/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform;

import com.elega9t.gui.platform.splsh.SplashScreen;

public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        final Context context = Context.getInstance();
        SplashScreen splashScreen = new SplashScreen(new javax.swing.ImageIcon(Launcher.class.getResource("/com/elega9t/platform/images/splashscr.png")).getImage(), 230);
        splashScreen.setVisible(true);
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", context.getApplicationName());
    }

}
