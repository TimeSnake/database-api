package de.timesnake.database.core.network;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.LinkedList;
import java.util.List;

public class NetworkFilesTable extends TableDDL {

    protected NetworkFilesTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Network.FILE_NAME);

        super.addColumn(Column.Network.FILE_PATH);
    }

    @Override
    protected void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public DbNetworkFile getNetworkFile(String name) {
        return new DbNetworkFile(this.databaseConnector, this.tableName, name);
    }

    public List<DbNetworkFile> getNetworkFiles() {
        List<DbNetworkFile> networkFiles = new LinkedList<>();

        for (String name : super.get(Column.Network.FILE_NAME)) {
            networkFiles.add(this.getNetworkFile(name));
        }

        return networkFiles;
    }
}