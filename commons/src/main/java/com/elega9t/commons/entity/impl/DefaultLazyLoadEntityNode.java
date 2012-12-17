/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.impl;

import com.elega9t.commons.entity.EntityNode;
import com.elega9t.commons.entity.LoadableEntity;

public class DefaultLazyLoadEntityNode<T extends EntityNode & LoadableEntity> extends DefaultLoadableEntityNode<T> {

    private boolean childrenLoaded = false;

    public DefaultLazyLoadEntityNode(String name) {
        super(name);
        load();
    }

    public void load() {
    }

    protected void loadChildren() throws EntityLoadException {
        clear();
        childrenLoaded = true;
    }

    @Override
    public int getChildCount() {
        if(!childrenLoaded) {
            try {
                loadChildren();
            } catch (EntityLoadException e) {
                throw new IllegalStateException(e);
            }
        }
        return super.getChildCount();
    }

    @Override
    public T getChildAt(int index) {
        if(!childrenLoaded) {
            try {
                loadChildren();
            } catch (EntityLoadException e) {
                throw new IllegalStateException(e);
            }
        }
        return super.getChildAt(index);
    }

    @Override
    public void addChild(T node) {
        if(!childrenLoaded) {
            try {
                loadChildren();
            } catch (EntityLoadException e) {
                throw new IllegalStateException(e);
            }
        }
        super.addChild(node);
    }

    @Override
    public boolean removeChild(T node) {
        if(!childrenLoaded) {
            try {
                loadChildren();
            } catch (EntityLoadException e) {
                throw new IllegalStateException(e);
            }
        }
        return super.removeChild(node);
    }

    @Override
    public void clear() {
        super.clear();
        childrenLoaded = false;
    }

    public boolean isChildrenLoaded() {
        return childrenLoaded;
    }

}
