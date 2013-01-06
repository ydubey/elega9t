/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.docking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class DockToggleButton extends DockButton {

    public DockToggleButton(DockLocation location) {
        this(location, null);
    }

    public DockToggleButton(DockLocation location, String text) {
        this(location, text, null);
    }

    public DockToggleButton(DockLocation location, String text, Icon icon) {
        this(location, text, icon, null);
    }

    public DockToggleButton(DockLocation location, String text, Icon icon, Icon disabledIcon) {
        super(location, text, icon, disabledIcon);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        selected = !selected;
        if(selected) {
            setBorder(BEVEL_BORDER);
        } else {
            setBorder(LINE_BORDER);
        }
        fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "selection"));
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if(!selected) {
            setBorder(LINE_BORDER);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if(!selected) {
            setBorder(EMPTY_BORDER);
        }
    }

}
