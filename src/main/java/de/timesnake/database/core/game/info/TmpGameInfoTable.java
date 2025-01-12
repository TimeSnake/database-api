/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class TmpGameInfoTable extends GameInfoTable {

  public TmpGameInfoTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName);
    super.addColumn(Column.Game.AUTO_START_PLAYER_NUMBER);
    super.addColumn(Column.Game.MIN_PLAYER_NUMBER);
    super.addColumn(Column.Game.TEAM_SIZES);
    super.addColumn(Column.Game.TEAM_MERGE);
    super.addColumn(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
    super.addColumn(Column.Game.SHOW_SELECTED_KITS);
    super.addColumn(Column.Game.HIDE_TEAMS);
    super.addColumn(Column.Game.DISCORD_TYPE);
    super.addColumn(Column.Game.DESCRIPTION);
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
  public DbTmpGameInfo getGame(String name) {
    return new DbTmpGameInfo(this.databaseConnector, this.tableName, name);
  }
}
