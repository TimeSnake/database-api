package de.timesnake.database.core.game.team;

import de.timesnake.database.util.game.DbGame;
import de.timesnake.database.util.object.DatabaseConnector;

public class DatabaseTeams extends DatabaseConnector {

    public DatabaseTeams(String name, String url, String user, String password) {
        super(name, url, user, password);
    }

    public void addGame(DbGame game) {
        new TeamsTable(this, game.getName()).create();
    }

    public TeamsTable getGameTeams(String gameName) {
        return new TeamsTable(this, gameName);
    }

    public void deleteGameTeams(DbGame game) {
        TeamsTable teamsTable = new TeamsTable(this, game.getName());
        teamsTable.delete();
    }
}
