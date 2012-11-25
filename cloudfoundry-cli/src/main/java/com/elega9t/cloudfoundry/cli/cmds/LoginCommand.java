/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginCommand extends Command {

    private String name = "mcs@bskyb.com";
    private String password = "teamboundy";

    public LoginCommand() {
        super("login");
    }

    @Override
    public int execute(Shell shell) {
        URL target = (URL) shell.getContextElement("cloudfoundry-target");
        CloudFoundryClient client = new CloudFoundryClient(new CloudCredentials(name, password), target);
        client.login();
        shell.outln("Successfully logged into [" + target + "]");
        shell.setContextElement("cloudfoundry-client", client);
        return 0;
    }

}
