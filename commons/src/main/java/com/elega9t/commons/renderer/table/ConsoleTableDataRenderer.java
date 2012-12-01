/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import com.elega9t.commons.renderer.Renderer;

public class ConsoleTableDataRenderer implements Renderer<DataModel> {

    private final Border border;

    public ConsoleTableDataRenderer() {
        this(Border.SINGLE);
    }

    public ConsoleTableDataRenderer(Border border) {
        this.border = border;
    }

    @Override
    public String render(DataModel dataModel) {
        StringBuilder rendered = new StringBuilder();
        // compute max column sizes
        int[] maxSize = new int[dataModel.columnCount()];
        for (int index=0; index<maxSize.length; index++) {
            maxSize[index] = dataModel.columnName(index).length();
        }
        for (int row=0; row< dataModel.rowCount(); row++) {
            for(int column=0; column<maxSize.length; column++) {
                if(maxSize[column] < dataModel.value(row, column).length()) {
                    maxSize[column] = dataModel.value(row, column).length();
                }
            }
        }

        // render header
        renderHeader(rendered, dataModel, maxSize, border);
        renderData(rendered, dataModel, maxSize, border);

        return rendered.toString();
    }

    private void repeat(StringBuilder rendered, char character, int count) {
        for(int i=0; i<count; i++) {
            rendered.append(character);
        }
    }

    private void renderHeader(StringBuilder rendered, DataModel dataModel, int[] maxSize, Border border) {
        rendered.append(border.getTopLeft()).append(border.getHorizontal());
        for (int column=0; column<maxSize.length; column++) {
            repeat(rendered, border.getHorizontal(), maxSize[column] + 1);
            if(column < maxSize.length -1) {
                rendered.append(border.getColumnSeparatorStart()).append(border.getHorizontal());
            } else {
                rendered.append(border.getTopRight());
            }
        }
        rendered.append("\n");
        rendered.append(border.getVertical()).append(border.getSpace());
        for (int column=0; column<maxSize.length; column++) {
            final String columnName = dataModel.columnName(column);
            rendered.append(columnName).append(border.getSpace());
            repeat(rendered, border.getSpace(), maxSize[column] - columnName.length());
            if(column < maxSize.length -1) {
                rendered.append(border.getVertical()).append(border.getSpace());
            } else {
                rendered.append(border.getVertical());
            }
        }
        rendered.append("\n");
        if(dataModel.rowCount() > 0) {
            rendered.append(border.getRowSeparatorStart());
        } else {
            rendered.append(border.getBottomLeft());
        }
        rendered.append(border.getHorizontal());
        for (int column=0; column<maxSize.length; column++) {
            repeat(rendered, border.getHorizontal(), maxSize[column] + 1);
            if(column < maxSize.length -1) {
                if(dataModel.rowCount() > 0) {
                    rendered.append(border.getRowColumnJunction());
                } else {
                    rendered.append(border.getColumnSeparatorEnd());
                }
                rendered.append(border.getHorizontal());
            } else {
                if(dataModel.rowCount() > 0) {
                    rendered.append(border.getRowSeparatorEnd());
                } else {
                    rendered.append(border.getBottomRight());
                }
            }
        }
        rendered.append("\n");
    }

    private void renderData(StringBuilder rendered, DataModel dataModel, int[] maxSize, Border border) {
        if(dataModel.rowCount() > 0) {
            for(int row = 0; row < dataModel.rowCount(); row++) {
                for(int column=0; column < maxSize.length; column++) {
                    rendered.append(border.getVertical()).append(border.getSpace());
                    final String value = dataModel.value(row, column);
                    rendered.append(value).append(border.getSpace());
                    repeat(rendered, border.getSpace(), maxSize[column] - value.length());
                }
                rendered.append(border.getVertical());
                rendered.append("\n");
            }
            rendered.append(border.getBottomLeft()).append(border.getHorizontal());
            for (int column=0; column<maxSize.length; column++) {
                repeat(rendered, border.getHorizontal(), maxSize[column] + 1);
                if(column < maxSize.length -1) {
                    rendered.append(border.getColumnSeparatorEnd()).append(border.getHorizontal());
                } else {
                    rendered.append(border.getBottomRight());
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ConsoleTableDataRenderer(Border.PLAIN).render(new DataModel() {
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
