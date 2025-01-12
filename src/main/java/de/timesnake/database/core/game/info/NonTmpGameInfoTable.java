/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class NonTmpGameInfoTable extends GameInfoTable {

  public NonTmpGameInfoTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName);
    super.addColumn(Column.Game.CREATION_REQUESTABLE);
    super.addColumn(Column.Game.OWNABLE);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @NotNull
  @Override
  public DbNonTmpGameInfo getGame(String name) {
    return new DbNonTmpGameInfo(this.databaseConnector, this.tableName, name);
  }
}
