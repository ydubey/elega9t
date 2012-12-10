/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

public class BackgroundText {

    private final String text;
    private final int y;
    private final int fontSize;
    private boolean underline;
    private boolean bold;
    private boolean alighWithPrevious;

    public BackgroundText(String text) {
        this(text, -1);
    }

    public BackgroundText(String text, int y) {
        this(text, y, 30);
    }

    public BackgroundText(String text, int y, int fontSize) {
        this.text = text;
        this.y = y;
        this.fontSize = fontSize;
    }

    public BackgroundText underlined() {
        this.underline = true;
        return this;
    }

    public BackgroundText bold() {
        this.bold = true;
        return this;
    }

    public BackgroundText alighWithPrevious() {
        this.alighWithPrevious = true;
        return this;
    }

    public String getText() {
        return text;
    }

    public int getY() {
        return y;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean isUnderline() {
        return underline;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isAlighWithPrevious() {
        return alighWithPrevious;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setAlighWithPrevious(boolean alighWithPrevious) {
        this.alighWithPrevious = alighWithPrevious;
    }
    
}
