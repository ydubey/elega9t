package com.elega9t.docking;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DockStateAction extends AbstractAction {

    private DockPanel dockPanel;
    private final DockButton dockButton;
    private final DockablePanel dockablePanel;

    public DockStateAction(DockPanel dockPanel, DockButton dockButton, DockablePanel dockablePanel) {
        this.dockPanel = dockPanel;
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
            dockablePanel.setVisible(dockButton.isSelected());
        }
        dockPanel.validate();
        dockPanel.updateUI();
    }

}
