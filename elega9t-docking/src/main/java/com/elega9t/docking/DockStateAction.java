/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.docking;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DockStateAction extends AbstractAction {

    private final DockButton dockButton;
    private final DockablePanel dockablePanel;

    private DockStateAction(DockButton dockButton, DockablePanel dockablePanel) {
        this.dockButton = dockButton;
        this.dockablePanel = dockablePanel;
        dockButton.addActionListener(this);
        dockablePanel.getDockButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dockablePanel.getDockButton()) {
            dockablePanel.setVisible(false);
            dockButton.setSelected(false);
        } else if(e.getSource() == dockButton) {
            boolean selected = dockButton.isSelected();
            dockablePanel.setVisible(selected);
        }
    }

    public static void install(DockButton dockButton, DockablePanel dockablePanel) {
        new DockStateAction(dockButton, dockablePanel);
    }

}
