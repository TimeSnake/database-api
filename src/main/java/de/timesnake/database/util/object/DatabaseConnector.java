/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.object;

import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.core.table.QueryTool;
import de.timesnake.database.util.Database;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

  private final String name;
  private final String user;
  private final String password;
  protected BasicDataSource ds;
  protected String url;

  public DatabaseConnector(String name, String url, String options, String user, String password) {
    this(name, url, options, user, password, DatabaseManager.DEFAULT_MAX_IDLE_CONNECTIONS);
  }

  public DatabaseConnector(String name, String url, String options, String user, String password,
                           int maxIdleConnections) {
    this.name = name;
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

  public boolean isConnected() {
    return this.ds != null && !this.ds.isClosed();
  }

  public void createDatabase(String... names) {
    Connection connection = null;
    Statement statement = null;

    try {
      connection = this.getConnection();
      if (connection == null) {
        Database.LOGGER.warn("Could not create connection to database '{}'", this.name);
        return;
      }

      statement = connection.createStatement();
      for (String name : names) {
        statement.addBatch("CREATE DATABASE IF NOT EXISTS `" + name + "`; ");
      }
      Database.LOGGER.info("Created databases '{}'", String.join("', '", names));
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, statement, null);
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

  public void saveTables() {

  }

}
