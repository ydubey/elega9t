/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mgr;

import com.elega9t.commons.cp.ClassFilter;
import com.elega9t.commons.cp.ClassPathResource;
import com.elega9t.commons.cp.ClassPathUtilities;
import com.elega9t.commons.entity.DefaultLoadableEntity;
import com.elega9t.elixir.Driver;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverManager extends DefaultLoadableEntity {

    private static DriverManager mgr = new DriverManager();

    private Map<String, Driver> drivers = new HashMap<String, Driver>();

    public DriverManager() {
        super("DriverManager");
    }

    @Override
    public void load() {
        List<ClassPathResource> classPathResources = ClassPathUtilities.getClassPathResources();
        for (ClassPathResource classPathResource : classPathResources) {
            List<Class> classes = null;
            try {
                classes = classPathResource.listClasses(new FilenameFilter() {
                                                                        @Override
                                                                        public boolean accept(File dir, String name) {
                                                                            return name.endsWith("DatabaseDriver.class");
                                                                        }
                                                                    }, new ClassFilter() {
                                                                        @Override
                                                                        public boolean accept(Class aClass) {
                                                                            return !aClass.isInterface();
                                                                        }
                                                                    }
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Class clazz : classes) {
                Object o = null;
                try {
                    o = clazz.newInstance();
                    if(o instanceof Driver) {
                        Driver driver = (Driver) o;
                        driver.load();
                        drivers.put(driver.getName().toLowerCase(), driver);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Driver getDriver(String name) {
        return drivers.get(name);
    }

    public Collection<Driver> drivers() {
        return drivers.values();
    }

    public static DriverManager getInstance() {
        return mgr;
    }

}
