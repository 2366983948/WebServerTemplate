package com.webserver.WebServerTemplate.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsEntity {

    private Database database;
    private Admin admin;

    public static class Database {
        private boolean initialize;

        @JsonProperty("initialize")
        public boolean isInitialize() {
            return initialize;
        }

        public void setInitialize(boolean initialize) {
            this.initialize = initialize;
        }
    }

    public static class Admin {
        private String username;
        private String password;

        @JsonProperty("username")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @JsonProperty("password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @JsonProperty("database")
    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    @JsonProperty("admin")
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
