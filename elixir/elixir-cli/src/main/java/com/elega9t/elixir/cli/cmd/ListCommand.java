/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.renderer.table.ColumnDataModel;
import com.elega9t.commons.renderer.table.ObjectCollectionDataModel;
import com.elega9t.commons.renderer.table.TableToStringRenderer;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.Driver;
import com.elega9t.elixir.binding.plugin.DriverDefinition;
import com.elega9t.elixir.mgr.DriverManager;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ListCommand extends DefaultEntity implements Command {

    private static final Map<String, Integer> operations = new HashMap<String, Integer>();
    static {
        operations.put("drivers", 1);
    }

    @Parameter(index=0)
    private String what;

    public ListCommand() {
        super("list");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        Integer whatToDo = operations.get(what.toLowerCase());
        switch (whatToDo) {
            case 1:
                TableToStringRenderer renderer = new TableToStringRenderer(shell.getBorder());
                out.println(renderer.render(new ObjectCollectionDataModel(DriverManager.getInstance().drivers(),
                        new ColumnDataModel<DriverDefinition>("Database Name") {
                            @Override
                            public String value(DriverDefinition driver) {
                                return driver.getDatabase();
                            }
                        },
                        new ColumnDataModel<DriverDefinition>("Vendors") {
                            @Override
                            public String value(DriverDefinition driver) {
                                return "blah";
                            }
                        },
                        new ColumnDataModel<DriverDefinition>("Website") {
                            @Override
                            public String value(DriverDefinition driver) {
                                return driver.getWebsite();
                            }
                        }
                )));
                break;
        }
        return 0;
    }

}
