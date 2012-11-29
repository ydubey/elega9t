/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import org.cloudfoundry.client.lib.CloudFoundryClient;

public class StartCommand extends Command {

    @Parameter(index=0)
    private String appName;

    public StartCommand() {
        super("start");
    }

    @Override
    public int execute(Shell shell) {
        CloudFoundryClient client = (CloudFoundryClient) shell.getContextElement("cloudfoundry-client");
        if(client == null) {
            throw new IllegalStateException("You haven't logged in to cloudfoundry yet.");
        }
        client.startApplication(appName);
        return 0;
    }

}
