/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import com.elega9t.commons.entity.EntityNode;

public class EntityNodeDataModel<T extends EntityNode> implements DataModel<T> {

    private EntityNode<T> entityNode;
    private ColumnDataModel<T>[] columnDataModels;

    public EntityNodeDataModel(EntityNode<T> entityNode, ColumnDataModel<T>... columnDataModels) {
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
        return columnDataModels[column].value(entityNode.getChild(row));
    }

    @Override
    public String columnName(int column) {
        return columnDataModels[column].name();
    }

}
