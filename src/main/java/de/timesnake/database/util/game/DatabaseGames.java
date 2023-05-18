/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.core.game.DbGame;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;

public interface DatabaseGames {

  @Nullable
  DbGame getGame(String gameName);

  boolean containsGame(String gameName);

  Collection<String> getGamesName();

  Collection<DbGame> getGames();
}
