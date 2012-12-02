/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import java.util.ArrayList;
import java.util.List;

public class DefaultEntityNode<T extends EntityNode> extends DefaultEntity implements EntityNode<T> {

    private List<T> children;

    public DefaultEntityNode(String name) {
        super(name);
        children = new ArrayList<T>();
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public T getChild(int index) {
        return children.get(index);
    }

    @Override
    public void addChild(T node) {
        children.add(node);
    }

    @Override
    public boolean removeChild(T node) {
        return children.remove(node);
    }

    @Override
    public void clear() {
        children.clear();
    }

}
