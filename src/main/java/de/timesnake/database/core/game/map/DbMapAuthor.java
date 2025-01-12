/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.user.DbUser;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class DbMapAuthor extends KeyedQueryTool {

  protected DbMapAuthor(DatabaseConnector databaseConnector, String tableName, String gameName,
                        String mapName, UUID authorUuid) {
    super(databaseConnector, tableName, true,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
  }

  @NotNull
  public String getMapName() {
    return super.keyEntries.get(Column.Game.MAP_NAME).getValue();
  }

  @NotNull
  public UUID getAuthorUuid() {
    return super.keyEntries.get(Column.Game.MAP_AUTHOR_UUID).getValue();
  }

  public Optional<String> getAuthorName() {
    DbUser user = Database.getUsers().getUser(this.getAuthorUuid());
    return user != null ? Optional.ofNullable(user.getName()) : Optional.empty();
  }
}
