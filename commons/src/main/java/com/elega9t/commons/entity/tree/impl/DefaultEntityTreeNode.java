/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.tree.impl;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.entity.tree.EntityTreeNode;
import com.elega9t.commons.util.SortedList;

import javax.swing.tree.TreeNode;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class DefaultEntityTreeNode<T extends EntityTreeNode> extends DefaultEntity implements EntityTreeNode<T> {

    protected List<T> children;
    private EntityTreeNode parent;
    private boolean allowsChildren;

    public DefaultEntityTreeNode(String name) {
        this(name, null);
    }

    public DefaultEntityTreeNode(String name, EntityTreeNode parent) {
        super(name);
        this.parent = parent;
        this.children = new SortedList<T>();
        this.allowsChildren = true;
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(EntityTreeNode parent) {
        this.parent = parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return allowsChildren;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(children);
    }

    @Override
    public T getChildAt(int index) {
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
