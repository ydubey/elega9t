/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.renderer.Border;
import com.elega9t.commons.renderer.ColumnDataProvider;
import com.elega9t.commons.renderer.ConsoleTableDataRenderer;
import com.elega9t.commons.renderer.ObjectCollectionDataProvider;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.util.StringUtilities;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;

import java.util.List;

public class AppsCommand extends Command {

    public AppsCommand() {
        super("apps");
    }

    @Override
    public int execute(Shell shell) {
        CloudFoundryClient client = (CloudFoundryClient) shell.getContextElement("cloudfoundry-client");
        List<CloudApplication> applications = client.getApplications();
        ConsoleTableDataRenderer consoleTableDataRenderer = new ConsoleTableDataRenderer(Border.PLAIN);
        shell.outln("");
        shell.outln(consoleTableDataRenderer.render(new ObjectCollectionDataProvider(applications,
                new ColumnDataProvider<CloudApplication>("Application") {
                    @Override
                    public String value(CloudApplication application) {
                        return application.getName();
                    }
                },
                new ColumnDataProvider<CloudApplication>("#") {
                    @Override
                    public String value(CloudApplication application) {
                        return String.valueOf(application.getInstances());
                    }
                },
                new ColumnDataProvider<CloudApplication>("Health") {
                    @Override
                    public String value(CloudApplication application) {
                        return application.getState().name();
                    }
                },
                new ColumnDataProvider<CloudApplication>("URLS") {
                    @Override
                    public String value(CloudApplication application) {
                        return StringUtilities.join(application.getUris(), ", ");
                    }
                },
                new ColumnDataProvider<CloudApplication>("Services") {
                    @Override
                    public String value(CloudApplication application) {
                        return StringUtilities.join(application.getServices(), ", ");
                    }
                }
        )));
        return 0;
    }

}
