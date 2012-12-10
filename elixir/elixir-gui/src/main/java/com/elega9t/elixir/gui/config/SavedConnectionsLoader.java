package com.elega9t.elixir.gui.config;

import java.util.ArrayList;
import java.util.List;

public class SavedConnectionsLoader {

    private static List<SavedConnection> savedConnections;

    public static class SavedConnection {

        private String driver;
        private String user;
        private String password;
        private String database;

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

    }

    public static void load() {
        savedConnections = new ArrayList<SavedConnection>();
    }

    public static List<SavedConnection> get() {
        return savedConnections;
    }

}
