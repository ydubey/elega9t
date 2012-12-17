/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import java.awt.*;

public abstract class LongTask {

    public void execute(Component component) throws Exception {
        try {
            component.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            executeTask();
        } finally {
            component.setCursor(Cursor.getDefaultCursor());
        }
    }

    protected abstract void executeTask() throws Exception;

}
