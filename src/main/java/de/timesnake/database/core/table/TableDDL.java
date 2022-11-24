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
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;

import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TableDDL extends Table {

    protected final List<Column<?>> primaryColumns;
    private final String primaryColumnsCreation;
    private List<Column<?>> columns = new ArrayList<>();

    protected TableDDL(DatabaseConnector databaseConnector, String tableName, Column<?>... primaryColumns) {
        this(databaseConnector, tableName, Arrays.stream(primaryColumns).toList());
    }

    protected TableDDL(DatabaseConnector databaseConnector, String tableName, List<Column<?>> primaryColumns) {
        super(databaseConnector, tableName);
        this.primaryColumns = primaryColumns;
        if (this.primaryColumns.size() == 1) {
            this.primaryColumnsCreation =
                    " `" + this.primaryColumns.get(0).getName() + "` " +
                            this.primaryColumns.get(0).getType().getName() + " PRIMARY KEY";
        } else {
            StringBuilder primaryList = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (Column<?> column : this.primaryColumns) {
                sb.append(column.getName());
                sb.append(" ").append(column.getType().getName());
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

    public String getTitle() {
        return this.tableName;
    }

    protected void create() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.createStatement();

            ps.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                    " (" + this.primaryColumnsCreation + ");");

            Database.LOGGER.info("[" + this.tableName + "] Table " + this.tableName + " " +
                    "created with primary-keys: " + primaryColumnsCreation);


            Set<String> primaryColumnNames = this.primaryColumns.stream().map(Column::getName).collect(Collectors.toSet());

            Map<String, Column<?>> columnByName = this.columns.stream().collect(Collectors.toMap(Column::getName, Function.identity()));

            rs = ps.executeQuery("SELECT * FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER);
            ResultSetMetaData meta = rs.getMetaData();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String name = meta.getColumnName(i);
                String type = meta.getColumnTypeName(i);
                int size = meta.getColumnDisplaySize(i);

                if (primaryColumnNames.contains(name)) {
                    continue;
                }

                Column<?> column = columnByName.remove(name);

                // check if column should exist, if not drop it, else check type
                if (column != null) {
                    // check column type
                    if (!column.getType().getSimpleName().equalsIgnoreCase(type) || column.getType().getLength() != size) {
                        ps.executeUpdate("ALTER TABLE " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                                " MODIFY " + COLUMN_WRAPPER + column.getName() + COLUMN_WRAPPER + " " + column.getType().getName() + ";");
                    }
                } else {
                    ps.executeUpdate("ALTER TABLE " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                            " DROP COLUMN " + COLUMN_WRAPPER + name + COLUMN_WRAPPER + ";");
                }
            }

            Column<?> columnBefore = this.primaryColumns.get(this.primaryColumns.size() - 1);

            for (Column<?> column : columns) {
                // add new column
                if (columnByName.remove(column.getName()) != null) {
                    ps.executeUpdate("ALTER TABLE " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                            " ADD COLUMN " + COLUMN_WRAPPER + column.getName() + COLUMN_WRAPPER + " " + column.getType().getName() +
                            " AFTER " + COLUMN_WRAPPER + columnBefore.getName() + COLUMN_WRAPPER + ";");
                }

                // update unique column
                if (column.getType().isUnique()) {
                    /*
                    try {
                        ps.executeUpdate("DROP INDEX " + COLUMN_WRAPPER + column.getName() + COLUMN_WRAPPER + " ON " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + ";");
                    } catch (SQLSyntaxErrorException ignored) {
                    }


                    System.out.println(this.tableName + " " + column.getName());
                    ps.executeUpdate("ALTER TABLE " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                            " ADD CONSTRAINT " + COLUMN_WRAPPER + column.getName() + COLUMN_WRAPPER + " UNIQUE(" + COLUMN_WRAPPER + column.getName() + COLUMN_WRAPPER + ");");

                     */
                }

                columnBefore = column;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, rs);
        }

        this.createTmp();
        this.loadTempData();

    }

    protected void createTmp() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;
        try {
            ps = connection.createStatement();
            ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);
            ps.addBatch("CREATE TABLE IF NOT EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER +
                    " LIKE " + this.tableName + "");
            ps.addBatch("ALTER TABLE " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + " ENGINE=memory");
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    protected void delete() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;

        try {
            ps = connection.createStatement();
            ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER);
            ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    private void loadTempData() {

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER +
                    " SELECT * FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + ";");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    protected void backup() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;

        try {
            ps = connection.createStatement();
            ps.addBatch("DELETE FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER);
            ps.addBatch("INSERT INTO " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                    " SELECT * FROM " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);
            ps.addBatch("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER);

            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }
    }

    protected void backup(Column<?>[] columns) {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;

        try {
            ps = connection.createStatement();
            ps.addBatch("DELETE FROM " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER + ";");
            ps.addBatch("INSERT INTO " + TABLE_WRAPPER + this.tableName + TABLE_WRAPPER +
                    " (" + this.getColumnsAsString(columns) + ") SELECT " + this.getColumnsAsString(columns) +
                    " FROM " + TABLE_WRAPPER + this.tableName + "_tmp" + TABLE_WRAPPER + ";");
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
        }

        this.dropTmpTable();
    }

    protected void dropTmpTable() {
        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("DROP TABLE IF EXISTS " + TABLE_WRAPPER + this.tableName + "_tmp" +
                    TABLE_WRAPPER + ";");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Table.closeQuery(connection, ps, null);
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
