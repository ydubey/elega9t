/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.TableType;
import com.elega9t.elixir.gui.mgr.IconsManager;

public class TableTypeGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity, TableType> {

    public TableTypeGuiEntity(TableType tableType) {
        super(tableType.getName(), IconsManager.getInstance().database().getDatabaseTablesIcon());
        this.entity = tableType;
    }

}
