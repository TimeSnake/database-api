package de.timesnake.database.util.game;

import de.timesnake.database.core.game.DbGame;

import java.util.Collection;

public interface DatabaseGames {
    DbGame getGame(String gameName);

    boolean containsGame(String gameName);

    Collection<String> getGamesName();

    Collection<DbGame> getGames();
}
