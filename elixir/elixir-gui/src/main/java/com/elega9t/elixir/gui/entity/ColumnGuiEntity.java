/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.elixir.Column;

public class ColumnGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity> {

    private final Column column;

    public ColumnGuiEntity(Column column) {
        super(column.getName(), new javax.swing.ImageIcon(ConnectionGuiEntity.class.getResource("/com/elega9t/elixir/gui/icons/column.png")));
        this.column = column;
    }

    @Override
    protected void loadChildren() throws EntityLoadException {
        super.loadChildren();
        loadChildren(column);
    }

}
