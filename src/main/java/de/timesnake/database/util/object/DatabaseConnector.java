/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.object;

import de.timesnake.channel.core.Channel;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.Database;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {


    private final String user;
    private final String password;
    protected BasicDataSource ds;
    protected String url;

    public DatabaseConnector(String name, String url, String options, String user, String password) {
        this(name, url, options, user, password, DatabaseManager.DEFAULT_MAX_IDLE_CONNECTIONS);
    }

    public DatabaseConnector(String name, String url, String options, String user, String password, int maxIdleConnections) {
        this.url = url + name + "?" + options;
        this.user = user;
        this.password = password;


        this.ds = new BasicDataSource();

        this.ds.setUrl(this.url);
        this.ds.setUsername(this.user);
        this.ds.setPassword(this.password);
        this.ds.setMinIdle(0);
        this.ds.setMaxIdle(maxIdleConnections);
        this.ds.setMaxOpenPreparedStatements(50);

        try {
            this.ds.start();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() {
        try {
            return this.ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void createDatabase(String name) {
        PreparedStatement ps = null;
        Connection connection = this.getConnection();

        if (connection == null) {
            Channel.LOGGER.warning("Could not create connection to database '" + name + "'");
            return;
        }

        try {
            ps = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS `" + name + "`;");
            ps.executeUpdate();
            Database.LOGGER.info("[" + name + "] Database created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    public void close() {
        try {
            this.ds.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTables() {

    }

    public void backupTables() {

    }

}
