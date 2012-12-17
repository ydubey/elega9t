/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.commons.shell.intrprtr.RequiredContextElement;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.io.BufferedReader;
import java.io.PrintStream;

public class StopCommand extends DefaultEntity implements Command {

    @RequiredContextElement(name="cloudfoundry-client", notSetMessage = "You haven't logged in to cloudfoundry yet.")
    private CloudFoundryClient client;

    @Parameter(index=0)
    private String appName;

    public StopCommand() {
        super("stop");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        client.stopApplication(appName);
        return 0;
    }

}
