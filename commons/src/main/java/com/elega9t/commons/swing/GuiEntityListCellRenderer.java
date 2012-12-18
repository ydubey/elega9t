/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import com.elega9t.commons.entity.GuiEntity;

import javax.swing.*;
import java.awt.*;

public class GuiEntityListCellRenderer extends JLabel implements ListCellRenderer {

    public GuiEntityListCellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value==null) {
            setIcon(null);
            setText(null);
            return this;
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        //Set the icon and text.  If icon was null, say so.
        final GuiEntity guiEntity = (GuiEntity) value;

        setIcon(guiEntity.getIcon());

        if (guiEntity.getIcon() != null) {
            setText(guiEntity.getName());
            setFont(list.getFont());
        } else {
            setUhOhText(guiEntity.getName() + " (no image available)", list.getFont());
        }
        return this;
    }

    //Set the font and text when no image was found.
    /**
     *
     * @param uhOhText
     * @param normalFont
     */
    protected void setUhOhText(String uhOhText, Font normalFont) {
        Font uhOhFont = normalFont.deriveFont(Font.ITALIC);
        setFont(uhOhFont);
        setText(uhOhText);
    }

}
