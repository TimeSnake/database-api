/*
 * workspace.database-api.main
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

package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Table {

    public static final String ENTRY_ARRAY_DELIMITER = ";";

    public static final String TABLE_WRAPPER = "`";
    public static final String COLUMN_WRAPPER = "`";

    public static void closeQuery(Connection connection, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    static String parseToColumnNameString(Collection<Column<?>> columns) {
        return columns.stream()
                .map(c -> c.getType().getSelectWrapper(c.getName()))
                .collect(Collectors.joining(", "));
    }

    protected final DatabaseConnector databaseConnector;
    protected final String tableName;
    protected UpdatePolicy updatePolicy;


    Table(DatabaseConnector databaseConnector, String tableName) {
        this.databaseConnector = databaseConnector;
        this.tableName = tableName;
        this.updatePolicy = UpdatePolicy.DISCARD_IF_NOT_EXISTS;

    }

    Table(DatabaseConnector databaseConnector, String tableName, UpdatePolicy updatePolicy) {
        this.databaseConnector = databaseConnector;
        this.tableName = tableName;
        this.updatePolicy = updatePolicy;
    }

    public String getTitle() {
        return this.tableName;
    }

    public UpdatePolicy getUpdatePolicy() {
        return updatePolicy;
    }

    public void setUpdatePolicy(UpdatePolicy updatePolicy) {
        this.updatePolicy = updatePolicy;
    }

    // add entry auto id

    protected void addEntrySynchronized(PrimaryEntries primaryValues, Entry<?>... values) {
        this.addEntrySynchronized(false, primaryValues, values);
    }

    protected void addEntry(PrimaryEntries primaryEntries, SyncExecute syncExecute, Entry<?>... values) {
        this.addEntry(false, primaryEntries, syncExecute, values);
    }

    protected final void addEntry(PrimaryEntries primaryEntries, Entry<?>... values) {
        this.addEntry(primaryEntries, () -> {
        }, values);
    }


    // delete entry sync

    protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, Entry<?>... values) {
        Integer id = this.getEntryId(idColumn);
        this.addEntrySynchronized(new PrimaryEntries(new Entry<>(id, idColumn)), values);
        return id;
    }

    // delete entry

    protected void addEntrySynchronized(boolean overrideExisting, PrimaryEntries primaryValues,
                                        Entry<?>... values) {
        if (overrideExisting) {
            this.deleteEntrySynchronized(primaryValues.getPrimaryEntries().toArray(new Entry[0]));
        }

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        ValuesClause valuesClause = new ValuesClause(primaryValues.getPrimaryEntries(),
                Arrays.asList(values));

        try {
            ps = connection.prepareStatement("INSERT INTO `" + this.tableName + "_tmp`" +
                                             valuesClause.getText() + ";");
            valuesClause.applyValues(ps, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuery(connection, ps, null);
        }
    }

    protected void addEntry(boolean overrideExisting, PrimaryEntries primaryEntries, SyncExecute syncExecute,
                            Entry<?>... values) {
        new Thread(() -> {
            addEntrySynchronized(overrideExisting, primaryEntries, values);
            syncExecute.run();
        }).start();
    }

    protected final Integer addEntryWithAutoId(Column<Integer> idColumn, Entry<?>... values) {
        return this.addEntryWithAutoId(idColumn, () -> {}, values);
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
        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("DELETE FROM `" + this.tableName + "_tmp`" + whereClause.getText() + ";");
            whereClause.applyValues(ps, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, PrimaryEntries primaryEntries,
                                                           Entry<?>... values) {
        List<Entry<?>> primaryList = primaryEntries.getPrimaryEntries();
        Entry<?>[] primaryArray = new Entry[primaryList.size()];
        primaryArray = primaryList.toArray(primaryArray);
        Integer id = this.getEntryId(idColumn, primaryArray);
        this.addEntrySynchronized(primaryEntries.with(new Entry<>(id, idColumn)), values);
        return id;
    }

    // set data

    protected final Integer addEntryWithAutoId(Column<Integer> idColumn, SyncExecute syncExecute,
                                               Entry<?>... values) {
        Integer id = this.getEntryId(idColumn);
        this.addEntry(new PrimaryEntries(new Entry<>(id, idColumn)), syncExecute, values);
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

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT MAX(`" + resultColumn.getName() + "`) FROM `" +
                                             this.tableName + "_tmp`" + whereClause.getText() + ";");
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
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, rs);
        }
        return null;

    }

    protected final Integer getLowestInteger(Column<Integer> resultColumn, Entry<?>... entries) {
        WhereClause whereClause = new WhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT MIN(`" + resultColumn.getName() + "`) FROM `" +
                                             this.tableName + "_tmp`" + whereClause.getText() + ";");
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
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, rs);
        }
        return null;

    }

    public Set<ColumnMap> get(Set<Column<?>> resultColumn, Entry<?>... entries) {
        WhereClause whereClause = new WhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Set<ColumnMap> result = new HashSet<>();

        try {
            ps = connection.prepareStatement("SELECT " + parseToColumnNameString(resultColumn) + " FROM `" +
                                             this.tableName + "_tmp`" + whereClause.getText() + ";");

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
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, rs);
        }

        return result;
    }

    protected final <Value> void set(Value value, Column<Value> valueColumn, Entry<?>... entries) {
        this.set(value, valueColumn, () -> {
        }, entries);
    }

    protected final <Value> void set(Value value, Column<Value> valueColumn, SyncExecute syncExecute,
                                     Entry<?>... entries) {
        new Thread(() -> {
            setSynchronized(value, valueColumn, entries);
            syncExecute.run();
        }).start();
    }

    protected final void set(Set<Entry<?>> values, Entry<?>... entries) {
        new Thread(() -> setSynchronized(values, entries)).start();
    }

    protected final void set(Set<Entry<?>> values, SyncExecute syncExecute, Entry<?>... entries) {
        new Thread(() -> {
            setSynchronized(values, entries);
            syncExecute.run();
        }).start();
    }

    protected final <Value> void setSynchronized(Value value, Column<Value> valueColumn, Entry<?>... entries) {
        this.setSynchronized(Set.of(new Entry<>(value, valueColumn)), entries);
    }

    protected final void setSynchronized(Set<Entry<?>> values, Collection<Entry<?>> keys) {
        this.setSynchronized(values, keys.toArray(new Entry[0]));
    }

    protected final void setSynchronized(Set<Entry<?>> values, Entry<?>... keys) {
        if (values != null) {
            Connection connection = this.databaseConnector.getConnection();
            PreparedStatement ps = null;

            EquationClause equationClause = new EquationClause(values);

            try {
                if (this.updatePolicy == UpdatePolicy.INSERT_IF_NOT_EXISTS) {
                    ValuesClause valuesClause = new ValuesClause(values, Arrays.asList(keys));

                    ps = connection.prepareStatement(
                            "INSERT INTO `" + this.tableName + "_tmp` " +
                            valuesClause.getText() +
                            "ON DUPLICATE KEY UPDATE " +
                            equationClause.getText() +
                            ";");

                    int index = valuesClause.applyValues(ps, 1);
                    equationClause.applyValues(ps, index);
                } else {
                    WhereClause whereClause = new WhereClause(keys);

                    ps = connection.prepareStatement(
                            "UPDATE `" + this.tableName + "_tmp` SET " +
                            equationClause.getText() +
                            whereClause.getText() +
                            ";");

                    int index = equationClause.applyValues(ps, 1);
                    whereClause.applyValues(ps, index);
                }
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeQuery(connection, ps, null);
            }
        }
    }

    public enum UpdatePolicy {
        INSERT_IF_NOT_EXISTS,
        DISCARD_IF_NOT_EXISTS
    }

}
