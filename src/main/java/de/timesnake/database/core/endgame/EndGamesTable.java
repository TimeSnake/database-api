package de.timesnake.database.core.endgame;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public class EndGamesTable extends TableDDL {

    protected EndGamesTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.EndGame.PLAYER_UUID);
        super.addColumn(Column.EndGame.PLAYER_NAME);
        super.addColumn(Column.EndGame.SERVER);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public de.timesnake.database.util.endgame.DbEndGameUser getUser(UUID uuid) {
        return new DbEndGameUser(this.databaseConnector, this.tableName, uuid);
    }

    public boolean containsUser(UUID uuid) {
        return super.getFirst(Column.EndGame.PLAYER_NAME) != null;
    }

    public void addUser(UUID uuid, String name, DbNonTmpGameServer server) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID)), new TableEntry<>(name,
                Column.EndGame.PLAYER_NAME), new TableEntry<>(server.getName(), Column.EndGame.SERVER));
    }

    public void removeUser(UUID uuid) {
        super.deleteEntry(new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID));
    }

    public UUID getUserFromServer(DbNonTmpGameServer server) {
        return super.getFirst(Column.EndGame.PLAYER_UUID, new TableEntry<>(server.getName(), Column.EndGame.SERVER));
    }
}
