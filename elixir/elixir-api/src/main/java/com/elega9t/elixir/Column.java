/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.EntityLoadException;

public class Column extends DatabaseEntity<Columns> {

    private final String catalogueName;
    private final String schemaName;
    private final String tableName;
    private final int dataType;
    private final String typeName;
    private final int size;
    private final int decimalDigits;
    private final int radix;
    private final int nullable;
    private final String defaultValue;
    private final String autoIncrement;

    public Column(String catalogueName, String schemaName, String tableName, String name, int dataType, String typeName, int size, int decimalDigits, int radix, int nullable, String defaultValue, String isAutoIncrement, Connection connection) throws EntityLoadException {
        super(name, connection);
        this.catalogueName = catalogueName;
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.dataType = dataType;
        this.typeName = typeName;
        this.size = size;
        this.decimalDigits = decimalDigits;
        this.radix = radix;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
        autoIncrement = isAutoIncrement;
    }

    @Override
    public void load() throws EntityLoadException {
        clear();
    }

    @Override
    public String toString() {
        return super.toString() + " ["+typeName+"]";
    }
}
