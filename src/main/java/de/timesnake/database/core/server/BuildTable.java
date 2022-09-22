package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class BuildTable extends TaskTable<DbBuildServer> {

    private final BuildWorldTable buildWorldTable;

    public BuildTable(DatabaseConnector databaseConnector, String nameTable, BuildWorldTable buildWorldTable) {
        super(databaseConnector, nameTable);
        this.buildWorldTable = buildWorldTable;
    }

    @Nullable
    @Override
    public DbBuildServer getServer(int port) {
        DbBuildServer server = new DbBuildServer(this.databaseConnector, port, this.tableName, this.buildWorldTable);
        return server.exists() ? server : null;
    }

    @Override
    public void backup() {
        super.dropTmpTable();
    }
}
