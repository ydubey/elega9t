/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import java.util.ArrayList;
import java.util.List;

public class DefaultEntityNode extends DefaultEntity implements EntityNode {

    private List<LoadableEntityNode> children;

    public DefaultEntityNode(String name) {
        super(name);
        children = new ArrayList<LoadableEntityNode>();
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
