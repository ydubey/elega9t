/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import com.elega9t.commons.renderer.Renderer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TableToStringRenderer implements Renderer<DataModel> {

    private final Border border;

    public TableToStringRenderer() {
        this(Border.SINGLE);
    }

    public TableToStringRenderer(Border border) {
        this.border = border;
    }

    @Override
    public String render(DataModel data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);
        render(data, out);
        return new String(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void render(DataModel data, PrintStream out) {
        // compute max column sizes
        int[] maxSize = new int[data.columnCount()];
        for (int index=0; index<maxSize.length; index++) {
            maxSize[index] = data.columnName(index).length();
        }
        for (int row=0; row< data.rowCount(); row++) {
            for(int column=0; column<maxSize.length; column++) {
                if(maxSize[column] < data.value(row, column).length()) {
                    maxSize[column] = data.value(row, column).length();
                }
            }
        }

        // render header
        renderHeader(data, out, maxSize, border);
        renderData(data, out, maxSize, border);
    }

    private void repeat(PrintStream out, char character, int count) {
        for(int i=0; i<count; i++) {
            out.print(character);
        }
    }

    private void renderHeader(DataModel data, PrintStream out, int[] maxSize, Border border) {
        out.print(border.getTopLeft());
        out.print(border.getHorizontal());
        for (int column=0; column<maxSize.length; column++) {
            repeat(out, border.getHorizontal(), maxSize[column] + 1);
            if(column < maxSize.length -1) {
                out.print(border.getColumnSeparatorStart());
                out.print(border.getHorizontal());
            } else {
                out.print(border.getTopRight());
            }
        }
        out.println();
        out.print(border.getVertical());
        out.print(border.getSpace());
        for (int column=0; column<maxSize.length; column++) {
            final String columnName = data.columnName(column);
            out.print(columnName);
            out.print(border.getSpace());
            repeat(out, border.getSpace(), maxSize[column] - columnName.length());
            if(column < maxSize.length -1) {
                out.print(border.getVertical());
                out.print(border.getSpace());
            } else {
                out.print(border.getVertical());
            }
        }
        out.print("\n");
        if(data.rowCount() > 0) {
            out.print(border.getRowSeparatorStart());
        } else {
            out.print(border.getBottomLeft());
        }
        out.print(border.getHorizontal());
        for (int column=0; column<maxSize.length; column++) {
            repeat(out, border.getHorizontal(), maxSize[column] + 1);
            if(column < maxSize.length -1) {
                if(data.rowCount() > 0) {
                    out.print(border.getRowColumnJunction());
                } else {
                    out.print(border.getColumnSeparatorEnd());
                }
                out.print(border.getHorizontal());
            } else {
                if(data.rowCount() > 0) {
                    out.print(border.getRowSeparatorEnd());
                } else {
                    out.print(border.getBottomRight());
                }
            }
        }
        out.print("\n");
    }

    private void renderData(DataModel data, PrintStream out, int[] maxSize, Border border) {
        if(data.rowCount() > 0) {
            for(int row = 0; row < data.rowCount(); row++) {
                for(int column=0; column < maxSize.length; column++) {
                    out.print(border.getVertical());
                    out.print(border.getSpace());
                    final String value = data.value(row, column);
                    out.print(value);
                    out.print(border.getSpace());
                    repeat(out, border.getSpace(), maxSize[column] - value.length());
                }
                out.print(border.getVertical());
                out.print("\n");
            }
            out.print(border.getBottomLeft());
            out.print(border.getHorizontal());
            for (int column=0; column<maxSize.length; column++) {
                repeat(out, border.getHorizontal(), maxSize[column] + 1);
                if(column < maxSize.length -1) {
                    out.print(border.getColumnSeparatorEnd());
                    out.print(border.getHorizontal());
                } else {
                    out.print(border.getBottomRight());
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new TableToStringRenderer(Border.PLAIN).render(new DataModel() {
            @Override
            public int rowCount() {
                return 5;
            }

            @Override
            public int columnCount() {
                return 5;
            }

            @Override
            public String value(int row, int column) {
                return row + "-" + column;
            }

            @Override
            public String columnName(int column) {
                return column + "";
            }
        }));
    }

}
