/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.EntityLoadException;

public class Table extends DatabaseEntity<Columns> {

    private final String catalogueName;
    private final String schemaName;

    public Table(String catalogueName, String schemaName, String name, Connection connection) {
        super(name, connection);
        this.catalogueName = catalogueName;
        this.schemaName = schemaName;
    }

    @Override
    public void load() throws EntityLoadException {
        clear();
        addChild(new Columns(catalogueName, schemaName, getName(), getConnection()));
    }

}
