/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.cloudfoundry.cli.cmds;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.util.StringUtilities;
import com.elega9t.commons.util.TextTable;
import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;

import java.net.URL;
import java.util.List;

import static com.elega9t.commons.util.StringUtilities.join;

public class AppsCommand extends Command {

    public AppsCommand() {
        super("apps");
    }

    @Override
    public int execute(Shell shell) {
        CloudFoundryClient client = (CloudFoundryClient) shell.getContextElement("cloudfoundry-client");
        List<CloudApplication> applications = client.getApplications();
        TextTable<CloudApplication> textTable = new TextTable<CloudApplication>(applications,
                new TextTable.ColumnValueProvider<CloudApplication>("Application") {
                    @Override
                    public String valueOf(CloudApplication application) {
                        return application.getName();
                    }
                },
                new TextTable.ColumnValueProvider<CloudApplication>("#") {
                    @Override
                    public String valueOf(CloudApplication application) {
                        return String.valueOf(application.getInstances());
                    }
                },
                new TextTable.ColumnValueProvider<CloudApplication>("Health") {
                    @Override
                    public String valueOf(CloudApplication application) {
                        return application.getState().name();
                    }
                },
                new TextTable.ColumnValueProvider<CloudApplication>("URLS") {
                    @Override
                    public String valueOf(CloudApplication application) {
                        return StringUtilities.join(application.getUris(), ", ");
                    }
                },
                new TextTable.ColumnValueProvider<CloudApplication>("Services") {
                    @Override
                    public String valueOf(CloudApplication application) {
                        return StringUtilities.join(application.getServices(), ", ");
                    }
                }
        );
        shell.outln("");
        shell.outln(textTable.toString());
        return 0;
    }

}
