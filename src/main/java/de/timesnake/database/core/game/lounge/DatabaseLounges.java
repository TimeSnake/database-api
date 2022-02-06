package de.timesnake.database.core.game.lounge;

import de.timesnake.database.util.game.DbLoungeMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;

import java.util.Collection;

public class DatabaseLounges extends DatabaseConnector implements de.timesnake.database.util.game.DatabaseLounges {

    protected DbLoungeMapTable loungeMapTable;

    public DatabaseLounges(String name, String url, String user, String password, String loungeMapTableName) {
        super(name, url, user, password);

        this.loungeMapTable = new DbLoungeMapTable(this, loungeMapTableName);
    }

    public void createTables() {
        this.loungeMapTable.create();
    }

    public void backupTables() {
        this.loungeMapTable.backup();
    }

    @Override
    public void addMap(String name, String locName, DbLocation spawn) {
        this.loungeMapTable.addMap(name, locName, spawn);
    }

    @Override
    public void removeMap(String name) {
        this.loungeMapTable.removeMap(name);
    }


    @Override
    public boolean containsMap(String name) {
        return this.loungeMapTable.containsMap(name);
    }


    @Override
    public de.timesnake.database.util.game.DbLoungeMap getMap(String name) {
        return this.loungeMapTable.getMap(name);
    }

    @Override
    public Collection<DbLoungeMap> getMaps() {
        return this.loungeMapTable.getMaps();
    }
}
