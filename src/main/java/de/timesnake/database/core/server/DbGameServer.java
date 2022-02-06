package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;

public class DbGameServer extends DbPvPServer implements de.timesnake.database.util.server.DbGameServer {

    public DbGameServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public String getGameInfo() {
        return super.getFirstWithKey(Column.Server.GAME_INFO);
    }

    @Override
    public void setGameInfo(String info) {
        super.setWithKey(info, Column.Server.GAME_INFO);
    }

    @Override
    public Type.Server getType() {
        return Type.Server.GAME;
    }
}
