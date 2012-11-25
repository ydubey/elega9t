/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

public class ObjectCollectionDataProvider<T> implements DataProvider<T> {

    private List<T> data;
    private ColumnDataProvider<T>[] columnDataProviders;

    public ObjectCollectionDataProvider(Collection<T> data, ColumnDataProvider<T>... columnDataProviders) {
        this.data = new ArrayList<T>(data);
        this.columnDataProviders = columnDataProviders;
    }

    public ObjectCollectionDataProvider(T[] data, ColumnDataProvider<T>... columnDataProviders) {
        this(asList(data), columnDataProviders);
    }

    @Override
    public int rowCount() {
        return data.size();
    }

    @Override
    public int columnCount() {
        return columnDataProviders.length;
    }

    @Override
    public String value(int row, int column) {
        return columnDataProviders[column].value(data.get(row));
    }

    @Override
    public String columnName(int column) {
        return columnDataProviders[column].name();
    }


}
