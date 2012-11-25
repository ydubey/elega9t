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

public class TargetCommand extends Command {

    private String url = "http://api.nimbus-03.cg.bskyb.com";

    public TargetCommand() {
        super("target");
    }

    @Override
    public int execute(Shell shell) {
        try {
            shell.setContextElement("cloudfoundry-target", new URL(url));
            shell.outln("Successfully targeted to [" + url + "]");
        } catch (MalformedURLException e) {
            shell.error(e);
            return 1;
        }
        return 0;
    }

}
