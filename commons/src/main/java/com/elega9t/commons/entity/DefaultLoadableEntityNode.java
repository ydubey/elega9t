/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import java.util.ArrayList;
import java.util.List;

public class DefaultLoadableEntityNode extends DefaultLoadableEntity implements LoadableEntityNode {

    private List<LoadableEntityNode> children;

    public DefaultLoadableEntityNode(String name) {
        super(name);
        children = new ArrayList<LoadableEntityNode>();
    }

    @Override
    public final void loadAll() throws EntityLoadException {
        load();
        loadChildren();
    }

    protected void loadChildren() {
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public void addChild(LoadableEntityNode node) {
        children.add(node);
    }

    @Override
    public boolean removeChild(LoadableEntityNode node) {
        return children.remove(node);
    }

    @Override
    public void clear() {
        children.clear();
    }

}
