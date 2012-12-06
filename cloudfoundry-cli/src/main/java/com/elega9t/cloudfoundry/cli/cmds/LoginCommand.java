/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.commons.shell.intrprtr.RequiredContextElement;
import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.URL;

public class LoginCommand extends DefaultEntity implements Command {

    @RequiredContextElement(name="cloudfoundry-target", notSetMessage = "cloudfoundry target is not set. Please use the target command to set the target url.")
    private URL target;

    @Parameter(index=0)
    private String name;
    @Parameter(index=1)
    private String password;

    public LoginCommand() {
        super("login");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        CloudFoundryClient client = new CloudFoundryClient(new CloudCredentials(name, password), target);
        client.login();
        out.println("Successfully logged into [" + target + "]");
        shell.setContextElement("cloudfoundry-client", client);
        return 0;
    }

}
