/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.tree.impl;

import com.elega9t.commons.entity.LoadableEntity;
import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.commons.entity.tree.EntityTreeNode;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class DefaultLazyLoadEntityTreeNode<T extends LoadableEntity & EntityTreeNode> extends DefaultLoadableEntityTreeNode<T> {

    public DefaultLazyLoadEntityTreeNode(String name) {
        super(name);
    }

    @Override
    public int getChildCount() {
        if(!loaded) {
            try {
                load();
            } catch (EntityLoadException e) {
                e.printStackTrace();
            } finally {
                loaded = true;
            }
        }
        return super.getChildCount();
    }

    @Override
    public int getIndex(TreeNode node) {
        if(!loaded) {
            try {
                load();
            } catch (EntityLoadException e) {
                e.printStackTrace();
            } finally {
                loaded = true;
            }
        }
        return super.getIndex(node);
    }

    @Override
    public Enumeration children() {
        if(!loaded) {
            try {
                load();
            } catch (EntityLoadException e) {
                e.printStackTrace();
            } finally {
                loaded = true;
            }
        }
        return super.children();
    }

    @Override
    public T getChildAt(int index) {
        if(!loaded) {
            try {
                load();
            } catch (EntityLoadException e) {
                e.printStackTrace();
            } finally {
                loaded = true;
            }
        }
        return super.getChildAt(index);
    }

}
