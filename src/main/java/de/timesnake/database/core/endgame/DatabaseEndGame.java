package de.timesnake.database.core.endgame;

import de.timesnake.database.util.endgame.DbEndGameUser;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbGameServer;

import java.util.UUID;

public class DatabaseEndGame extends DatabaseConnector implements de.timesnake.database.util.endgame.DatabaseEndGame {

    private final String usersTableName;
    private final EndGamesTable usersTable;


    public DatabaseEndGame(String name, String url, String user, String password, String usersTableName) {
        super(name, url, user, password);
        this.usersTableName = usersTableName;

        this.usersTable = new EndGamesTable(this, this.usersTableName);
    }

    @Override
    public void createTables() {
        this.usersTable.create();
    }

    @Override
    public void backupTables() {
        this.usersTable.backup();
    }

    @Override
    public DbEndGameUser getUser(UUID uuid) {
        return this.usersTable.getUser(uuid);
    }

    @Override
    public boolean containsUser(UUID uuid) {
        return this.usersTable.containsUser(uuid);
    }

    @Override
    public void addUser(UUID uuid, String name, DbGameServer server) {
        this.usersTable.addUser(uuid, name, server);
    }

    @Override
    public void removeUser(UUID uuid) {
        this.usersTable.removeUser(uuid);
    }

    @Override
    public UUID getUserFromServer(DbGameServer server) {
        return this.usersTable.getUserFromServer(server);
    }


}
