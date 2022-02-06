package de.timesnake.database.core.game.kit;

import de.timesnake.database.util.game.DbGame;
import de.timesnake.database.util.object.DatabaseConnector;

public class DatabaseKits extends DatabaseConnector {

    public DatabaseKits(String name, String url, String user, String password) {
        super(name, url, user, password);
    }

    public KitsTable getGameKits(String gameName) {
        return new KitsTable(this, gameName);
    }

    public void deleteGameKits(DbGame game) {
        KitsTable teamsTable = new KitsTable(this, game.getName());
        teamsTable.delete();
    }
}
