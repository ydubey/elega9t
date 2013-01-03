/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */
package com.elega9t.docking;

import javax.swing.*;
import java.awt.*;

public class TextBackgroundTabbedPane extends JTabbedPane {
    
    private final BackgroundText[] backgroundText;

    public TextBackgroundTabbedPane(BackgroundText... backgroundText) {
        this.backgroundText = backgroundText;
    }

    public TextBackgroundTabbedPane(int i, BackgroundText... backgroundText) {
        super(i);
        this.backgroundText = backgroundText;
    }

    public TextBackgroundTabbedPane(int i, int i1, BackgroundText... backgroundText) {
        super(i, i1);
        this.backgroundText = backgroundText;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBackgroundText(graphics, getFont(), getHeight(), getWidth());
    }

    private void paintBackgroundText(Graphics graphics, Font font, int height, int width) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int lastXPos = -1;
        for(int index=0; index < backgroundText.length; index++) {
            final BackgroundText bgText = backgroundText[index];
            int yPos = bgText.getY();
            int stringHeight = (int) graphics.getFontMetrics().getStringBounds(bgText.getText(), graphics).getHeight();
            if(yPos == -1) {
                yPos = (height - stringHeight) / 2;
            }
            graphics.setFont(new Font(font.getName(), bgText.isBold() ? Font.BOLD : Font.PLAIN, bgText.getFontSize()));
            int stringWidth = (int) graphics.getFontMetrics().getStringBounds(bgText.getText(), graphics).getWidth();
            int xPos = lastXPos;
            if(xPos == -1 || !bgText.isAlighWithPrevious()) {
                xPos = (width - stringWidth) / 2;
            }
            graphics.setColor(Color.WHITE);
            graphics.drawString(bgText.getText(), xPos + 1, yPos + 1);
            if(bgText.isUnderline()) {
                graphics.drawLine(xPos + 1, yPos + 6, xPos + stringWidth, yPos + 6);
            }
            graphics.setColor(new Color(0, 0, 0, 0.6f));
            graphics.drawString(bgText.getText(), xPos, yPos);
            if(bgText.isUnderline()) {
                graphics.drawLine(xPos, yPos + 5, xPos + stringWidth, yPos + 5);
            }
            lastXPos = xPos;
        }
    }

}
