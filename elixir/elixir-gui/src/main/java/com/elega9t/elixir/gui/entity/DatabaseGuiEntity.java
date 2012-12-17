/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.commons.entity.tree.impl.DefaultLoadableGuiEntityTreeNode;
import com.elega9t.elixir.DatabaseEntity;

import javax.swing.*;

public class DatabaseGuiEntity<T extends DatabaseGuiEntity> extends DefaultLoadableGuiEntityTreeNode<T> {

    public DatabaseGuiEntity(String name, Icon icon) {
        super(name, icon);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    protected <R extends DatabaseEntity> void load(R entity) {
        int childCount = entity.getChildCount();
        DatabaseGuiEntityFactory factory = new DatabaseGuiEntityFactory();
        for(int index=0; index<childCount; index++) {
            DatabaseEntity en = (DatabaseEntity) entity.getChildAt(index);
            addChild((T) en.visit(factory));
        }
        loaded = true;
    }

}
