/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.tree;

import com.elega9t.commons.entity.DefaultLazyLoadEntityNode;
import com.elega9t.commons.entity.EntityLoadException;

import java.io.File;
import java.io.IOException;

public class FolderEntityNode extends DefaultLazyLoadEntityNode<FolderEntityNode> {

    private File file;

    public FolderEntityNode() throws IOException, EntityLoadException {
        this(new File("."));
    }

    public FolderEntityNode(String file) throws IOException, EntityLoadException {
        this(new File(file));
    }

    public FolderEntityNode(File file) throws IOException, EntityLoadException {
        super(file.getCanonicalFile().getName());
        this.file = file;
    }

    @Override
    protected void loadChildren() throws EntityLoadException {
        super.loadChildren();
        if(file.isDirectory()) {
            for (File child : file.listFiles()) {
                try {
                    addChild(new FolderEntityNode(child));
                } catch (IOException e) {
                    throw new EntityLoadException(e);
                }
            }
        }
    }

}
