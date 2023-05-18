/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbGame {

  boolean exists();

  @NotNull
  DbGameInfo getInfo();

  @NotCached
  Collection<Integer> getKitIds();

  @Nullable
  @NotCached
  DbKit getKit(int id);

  @Nullable
  @NotCached
  DbKit getKit(String name);

  @NotCached
  void removeKit(Integer id);

  @NotCached
  void removeKitSynchronized(Integer id);

  @NotCached
  void addKit(Integer id, String name, String itemType, Collection<String> description)
      throws UnsupportedStringException;

  @NotCached
  void addKit(String name, String itemType, Collection<String> description)
      throws UnsupportedStringException;

  @NotCached
  @NotNull
  Collection<DbKit> getKits();

  @NotCached
  void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers,
      String itemName,
      List<String> description, List<String> info, List<String> authors);

  @NotCached
  void removeMap(String name);

  @NotCached
  @NotNull
  DbMap getMap(String mapName);

  @NotCached
  @NotNull
  Collection<DbMap> getMaps();

  @NotCached
  @NotNull
  Collection<DbMap> getMaps(Integer players);

  @NotCached
  boolean containsMap(String mapName);

  @NotNull
  @NotCached
  Set<StatType<?>> getStats();

  @Nullable
  @NotCached
  StatType<?> getStat(String name);

  @NotCached
  void addStat(StatType<?> stat);

  @NotCached
  void removeStat(StatType<?> stat);

  @Nullable
  @NotCached
  GameUserStatistic getUserStatistic(UUID uuid);

  @NotNull
  @NotCached
  Collection<GameUserStatistic> getUserStatistics();

  @NotNull
  @NotCached
  <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type);

  @NotNull
  DbGame toDatabase();

  @NotNull
  DbGame toLocal();
}
