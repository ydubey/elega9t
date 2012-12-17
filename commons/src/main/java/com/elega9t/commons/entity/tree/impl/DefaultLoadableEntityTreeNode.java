/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.tree.impl;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.commons.entity.tree.EntityTreeNode;
import com.elega9t.commons.entity.LoadableEntity;

public class DefaultLoadableEntityTreeNode<T extends EntityTreeNode & LoadableEntity> extends DefaultEntityTreeNode<T> implements EntityTreeNode<T>, LoadableEntity {

    protected boolean loaded;

    public DefaultLoadableEntityTreeNode(String name) {
        super(name);
    }

    public void load() throws EntityLoadException {
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void clear() {
        super.clear();
        loaded = false;
    }

}
