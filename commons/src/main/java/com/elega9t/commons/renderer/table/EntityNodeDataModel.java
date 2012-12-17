/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import com.elega9t.commons.entity.EntityTreeNode;
import com.elega9t.commons.entity.EntityTreeNode;

public class EntityNodeDataModel<T extends EntityTreeNode> implements DataModel<T> {

    private EntityTreeNode<T> entityNode;
    private ColumnDataModel<T>[] columnDataModels;

    public EntityNodeDataModel(EntityTreeNode<T> entityNode, ColumnDataModel<T>... columnDataModels) {
        this.entityNode = entityNode;
        this.columnDataModels = columnDataModels;
    }

    @Override
    public int rowCount() {
        return entityNode.getChildCount();
    }

    @Override
    public int columnCount() {
        return columnDataModels.length;
    }

    @Override
    public String value(int row, int column) {
        return columnDataModels[column].value(entityNode.getChildAt(row));
    }

    @Override
    public String columnName(int column) {
        return columnDataModels[column].name();
    }

}
