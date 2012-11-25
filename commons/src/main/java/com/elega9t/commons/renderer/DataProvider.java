package com.elega9t.commons.renderer;

/**
 * Created with IntelliJ IDEA.
 * User: yogesh
 * Date: 25/11/2012
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
public interface DataProvider<T> {
    int rowCount();

    int columnCount();

    String value(int row, int column);

    String columnName(int column);
}
