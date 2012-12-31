/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.event.exit;

import com.elega9t.gui.platform.Context;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class DefaultApplicationClosingEventListener implements ApplicationClosingEventListener {

    @Override
    public boolean closing(WindowEvent evt) {
        return JOptionPane.showOptionDialog(Context.getInstance().getMain(),
                "Are You Sure to Close this Application?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION;
    }

}
