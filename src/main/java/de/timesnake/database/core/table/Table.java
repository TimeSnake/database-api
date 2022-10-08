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

package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.*;
import de.timesnake.library.basic.util.Status;
import de.timesnake.library.basic.util.Tuple;
import de.timesnake.library.basic.util.chat.ExTextColor;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.*;

public class Table {

    public static final List<String> NOT_ALLOWED_STRINGS = List.of("\"", "'", "`");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String ENTRY_ARRAY_SPLITTER = ";";

    public static final String TABLE_WRAPPER = "`";
    public static final String COLUMN_WRAPPER = "`";
    public static final String ENTRY_WRAPPER = "\"";

    public static <Value> String parseTypeToDatabaseString(Column<?> column, Value value) {
        String string = parseTypeToString(value);

        if (!column.getType().isWrapped()) {
            return string;
        }

        return string != null ? ENTRY_WRAPPER + string + ENTRY_WRAPPER : "NULL";
    }

    public static <Value> String parseTypeToString(Value value) {
        if (value != null) {
            if (value instanceof String) {
                return replaceNotAllowedStrings(((String) value));
            } else if (value instanceof Integer || value instanceof Float || value instanceof Double
                    || value instanceof Long) {
                return replaceNotAllowedStrings(String.valueOf(value));
            } else if (value instanceof StringBuilder || value instanceof UUID) {
                return replaceNotAllowedStrings(value.toString());
            } else if (value instanceof Boolean) {
                return (Boolean) value ? "1" : "0";
            } else if (value instanceof Status) {
                return replaceNotAllowedStrings(((Status) value).getSimpleName());
            } else if (value instanceof Type) {
                return replaceNotAllowedStrings(((Type) value).getDatabaseValue());
            } else if (value instanceof Object[]) {
                if (((Object[]) value).length != 0) {
                    StringBuilder array = new StringBuilder();
                    for (Object element : ((Object[]) value)) {
                        array.append(parseTypeToString(element)).append(ENTRY_ARRAY_SPLITTER);
                    }
                    array.delete(array.length() - ENTRY_ARRAY_SPLITTER.length() - 1, array.length());
                    return replaceNotAllowedStrings(parseTypeToString(array));
                }
            } else if (value instanceof Collection) {
                if (((Collection<?>) value).size() > 0) {
                    StringBuilder list = new StringBuilder();
                    for (Object element : ((Collection<?>) value)) {
                        list.append(parseTypeToString(element)).append(ENTRY_ARRAY_SPLITTER);
                    }
                    list.delete(list.length() - ENTRY_ARRAY_SPLITTER.length() - 1, list.length());
                    return replaceNotAllowedStrings(parseTypeToString(list));
                }
                return null;
            } else if (value instanceof Date) {
                return replaceNotAllowedStrings(DATE_FORMAT.format(value));
            } else if (value instanceof BlockSide) {
                return ((BlockSide) value).name();
            } else if (value instanceof Object) {
                return replaceNotAllowedStrings(value.toString());
            } else if (value instanceof Color) {
                return ((Color) value).getRed() + ENTRY_ARRAY_SPLITTER + ((Color) value).getGreen() + ENTRY_ARRAY_SPLITTER +
                        ((Color) value).getBlue() + ENTRY_ARRAY_SPLITTER + ((Color) value).getAlpha();
            } else if (value instanceof File) {
                return ((File) value).getAbsolutePath();
            } else if (value instanceof Path) {
                return ((Path) value).toString();
            } else if (value instanceof ExTextColor) {
                return value.toString();
            }
        }
        return null;
    }

