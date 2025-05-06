/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.ColumnType;
import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;

import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefinitionAndQueryTool extends QueryTool {

  protected final List<Column<?>> primaryColumns;
  private final String primaryColumnsCreation;
  private List<Column<?>> columns = new ArrayList<>();

  protected DefinitionAndQueryTool(DatabaseConnector databaseConnector, String tableName, Column<?>... primaryColumns) {
    this(databaseConnector, tableName, Arrays.stream(primaryColumns).toList());
  }

  protected DefinitionAndQueryTool(DatabaseConnector databaseConnector, String tableName, boolean temporary,
                                   Column<?>... primaryColumns) {
    this(databaseConnector, tableName, temporary, Arrays.stream(primaryColumns).toList());
  }

  protected DefinitionAndQueryTool(DatabaseConnector databaseConnector, String tableName,
                                   List<Column<?>> primaryColumns) {
    this(databaseConnector, tableName, true, primaryColumns);
  }

  protected DefinitionAndQueryTool(DatabaseConnector databaseConnector, String tableName, boolean temporary,
                                   List<Column<?>> primaryColumns) {
    super(databaseConnector, tableName, UpdatePolicy.DISCARD_IF_NOT_EXISTS, temporary);
    this.primaryColumns = primaryColumns;

    if (this.primaryColumns.size() == 1) {
      this.primaryColumnsCreation = " `" + this.primaryColumns.get(0).getName() + "` " + this.primaryColumns.get(0).getType().getEnhancedName() + " PRIMARY KEY";
    } else {
      StringBuilder primaryList = new StringBuilder();
      StringBuilder sb = new StringBuilder();
      for (Column<?> column : this.primaryColumns) {
        sb.append(column.getName());
        sb.append(" ").append(column.getType().getEnhancedName());
        sb.append(", ");
        primaryList.append(column.getName());
        primaryList.append(", ");
      }
      sb.delete(sb.length() - 2, sb.length());
      primaryList.delete(primaryList.length() - 2, primaryList.length());
      sb.append(", PRIMARY KEY (");
      sb.append(primaryList);
      sb.append(")");

      this.primaryColumnsCreation = sb.toString();
    }

  }

  protected void create() {
    Connection connection = null;
    Statement ps = null;
    ResultSet rs = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.createStatement();

      ps.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + " (" + this.primaryColumnsCreation + ");");

      Database.LOGGER.info("Table {} created with primary-keys: '{}'", this.tableName, primaryColumnsCreation);

      Set<String> primaryColumnNames = this.primaryColumns.stream().map(Column::getName).collect(Collectors.toSet());

      Map<String, Column<?>> columnByName = this.columns.stream().collect(Collectors.toMap(Column::getName, Function.identity()));

      rs = ps.executeQuery("SELECT * FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER);
      ResultSetMetaData meta = rs.getMetaData();

      for (int i = 1; i <= meta.getColumnCount(); i++) {
        String name = meta.getColumnName(i);
        String type = meta.getColumnTypeName(i);
        int length = meta.getColumnDisplaySize(i);

        if (primaryColumnNames.contains(name)) {
          continue;
        }

        Column<?> column = columnByName.remove(name);

        // check if column should exist, if not drop it, else check type
        if (column != null) {
          // check column type
          if (!column.getType().getSimpleName().equalsIgnoreCase(type)) {
            Database.LOGGER.warn("Type ('{}') of column '{}' in table '{}' differs from configured type ('{}')", type, column.getName(), this.tableName, column.getType().getSimpleName());
          }
          if (column.getType().getLength() != length) {
            Database.LOGGER.warn("Length ('{}') of column '{}' in table '{}' differs from configured length ('{}')", length, column.getName(), this.tableName, column.getType().getLength());
          }
        } else {
          ps.executeUpdate("ALTER TABLE " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + " DROP COLUMN " + COLUMN_WRAPPER + name + COLUMN_WRAPPER + ";");
          Database.LOGGER.info("Dropped column '{}' in table '{}'", name, this.tableName);
        }
      }

      Column<?> columnBefore = this.primaryColumns.get(this.primaryColumns.size() - 1);

      for (Column<?> column : columns) {
        // add new column
        if (columnByName.remove(column.getName()) != null) {
          ps.executeUpdate("ALTER TABLE " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + " ADD COLUMN " + COLUMN_WRAPPER + column.getName() + COLUMN_WRAPPER + " " + column.getType().getEnhancedName() + " AFTER " + COLUMN_WRAPPER + columnBefore.getName() + COLUMN_WRAPPER + ";");
          Database.LOGGER.info("Added column '{}' in table '{}'", column.getName(), this.tableName);
        }

        columnBefore = column;
      }
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, rs);
    }

    this.loadFunctions();

    if (this.isTemporary()) {
      this.createTmp();
      this.loadTempData();
    }
  }

  protected void loadFunctions() {
    Connection connection = null;
    Statement ps = null;
    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.createStatement();

      if (this.primaryColumns.stream().anyMatch(c -> c.getType().equals(ColumnType.UUID)) || this.columns.stream().anyMatch(c -> c.getType().equals(ColumnType.UUID))) {
        try {
          ps.executeUpdate("""
              CREATE FUNCTION IF NOT EXISTS BIN_TO_UUID(bin BINARY(16))
              RETURNS CHAR(36) DETERMINISTIC
              BEGIN
                DECLARE hex CHAR(32);
                SET hex = HEX(bin);
                RETURN LOWER(CONCAT(LEFT(hex, 8), '-', MID(hex, 9, 4), '-', MID(hex, 13, 4), '-', MID(hex, 17, 4), '-', RIGHT(hex, 12)));
              END;
              """);
          Database.LOGGER.info("Added 'BIN_TO_UUID' function in table '{}'", this.tableName);
        } catch (SQLException e) {
          Database.LOGGER.warn("Could not load bin to uuid function in table {}", this.tableName);
          DatabaseManager.getInstance().handleSQLException(e);
        }

        try {
          ps.executeUpdate("""
              CREATE FUNCTION IF NOT EXISTS UUID_TO_BIN(uuid CHAR(36))
              RETURNS BINARY(16) DETERMINISTIC
              BEGIN
                RETURN UNHEX(CONCAT(REPLACE(uuid, '-', '')));
              END;
              """);
          Database.LOGGER.info("Added 'UUID_TO_BIN' function in table '{}'", this.tableName);
        } catch (SQLException e) {
          Database.LOGGER.warn("Could not load uuid to bin function in table{}", this.tableName);
          DatabaseManager.getInstance().handleSQLException(e);
        }
      }
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }

  }

  protected void createTmp() {

    Connection connection = null;
    Statement ps = null;
    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.createStatement();
      ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);
      ps.addBatch("CREATE TABLE IF NOT EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + " LIKE " + this.tableName + "");
      ps.addBatch("ALTER TABLE " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + " ENGINE=memory");
      ps.executeBatch();
      Database.LOGGER.info("Created temporary table '{}'", this.tableName);
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }
  }

  protected void delete() {

    Connection connection = null;
    Statement ps = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.createStatement();
      ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER);
      if (this.isTemporary()) {
        ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);
      }
      ps.executeBatch();
      Database.LOGGER.info("Deleted tables '{}' and '{}_tmp'", this.tableName, this.tableName);
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }
  }

  private void loadTempData() {

    Connection connection = null;
    PreparedStatement ps = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.prepareStatement("INSERT INTO " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + " SELECT * FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + ";");
      ps.executeUpdate();
      Database.LOGGER.info("Inserted data into temporary for table '{}'", this.tableName);
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }
  }

  protected void save() {

    Connection connection = null;
    Statement ps = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.createStatement();
      ps.addBatch("DELETE FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER);
      ps.addBatch("INSERT INTO " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + " SELECT * FROM " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);
      ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);

      ps.executeBatch();
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }
  }

  protected void save(Column<?>[] columns) {

    Connection connection = null;
    Statement ps = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.createStatement();
      ps.addBatch("DELETE FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + ";");
      ps.addBatch("INSERT INTO " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + " (" + this.getColumnsAsString(columns) + ") SELECT " + this.getColumnsAsString(columns) + " FROM " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + ";");
      ps.executeBatch();
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }

    this.dropTmpTable();
  }

  protected void dropTmpTable() {
    Connection connection = null;
    PreparedStatement ps = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.prepareStatement("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + ";");
      ps.executeUpdate();
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, null);
    }
  }


  private String getColumnsAsString(Column<?>... columns) {
    StringBuilder sb = new StringBuilder();
    for (Column<?> column : columns) {
      sb.append(column.getName());
      sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    return sb.toString();
  }

  protected List<Column<?>> getColumns() {
    return columns;
  }

  protected void setColumns(List<Column<?>> columns) {
    this.columns = columns;
  }

  public List<Column<?>> getPrimaryColumns() {
    return primaryColumns;
  }

  /**
   * Do not add the primary column
   *
   * @param column to add
   */
  protected void addColumn(Column<?> column) {
    this.columns.add(column);
  }

  public List<Column<?>> getAllColumns() {
    List<Column<?>> columns = new ArrayList<>(this.getPrimaryColumns());
    columns.addAll(this.getColumns());
    return columns;
  }
}
