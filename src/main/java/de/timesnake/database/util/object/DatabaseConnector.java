package de.timesnake.database.util.object;

import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.core.table.BasicTable;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {

    protected String name;

    protected BasicDataSource ds;

    protected String url;
    private final String user;
    private final String password;

    public DatabaseConnector(String name, String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.name = name;


        this.ds = new BasicDataSource();

        this.ds.setDriverClassName("com.mysql.jdbc.Driver");
        this.ds.setUrl(this.url);
        this.ds.setUsername(this.user);
        this.ds.setPassword(this.password);
        this.ds.setMinIdle(0);
        this.ds.setMaxIdle(10);
        this.ds.setMaxOpenPreparedStatements(100);

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
            BasicTable.closeQuery(connection, ps, null);
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

}
