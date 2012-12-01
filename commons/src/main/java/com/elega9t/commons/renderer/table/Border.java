/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

public enum Border {

    PLAIN(' ', '+', '-', '+', '+', '+', '+', '+', '|', '+', '+', '+'),
    SINGLE(' ', '┌', '─', '┬', '┐', '├', '┼', '┤', '│', '└', '┴', '┘'),
    DOUBLE(' ', '╔', '═', '╦', '╗', '╠', '╬', '╣', '║', '╚', '╩', '╝'),
    THICK(' ', '┏', '━', '┳', '┓', '┣', '╋', '┫', '┃', '┗', '┻', '┛'),
    HORIZONTAL_SINGLE(' ', '╓', '─', '╥', '╖', '╟', '╫', '╢', '║', '╙', '╨', '╜'),
    VERTICAL_SINGLE(' ', '╒', '═', '╤', '╕', '╞', '╪', '╡', '│', '╘', '╧', '╛');

    private final char space;
    private final char topLeft;
    private final char horizontal;
    private final char columnSeparatorStart;
    private final char topRight;
    private final char rowSeparatorStart;
    private final char rowColumnJunction;
    private final char rowSeparatorEnd;
    private final char vertical;
    private final char bottomLeft;
    private final char columnSeparatorEnd;
    private final char bottomRight;

    Border(char space, char topLeft, char horizontal, char columnSeparatorStart, char topRight, char rowSeparatorStart, char rowColumnJunction, char rowSeparatorEnd, char vertical, char bottomLeft, char columnSeparatorEnd, char bottomRight) {
        this.space = space;
        this.topLeft = topLeft;
        this.horizontal = horizontal;
        this.columnSeparatorStart = columnSeparatorStart;
        this.topRight = topRight;
        this.rowSeparatorStart = rowSeparatorStart;
        this.rowColumnJunction = rowColumnJunction;
        this.rowSeparatorEnd = rowSeparatorEnd;
        this.vertical = vertical;
        this.bottomLeft = bottomLeft;
        this.columnSeparatorEnd = columnSeparatorEnd;
        this.bottomRight = bottomRight;
    }

    public char getSpace() {
        return space;
    }

    public char getTopLeft() {
        return topLeft;
    }

    public char getHorizontal() {
        return horizontal;
    }

    public char getColumnSeparatorStart() {
        return columnSeparatorStart;
    }

    public char getTopRight() {
        return topRight;
    }

    public char getRowSeparatorStart() {
        return rowSeparatorStart;
    }

    public char getRowColumnJunction() {
        return rowColumnJunction;
    }

    public char getRowSeparatorEnd() {
        return rowSeparatorEnd;
    }

    public char getVertical() {
        return vertical;
    }

    public char getBottomLeft() {
        return bottomLeft;
    }

    public char getColumnSeparatorEnd() {
        return columnSeparatorEnd;
    }

    public char getBottomRight() {
        return bottomRight;
    }

}
