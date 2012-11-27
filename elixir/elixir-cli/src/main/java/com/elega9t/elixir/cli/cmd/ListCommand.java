/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.renderer.Border;
import com.elega9t.commons.renderer.ColumnDataProvider;
import com.elega9t.commons.renderer.ConsoleTableDataRenderer;
import com.elega9t.commons.renderer.ObjectCollectionDataProvider;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.DatabaseDriver;

import java.util.HashMap;
import java.util.Map;

import static com.elega9t.commons.util.StringUtilities.join;

public class ListCommand extends Command {

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
    public int execute(Shell shell) {
        Integer whatToDo = operations.get(what.toLowerCase());
        switch (whatToDo) {
            case 1:
                Map<String, DatabaseDriver> drivers = (Map<String, DatabaseDriver>) shell.getContextElement("elixir-drivers");
                ConsoleTableDataRenderer renderer = new ConsoleTableDataRenderer(Border.SINGLE);
                shell.outln(renderer.render(new ObjectCollectionDataProvider(drivers.values(),
                        new ColumnDataProvider<DatabaseDriver>("Database Name") {
                            @Override
                            public String value(DatabaseDriver driver) {
                                return driver.databaseName();
                            }
                        },
                        new ColumnDataProvider<DatabaseDriver>("Driver Available") {
                            @Override
                            public String value(DatabaseDriver driver) {
                                return driver.isAvailable() ? "YES" : "NO";
                            }
                        },
                        new ColumnDataProvider<DatabaseDriver>("Supported Versions") {
                            @Override
                            public String value(DatabaseDriver driver) {
                                return join(driver.supportedVersions());
                            }
                        }
                )));
                break;
        }
        return 0;
    }

}
