/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.commons.entity.GuiEntityNode;

import javax.swing.*;

public class DatabaseGuiEntity<T extends DatabaseGuiEntity> extends GuiEntityNode<T> {

    public DatabaseGuiEntity(String name, Icon icon) {
        super(name, icon);
    }

}
