package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

import java.util.Set;

public class BuildWorldTable extends TableDDL {

    protected BuildWorldTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Server.NAME, Column.Server.BUILD_WORLD);
        super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    protected void backup() {
        super.dropTmpTable();
    }

    public Set<String> getWorldNames() {
        return super.get(Column.Server.BUILD_WORLD);
    }

    public Set<String> getWorldNames(String serverName) {
        return super.get(Column.Server.BUILD_WORLD, new TableEntry<>(serverName, Column.Server.NAME));
    }

    public String getBuildServer(String worldName) {
        return super.getFirst(Column.Server.NAME, new TableEntry<>(worldName, Column.Server.BUILD_WORLD));
    }

    public void addWorld(String serverName, String worldName) {
        super.set(worldName, Column.Server.BUILD_WORLD, new TableEntry<>(serverName, Column.Server.NAME));
    }

    public void addWorld(String serverName, String worldName, SyncExecute syncExecute) {
        super.set(worldName, Column.Server.BUILD_WORLD, syncExecute, new TableEntry<>(serverName, Column.Server.NAME));
    }

    public void removeWorld(String worldName) {
        super.deleteEntry(new TableEntry<>(worldName, Column.Server.BUILD_WORLD));
    }

    public void removeWorld(String worldName, SyncExecute syncExecute) {
        super.deleteEntry(syncExecute, new TableEntry<>(worldName, Column.Server.BUILD_WORLD));
    }

    public void removeServer(String serverName) {
        super.deleteEntry(new TableEntry<>(serverName, Column.Server.NAME));
    }

    public void removeServer(String serverName, SyncExecute syncExecute) {
        super.deleteEntry(syncExecute, new TableEntry<>(serverName, Column.Server.NAME));
    }

}
