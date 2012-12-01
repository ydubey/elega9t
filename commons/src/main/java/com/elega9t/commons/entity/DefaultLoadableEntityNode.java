/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public class DefaultLoadableEntityNode<T extends LoadableEntityNode> extends DefaultEntityNode<T> implements LoadableEntityNode<T> {

    public DefaultLoadableEntityNode(String name) {
        super(name);
    }

    @Override
    public final void loadAll() throws EntityLoadException {
        load();
        loadChildren();
    }

    public void load() {
    }

}
