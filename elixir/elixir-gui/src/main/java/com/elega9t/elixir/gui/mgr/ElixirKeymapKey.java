/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgr;

public enum ElixirKeymapKey {

    EXPAND_SHORTHAND("Expand Shorthand"),
    EXECUTE_QUERY("Execute Query");

    private final String display;

    ElixirKeymapKey(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

}
