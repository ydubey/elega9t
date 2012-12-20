/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EtchedBorderOnMouseOverListener extends MouseAdapter {

    private final JComponent component;
    private final Border normalBorder;
    private EtchedBorder etchedBorder = new EtchedBorder();

    public EtchedBorderOnMouseOverListener(JComponent component) {
        this.component = component;
        normalBorder = component.getBorder();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBorder(etchedBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBorder(normalBorder);
    }

}
