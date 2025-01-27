/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedGameInfo;
import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.game.DbKit;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DbCachedGame implements de.timesnake.database.util.game.DbGame {

  protected DbGame database;
  protected DbCachedGameInfo info;

  protected DbCachedGame(DbGame database, DbCachedGameInfo localInfo) {
    this.database = database;
    this.info = localInfo;
  }

  @Override
  public boolean exists() {
    return this.getInfo().exists();
  }

  protected DbGame getDatabase() {
    return this.database;
  }

  @NotNull
  @Override
  public DbCachedGameInfo getInfo() {
    return this.info;
  }

  @NotNull
  @Override
  public List<Integer> getKitIds() {
    return this.database.getKitIds();
  }

  @Nullable
  @Override
  public DbKit getKit(int id) {
    return this.database.getKit(id);
  }

  @Nullable
  @Override
  public DbKit getKit(String name) {
    return this.database.getKit(name);
  }

  @Override
  public void removeKit(Integer id) {
    this.database.removeKit(id);
  }

  @Override
  public void removeKitSynchronized(Integer id) {
    this.database.removeKitSynchronized(id);
  }

  @Override
  public void addKit(Integer id, String name, String itemType, Collection<String> description)
      throws UnsupportedStringException {
    this.database.addKit(id, name, itemType, description);
  }

  @NotNull
  @Override
  public Collection<DbKit> getKits() {
    return this.database.getKits();
  }

  @Override
  public void addMap(String name) {
    this.database.addMap(name);
  }

  @Override
  public void removeMap(String name) {
    this.database.removeMap(name);
  }

  @Nullable
  @Override
  public DbMap getMap(String mapName) {
    return this.database.getMap(mapName);
  }

  @Override
  public @NotNull Collection<String> getMapNames() {
    return this.database.getMapNames();
  }

  @NotNull
  @Override
  public List<DbMap> getMaps() {
    return this.database.getMaps();
  }

  @NotNull
  @Override
  public List<DbMap> getMaps(Integer players) {
    return this.database.getMaps(players);
  }

  @Override
  public boolean containsMap(String mapName) {
    return this.database.containsMap(mapName);
  }

  @NotNull
  @Override
  public Set<StatType<?>> getStats() {
    return this.database.getStats();
  }

  @Nullable
  @Override
  public StatType<?> getStat(String name) {
    return this.database.getStat(name);
  }

  @Override
  public void addStat(StatType<?> stat) {
    this.database.addStat(stat);
  }

  @Override
  public void removeStat(StatType<?> stat) {
    this.database.removeStat(stat);
  }

  @Nullable
  @Override
  public GameUserStatistic getUserStatistic(UUID uuid) {
    return this.database.getUserStatistic(uuid);
  }

  @NotNull
  @Override
  public List<GameUserStatistic> getUserStatistics() {
    return this.database.getUserStatistics();
  }

  @Override
  @NotNull
  public <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type) {
    return this.database.getStatOfUsers(period, type);
  }

  @Override
  public void updateServerOption(String key, String value) {
    this.database.updateServerOption(key, value);
  }

  @Override
  public void removeServerOption(String key) {
    this.database.removeServerOption(key);
  }

  @Override
  public @Nullable String getServerOptionValue(String key) {
    return this.database.getServerOptionValue(key);
  }

  @Override
  public @NotNull Map<String, String> getServerOptions() {
    return this.database.getServerOptions();
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGame toDatabase() {
    return this.getDatabase();
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGame toLocal() {
    return this.getDatabase().toLocal();
  }
}
