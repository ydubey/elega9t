/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import javax.swing.*;

public class DefaultGuiEntity extends DefaultEntity implements GuiEntity {

    private Icon icon;
    private String tooltip;

    public DefaultGuiEntity(String name) {
        this(name, null);
    }

    public DefaultGuiEntity(String name, Icon icon) {
        this(name, icon, null);
    }

    public DefaultGuiEntity(String name, Icon icon, String tooltip) {
        super(name);
        this.icon = icon;
        this.tooltip = tooltip;
    }

    @Override
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

}
