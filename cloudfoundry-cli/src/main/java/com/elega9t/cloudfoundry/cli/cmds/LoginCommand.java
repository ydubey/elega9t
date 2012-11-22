/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginCommand extends Command {

    private String url;

    public LoginCommand() {
        super("login");
    }

    @Override
    public int execute(Shell shell) {
        try {
            shell.setContextElement("cloudfoundry-client", new CloudFoundryClient(new URL(url)));
        } catch (MalformedURLException e) {
            shell.outln(e.getMessage());
            return 1;
        }
        return 0;
    }

}
