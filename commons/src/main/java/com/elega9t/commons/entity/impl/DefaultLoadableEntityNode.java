/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.impl;

import com.elega9t.commons.entity.EntityNode;
import com.elega9t.commons.entity.LoadableEntity;

public class DefaultLoadableEntityNode<T extends EntityNode & LoadableEntity> extends DefaultEntityNode<T> implements EntityNode<T>, LoadableEntity {

    private boolean loaded;

    public DefaultLoadableEntityNode(String name) {
        super(name);
    }

    public void load() throws EntityLoadException {
        final int childCount = getChildCount();
        for(int index=0; index < childCount; index++) {
            final T child = getChildAt(index);
            child.load();
        }
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void reset() {
        super.clear();
        loaded = false;
    }

}
