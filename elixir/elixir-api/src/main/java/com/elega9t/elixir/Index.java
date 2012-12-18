/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.impl.EntityLoadException;

public class Index extends DatabaseEntity<Columns> {

    private final String catalogueName;
    private final String schemaName;
    private final String tableName;

    public Index(String catalogueName, String schemaName, String tableName, Indexes indexes, String name, Connection connection) throws EntityLoadException {
        super(name, indexes, connection);
        this.catalogueName = catalogueName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    @Override
    public void load() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public <R> R visit(DatabaseEntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
