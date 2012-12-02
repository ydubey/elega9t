/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.DefaultLoadableEntityNode;
import com.elega9t.commons.entity.EntityLoadException;

public class Table extends DefaultLoadableEntityNode {

    private final Tables tables;

    public Table(Tables tables, String name) {
        super(name);
        this.tables = tables;
    }

    @Override
    public void load() throws EntityLoadException {
        clear();
        //addChild(new Tables(this));
    }

    public Connection getConnection() {
        return tables.getConnection();
    }

}
