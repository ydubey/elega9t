/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.net.URL;

public class LoginCommand extends Command {

    @Parameter(index=0)
    private String name;
    @Parameter(index=1)
    private String password;

    public LoginCommand() {
        super("login");
    }

    @Override
    public int execute(Shell shell) {
        URL target = (URL) shell.getContextElement("cloudfoundry-target");
        if(target == null) {
            throw new IllegalStateException("cloudfoundry target is not set. Please use the target command to set the target url.");
        }
        CloudFoundryClient client = new CloudFoundryClient(new CloudCredentials(name, password), target);
        client.login();
        shell.outln("Successfully logged into [" + target + "]");
        shell.setContextElement("cloudfoundry-client", client);
        return 0;
    }

}
