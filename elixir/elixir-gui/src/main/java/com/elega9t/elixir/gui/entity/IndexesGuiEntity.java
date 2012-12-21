/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.Indexes;
import com.elega9t.elixir.gui.mgr.IconsManager;

public class IndexesGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity, Indexes> {

    public IndexesGuiEntity(Indexes indexes) {
        super(indexes.getName(), IconsManager.getInstance().database.getTableIndexesIcon());
        this.entity = indexes;
    }

}
