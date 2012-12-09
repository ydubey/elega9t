/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */
package com.elega9t.elixir.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTabbedPane;

public class TextBackgroundSplitPane extends JTabbedPane {
    
    private final BackgroundText[] backgroundText;

    public TextBackgroundSplitPane(BackgroundText... backgroundText) {
        this.backgroundText = backgroundText;
    }

    public TextBackgroundSplitPane(int i, BackgroundText... backgroundText) {
        super(i);
        this.backgroundText = backgroundText;
    }

    public TextBackgroundSplitPane(int i, int i1, BackgroundText... backgroundText) {
        super(i, i1);
        this.backgroundText = backgroundText;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int lastXPos = -1;
        for(int index=0; index < backgroundText.length; index++) {
            final BackgroundText bgText = backgroundText[index];
            int yPos = bgText.getY();
            int stringHeight = (int) graphics.getFontMetrics().getStringBounds(bgText.getText(), graphics).getHeight();
            if(yPos == -1) {
                yPos = (getHeight() - stringHeight) / 2;
            }    
            lastXPos = paintString(graphics, bgText, yPos, lastXPos);
        }
    }

    private int paintString(Graphics graphics, BackgroundText backgroundText, int yPos, int lastXPos) {
        graphics.setFont(new Font(getFont().getName(), backgroundText.isBold() ? Font.BOLD : Font.PLAIN, backgroundText.getFontSize()));
        int stringWidth = (int) graphics.getFontMetrics().getStringBounds(backgroundText.getText(), graphics).getWidth();
        int xPos = lastXPos;
        if(xPos == -1 || !backgroundText.isAlighWithPrevious()) {
            xPos = (getWidth() - stringWidth) / 2;
        }
        graphics.setColor(Color.WHITE);
        graphics.drawString(backgroundText.getText(), xPos + 1, yPos + 1);
        if(backgroundText.isUnderline()) {
            graphics.drawLine(xPos + 1, yPos + 6, xPos + stringWidth, yPos + 6);
        }
        graphics.setColor(new Color(0, 0, 0, 0.6f));
        graphics.drawString(backgroundText.getText(), xPos, yPos);
        if(backgroundText.isUnderline()) {
            graphics.drawLine(xPos, yPos + 5, xPos + stringWidth, yPos + 5);
        }
        return xPos;
    }
    
}
