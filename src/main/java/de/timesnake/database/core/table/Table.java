package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Build a new Table:
 * <p>
 * Constructor
 * - define primary columns
 * - add columns (not primary columns)
 * <p>
 * Methods
 * - create and backup methods
 * - other: add, remove, get, getAll
 */
public class Table extends BasicTable {

    protected final List<Column<?>> primaryColumns;
    private final String primaryColumnsCreation;
    private List<Column<?>> columns = new ArrayList<>();

    protected Table(DatabaseConnector databaseConnector, String tableName, Column<?>... primaryColumns) {
        this(databaseConnector, tableName, List.of(primaryColumns));

    }

    protected Table(DatabaseConnector databaseConnector, String tableName, List<Column<?>> primaryColumns) {
        super(databaseConnector, tableName);
        this.primaryColumns = primaryColumns;
        if (this.primaryColumns.size() == 1) {
            this.primaryColumnsCreation = " `" + this.primaryColumns.get(0).getName() + "` " + this.primaryColumns.get(0).getType().getName() + " PRIMARY KEY";
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
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + this.tableName + "` (" + this.primaryColumnsCreation + ");");
            ps.executeUpdate();

            DatabaseManager.getInstance().broadcast("[Database][" + this.tableName + "] Table " + this.tableName + " created with primary-keys: " + primaryColumnsCreation);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, rs);
        }

        Column<?> columnBefore = this.primaryColumns.get(this.primaryColumns.size() - 1);
        for (Column<?> column : columns) {
            boolean isExisting = false;

            connection = this.databaseConnector.getConnection();
            ps = null;

            try {
                ps = connection.prepareStatement("SHOW COLUMNS FROM `" + this.tableName + "` LIKE '" + column.getName() + "';");
                rs = ps.executeQuery();
                if (rs.next()) {
                    if (rs.getString("Field") != null) {
                        isExisting = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BasicTable.closeQuery(connection, ps, rs);
            }

            //if column exists
            if (isExisting) {
                boolean isTypeEquals = false;

                connection = this.databaseConnector.getConnection();
                ps = null;

                try {
                    ps = connection.prepareStatement("SHOW FIELDS FROM `" + this.tableName + "` WHERE FIELD = '" + column.getName() + "';");
                    isTypeEquals = this.checkColumnType(ps, column);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    BasicTable.closeQuery(connection, ps, rs);
                }

                //if type is not equals
                if (!isTypeEquals) {

                    connection = this.databaseConnector.getConnection();
                    ps = null;

                    try {
                        ps = connection.prepareStatement("ALTER TABLE `" + this.tableName + "` MODIFY COLUMN " + column.getName() + " " + column.getType().getName() + ";");
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return;
                    } finally {
                        BasicTable.closeQuery(connection, ps, rs);
                    }
                }
            }
            //if column not exists
            else {

                connection = this.databaseConnector.getConnection();
                ps = null;

                try {
                    ps = connection.prepareStatement("ALTER TABLE `" + this.tableName + "` ADD COLUMN `" + column.getName() + "` " + column.getType().getName() + " AFTER `" + columnBefore.getName() + "`;");
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    BasicTable.closeQuery(connection, ps, rs);
                }
            }

            columnBefore = column;
        }

        this.createTmp();
        this.loadTempData();

    }

    private boolean checkColumnType(PreparedStatement ps, Column<?> column) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String result = rs.getString("Type");
            if (result.equals(column.getType().getName().toLowerCase())) {
                return true;
            } else if ((result.startsWith("int") || result.startsWith("tinyint")) && column.getType().getName().startsWith("boolean")) {
                return true;
            }
        }
        return false;
    }

    protected void createTmp() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;
        try {
            ps = connection.createStatement();
            ps.addBatch("DROP TABLE IF EXISTS `" + this.tableName + "_tmp`");
            ps.addBatch("CREATE TABLE IF NOT EXISTS `" + this.tableName + "_tmp`" + " LIKE " + this.tableName + "");
            ps.addBatch("ALTER TABLE `" + this.tableName + "_tmp` ENGINE=memory");
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
        }
    }

    protected void delete() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;

        try {
            ps = connection.createStatement();
            ps.addBatch("DROP TABLE IF EXISTS `" + this.tableName + "`");
            ps.addBatch("DROP TABLE IF EXISTS `" + this.tableName + "_tmp`");
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
        }
    }

    private void loadTempData() {

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO `" + this.tableName + "_tmp` SELECT * FROM `" + this.tableName + "`;");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
        }
    }

    protected void createBackup() {

        Connection connection = this.databaseConnector.getConnection();
        Statement ps = null;

        try {
            ps = connection.createStatement();
            ps.addBatch("DELETE FROM `" + this.tableName + "`");
            ps.addBatch("INSERT INTO `" + this.tableName + "` SELECT * FROM `" + this.tableName + "_tmp`");
            ps.addBatch("DROP TABLE IF EXISTS `" + this.tableName + "_tmp`");

            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
        }
    }

    protected void createBackup(Column<?>[] columns) {

        Connection connection = this.databaseConnector.getConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("DELETE FROM `" + this.tableName + "`;");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
        }

        connection = this.databaseConnector.getConnection();
        ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO `" + this.tableName + "` (" + this.getColumnsAsString(columns) + ") SELECT " + this.getColumnsAsString(columns) + " FROM `" + this.tableName + "_tmp`;");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
        }

        connection = this.databaseConnector.getConnection();
        ps = null;

        try {
            ps = connection.prepareStatement("DROP TABLE IF EXISTS `" + this.tableName + "_tmp`;");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicTable.closeQuery(connection, ps, null);
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

    public List<Column<?>> getPrimaryColumns() {
        return primaryColumns;
    }

    protected void setColumns(List<Column<?>> columns) {
        this.columns = columns;
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
