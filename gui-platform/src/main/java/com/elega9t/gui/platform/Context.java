/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform;

import com.elega9t.commons.swing.NodeIcon;
import com.elega9t.gui.platform.event.exit.ApplicationClosingEventListener;
import com.elega9t.gui.platform.event.exit.DefaultApplicationClosingEventListener;
import com.elega9t.gui.platform.mgr.PluginProcessor;
import com.elega9t.platform.binding.plugin.Plugin;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;

public final class Context implements PluginProcessor {

    private static Context INSTANCE;

    private Main main;

    private String applicationName;
    private ImageIcon applicationIcon;
    private ApplicationClosingEventListener applicationClosingEventListener;

    private Context() {
        applicationName = "Elega9t GUI Platform v1.0-SNAPSHOT";
        applicationIcon = new javax.swing.ImageIcon(getClass().getResource("/com/elega9t/platform/icons/icon.png"));
        applicationClosingEventListener = new DefaultApplicationClosingEventListener();
        UIManager.put("Tree.collapsedIcon", new IconUIResource(new NodeIcon(NodeIcon.TYPE.COLLAPSED)));
        UIManager.put("Tree.expandedIcon",  new IconUIResource(new NodeIcon(NodeIcon.TYPE.EXPANDED)));
    }

    public static Context getInstance() {
        if(INSTANCE == null) {
            synchronized (Context.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Context();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized Main getMain() {
        if(main == null) {
            main = new Main();
        }
        return main;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public ImageIcon getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(ImageIcon applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public ApplicationClosingEventListener getApplicationClosingEventListener() {
        return applicationClosingEventListener;
    }

    public void setApplicationClosingEventListeners(ApplicationClosingEventListener applicationClosingEventListener) {
        this.applicationClosingEventListener = applicationClosingEventListener;
    }

    @Override
    public void process(Plugin plugin) {
        if("Application".equalsIgnoreCase(plugin.getInfo().getCategory())) {
            applicationName = plugin.getInfo().getName() + " v" + plugin.getInfo().getVersion();
            String iconPath = plugin.getInfo().getIcon();
            if(iconPath != null) {
                applicationIcon = new ImageIcon(iconPath);
            }
        }
    }

}
