/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.tree;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.commons.entity.tree.impl.DefaultLazyLoadEntityTreeNode;

import java.io.File;
import java.io.IOException;

public class FolderEntityNode extends DefaultLazyLoadEntityTreeNode<FolderEntityNode> {

    private File file;

    public FolderEntityNode() throws IOException, EntityLoadException {
        this(new File("."));
    }

    public FolderEntityNode(String file) throws IOException, EntityLoadException {
        this(new File(file));
    }

    public FolderEntityNode(File file) throws IOException, EntityLoadException {
        this(file, null);
    }

    public FolderEntityNode(File file, FolderEntityNode parent) throws IOException, EntityLoadException {
        super(file.getCanonicalFile().getName(), parent);
        this.file = file;
    }

    @Override
    public void load() throws EntityLoadException {
        if(file.isDirectory()) {
            final File[] files = file.listFiles();
            if(files != null) {
                for (File child : files) {
                    try {
                        addChild(new FolderEntityNode(child, this));
                    } catch (IOException e) {
                        throw new EntityLoadException(e);
                    }
                }
            }
        }
        loaded = true;
    }

}
