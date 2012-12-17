/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.elixir.Table;

public class TableGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity> {

    private final Table table;

    public TableGuiEntity(Table table) {
        super(table.getName(), new javax.swing.ImageIcon(ConnectionGuiEntity.class.getResource("/com/elega9t/elixir/gui/icons/table.png")));
        this.table = table;
    }

    @Override
    public void load() throws EntityLoadException {
        load(table);
    }

}
