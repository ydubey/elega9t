/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import java.awt.*;

public class NodeIcon implements Icon {

    public static enum TYPE {
        EXPANDED,
        COLLAPSED
    }

    private static final int SIZE = 9;

    private TYPE type;

    public NodeIcon(TYPE type) {
        this.type = type;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(UIManager.getColor("Tree.background"));
        g.fillRect(x, y, SIZE - 1, SIZE - 1);

        g.setColor(UIManager.getColor("Tree.hash").darker());
        g.drawRect(x, y, SIZE - 1, SIZE - 1);

        g.setColor(UIManager.getColor("Tree.foreground"));
        g.drawLine(x + 2, y + SIZE / 2, x + SIZE - 3, y + SIZE / 2);
        if (type == TYPE.COLLAPSED) {
            g.drawLine(x + SIZE / 2, y + 2, x + SIZE / 2, y + SIZE - 3);
        }
    }

    public int getIconWidth() {
        return SIZE;
    }

    public int getIconHeight() {
        return SIZE;
    }

}