/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.DatabaseDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCommand extends Command {

    private static final Map<String, Integer> operations = new HashMap<String, Integer>();
    static {
        operations.put("db", 1);
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
                List<DatabaseDriver> drivers = (List<DatabaseDriver>) shell.getContextElement("elixir-drivers");
                for (DatabaseDriver driver : drivers) {
                    shell.outln(driver.databaseName());
                }
                break;
        }
        return 0;
    }

}
