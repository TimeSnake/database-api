/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.survival_games;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbSurvivalGamesItem extends KeyedQueryTool implements
    de.timesnake.database.util.hungergames.DbHungerGamesItem {

  public DbSurvivalGamesItem(DatabaseConnector databaseConnector, String nameTable, Integer itemId) {
    super(databaseConnector, nameTable, true, new Entry<>(itemId, Column.HungerGames.ITEM_ID));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.HungerGames.ITEM_ID) != null;
  }

  @NotNull
  @Override
  public String getType() {
    return super.getFirstWithKey(Column.HungerGames.ITEM_TYPE);
  }

  @NotNull
  @Override
  public Integer getAmount() {
    return super.getFirstWithKey(Column.HungerGames.ITEM_AMOUNT);
  }

  @NotNull
  @Override
  public Float getChance() {
    return super.getFirstWithKey(Column.HungerGames.ITEM_CHANCE);
  }

  @NotNull
  @Override
  public Integer getLevel() {
    return super.getFirstWithKey(Column.HungerGames.ITEM_LEVEL);
  }

}
