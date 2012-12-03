/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.EntityLoadException;

public class Table extends DatabaseEntity {

    public Table(String name, Connection connection) {
        super(name, connection);
    }

    @Override
    public void load() throws EntityLoadException {
        clear();
        //addChild(new Tables(this));
    }

}
