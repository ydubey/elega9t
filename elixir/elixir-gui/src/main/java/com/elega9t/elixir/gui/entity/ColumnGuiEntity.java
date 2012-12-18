/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.Column;
import com.elega9t.elixir.gui.mgr.IconsManager;

public class ColumnGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity, Column> {

    public ColumnGuiEntity(Column column) {
        super(column.getName(), IconsManager.getInstance().database().getColumnIcon());
        this.entity = column;
        loaded = true;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

}
