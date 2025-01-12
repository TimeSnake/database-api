/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.survival_games;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class SurvivalGamesItemTable extends DefinitionAndQueryTool {

  public SurvivalGamesItemTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable, Column.HungerGames.ITEM_ID);
    super.addColumn(Column.HungerGames.ITEM_TYPE);
    super.addColumn(Column.HungerGames.ITEM_AMOUNT);
    super.addColumn(Column.HungerGames.ITEM_CHANCE);
    super.addColumn(Column.HungerGames.ITEM_LEVEL);
  }

  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @NotNull
  public de.timesnake.database.util.hungergames.DbHungerGamesItem getItem(int id) {
    return new DbSurvivalGamesItem(this.databaseConnector, this.tableName, id);
  }

  public Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems() {
    ArrayList<de.timesnake.database.util.hungergames.DbHungerGamesItem> items = new ArrayList<>();
    for (int id : this.get(Column.HungerGames.ITEM_ID)) {
      items.add(this.getItem(id));
    }
    return items;
  }

  public Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems(
      Integer level) {
    ArrayList<de.timesnake.database.util.hungergames.DbHungerGamesItem> items = new ArrayList<>();
    for (int i = 0; i <= level; i++) {
      for (int id : this.get(Column.HungerGames.ITEM_ID,
          new Entry<>(i, Column.HungerGames.ITEM_LEVEL))) {
        items.add(this.getItem(id));
      }
    }
    return items;
  }

  public void addItem(String type, Integer amount, Float chance, Integer level) {
    super.addEntryWithAutoIdSynchronized(Column.HungerGames.ITEM_ID, new Entry<>(type,
            Column.HungerGames.ITEM_TYPE), new Entry<>(amount, Column.HungerGames.ITEM_AMOUNT),
        new Entry<>(chance, Column.HungerGames.ITEM_CHANCE), new Entry<>(level,
            Column.HungerGames.ITEM_LEVEL));
  }

  public void removeItem(String type, Integer amount) {
    this.removeItem(this.getId(type, amount));
  }

  public void removeItem(int id) {
    super.deleteEntry(new Entry<>(id, Column.HungerGames.ITEM_ID));
  }

  @Nullable
  public Integer getId(String type, Integer amount) {
    return super.getFirst(Column.HungerGames.ITEM_ID,
        new Entry<>(type, Column.HungerGames.ITEM_TYPE),
        new Entry<>(amount, Column.HungerGames.ITEM_AMOUNT));
  }
}
