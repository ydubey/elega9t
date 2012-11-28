/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli;

import com.elega9t.commons.cp.ClassFilter;
import com.elega9t.commons.cp.ClassPathResource;
import com.elega9t.commons.cp.ClassPathUtilities;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Interpreter;
import com.elega9t.commons.shell.intrprtr.cmd.ExitCommand;
import com.elega9t.elixir.DatabaseDriver;
import com.elega9t.elixir.cli.cmd.ConnectCommand;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        Map<String, DatabaseDriver> drivers = new HashMap<String, DatabaseDriver>();
        List<ClassPathResource> classPathResources = ClassPathUtilities.getClassPathResources();
        for (ClassPathResource classPathResource : classPathResources) {
            List<Class> classes = classPathResource.listClasses(new FilenameFilter() {
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
            for (Class clazz : classes) {
                Object o = null;
                try {
                    o = clazz.newInstance();
                    if(o instanceof DatabaseDriver) {
                        DatabaseDriver databaseDriver = (DatabaseDriver) o;
                        databaseDriver.loadDrivers();
                        drivers.put(databaseDriver.databaseName().toLowerCase(), databaseDriver);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        final Interpreter elx = new Interpreter("elx", ExitCommand.class.getPackage(), ConnectCommand.class.getPackage());
        Shell shell = new Shell(elx);
        shell.setContextElement("elixir-drivers", drivers);
        shell.execute();
    }

}
