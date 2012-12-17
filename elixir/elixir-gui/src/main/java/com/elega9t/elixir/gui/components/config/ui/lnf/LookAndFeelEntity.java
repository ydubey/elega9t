/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.components.config.ui.lnf;

import com.elega9t.commons.entity.impl.DefaultEntity;

import javax.swing.*;

public class LookAndFeelEntity extends DefaultEntity {

    private final UIManager.LookAndFeelInfo lnf;

    public LookAndFeelEntity(UIManager.LookAndFeelInfo lnf) {
        super(lnf.getName());
        this.lnf = lnf;
    }

    public String getLnfClassName() {
        return lnf.getClassName();
    }

}
