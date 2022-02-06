package de.timesnake.database.core.endgame;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbGameServer;

import java.util.UUID;

public class EndGamesTable extends Table {

    protected EndGamesTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.EndGame.PLAYER_UUID);
        super.addColumn(Column.EndGame.PLAYER_NAME);
        super.addColumn(Column.EndGame.SERVER);
    }

    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    public de.timesnake.database.util.endgame.DbEndGameUser getUser(UUID uuid) {
        return new DbEndGameUser(this.databaseConnector, this.tableName, uuid);
    }

    public boolean containsUser(UUID uuid) {
        return super.getFirst(Column.EndGame.PLAYER_NAME) != null;
    }

    public void addUser(UUID uuid, String name, DbGameServer server) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID)), new TableEntry<>(name, Column.EndGame.PLAYER_NAME), new TableEntry<>(server.getName(), Column.EndGame.SERVER));
    }

    public void removeUser(UUID uuid) {
        super.deleteEntry(new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID));
    }

    public UUID getUserFromServer(DbGameServer server) {
        return super.getFirst(Column.EndGame.PLAYER_UUID, new TableEntry<>(server.getName(), Column.EndGame.SERVER));
    }
}
