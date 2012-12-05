/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;

public class MoreCommand extends DefaultEntity implements Command {

    public MoreCommand() {
        super("more");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) throws Exception {
        while(in.ready()) {
            for(int count = 0; count < 10 && in.ready(); count++) {
                String line = in.readLine();
                out.println(line);
            }
            if(in.ready()) {
                out.println("\t\t\t\t---- MORE ----");
                new Scanner(System.in).nextLine();
            }
        }
        return 0;
    }

}
