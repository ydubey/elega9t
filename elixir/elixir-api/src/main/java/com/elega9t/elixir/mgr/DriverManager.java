/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mgr;

import com.elega9t.commons.entity.impl.DefaultLoadableEntity;
import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.elixir.Driver;
import com.elega9t.elixir.binding.driverdef.DriverDefinition;
import com.elega9t.elixir.binding.driverdef.DriverDefinitions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DriverManager extends DefaultLoadableEntity {

    private static DriverManager mgr = new DriverManager();

    private Map<String, DriverDefinition> drivers = new HashMap<String, DriverDefinition>();

    public DriverManager() {
        super("DriverManager");
    }

    @Override
    public void load() throws EntityLoadException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DriverDefinitions.class.getPackage().getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final DriverDefinitions driverDefinitions = (DriverDefinitions) unmarshaller.unmarshal(getClass().getResourceAsStream("/driver-definitions.xml"));
            for (DriverDefinition driverDefinition : driverDefinitions.getDriverDefinition()) {
                drivers.put(driverDefinition.getDatabase().toLowerCase(), driverDefinition);
            }
        } catch (JAXBException e) {
            throw new EntityLoadException(e);
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
