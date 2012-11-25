package com.elega9t.commons.renderer;

public interface DataProvider<T> {

    int rowCount();

    int columnCount();

    String value(int row, int column);

    String columnName(int column);

}
