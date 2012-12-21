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

        public ImageIcon getIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/system/icon.png"));
        }

        public ImageIcon getInfoIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/system/info.png"));
        }

        public ImageIcon getEventManagerIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/system/bubble.png"));
        }

    }

    public class Database {

        public ImageIcon getDatabaseIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/database.png"));
        }

        public ImageIcon getConnectedDatabaseIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/database_connected.png"));
        }

        public ImageIcon getDatabaseTablesIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/database_tables.png"));
        }

        public ImageIcon getTableIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/table.png"));
        }

        public ImageIcon getTableColumnsIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/table_columns.png"));
        }

        public ImageIcon getColumnIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/column.png"));
        }

        public ImageIcon getTableIndexesIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/table_indexes.png"));
        }

        public ImageIcon getIndexIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/index.png"));
        }

    }

    public class SqlResultTable {

        public ImageIcon getSaveIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/table_save.png"));
        }

        public ImageIcon getDeleteRowsIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/database/table_delete_row.png"));
        }

    }

}
