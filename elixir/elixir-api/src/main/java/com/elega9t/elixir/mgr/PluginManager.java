/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mgr;

import com.elega9t.commons.entity.impl.DefaultLoadableEntity;
import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.elixir.binding.plugin.Plugin;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

public class PluginManager extends DefaultLoadableEntity {

    private static PluginManager INSTANCE = new PluginManager();

    private List<PluginProcessor> pluginProcessors = new ArrayList<PluginProcessor>();

    protected PluginManager() {
        super("Plugin Manager");
    }

    public static PluginManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void load() throws EntityLoadException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class.getPackage().getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final Plugin plugin = (Plugin) unmarshaller.unmarshal(getClass().getResourceAsStream("/plugin.xml"));
            processPlugin(plugin);
        } catch (JAXBException e) {
            throw new EntityLoadException(e);
        }
    }

    protected void processPlugin(Plugin plugin) throws EntityLoadException {
        for (PluginProcessor pluginProcessor : pluginProcessors) {
            pluginProcessor.process(plugin);
        }
    }

    public void addPluginProcessor(PluginProcessor pluginProcessor) {
        pluginProcessors.add(pluginProcessor);
    }

}
