/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.commons.entity.tree.EntityTreeNode;
import com.elega9t.commons.entity.tree.impl.DefaultLazyLoadGuiEntityTreeNode;
import com.elega9t.elixir.DatabaseEntity;

import javax.swing.*;

public class DatabaseGuiEntity<T extends DatabaseGuiEntity, R extends EntityTreeNode> extends DefaultLazyLoadGuiEntityTreeNode<T> {

    protected R databaseEntity;

    public DatabaseGuiEntity(String name, Icon icon) {
        super(name, icon);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    public void load() throws EntityLoadException{
        int childCount = databaseEntity.getChildCount();
        DatabaseGuiEntityFactory factory = new DatabaseGuiEntityFactory();
        for(int index=0; index<childCount; index++) {
            DatabaseEntity child = (DatabaseEntity) databaseEntity.getChildAt(index);
            T node = (T) child.visit(factory);
            node.setParent(this);
            addChild(node);
        }
        loaded = true;
    }

}
