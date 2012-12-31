/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.actions.menu.file;

import com.elega9t.gui.platform.Context;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Context instance = Context.getInstance();
        if (JOptionPane.showOptionDialog(instance.getMain(),
                "Are you sure you want to exit " + instance.getApplicationName() + "?",
                "Confirm Exit", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) {
            instance.getMain().dispose();
        }
    }

}
