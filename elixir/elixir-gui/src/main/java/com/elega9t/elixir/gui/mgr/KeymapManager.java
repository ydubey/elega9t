/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgr;

import com.elega9t.commons.swing.KeymapListener;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class KeymapManager {

    private static final KeymapManager INSTANCE = new KeymapManager();

    public final EditorActions editor = new EditorActions();
    private Map<ElixirKeymapKey, List<KeymapListener>> keymapListeners = new HashMap<ElixirKeymapKey, List<KeymapListener>>();

    public static KeymapManager getInstance() {
        return INSTANCE;
    }

    public synchronized void addKeymapListener(ElixirKeymapKey keymapKey, KeymapListener keymapListener) {
        List<KeymapListener> keymapListenersForKey = keymapListeners.get(keymapKey);
        if(keymapListenersForKey == null) {
            keymapListenersForKey = new ArrayList<KeymapListener>();
            keymapListeners.put(keymapKey, keymapListenersForKey);
        }
        keymapListenersForKey.add(keymapListener);
    }

    public class EditorActions {

        private Map<ElixirKeymapKey, KeyStroke> actions = new LinkedHashMap<ElixirKeymapKey, KeyStroke>();

        private EditorActions() {
            this.actions.put(ElixirKeymapKey.EXPAND_SHORTHAND, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
            this.actions.put(ElixirKeymapKey.EXECUTE_QUERY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
        }

        public KeyStroke getKeyStroke(ElixirKeymapKey key) {
            return actions.get(key);
        }

        public void updateKeyStroke(ElixirKeymapKey keymapKey, KeyStroke keyStroke) {
            List<KeymapListener> keymapListenersForKey = keymapListeners.get(keymapKey);
            if(keymapListenersForKey != null) {
                for (KeymapListener keymapListener : keymapListenersForKey) {
                    keymapListener.updateActionKey(keyStroke);
                }
            }
        }

    }

}
