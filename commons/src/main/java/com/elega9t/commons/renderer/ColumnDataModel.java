/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer;

public abstract class ColumnDataModel<T> {

    private final String name;

    public ColumnDataModel(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public abstract String value(T obj);

}
