/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.tree.impl;

import com.elega9t.commons.entity.GuiEntity;
import com.elega9t.commons.entity.LoadableEntity;
import com.elega9t.commons.entity.tree.EntityTreeNode;

import javax.swing.*;

public class DefaultLazyLoadGuiEntityTreeNode<T extends GuiEntity & LoadableEntity & EntityTreeNode> extends DefaultLazyLoadEntityTreeNode<T> implements GuiEntity {

    private Icon icon;
    private String tooltip;

    public DefaultLazyLoadGuiEntityTreeNode(String name) {
        this(name, (T) null);
    }

    public DefaultLazyLoadGuiEntityTreeNode(String name, EntityTreeNode parent) {
        this(name, parent, null);
    }

    public DefaultLazyLoadGuiEntityTreeNode(String name, Icon icon) {
        this(name, icon, null);
    }

    public DefaultLazyLoadGuiEntityTreeNode(String name, EntityTreeNode parent, Icon icon) {
        this(name, parent, icon, null);
    }

    public DefaultLazyLoadGuiEntityTreeNode(String name, Icon icon, String tooltip) {
        this(name, null, icon, tooltip);
    }

    public DefaultLazyLoadGuiEntityTreeNode(String name, EntityTreeNode parent, Icon icon, String tooltip) {
        super(name, parent);
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
