package de.timesnake.database.core.endgame;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public class DbEndGameUser extends TableQuery implements de.timesnake.database.util.endgame.DbEndGameUser {

    protected DbEndGameUser(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.EndGame.PLAYER_UUID) != null;
    }

    @Override
    public void delete() {
        super.deleteWithKey();
    }

    @Override
    public UUID getUniqueId() {
        return (UUID) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.EndGame.PLAYER_NAME);
    }

    @Override
    public DbNonTmpGameServer getServer() {
        String serverName = super.getFirstWithKey(Column.EndGame.SERVER);
        return serverName == null ? null : Database.getServers().getServer(serverName);
    }

    @Override
    public void setServer(DbNonTmpGameServer server) {
        super.setWithKey(server.getName(), Column.EndGame.SERVER);
    }
}
