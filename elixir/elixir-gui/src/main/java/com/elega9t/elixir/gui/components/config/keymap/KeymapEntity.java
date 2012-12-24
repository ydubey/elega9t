/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.components.config.keymap;

import com.elega9t.commons.entity.UniqueEntity;
import com.elega9t.commons.entity.tree.impl.DefaultGuiEntityTreeNode;
import com.elega9t.commons.swing.KeymapListener;
import com.elega9t.elixir.gui.mgr.IconsManager;
import com.elega9t.elixir.gui.mgr.KeymapManager;

import javax.swing.*;

public class KeymapEntity extends DefaultGuiEntityTreeNode<DefaultGuiEntityTreeNode> implements KeymapListener, UniqueEntity {

    private final String id;
    private KeyStroke keyStroke;

    public KeymapEntity(KeymapManager.KeymapKeystrokeAction keymapKeystrokeAction) {
        super(keymapKeystrokeAction.getName(), IconsManager.getInstance().config.keymap.getKeyStrokeIcon());
        this.id = keymapKeystrokeAction.getId();
        this.keyStroke = keymapKeystrokeAction.getKeyStroke();
    }

    public KeyStroke getKeyStroke() {
        return keyStroke;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUniqueName() {
        return id;
    }

    @Override
    public void update(KeyStroke keyStroke) {
        this.keyStroke = keyStroke;
    }

    @Override
    public String toString() {
        return getName() + " - " + keyStroke;
    }

}
