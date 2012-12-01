package com.elega9t.commons.renderer.table;

public interface DataModel<T> {

    int rowCount();

    int columnCount();

    String value(int row, int column);

    String columnName(int column);

}
