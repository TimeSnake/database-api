/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbNonTmpGameInfo extends DbGameInfo implements
    de.timesnake.database.util.game.DbNonTmpGameInfo {

  public DbNonTmpGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
    super(databaseConnector, nameTable, gameName);
  }

  @Override
  public boolean isCreationRequestable() {
    return super.getFirstWithKey(Column.Game.CREATION_REQUESTABLE);
  }

  @Override
  public void setCreationRequestable(Boolean creationRequestable) {
    super.setWithKey(creationRequestable, Column.Game.CREATION_REQUESTABLE);
  }

  @Override
  public boolean isOwnable() {
    return super.getFirstWithKey(Column.Game.OWNABLE);
  }

  @Override
  public void setOwnable(Boolean ownable) {
    super.setWithKey(ownable, Column.Game.OWNABLE);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbNonTmpGameInfo toDatabase() {
    return this;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbNonTmpGameInfo toLocal() {
    return new DbCachedNonTmpGameInfo(this);
  }
}
