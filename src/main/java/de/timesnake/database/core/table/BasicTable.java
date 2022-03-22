package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.*;
import de.timesnake.library.basic.util.Status;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class BasicTable {

    /**
     * Build a new Table:
     * <p>
     * Constructor
     * - define primary column, criteria
     * - add columns
     * <p>
     * Methods
     * - create and backup methods
     * - other: add, remove, get, getAll
     */

    public static final List<String> NOT_ALLOWED_STRINGS = List.of("\"", "'", "`");

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String ENTRY_ARRAY_SPLITTER = ";";

    protected final DatabaseConnector databaseConnector;
    protected final String tableName;

    BasicTable(DatabaseConnector databaseConnector, String tableName) {
        this.databaseConnector = databaseConnector;
        if (tableName != null) {
            this.tableName = tableName;
        } else {
            this.tableName = null;
            System.out.println("[Database] ERROR table name is null");
        }

    }

    public String getTitle() {
        return this.tableName;
    }


    // add entry sync

    protected void addEntrySynchronized(PrimaryEntries primaryValues, TableEntry<?>... values) {
        this.addEntrySynchronized(false, primaryValues, values);
    }

    protected void addEntrySynchronized(boolean overrideExisting, PrimaryEntries primaryValues, TableEntry<?>... values) {
        String columnPrimaryString = primaryValues.getColumnsAsEntry();
        String valuePrimaryString = primaryValues.getValuesAsEntry();

        if (overrideExisting) {
            this.deleteEntrySynchronized(primaryValues.getPrimaryEntries().toArray(new TableEntry[0]));
        }

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO `" + this.tableName + "_tmp`" + " (" + columnPrimaryString + ")" + "VALUES (" + valuePrimaryString + ");");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuery(connection, ps, null);
        }

        if (values.length > 0) {
            this.setSynchronized(Set.of(values), primaryValues.getPrimaryEntries());
        }
    }

    // add entry

    protected void addEntry(PrimaryEntries primaryEntries, SyncExecute syncExecute, TableEntry<?>... values) {
        this.addEntry(false, primaryEntries, syncExecute, values);
    }

    protected void addEntry(boolean overrideExisting, PrimaryEntries primaryEntries, SyncExecute syncExecute, TableEntry<?>... values) {
        new Thread(() -> {
            addEntrySynchronized(overrideExisting, primaryEntries, values);
            syncExecute.run();
        }).start();
    }

    protected final void addEntry(PrimaryEntries primaryEntries, TableEntry<?>... values) {
        this.addEntry(primaryEntries, () -> {
        }, values);
    }


    // add entry auto id sync

    protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, TableEntry<?>... values) {
        Integer id = this.getEntryId(idColumn);
        this.addEntrySynchronized(new PrimaryEntries(new TableEntry<>(id, idColumn)), values);
        return id;
    }

    protected final Integer addEntryWithAutoIdSynchronized(Column<Integer> idColumn, PrimaryEntries primaryEntries, TableEntry<?>... values) {
        List<TableEntry<?>> primaryList = primaryEntries.getPrimaryEntries();
        TableEntry<?>[] primaryArray = new TableEntry[primaryList.size()];
        primaryArray = primaryList.toArray(primaryArray);
        Integer id = this.getEntryId(idColumn, primaryArray);
        this.addEntrySynchronized(primaryEntries.with(new TableEntry<>(id, idColumn)), values);
        return id;
    }

    // add entry auto id

    protected final Integer addEntryWithAutoId(Column<Integer> idColumn, SyncExecute syncExecute, TableEntry<?>... values) {
        Integer id = this.getEntryId(idColumn);
        this.addEntry(new PrimaryEntries(new TableEntry<>(id, idColumn)), syncExecute, values);
        return id;
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


    // delete entry sync

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

    // delete entry

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

    protected final Integer getHighestInteger(Column<Integer> resultColumn, TableEntry<?>... entries) {
        String s = BasicTable.parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT MAX(`" + resultColumn.getName() + "`) FROM `" + this.tableName + "_tmp`" + s + ";");
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
            BasicTable.closeQuery(connection, ps, rs);
        }
        return null;

    }

    protected final Integer getLowestInteger(Column<Integer> resultColumn, TableEntry<?>... entries) {
        String s = BasicTable.parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT MIN(`" + resultColumn.getName() + "`) FROM `" + this.tableName + "_tmp`" + s + ";");
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
            BasicTable.closeQuery(connection, ps, rs);
        }
        return null;

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

    public Set<ColumnMap> get(Set<Column<?>> resultColumn, TableEntry<?>... entries) {
        String s = parseToWhereClause(entries);

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Set<ColumnMap> result = new HashSet<>();

        try {
            ps = connection.prepareStatement("SELECT " + parseToSelectClause(resultColumn) + " FROM `" + this.tableName + "_tmp`" + s + ";");
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
            BasicTable.closeQuery(connection, ps, rs);
        }

        return result;
    }

    // set data

    protected final <Value> void set(Value value, Column<Value> valueColumn, TableEntry<?>... entries) {
        this.set(value, valueColumn, () -> {
        }, entries);
    }

    protected final <Value> void set(Value value, Column<Value> valueColumn, SyncExecute syncExecute, TableEntry<?>... entries) {
        new Thread(() -> {
            setSynchronized(value, valueColumn, entries);
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
                ps = connection.prepareStatement("UPDATE `" + this.tableName + "_tmp` SET " + parseToUpdateClause(values) + whereClause + ";");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeQuery(connection, ps, null);
            }
        }
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


    public static <Value> String parseType(Value value) {
        if (value != null) {
            if (value instanceof String) {
                return replaceNotAllowedStrings(((String) value));
            } else if (value instanceof Integer || value instanceof Float || value instanceof Double || value instanceof Long) {
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
                    Object firstElement = ((Object[]) value)[0];
                    for (Object element : ((Object[]) value)) {
                        if (element != firstElement) {
                            array.append(ENTRY_ARRAY_SPLITTER);
                        }
                        array.append(parseType(element));
                    }
                    return replaceNotAllowedStrings(parseType(array));
                }
            } else if (value instanceof Collection) {
                if (((Collection<?>) value).size() > 0) {
                    StringBuilder list = new StringBuilder();
                    Object firstElement = ((Collection<?>) value).iterator().next();
                    for (Object element : ((Collection<?>) value)) {
                        if (element != firstElement) {
                            list.append(ENTRY_ARRAY_SPLITTER);
                        }
                        list.append(parseType(element));
                    }
                    return replaceNotAllowedStrings(parseType(list));
                }
                return null;
            } else if (value instanceof Date) {
                return replaceNotAllowedStrings(DATE_FORMAT.format(value));
            } else if (value instanceof Object) {
                return replaceNotAllowedStrings(value.toString());
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
        }
        return (Value) string;
    }

    public static String replaceNotAllowedStrings(String value) {
        for (String s : NOT_ALLOWED_STRINGS) {
            value = value.replace(s, "");
        }
        return value;
    }

    static String parseToWhereClause(TableEntry<?>... entries) {
        StringBuilder sb = new StringBuilder();
        if (entries.length != 0) {
            String where = " WHERE ";
            sb.append(where);
            for (TableEntry<?> entry : entries) {
                if (sb.length() != where.length()) {
                    sb.append(" AND ");
                }
                String criteria = BasicTable.parseType(entry.getValue());
                sb.append("`").append(entry.getColumn().getName()).append("`");
                sb.append("=\"").append(criteria).append("\"");
            }
        }
        return sb.toString();
    }

    static String parseToSelectClause(Set<Column<?>> columns) {
        StringBuilder sb = new StringBuilder();
        for (Column<?> column : columns) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append("`").append(column.getName()).append("`");
        }
        return sb.toString();
    }

    static String parseToUpdateClause(TableEntry<?>... values) {
        return parseToUpdateClause(Arrays.asList(values));
    }

    static String parseToUpdateClause(Collection<TableEntry<?>> values) {
        StringBuilder sb = new StringBuilder();
        for (TableEntry<?> value : values) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append("`").append(value.getColumn().getName()).append("`").append("=");

            String val = parseType(value.getValue());
            if (val == null) {
                sb.append("null");
            } else {
                sb.append("\"").append(val).append("\"");
            }

        }
        return sb.toString();
    }

}
