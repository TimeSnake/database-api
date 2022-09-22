package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseKits extends DatabaseConnector {

    public DatabaseKits(String name, String url, String user, String password) {
        super(name, url, user, password);
    }

    @NotNull
    public KitsTable getGameKits(String gameName) {
        return new KitsTable(this, gameName);
    }

    public void deleteGameKits(DbGame game) {
        KitsTable teamsTable = new KitsTable(this, game.getInfo().getName());
        teamsTable.delete();
    }
}
