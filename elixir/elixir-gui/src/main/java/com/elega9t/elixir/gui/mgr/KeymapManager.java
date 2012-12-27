/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgr;

import com.elega9t.commons.entity.impl.DefaultUniqueEntity;
import com.elega9t.commons.swing.KeymapListener;
import com.elega9t.commons.swing.UpdatableAction;
import com.elega9t.elixir.binding.plugin.*;
import com.elega9t.elixir.mgr.PluginProcessor;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.lang.reflect.Constructor;
import java.util.*;

public class KeymapManager implements PluginProcessor {

    private static final KeymapManager INSTANCE = new KeymapManager();

    private Map<String, List<KeymapListener>> keymapListeners = new HashMap<String, List<KeymapListener>>();
    private Map<String, Map<String, Map<String, KeymapKeystrokeAction>>> keymaps = new TreeMap<String, Map<String, Map<String, KeymapKeystrokeAction>>>();

    private KeymapManager() {
    }

    public static KeymapManager getInstance() {
        return INSTANCE;
    }

    public synchronized void addKeymapListener(String keymapGroupName, String actionName, KeymapListener keymapListener) {
        final String key = keymapGroupName + "." + actionName;
        List<KeymapListener> keymapListenersForKey = keymapListeners.get(key);
        if(keymapListenersForKey == null) {
            keymapListenersForKey = new ArrayList<KeymapListener>();
            keymapListeners.put(key, keymapListenersForKey);
        }
        keymapListenersForKey.add(keymapListener);
    }

    @Override
    public void process(Plugin plugin) {
        if(plugin.getKeymaps() != null) {
            addAll(plugin.getKeymaps().getKeymap());
        }
    }

    private void addAll(List<Keymap> keymaps) {
        for (Keymap keymap : keymaps) {
            add(keymap);
        }
    }

    private void add(Keymap keymap) {
        Map<String, Map<String, KeymapKeystrokeAction>> storedKeymap = this.keymaps.get(keymap.getName());
        if(storedKeymap == null) {
            storedKeymap = new TreeMap<String, Map<String, KeymapKeystrokeAction>>();
            this.keymaps.put(keymap.getName(), storedKeymap);
        }
        for (KeymapGroup keymapGroup : keymap.getKeymapGroup()) {
            Map<String, KeymapKeystrokeAction> storedKeyStrokes = storedKeymap.get(keymapGroup.getName().value());
            if(storedKeyStrokes == null) {
                storedKeyStrokes = new TreeMap<String, KeymapKeystrokeAction>();
                storedKeymap.put(keymapGroup.getName().value(), storedKeyStrokes);
            }
            for (KeymapAction keymapAction : keymapGroup.getKeymapAction()) {
                try {
                    storedKeyStrokes.put(keymapAction.getId(), new KeymapKeystrokeAction(keymapAction));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateKeyStroke(String keymapName, String keymapGroupName, String actionName, KeyStroke keyStroke) {
        final KeymapKeystrokeAction keymapKeystrokeAction = this.keymaps.get(keymapName).get(keymapGroupName).get(actionName);
        keymapKeystrokeAction.setKeyStroke(keyStroke);
        final String key = keymapGroupName + "." + actionName;
        List<KeymapListener> keymapListenersForKey = keymapListeners.get(key);
        if(keymapListenersForKey != null) {
            for (KeymapListener keymapListener : keymapListenersForKey) {
                keymapListener.update(keyStroke);
            }
        }
    }

    public void installEditorActions(JTextComponent textComponent) {
        final Map<String, KeymapKeystrokeAction> stringKeyStrokeMap = keymaps.get("Default").get("EDITOR_ACTIONS");
        for (String name : stringKeyStrokeMap.keySet()) {
            final KeymapKeystrokeAction keymapKeystrokeAction = stringKeyStrokeMap.get(name);
            try {
                final Constructor<UpdatableAction> constructor = keymapKeystrokeAction.actionClass.getConstructor(String.class, JTextComponent.class, KeyStroke.class);
                final UpdatableAction updatableAction = constructor.newInstance(keymapKeystrokeAction.getId(), textComponent, keymapKeystrokeAction.getKeyStroke());
                addKeymapListener("EDITOR_ACTIONS", keymapKeystrokeAction.getId(), updatableAction);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Set<String> keymaps() {
        return keymaps.keySet();
    }

    public Map<String, Map<String, KeymapKeystrokeAction>> keymaps(String name) {
        return keymaps.get(name);
    }

    public class KeymapKeystrokeAction extends DefaultUniqueEntity {

        private KeyStroke keyStroke;
        private final Class<UpdatableAction> actionClass;

        public KeymapKeystrokeAction(KeymapAction keymapAction) throws ClassNotFoundException {
            super(keymapAction.getId(), keymapAction.getName());
            this.keyStroke = KeyStroke.getKeyStroke(keymapAction.getKeyboardShortcut());
            actionClass = (Class<UpdatableAction>) Class.forName(keymapAction.getActionClass());
        }

        public void setKeyStroke(KeyStroke keyStroke) {
            this.keyStroke = keyStroke;
        }

        public KeyStroke getKeyStroke() {
            return keyStroke;
        }

    }

}
