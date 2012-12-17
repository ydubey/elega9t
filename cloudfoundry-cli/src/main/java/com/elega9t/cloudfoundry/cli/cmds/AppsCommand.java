/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.renderer.table.ColumnDataModel;
import com.elega9t.commons.renderer.table.TableToStringRenderer;
import com.elega9t.commons.renderer.table.ObjectCollectionDataModel;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.RequiredContextElement;
import com.elega9t.commons.util.StringUtilities;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

public class AppsCommand extends DefaultEntity implements Command {

    @RequiredContextElement(name="cloudfoundry-client", notSetMessage = "You haven't logged in to cloudfoundry yet.")
    private CloudFoundryClient client;

    public AppsCommand() {
        super("apps");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        List<CloudApplication> applications = client.getApplications();
        TableToStringRenderer tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
        out.println("");
        out.println(tableToStringRenderer.render(new ObjectCollectionDataModel(applications,
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
