/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.DefaultLoadableEntityNode;
import com.elega9t.commons.entity.EntityLoadException;

public class Schema extends DefaultLoadableEntityNode<Tables> {

    private final Schemas schemas;
    private Tables tables;

    public Schema(Schemas schemas, String name) {
        super(name);
        this.schemas = schemas;
    }

    @Override
    public void load() throws EntityLoadException {
        clear();
        tables = new Tables(this);
        addChild(tables);
    }

    public Connection getConnection() {
        return schemas.getConnection();
    }

    public Tables getTables() {
        return tables;
    }

}
