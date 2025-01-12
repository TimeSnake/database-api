/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.statistic;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class StatisticTable {

  private final StatisticTypeTable typesTable;
  private final UserStatisticTable userTable;

  public StatisticTable(DatabaseConnector databaseConnector, String typesTableName, String userTableName) {
    this.typesTable = new StatisticTypeTable(databaseConnector, typesTableName);
    this.userTable = new UserStatisticTable(databaseConnector, userTableName);
  }

  public void create() {
    this.typesTable.create();
    this.userTable.create();
  }

  public void save() {
    this.typesTable.save();
    this.userTable.save();
  }

  public void delete() {
    this.typesTable.delete();
    this.userTable.delete();
  }

  public Set<StatType<?>> getStats(String gameName) {
    return typesTable.getStats(gameName);
  }

  @NotNull
  public StatType<?> getStat(String gameName, String name) {
    return typesTable.getStat(gameName, name);
  }

  public void addStat(String gameName, StatType<?> stat) {
    typesTable.addStat(gameName, stat);
  }

  public void removeStat(String gameName, StatType<?> stat) {
    typesTable.removeStat(gameName, stat);
  }

  public List<GameUserStatistic> getUserStatistics(String gameName) {
    return this.userTable.getStatistics(gameName);
  }

  @NotNull
  public GameUserStatistic getUserStatistic(String gameName, UUID uuid) {
    return this.userTable.getStatistic(gameName, uuid);
  }

  public <Value> Map<UUID, Value> getStatOfUsers(String gameName, StatPeriod period, StatType<Value> type) {
    return this.userTable.getStatOfUsers(gameName, period, type);
  }
}
