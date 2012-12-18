/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.Index;
import com.elega9t.elixir.gui.mgr.IconsManager;

public class IndexGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity, Index> {

    public IndexGuiEntity(Index index) {
        super(index.getName(), IconsManager.getInstance().database().getIndexIcon());
        this.entity = index;
        loaded = true;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

}
