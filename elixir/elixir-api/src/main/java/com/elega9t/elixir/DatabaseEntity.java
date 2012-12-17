/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.tree.impl.DefaultLazyLoadEntityTreeNode;

public abstract class DatabaseEntity<T extends DatabaseEntity> extends DefaultLazyLoadEntityTreeNode<T> {

    private final Connection connection;

    public DatabaseEntity(String name, DatabaseEntity parent, Connection connection) {
        super(name, parent);
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public T getChildAt(int index) {
        return super.getChildAt(index);
    }

    public abstract <R> R visit(DatabaseEntityVisitor<R> visitor);

}