    public static <Value> Value parseTypeFromString(Column<Value> column, String string) {
        Class<Value> valueClass = column.getValueClass();

        if (valueClass.equals(Boolean.class)) {
            return string != null ? (Value) Boolean.valueOf(string.equals("1")) : (Value) Boolean.valueOf(false);
        }

        if (string == null) {
            return null;
        }

        if (valueClass.equals(String.class)) {
            return (Value) string;
        } else if (valueClass.equals(Integer.class)) {
            return (Value) Integer.valueOf(string);
        } else if (valueClass.equals(Float.class)) {
            return (Value) Float.valueOf(string);
        } else if (valueClass.equals(Long.class)) {
            return (Value) Long.valueOf(string);
        } else if (valueClass.equals(Double.class)) {
            return (Value) Double.valueOf(string);
        } else if (valueClass.equals(UUID.class)) {
            return (Value) UUID.fromString(string);
        } else if (Status.class.isAssignableFrom(valueClass)) {
            if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.User.class)) {
                return (Value) de.timesnake.library.basic.util.Status.User.parseValue(string);
            } else if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.Server.class)) {
                return (Value) de.timesnake.library.basic.util.Status.Server.parseValue(string);
            } else if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.Permission.class)) {
                return (Value) de.timesnake.library.basic.util.Status.Permission.parseValue(string);
            } else if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.Ticket.class)) {
                return (Value) de.timesnake.library.basic.util.Status.Ticket.parseValue(string);
            }
            return null;
        } else if (Type.class.isAssignableFrom(valueClass)) {
            return (Value) Type.getByDatabaseValue(((Column<? extends Type>) column), string);
        } else if (valueClass.equals(DbIntegerArrayList.class)) {
            DbIntegerArrayList list = new DbIntegerArrayList();
            String[] strings = string.split(ENTRY_ARRAY_SPLITTER);
            for (String s : strings) {
                try {
                    list.add(Integer.parseInt(s));
                } catch (NumberFormatException ignored) {
                }
            }
            return (Value) list;
        } else if (valueClass.equals(DbStringArrayList.class)) {
            return (Value) new ArrayList<>(Arrays.asList(string.split(ENTRY_ARRAY_SPLITTER)));
        } else if (valueClass.equals(String[].class)) {
            return (Value) string.split(ENTRY_ARRAY_SPLITTER);
        } else if (valueClass.equals(Integer[].class)) {
            String[] strings = string.split(ENTRY_ARRAY_SPLITTER);
            Integer[] ints = new Integer[strings.length];
            for (int i = 0; i < strings.length; i++) {
                try {
                    ints[i] = Integer.parseInt(strings[i]);
                } catch (NumberFormatException ignored) {
                }
            }
            return (Value) ints;
        } else if (valueClass.equals(Date.class)) {
            try {
                return (Value) DATE_FORMAT.parse(string);
            } catch (ParseException e) {
                return null;
            }
        } else if (valueClass.equals(BlockSide.class)) {
            return (Value) BlockSide.valueOf(string.toUpperCase());
        } else if (valueClass.equals(Color.class)) {
            String[] rgba = string.replace(" ", "").split(ENTRY_ARRAY_SPLITTER);
            return (Value) new Color(Integer.valueOf(rgba[0]), Integer.valueOf(rgba[1]), Integer.valueOf(rgba[2]),
                    Integer.valueOf(rgba[3]));
        } else if (valueClass.equals(File.class)) {
            return (Value) new File(string);
        } else if (valueClass.equals(Path.class)) {
            return ((Value) Path.of(string));
        } else if (valueClass.equals(ExTextColor.class)) {
            return (Value) ExTextColor.NAMES.value(string);
        }
        return (Value) string;
    }

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

    public static String replaceNotAllowedStrings(String value) {
        for (String s : NOT_ALLOWED_STRINGS) {
            value = value.replace(s, "");
        }
        return value;
    }

    static String parseToWhereClause(TableEntry<?>... entries) {
        return entries.length > 0 ? " WHERE " + parseToEquationString(Arrays.asList(entries), " AND ") : "";
    }

    static String parseToColumnNameString(Collection<Column<?>> columns) {
        StringBuilder sb = new StringBuilder();
        for (Column<?> column : columns) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(COLUMN_WRAPPER).append(column.getName()).append(COLUMN_WRAPPER);
        }
        return sb.toString();
    }

    static String parseToEquationString(Collection<TableEntry<?>> values, String splitter) {
        StringBuilder sb = new StringBuilder();
        for (TableEntry<?> entry : values) {
            if (sb.length() != 0) {
                sb.append(splitter);
            }
            sb.append(COLUMN_WRAPPER).append(entry.getColumn().getName()).append(COLUMN_WRAPPER).append("=");

            sb.append(TableDDL.parseTypeToDatabaseString(entry.getColumn(), entry.getValue()));

        }
        return sb.toString();
    }

    static Tuple<String, String> parseToColumnValueStrings(Collection<TableEntry<?>>... entries) {
        StringBuilder columns = new StringBuilder();
        StringBuilder columnValues = new StringBuilder();

        for (Collection<TableEntry<?>> entryCollection : entries) {
            for (TableEntry<?> entry : entryCollection) {
                columns.append(COLUMN_WRAPPER).append(entry.getColumn().getName()).append(COLUMN_WRAPPER);
                columns.append(", ");

                columnValues.append(parseTypeToDatabaseString(entry.getColumn(), entry.getValue()));
                columnValues.append(", ");
            }
        }

        columns.delete(columns.length() - 2, columns.length());
        columnValues.delete(columnValues.length() - 2, columnValues.length());

        return new Tuple<>(columns.toString(), columnValues.toString());
    }

    protected final DatabaseConnector databaseConnector;
    // add entry auto id sync
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

    protected void addEntrySynchronized(PrimaryEntries primaryValues, TableEntry<?>... values) {
        this.addEntrySynchronized(false, primaryValues, values);
    }

    protected void addEntry(PrimaryEntries primaryEntries, SyncExecute syncExecute, TableEntry<?>... values) {
        this.addEntry(false, primaryEntries, syncExecute, values);
    }

    protected final void addEntry(PrimaryEntries primaryEntries, TableEntry<?>... values) {
        this.addEntry(primaryEntries, () -> {
        }, values);
    }


    // delete entry sync

    protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, TableEntry<?>... values) {
        Integer id = this.getEntryId(idColumn);
        this.addEntrySynchronized(new PrimaryEntries(new TableEntry<>(id, idColumn)), values);
        return id;
    }

    // delete entry

    protected void addEntrySynchronized(boolean overrideExisting, PrimaryEntries primaryValues,
                                        TableEntry<?>... values) {
        if (overrideExisting) {
            this.deleteEntrySynchronized(primaryValues.getPrimaryEntries().toArray(new TableEntry[0]));
        }

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        Tuple<String, String> columnValues = parseToColumnValueStrings(primaryValues.getPrimaryEntries(),
                Arrays.asList(values));

        try {
            ps = connection.prepareStatement("INSERT INTO `" + this.tableName + "_tmp`" + " (" +
                    columnValues.getA() + ")" + "VALUES (" + columnValues.getB() + ");");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuery(connection, ps, null);
        }
    }

    protected void addEntry(boolean overrideExisting, PrimaryEntries primaryEntries, SyncExecute syncExecute,
                            TableEntry<?>... values) {
        new Thread(() -> {
            addEntrySynchronized(overrideExisting, primaryEntries, values);
            syncExecute.run();
        }).start();
    }

    protected final Integer addEntryWithAutoId(Column<Integer> idColumn, TableEntry<?>... values) {
        return this.addEntryWithAutoId(idColumn, () -> {
        }, values);
    }

    private int getEntryId(Column<Integer> idColumn, TableEntry<?>... primaryValues) {
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

    protected void deleteEntrySynchronized(TableEntry<?>... entries) {
        String whereClause = parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("DELETE FROM `" + this.tableName + "_tmp`" + whereClause + ";");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuery(connection, ps, null);
        }
    }

    protected void deleteEntry(SyncExecute syncExecute, TableEntry<?>... entries) {
        new Thread(() -> {
            this.deleteEntrySynchronized(entries);
            syncExecute.run();
        }).start();
    }

    protected void deleteEntry(TableEntry<?>... entries) {
        this.deleteEntry(() -> {
        }, entries);
    }

    protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, PrimaryEntries primaryEntries,
                                                           TableEntry<?>... values) {
        List<TableEntry<?>> primaryList = primaryEntries.getPrimaryEntries();
        TableEntry<?>[] primaryArray = new TableEntry[primaryList.size()];
        primaryArray = primaryList.toArray(primaryArray);
        Integer id = this.getEntryId(idColumn, primaryArray);
        this.addEntrySynchronized(primaryEntries.with(new TableEntry<>(id, idColumn)), values);
        return id;
    }

    // set data

    protected final Integer addEntryWithAutoId(Column<Integer> idColumn, SyncExecute syncExecute,
                                               TableEntry<?>... values) {
        Integer id = this.getEntryId(idColumn);
        this.addEntry(new PrimaryEntries(new TableEntry<>(id, idColumn)), syncExecute, values);
        return id;
    }

    public <Value> Value getFirst(Column<Value> resultColumn, TableEntry<?>... entries) {
        return this.getFirst(Set.of(resultColumn), entries).get(resultColumn);
    }

    public ColumnMap getFirst(Set<Column<?>> resultColumn, TableEntry<?>... entries) {
        Set<ColumnMap> result = this.get(resultColumn, entries);
        return result.isEmpty() ? new ColumnMap() : result.iterator().next();
    }

    public <Value> Set<Value> get(Column<Value> resultColumn, TableEntry<?>... entries) {
        Set<ColumnMap> results = this.get(Set.of(resultColumn), entries);
        Set<Value> values = new HashSet<>();

        for (ColumnMap result : results) {
            values.add(result.get(resultColumn));
        }

        return values;
    }

    protected final Integer getHighestInteger(Column<Integer> resultColumn, TableEntry<?>... entries) {
        String s = Table.parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT MAX(`" + resultColumn.getName() + "`) FROM `" +
                    this.tableName + "_tmp`" + s + ";");
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

    protected final Integer getLowestInteger(Column<Integer> resultColumn, TableEntry<?>... entries) {
        String s = Table.parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT MIN(`" + resultColumn.getName() + "`) FROM `" +
                    this.tableName + "_tmp`" + s + ";");
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

    public Set<ColumnMap> get(Set<Column<?>> resultColumn, TableEntry<?>... entries) {
        String s = parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Set<ColumnMap> result = new HashSet<>();

        try {
            ps = connection.prepareStatement("SELECT " + parseToColumnNameString(resultColumn) + " FROM `" +
                    this.tableName + "_tmp`" + s + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                ColumnMap map = new ColumnMap();
                for (Column<?> column : resultColumn) {
                    map.put(column, parseTypeFromString(column, rs.getString(column.getName())));
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

    protected final <Value> void set(Value value, Column<Value> valueColumn, TableEntry<?>... entries) {
        this.set(value, valueColumn, () -> {
        }, entries);
    }

    protected final <Value> void set(Value value, Column<Value> valueColumn, SyncExecute syncExecute,
                                     TableEntry<?>... entries) {
        new Thread(() -> {
            setSynchronized(value, valueColumn, entries);
            syncExecute.run();
        }).start();
    }

    protected final void set(Set<TableEntry<?>> values, TableEntry<?>... entries) {
        new Thread(() -> setSynchronized(values, entries)).start();
    }

    protected final void set(Set<TableEntry<?>> values, SyncExecute syncExecute, TableEntry<?>... entries) {
        new Thread(() -> {
            setSynchronized(values, entries);
            syncExecute.run();
        }).start();
    }

    protected final <Value> void setSynchronized(Value value, Column<Value> valueColumn, TableEntry<?>... entries) {
        this.setSynchronized(Set.of(new TableEntry<>(value, valueColumn)), entries);
    }

    protected final void setSynchronized(Set<TableEntry<?>> values, Collection<TableEntry<?>> keys) {
        this.setSynchronized(values, keys.toArray(new TableEntry[0]));
    }

    protected final void setSynchronized(Set<TableEntry<?>> values, TableEntry<?>... keys) {
        String whereClause = parseToWhereClause(keys);

        if (values != null) {
            Connection connection = this.databaseConnector.getConnection();
            PreparedStatement ps = null;

            try {
                if (this.updatePolicy == UpdatePolicy.INSERT_IF_NOT_EXISTS) {
                    Tuple<String, String> columnValues = parseToColumnValueStrings(values, Arrays.asList(keys));

                    ps = connection.prepareStatement("INSERT INTO `" + this.tableName + "_tmp` (" +
                            columnValues.getA() + ") VALUES (" + columnValues.getB() + ") " +
                            "ON DUPLICATE KEY UPDATE " + parseToEquationString(values, ", ") + ";");
                } else {
                    ps = connection.prepareStatement("UPDATE `" + this.tableName + "_tmp` SET " +
                            parseToEquationString(values, ", ") + whereClause + ";");
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
