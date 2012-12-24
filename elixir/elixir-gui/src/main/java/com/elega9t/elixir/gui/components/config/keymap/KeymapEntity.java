/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.components.config.keymap;

import com.elega9t.commons.entity.tree.impl.DefaultGuiEntityTreeNode;
import com.elega9t.commons.swing.KeymapListener;
import com.elega9t.elixir.gui.mgr.ElixirKeymapKey;
import com.elega9t.elixir.gui.mgr.IconsManager;

import javax.swing.*;

public class KeymapEntity extends DefaultGuiEntityTreeNode<DefaultGuiEntityTreeNode> implements KeymapListener {

    private final ElixirKeymapKey keymapKey;
    private KeyStroke keyStroke;

    public KeymapEntity(ElixirKeymapKey keymapKey, KeyStroke keyStroke) {
        super(keymapKey.getDisplay(), IconsManager.getInstance().config.keymap.getKeyStrokeIcon());
        this.keymapKey = keymapKey;
        this.keyStroke = keyStroke;
    }

    public ElixirKeymapKey getKeymapKey() {
        return keymapKey;
    }

    public KeyStroke getKeyStroke() {
        return keyStroke;
    }

    @Override
    public void updateActionKey(KeyStroke keyStroke) {
        this.keyStroke = keyStroke;
    }

    @Override
    public String toString() {
        return getName() + " - " + keyStroke;
    }

}
