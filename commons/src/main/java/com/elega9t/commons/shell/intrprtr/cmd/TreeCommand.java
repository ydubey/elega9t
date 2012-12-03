/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.renderer.tree.TreeToStringRenderer;
import com.elega9t.commons.renderer.tree.FolderEntityNode;
import com.elega9t.commons.shell.EnvironmentProperty;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.IOException;

public class TreeCommand extends DefaultEntity implements Command {

    public TreeCommand() {
        super("tree");
    }

    @Override
    public int execute(Shell shell) throws IOException {
        TreeToStringRenderer renderer = new TreeToStringRenderer(shell.getBorder());
        shell.outln(renderer.render(new FolderEntityNode(shell.getEnvironmentProperty(EnvironmentProperty.PWD))));
        return 0;
    }

}
