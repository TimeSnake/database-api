/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column.Game;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class GameInfoTable extends SimpleGameInfoTable {

  protected GameInfoTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName);
    super.addColumn(Game.MAX_PLAYERS);
    super.addColumn(Game.KITS);
    super.addColumn(Game.MAPS);
    super.addColumn(Game.STATISTICS);
    super.addColumn(Game.TEXTURE_PACK_LINK);
    super.addColumn(Game.TEXTURE_PACK_HASH);
    super.addColumn(Game.OLD_PVP);
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
  public DbGameInfo getGame(String name) {
    return new DbGameInfo(this.databaseConnector, this.tableName, name);
  }
}
