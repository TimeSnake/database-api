/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class DbPlayer extends KeyedQueryTool implements
    de.timesnake.database.util.user.DbPlayer {

  protected DbPlayer(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
    super(databaseConnector, nameTable, true, new Entry<>(uuid, Column.User.UUID));
  }

  @Override
  public boolean exists() {
    return super.getFirst(Column.User.UUID) != null;
  }

  @NotNull
  @Override
  public String getName() {
    return super.getFirstWithKey(Column.User.NAME);
  }

  @NotNull
  @Override
  public UUID getUniqueId() {
    return super.keyEntries.get(Column.User.UUID).getValue();
  }

}
