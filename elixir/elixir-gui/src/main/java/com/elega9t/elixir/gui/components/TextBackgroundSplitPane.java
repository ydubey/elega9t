/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */
package com.elega9t.elixir.gui.components;

import com.elega9t.commons.swing.BackgroundText;

import java.awt.*;
import javax.swing.JTabbedPane;

import static com.elega9t.commons.swing.SwingUtilities.paintBackgroundText;

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
        paintBackgroundText(graphics, getFont(), getHeight(), getWidth(), backgroundText);
    }
    
}
