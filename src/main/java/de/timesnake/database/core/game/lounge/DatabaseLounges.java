package de.timesnake.database.core.game.lounge;

import de.timesnake.database.util.game.DbLoungeMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;

import java.util.Collection;

public class DatabaseLounges extends DatabaseConnector implements de.timesnake.database.util.game.DatabaseLounges {

    protected DbLoungeMapTable loungeMapTable;
    protected DbLoungeMapDisplayTable loungeMapDisplayTable;

    public DatabaseLounges(String name, String url, String user, String password, String loungeMapTableName,
                           String loungeMapDisplayTableName) {
        super(name, url, user, password);

        this.loungeMapTable = new DbLoungeMapTable(this, loungeMapTableName);
        this.loungeMapDisplayTable = new DbLoungeMapDisplayTable(this, loungeMapDisplayTableName);
    }

    public void createTables() {
        this.loungeMapTable.create();
        this.loungeMapDisplayTable.create();
    }

    public void backupTables() {
        this.loungeMapTable.backup();
        this.loungeMapDisplayTable.backup();
    }

    @Override
    public void addMap(String name, DbLocation spawn) {
        this.loungeMapTable.addMap(name, spawn);
    }

    @Override
    public void removeMap(String name) {
        this.loungeMapTable.removeMap(name);
    }


    @Override
    public boolean containsMap(String name) {
        return this.loungeMapTable.containsMap(name, this.loungeMapDisplayTable);
    }


    @Override
    public de.timesnake.database.util.game.DbLoungeMap getMap(String name) {
        return this.loungeMapTable.getMap(name, this.loungeMapDisplayTable);
    }

    @Override
    public Collection<DbLoungeMap> getMaps() {
        return this.loungeMapTable.getMaps(this.loungeMapDisplayTable);
    }

    @Override
    public Collection<DbLoungeMap> getCachedMaps() {
        return this.loungeMapTable.getCachedMaps(this.loungeMapDisplayTable);
    }
}
