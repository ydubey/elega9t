/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli;

import com.elega9t.cloudfoundry.cli.cmds.*;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Interpreter;
import com.elega9t.commons.shell.intrprtr.cmd.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        Shell shell = new Shell(new Interpreter("yok", ExitCommand.class.getPackage(), TargetCommand.class.getPackage()));
        shell.execute();
    }

}
