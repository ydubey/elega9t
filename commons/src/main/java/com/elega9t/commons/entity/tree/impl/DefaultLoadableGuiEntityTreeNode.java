/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.tree.impl;

import com.elega9t.commons.entity.GuiEntity;
import com.elega9t.commons.entity.tree.EntityTreeNode;

import javax.swing.*;
import javax.swing.tree.TreeNode;

public class DefaultLoadableGuiEntityTreeNode<T extends DefaultLoadableGuiEntityTreeNode> extends DefaultLoadableEntityTreeNode<T> implements GuiEntity, TreeNode {

    private Icon icon;
    private String tooltip;

    public DefaultLoadableGuiEntityTreeNode(String name) {
        this(name, (EntityTreeNode) null);
    }

    public DefaultLoadableGuiEntityTreeNode(String name, EntityTreeNode parent) {
        this(name, parent, null);
    }

    public DefaultLoadableGuiEntityTreeNode(String name, Icon icon) {
        this(name, null, icon);
    }

    public DefaultLoadableGuiEntityTreeNode(String name, EntityTreeNode parent, Icon icon) {
        this(name, parent, icon, null);
    }

    public DefaultLoadableGuiEntityTreeNode(String name, Icon icon, String tooltip) {
        this(name, null, icon, tooltip);
    }

    public DefaultLoadableGuiEntityTreeNode(String name, EntityTreeNode parent, Icon icon, String tooltip) {
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
    public void setToolTipText(String tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public String getToolTipText() {
        return tooltip;
    }

}
