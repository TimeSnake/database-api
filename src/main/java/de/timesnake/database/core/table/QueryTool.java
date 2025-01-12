/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class QueryTool {

  public static final String ENTRY_ARRAY_DELIMITER = ";";

  public static final String TABLE_WRAPPER = "`";
  public static final String COLUMN_WRAPPER = "`";

  public static void closeQuery(Connection connection, Statement ps, ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        DatabaseManager.getInstance().handleSQLException(e);
      }
    }
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException e) {
        DatabaseManager.getInstance().handleSQLException(e);
      }
    }
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        DatabaseManager.getInstance().handleSQLException(e);
      }
    }
  }

  static String parseToColumnNameString(Collection<Column<?>> columns) {
    return columns.stream().map(c -> c.getType().getSelectWrapper(c.getName())).collect(Collectors.joining(", "));
  }

  protected final DatabaseConnector databaseConnector;
  protected final String tableName;
  protected final boolean temporary;
  protected UpdatePolicy updatePolicy;

  QueryTool(DatabaseConnector databaseConnector, String tableName, UpdatePolicy updatePolicy, boolean temporary) {
    this.databaseConnector = databaseConnector;
    this.tableName = tableName;
    this.temporary = temporary;
    this.updatePolicy = updatePolicy;
  }

  public String getTableName() {
    return this.tableName;
  }

  public boolean isTemporary() {
    return temporary;
  }

  public String buildTableName() {
    return this.isTemporary() ? this.getTableName() + "_tmp" : this.getTableName();
  }

  public UpdatePolicy getUpdatePolicy() {
    return updatePolicy;
  }

  public void setUpdatePolicy(UpdatePolicy updatePolicy) {
    this.updatePolicy = updatePolicy;
  }

  // add entry auto id

  protected void addEntrySynchronized(PrimaryKeyEntries primaryValues, Entry<?>... values) {
    this.addEntrySynchronized(false, primaryValues, values);
  }

  protected void addEntry(PrimaryKeyEntries primaryKeyEntries, SyncExecute syncExecute, Entry<?>... values) {
    this.addEntry(false, primaryKeyEntries, syncExecute, values);
  }

  protected final void addEntry(PrimaryKeyEntries primaryKeyEntries, Entry<?>... values) {
    this.addEntry(primaryKeyEntries, () -> {
    }, values);
  }

  // delete entry sync

  protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, Entry<?>... values) {
    Integer id = this.getEntryId(idColumn);
    this.addEntrySynchronized(new PrimaryKeyEntries(new Entry<>(id, idColumn)), values);
    return id;
  }

  // delete entry

  protected void addEntrySynchronized(boolean overrideExisting, PrimaryKeyEntries primaryValues, Entry<?>... values) {
    if (overrideExisting) {
      this.deleteEntrySynchronized(primaryValues.getPrimaryEntries().toArray(new Entry[0]));
    }

    Connection connection = null;
    PreparedStatement ps = null;

    try {
      connection = this.databaseConnector.getConnection();

      ValuesClause valuesClause = new ValuesClause(primaryValues.getPrimaryEntries(), Arrays.asList(values));

      ps = connection.prepareStatement("INSERT INTO " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER + valuesClause.getText() + ";");
      valuesClause.applyValues(ps, 1);
      ps.executeUpdate();
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      closeQuery(connection, ps, null);
    }
  }

  protected void addEntry(boolean overrideExisting, PrimaryKeyEntries primaryKeyEntries, SyncExecute syncExecute,
                          Entry<?>... values) {
    new Thread(() -> {
      addEntrySynchronized(overrideExisting, primaryKeyEntries, values);
      syncExecute.run();
    }).start();
  }

  protected final Integer addEntryWithAutoId(Column<Integer> idColumn, Entry<?>... values) {
    return this.addEntryWithAutoId(idColumn, () -> {
    }, values);
  }

  private int getEntryId(Column<Integer> idColumn, Entry<?>... primaryValues) {
    int id = 1;
    Collection<Integer> ids = this.get(idColumn, primaryValues);
    if (ids == null) {
      ids = new ArrayList<>();
    }
    while (ids.contains(id)) {
      id++;
    }
    return id;
  }

  protected void deleteEntrySynchronized(Entry<?>... entries) {
    WhereClause whereClause = new WhereClause(entries);
    Connection connection = null;
    PreparedStatement ps = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.prepareStatement("DELETE FROM " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER + whereClause.getText() + ";");
      whereClause.applyValues(ps, 1);
      ps.executeUpdate();
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      closeQuery(connection, ps, null);
    }
  }

  protected void deleteEntry(SyncExecute syncExecute, Entry<?>... entries) {
    new Thread(() -> {
      this.deleteEntrySynchronized(entries);
      syncExecute.run();
    }).start();
  }

  protected void deleteEntry(Entry<?>... entries) {
    this.deleteEntry(() -> {
    }, entries);
  }

  protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn,
                                                         PrimaryKeyEntries primaryKeyEntries, Entry<?>... values) {
    Collection<Entry<?>> primaryList = primaryKeyEntries.getPrimaryEntries();
    Entry<?>[] primaryArray = new Entry[primaryList.size()];
    primaryArray = primaryList.toArray(primaryArray);
    Integer id = this.getEntryId(idColumn, primaryArray);
    this.addEntrySynchronized(primaryKeyEntries.with(new Entry<>(id, idColumn)), values);
    return id;
  }

  // set data

  protected final Integer addEntryWithAutoId(Column<Integer> idColumn, SyncExecute syncExecute, Entry<?>... values) {
    Integer id = this.getEntryId(idColumn);
    this.addEntry(new PrimaryKeyEntries(new Entry<>(id, idColumn)), syncExecute, values);
    return id;
  }

  public <Value> Value getFirst(Column<Value> resultColumn, Entry<?>... entries) {
    return this.getFirst(Set.of(resultColumn), entries).get(resultColumn);
  }

  public ColumnMap getFirst(Set<Column<?>> resultColumn, Entry<?>... entries) {
    Set<ColumnMap> result = this.get(resultColumn, entries);
    return result.isEmpty() ? new ColumnMap() : result.iterator().next();
  }

  public <Value> Set<Value> get(Column<Value> resultColumn, Entry<?>... entries) {
    Set<ColumnMap> results = this.get(Set.of(resultColumn), entries);
    Set<Value> values = new HashSet<>();

    for (ColumnMap result : results) {
      values.add(result.get(resultColumn));
    }

    return values;
  }

  protected final Integer getHighestInteger(Column<Integer> resultColumn, Entry<?>... entries) {
    WhereClause whereClause = new WhereClause(entries);

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.prepareStatement("SELECT MAX(`" + resultColumn.getName() + "`) FROM " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER + whereClause.getText() + ";");

      whereClause.applyValues(ps, 1);
      rs = ps.executeQuery();

      Integer value = null;
      if (rs.next()) {
        value = rs.getInt("MAX(`" + resultColumn.getName() + "`)");
        if (rs.wasNull()) {
          return null;
        }
      }
      return value;
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, rs);
    }
    return null;

  }

  protected final Integer getLowestInteger(Column<Integer> resultColumn, Entry<?>... entries) {
    WhereClause whereClause = new WhereClause(entries);

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.prepareStatement("SELECT MIN(`" + resultColumn.getName() + "`) FROM " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER + whereClause.getText() + ";");
      whereClause.applyValues(ps, 1);
      rs = ps.executeQuery();

      Integer value = null;
      if (rs.next()) {
        value = rs.getInt("MIN(`" + resultColumn.getName() + "`)");
        if (rs.wasNull()) {
          return null;
        }
      }
      return value;
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, rs);
    }
    return null;

  }

  public Set<ColumnMap> get(Set<Column<?>> resultColumn, Entry<?>... entries) {
    WhereClause whereClause = new WhereClause(entries);

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    Set<ColumnMap> result = new HashSet<>();

    try {
      connection = this.databaseConnector.getConnection();
      ps = connection.prepareStatement("SELECT " + parseToColumnNameString(resultColumn) + " FROM " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER + whereClause.getText() + ";");

      whereClause.applyValues(ps, 1);
      rs = ps.executeQuery();

      while (rs.next()) {
        ColumnMap map = new ColumnMap();
        for (Column<?> column : resultColumn) {
          map.put(column, column.parseFromResultSet(rs));
        }
        result.add(map);
      }
    } catch (SQLException e) {
      DatabaseManager.getInstance().handleSQLException(e);
    } finally {
      QueryTool.closeQuery(connection, ps, rs);
    }

    return result;
  }

  protected final <Value> void set(Value value, Column<Value> valueColumn, Entry<?>... keys) {
    this.set(value, valueColumn, () -> {
    }, keys);
  }

  protected final <Value> void set(Value value, Column<Value> valueColumn, SyncExecute syncExecute, Entry<?>... keys) {
    new Thread(() -> {
      setSynchronized(value, valueColumn, keys);
      syncExecute.run();
    }).start();
  }

  protected final void set(Set<Entry<?>> values, Entry<?>... keys) {
    new Thread(() -> setSynchronized(values, keys)).start();
  }

  protected final void set(Set<Entry<?>> values, SyncExecute syncExecute, Entry<?>... keys) {
    new Thread(() -> {
      setSynchronized(values, keys);
      syncExecute.run();
    }).start();
  }

  protected final <Value> void setSynchronized(Value value, Column<Value> valueColumn, Entry<?>... keys) {
    this.setSynchronized(Set.of(new Entry<>(value, valueColumn)), keys);
  }

  protected final void setSynchronized(Set<Entry<?>> values, Collection<Entry<?>> keys) {
    this.setSynchronized(values, keys.toArray(new Entry[0]));
  }

  protected final void setSynchronized(Set<Entry<?>> values, Entry<?>... keys) {
    if (values != null) {
      Connection connection = null;
      PreparedStatement ps = null;

      EquationClause equationClause = new EquationClause(values);

      try {
        connection = this.databaseConnector.getConnection();
        if (this.updatePolicy == UpdatePolicy.INSERT_IF_NOT_EXISTS) {
          ValuesClause valuesClause = new ValuesClause(values, Arrays.asList(keys));

          ps = connection.prepareStatement("INSERT INTO " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER + " " + valuesClause.getText() + "ON DUPLICATE KEY UPDATE " + equationClause.getText() + ";");

          int index = valuesClause.applyValues(ps, 1);
          equationClause.applyValues(ps, index);
        } else {
          WhereClause whereClause = new WhereClause(keys);

          ps = connection.prepareStatement("UPDATE " + TABLE_WRAPPER + this.buildTableName() + TABLE_WRAPPER +" SET " + equationClause.getText() + whereClause.getText() + ";");

          int index = equationClause.applyValues(ps, 1);
          whereClause.applyValues(ps, index);
        }
        ps.executeUpdate();
      } catch (SQLException e) {
        DatabaseManager.getInstance().handleSQLException(e);
      } finally {
        closeQuery(connection, ps, null);
      }
    }
  }

  public enum UpdatePolicy {
    INSERT_IF_NOT_EXISTS, DISCARD_IF_NOT_EXISTS
  }

}
