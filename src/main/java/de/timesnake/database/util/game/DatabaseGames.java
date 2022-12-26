/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.core.game.DbGame;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DatabaseGames {

    @Nullable
    DbGame getGame(String gameName);

    boolean containsGame(String gameName);

    Collection<String> getGamesName();

    Collection<DbGame> getGames();
}
