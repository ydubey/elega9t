/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.*;

public class DatabaseGuiEntityFactory implements DatabaseEntityVisitor<DatabaseGuiEntity> {

    @Override
    public DatabaseGuiEntity visit(Columns columns) {
        return null;
    }

    @Override
    public DatabaseGuiEntity visit(Column column) {
        return null;
    }

    @Override
    public DatabaseGuiEntity visit(Schema schema) {
        return null;
    }

    @Override
    public DatabaseGuiEntity visit(Schemas schemas) {
        return null;
    }

    @Override
    public DatabaseGuiEntity visit(Table table) {
        return null;
    }

    @Override
    public DatabaseGuiEntity visit(TableType tableType) {
        return new TableTypeGuiEntity(tableType.getName());
    }

    @Override
    public DatabaseGuiEntity visit(TableTypes tableTypes) {
        return null;
    }

}
