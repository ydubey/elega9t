/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.*;

public class DatabaseGuiEntityFactory implements DatabaseEntityVisitor<DatabaseGuiEntity> {

    @Override
    public DatabaseGuiEntity visit(Columns columns) {
        return new ColumnsGuiEntity(columns);
    }

    @Override
    public DatabaseGuiEntity visit(Column column) {
        return new ColumnGuiEntity(column);
    }

    @Override
    public DatabaseGuiEntity visit(Schema schema) {
        return new DatabaseGuiEntity(schema.getName(), null);
    }

    @Override
    public DatabaseGuiEntity visit(Schemas schemas) {
        return new DatabaseGuiEntity(schemas.getName(), null);
    }

    @Override
    public DatabaseGuiEntity visit(Table table) {
        return new TableGuiEntity(table);
    }

    @Override
    public DatabaseGuiEntity visit(TableType tableType) {
        return new TableTypeGuiEntity(tableType);
    }

    @Override
    public DatabaseGuiEntity visit(TableTypes tableTypes) {
        return new DatabaseGuiEntity(tableTypes.getName(), null);
    }

    @Override
    public DatabaseGuiEntity visit(Indexes indexes) {
        return new IndexesGuiEntity(indexes);
    }

    @Override
    public DatabaseGuiEntity visit(Index index) {
        return new IndexGuiEntity(index);
    }

}
