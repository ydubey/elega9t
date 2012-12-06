/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.RequiredContextElement;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.URL;

public class LogoutCommand extends DefaultEntity implements Command {

    @RequiredContextElement(name="cloudfoundry-client", notSetMessage = "You haven't logged in to cloudfoundry yet.")
    private CloudFoundryClient client;

    @RequiredContextElement(name="cloudfoundry-target", notSetMessage = "cloudfoundry target is not set. Please use the target command to set the target url.")
    private URL target;

    public LogoutCommand() {
        super("logout");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        client.logout();
        out.println("Successfully logged out from [" + target + "]");
        return 0;
    }

}
