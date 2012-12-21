/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.Table;
import com.elega9t.elixir.gui.mgr.IconsManager;

public class TableGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity, Table> {

    public TableGuiEntity(Table table) {
        super(table.getName(), IconsManager.getInstance().database.getTableIcon());
        this.entity = table;
    }

}
