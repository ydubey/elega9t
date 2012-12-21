/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgr;

import java.util.HashMap;
import java.util.Map;

public class ShorthandManager {

    private Map<String, String> shorthands = new HashMap<String, String>();

    private static final ShorthandManager INSTANCE = new ShorthandManager();

    private ShorthandManager() {
        shorthands.put("s*f", "select * from ");
        shorthands.put("ssf", "select * from ");
        shorthands.put("ii", "insert into ");
        shorthands.put("gb", "group by ");
        shorthands.put("inn", "is not null ");
    }

    public static ShorthandManager getInstance() {
        return INSTANCE;
    }

    public Map<String, String> getShorthands() {
        return shorthands;
    }

}
