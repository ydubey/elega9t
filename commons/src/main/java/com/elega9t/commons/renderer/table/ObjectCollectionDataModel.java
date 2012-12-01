/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

public class ObjectCollectionDataModel<T> implements DataModel<T> {

    private List<T> data;
    private ColumnDataModel<T>[] columnDataModels;

    public ObjectCollectionDataModel(Collection<T> data, ColumnDataModel<T>... columnDataModels) {
        this.data = new ArrayList<T>(data);
        this.columnDataModels = columnDataModels;
    }

    public ObjectCollectionDataModel(T[] data, ColumnDataModel<T>... columnDataModels) {
        this(asList(data), columnDataModels);
    }

    @Override
    public int rowCount() {
        return data.size();
    }

    @Override
    public int columnCount() {
        return columnDataModels.length;
    }

    @Override
    public String value(int row, int column) {
        return columnDataModels[column].value(data.get(row));
    }

    @Override
    public String columnName(int column) {
        return columnDataModels[column].name();
    }


}
