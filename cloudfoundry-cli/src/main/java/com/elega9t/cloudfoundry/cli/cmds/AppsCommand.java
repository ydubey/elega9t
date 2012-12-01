/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.renderer.ColumnDataModel;
import com.elega9t.commons.renderer.ConsoleTableDataRenderer;
import com.elega9t.commons.renderer.ObjectCollectionDataModel;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.util.StringUtilities;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;

import java.util.List;

public class AppsCommand extends DefaultEntity implements Command {

    public AppsCommand() {
        super("apps");
    }

    @Override
    public int execute(Shell shell) {
        CloudFoundryClient client = (CloudFoundryClient) shell.getContextElement("cloudfoundry-client");
        List<CloudApplication> applications = client.getApplications();
        ConsoleTableDataRenderer consoleTableDataRenderer = new ConsoleTableDataRenderer(shell.getBorder());
        shell.outln("");
        shell.outln(consoleTableDataRenderer.render(new ObjectCollectionDataModel(applications,
                new ColumnDataModel<CloudApplication>("Application") {
                    @Override
                    public String value(CloudApplication application) {
                        return application.getName();
                    }
                },
                new ColumnDataModel<CloudApplication>("#") {
                    @Override
                    public String value(CloudApplication application) {
                        return String.valueOf(application.getInstances());
                    }
                },
                new ColumnDataModel<CloudApplication>("Health") {
                    @Override
                    public String value(CloudApplication application) {
                        return application.getState().name();
                    }
                },
                new ColumnDataModel<CloudApplication>("URLS") {
                    @Override
                    public String value(CloudApplication application) {
                        return StringUtilities.join(application.getUris(), ", ");
                    }
                },
                new ColumnDataModel<CloudApplication>("Services") {
                    @Override
                    public String value(CloudApplication application) {
                        return StringUtilities.join(application.getServices(), ", ");
                    }
                }
        )));
        return 0;
    }

}
