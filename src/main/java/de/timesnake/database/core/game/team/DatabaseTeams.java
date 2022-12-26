/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseTeams extends DatabaseConnector {

    public DatabaseTeams(String name, String url, String options, String user, String password) {
        super(name, url, options, user, password);
    }

    public void addGame(DbGame game) {
        new TeamsTable(this, game.getInfo().getName()).create();
    }

    @NotNull
    public TeamsTable getGameTeams(String gameName) {
        return new TeamsTable(this, gameName);
    }

    public void deleteGameTeams(DbGame game) {
        TeamsTable teamsTable = new TeamsTable(this, game.getInfo().getName());
        teamsTable.delete();
    }
}
