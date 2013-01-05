/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.plugin;

import com.elega9t.commons.cp.ClassPathResource;
import com.elega9t.commons.cp.ClassPathUtilities;
import com.elega9t.commons.entity.impl.DefaultLoadableEntity;
import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.gui.platform.mgr.event.Event;
import com.elega9t.gui.platform.mgr.event.EventManager;
import com.elega9t.platform.binding.plugin.Plugin;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class PluginManager extends DefaultLoadableEntity {

    public static final String PLUGIN_LOAD_EVENT_TYPE = "PLUGIN";

    private static PluginManager INSTANCE = new PluginManager();

    protected PluginManager() {
        super("Plugin Manager");
    }

    public static PluginManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void load() {
        final List<ClassPathResource> classPathResources = ClassPathUtilities.getClassPathResources();
        for (int index = 0; index < classPathResources.size(); index++) {
            ClassPathResource classPathResource = classPathResources.get(index);
            try {
                final List<InputStream> inputStreams = classPathResource.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return dir.getName().equals("META-INF") && name.equals("plugin.xml");
                    }
                });
                long time = System.currentTimeMillis();
                for (InputStream inputStream : inputStreams) {
                    Plugin plugin = load(inputStream);
                    if(inputStreams.size() > 0) {
                        EventManager.getInstance().fireLogEvent(new Event(plugin, PLUGIN_LOAD_EVENT_TYPE, new Date(), plugin.getInfo().getName() + " plugin loaded from '" + classPathResource.toString() + "' in " + (System.currentTimeMillis() - time) + "ms."));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Plugin load(InputStream inputStream) throws JAXBException, EntityLoadException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class.getPackage().getName());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final Plugin plugin = (Plugin) unmarshaller.unmarshal(inputStream);
        return plugin;
    }

}
