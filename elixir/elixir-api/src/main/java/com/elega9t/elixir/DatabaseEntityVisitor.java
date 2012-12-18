/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

public interface DatabaseEntityVisitor<T> {

    T visit(Columns columns);

    T visit(Column column);

    T visit(Schema schema);

    T visit(Schemas schemas);

    T visit(Table table);

    T visit(TableType tableType);

    T visit(TableTypes tableTypes);

    T visit(Indexes indexes);

    T visit(Index index);

}
