/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.util.object;

import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.core.table.Table;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {


    private final String user;
    private final String password;
    protected String name;
    protected BasicDataSource ds;
    protected String url;

    public DatabaseConnector(String name, String url, String user, String password) {
        this(name, url, user, password, DatabaseManager.DEFAULT_MAX_IDLE_CONNECTIONS);
    }

    public DatabaseConnector(String name, String url, String user, String password, int maxIdleConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.name = name;


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
        try {
            ps = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS `" + name + "`;");
            ps.executeUpdate();
            DatabaseManager.getInstance().broadcast("[Database][" + name + "] Database created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    public String getName() {
        return name;
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
