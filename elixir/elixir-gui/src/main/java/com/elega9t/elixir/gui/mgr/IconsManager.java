/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgr;

import javax.swing.*;

public class IconsManager {

    public static IconsManager INSTANCE = new IconsManager();

    public static IconsManager getInstance() {
        return INSTANCE;
    }

    public System system() {
        return new System();
    }

    public Database database() {
        return new Database();
    }

    public class System {

        public Icon getInfoIcon() {
            return new javax.swing.ImageIcon(IconsManager.class.getResource("/com/elega9t/elixir/gui/icons/info.png"));
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

}
