/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.UnnamedParameter;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import java.net.MalformedURLException;
import java.net.URL;

public class TargetCommand extends Command {

    // http://api.nimbus-03.cg.bskyb.com

    @UnnamedParameter(0)
    private String url;

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
