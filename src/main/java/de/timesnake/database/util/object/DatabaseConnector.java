/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.object;

import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.Database;
import de.timesnake.library.basic.util.Loggers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnector {


    private final String user;
    private final String password;
    protected BasicDataSource ds;
    protected String url;

    public DatabaseConnector(String name, String url, String options, String user,
            String password) {
        this(name, url, options, user, password, DatabaseManager.DEFAULT_MAX_IDLE_CONNECTIONS);
    }

    public DatabaseConnector(String name, String url, String options, String user, String password,
            int maxIdleConnections) {
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
    }

    public void connect() throws SQLException {
        this.ds.start();
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

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

    public void createDatabase(String name) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.getConnection();
            if (connection == null) {
                Loggers.CHANNEL.warning("Could not create connection to database '" + name + "'");
                return;
            }

            ps = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS `" + name + "`;");
            ps.executeUpdate();
            Database.LOGGER.info("Created database '" + name + "'");
        } catch (SQLException e) {
            DatabaseManager.getInstance().handleSQLException(e);
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    public void close() {
        try {
            this.ds.close();
        } catch (SQLException e) {
            DatabaseManager.getInstance().handleSQLException(e);
        }
    }

    public void createTables() {

    }

    public void backupTables() {

    }

}
