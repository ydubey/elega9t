/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.plugin;

import com.elega9t.commons.cp.ClassPathResource;
import com.elega9t.commons.cp.ClassPathUtilities;
import com.elega9t.commons.entity.impl.DefaultLoadableEntity;
import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.gui.platform.mgr.log.LogEvent;
import com.elega9t.gui.platform.mgr.log.LogManager;
import com.elega9t.platform.binding.plugin.Plugin;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PluginManager extends DefaultLoadableEntity {

    private static PluginManager INSTANCE = new PluginManager();

    private List<PluginProcessor> pluginProcessors = new ArrayList<PluginProcessor>();
    private List<PluginLoadEventListener> pluginLoadListeners = new ArrayList<PluginLoadEventListener>();

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
                for (InputStream inputStream : inputStreams) {
                    load(inputStream);
                }
                firePluginLoadEvent(classPathResources.size(), index + 1);
                LogManager.getInstance().fireLogEvent(new LogEvent("PLUGIN", classPathResource.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void firePluginLoadEvent(int size, int index) {
        for (PluginLoadEventListener pluginLoadListener : pluginLoadListeners) {
            pluginLoadListener.pluginLoading(size, index);
        }
    }

    private void load(InputStream inputStream) throws JAXBException, EntityLoadException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class.getPackage().getName());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final Plugin plugin = (Plugin) unmarshaller.unmarshal(inputStream);
        processPlugin(plugin);
    }

    protected void processPlugin(Plugin plugin) throws EntityLoadException {
        for (PluginProcessor pluginProcessor : pluginProcessors) {
            pluginProcessor.process(plugin);
        }
    }

    public void addPluginProcessor(PluginProcessor pluginProcessor) {
        pluginProcessors.add(pluginProcessor);
    }

    public void addPluginLoadEventListener(PluginLoadEventListener pluginLoadEventListener) {
        pluginLoadListeners.add(pluginLoadEventListener);
    }

}
