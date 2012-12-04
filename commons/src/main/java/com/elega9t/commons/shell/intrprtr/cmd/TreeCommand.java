/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.entity.EntityLoadException;
import com.elega9t.commons.renderer.tree.TreeToStringRenderer;
import com.elega9t.commons.renderer.tree.FolderEntityNode;
import com.elega9t.commons.shell.EnvironmentProperty;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class TreeCommand extends DefaultEntity implements Command {

    public TreeCommand() {
        super("tree");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) throws IOException, EntityLoadException {
        TreeToStringRenderer renderer = new TreeToStringRenderer(shell.getBorder());
        renderer.render(new FolderEntityNode(shell.getEnvironmentProperty(EnvironmentProperty.PWD)), out);
        return 0;
    }

}
