/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.actions.menu.file;

import com.elega9t.elixir.gui.dialog.ConnectToDatabaseDialog;
import com.elega9t.elixir.gui.form.Context;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConnectToDatabaseAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new ConnectToDatabaseDialog(Context.getInstance().getMain(), true).openDialog();
    }

}
