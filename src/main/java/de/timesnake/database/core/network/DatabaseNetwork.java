package de.timesnake.database.core.network;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class DatabaseNetwork extends DatabaseConnector implements de.timesnake.database.util.network.DatabaseNetwork {

    private final NetworkFilesTable networkFilesTable;

    private final String networkFilesTableName;

    public DatabaseNetwork(String name, String url, String user, String password, String networkFilesTableName) {
        super(name, url, user, password);
        this.networkFilesTableName = networkFilesTableName;
        this.networkFilesTable = new NetworkFilesTable(this, this.networkFilesTableName);
    }

    @Override
    public void createTables() {
        networkFilesTable.create();
    }

    @Override
    public void backupTables() {
        networkFilesTable.backup();
    }

    @Override
    public void addNetworkFile(String name, File filePath) {
        this.networkFilesTable.addNetworkFile(name, filePath);
    }

    @NotNull
    @Override
    public DbNetworkFile getNetworkFile(String name) {
        return this.networkFilesTable.getNetworkFile(name);
    }

    @NotNull
    @Override
    public List<DbNetworkFile> getNetworkFiles() {
        return this.networkFilesTable.getNetworkFiles();
    }
}
