/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.tree;

import com.elega9t.commons.entity.DefaultEntityNode;

import java.io.File;
import java.io.IOException;

public class FolderEntityNode extends DefaultEntityNode<FolderEntityNode> {

    public FolderEntityNode() throws IOException {
        this(new File("."));
    }

    public FolderEntityNode(String file) throws IOException {
        this(new File(file));
    }

    public FolderEntityNode(File file) throws IOException {
        super(file.getCanonicalFile().getName());
        if(file.isDirectory()) {
            for (File child : file.listFiles()) {
                addChild(new FolderEntityNode(child));
            }
        }
    }

}
