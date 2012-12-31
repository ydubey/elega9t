/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.actions.menu.edit;

import com.elega9t.commons.swing.config.ConfigDialog;
import com.elega9t.elixir.gui.ResourceStrings;
import com.elega9t.elixir.gui.components.config.keymap.KeymapConfigPanel;
import com.elega9t.elixir.gui.components.config.ui.lnf.LookAndFeelConfigPanel;
import com.elega9t.elixir.gui.form.Context;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SettingsAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new ConfigDialog(
                Context.getInstance().getMain(),
                true,
                ResourceStrings.dialog.settings.getString("title"),
                ResourceStrings.buttons.getString("apply"),
                ResourceStrings.buttons.getString("cancel"),
                ResourceStrings.buttons.getString("help"),
                new KeymapConfigPanel(),
                new LookAndFeelConfigPanel()
        ).setVisible(true);
    }

}
