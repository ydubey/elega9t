/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mgr;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.elixir.Driver;
import com.elega9t.elixir.binding.plugin.DriverDefinition;
import com.elega9t.elixir.binding.plugin.Plugin;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class DriverManager extends DefaultEntity implements PluginProcessor {

    private static DriverManager mgr = new DriverManager();

    private Map<String, DriverDefinition> drivers = new TreeMap<String, DriverDefinition>();

    public DriverManager() {
        super("DriverManager");
    }

    public void process(Plugin plugin) {
        if(plugin.getDriverDefinitions() != null) {
            for (DriverDefinition driverDefinition : plugin.getDriverDefinitions().getDefinition()) {
                drivers.put(driverDefinition.getDatabase().toLowerCase(), driverDefinition);
            }
        }
    }

    public Driver getDriver(String name) {
        final DriverDefinition driverDefinition = drivers.get(name.toLowerCase());
        return new Driver(driverDefinition);
    }

    public Collection<DriverDefinition> drivers() {
        return drivers.values();
    }

    public static DriverManager getInstance() {
        return mgr;
    }

}
