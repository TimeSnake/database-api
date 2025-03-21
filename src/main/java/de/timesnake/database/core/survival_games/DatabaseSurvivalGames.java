/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.survival_games;

import de.timesnake.database.util.hungergames.DbHungerGamesItem;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DatabaseSurvivalGames extends DatabaseConnector implements
    de.timesnake.database.util.hungergames.DatabaseHungerGames {

  private final SurvivalGamesItemTable itemsTable;

  public DatabaseSurvivalGames(String name, String url, String options, String user, String password,
                               String itemsTableName) {
    super(name, url, options, user, password);

    this.itemsTable = new SurvivalGamesItemTable(this, itemsTableName);
  }

  @Override
  public void createTables() {
    this.itemsTable.create();
  }

  @Override
  public void saveTables() {
    this.itemsTable.save();
  }

  @Override
  public Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems() {
    return this.itemsTable.getItems();
  }

  @Override
  public Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems(
      Integer level) {
    return this.itemsTable.getItems(level);
  }

  @NotNull
  @Override
  public DbHungerGamesItem getItem(int id) {
    return this.itemsTable.getItem(id);
  }

  @Override
  public void addItem(String type, Integer amount, Float chance, Integer level) {
    this.itemsTable.addItem(type, amount, chance, level);
  }

  @Override
  public void removeItem(String type, Integer amount) {
    this.itemsTable.removeItem(type, amount);
  }

  @Override
  public void removeItem(int id) {
    this.itemsTable.removeItem(id);
  }

  @Nullable
  @Override
  public Integer getId(String type, Integer amount) {
    return this.itemsTable.getId(type, amount);
  }


}
