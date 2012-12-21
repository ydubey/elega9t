/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgr;

import javax.swing.*;

public class IconsManager {

    public static IconsManager INSTANCE = new IconsManager();

    public final System system = new System();
    public final Database database = new Database();
    public final SqlResultTable sqlResultTable = new SqlResultTable();

    public static IconsManager getInstance() {
        return INSTANCE;
    }

    public class System {

        public Icon getInfoIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/info.png"));
        }

        public Icon getEventManagerIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/bubble.png"));
        }

    }

    public class Database {

        public Icon getDatabaseIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database.png"));
        }

        public Icon getConnectedDatabaseIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database_connected.png"));
        }

        public Icon getDatabaseTablesIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database_tables.png"));
        }

        public Icon getTableIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/table.png"));
        }

        public Icon getTableColumnsIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/table_columns.png"));
        }

        public Icon getColumnIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/column.png"));
        }

        public Icon getTableIndexesIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/table_indexes.png"));
        }

        public Icon getIndexIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/index.png"));
        }

    }

    public class SqlResultTable {

        public Icon getSaveIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/table_save.png"));
        }

        public Icon getDeleteRowsIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/table_delete_row.png"));
        }

    }

}
