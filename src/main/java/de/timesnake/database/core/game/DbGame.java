/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedGameInfo;
import de.timesnake.database.core.game.info.DbGameInfo;
import de.timesnake.database.core.game.kit.KitTable;
import de.timesnake.database.core.game.server_options.GameServerOptionTable;
import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.core.game.statistic.StatisticTable;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.game.DbKit;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.Availability;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DbGame implements de.timesnake.database.util.game.DbGame {

  protected final DatabaseConnector databaseConnector;
  protected final String gameName;
  protected final DbGameInfo info;
  protected final KitTable kitTable;
  protected final StatisticTable statisticTable;
  protected final GameServerOptionTable gameServerOptionTable;

  protected final boolean statistics;
  protected final boolean kits;
  protected final boolean maps;

  protected DbGame(DatabaseConnector databaseConnector, String gameName, DbGameInfo info,
                   KitTable kitTable, StatisticTable statisticTable, GameServerOptionTable gameServerOptionTable) {
    this.databaseConnector = databaseConnector;
    this.gameName = gameName;
    this.info = info;

    this.kitTable = kitTable;
    this.statisticTable = statisticTable;
    this.gameServerOptionTable = gameServerOptionTable;

    this.statistics = this.info.hasStatistics();

    Availability kitsAvailability = this.info.getKitAvailability();
    this.kits = kitsAvailability.equals(Availability.ALLOWED) || kitsAvailability.equals(Availability.REQUIRED);

    Availability mapsAvailability = this.info.getMapAvailability();
    this.maps = mapsAvailability.equals(Availability.ALLOWED) || mapsAvailability.equals(Availability.REQUIRED);
  }

  @NotNull
  @Override
  public DbGameInfo getInfo() {
    return this.info;
  }

  @Override
  public boolean exists() {
    return this.getInfo().exists();
  }

  @NotNull
  @Override
  public List<Integer> getKitIds() {
    if (this.kits) {
      return this.kitTable.getKitIds(this.gameName);
    }
    return new ArrayList<>();
  }

  @Nullable
  @Override
  public DbKit getKit(int id) {
    if (this.kits) {
      return this.kitTable.getKit(this.gameName, id);
    }
    return null;
  }

  @Nullable
  @Override
  public DbKit getKit(String name) {
    if (this.kits) {
      return this.kitTable.getKit(this.gameName, name);
    }
    return null;
  }

  @Override
  public void removeKit(Integer id) {
    if (this.kits) {
      this.kitTable.removeKit(this.gameName, id);
    }
  }

  @Override
  public void removeKitSynchronized(Integer id) {
    if (this.kits) {
      this.kitTable.removeKitSynchronized(this.gameName, id);
    }
  }

  @Override
  public void addKit(Integer id, String name, String itemType, Collection<String> description)
      throws UnsupportedStringException {
    if (this.kits) {
      this.kitTable.addKit(this.gameName, id, name, itemType, description);
    }
  }

  @NotNull
  @Override
  public Collection<DbKit> getKits() {
    Collection<DbKit> kits = new ArrayList<>();
    for (Integer id : this.getKitIds()) {
      DbKit kit = this.getKit(id);
      if (kit != null && kit.exists()) {
        kits.add(kit);
      }
    }
    return kits;
  }

  @Override
  public void addMap(String name) {
    if (this.maps) {
      Database.getGames().addGameMap(this.gameName, name);
    }
  }

  @Override
  public void removeMap(String name) {
    if (this.maps) {
      Database.getGames().removeMap(this.gameName, name);
    }
  }

  @Nullable
  @Override
  public DbMap getMap(String mapName) {
    if (this.maps) {
      return Database.getGames().getMap(this.gameName, mapName);
    }
    return null;
  }

  @NotNull
  public List<DbMap> getMaps(Integer players) {
    if (this.maps) {
      return Database.getGames().getMaps(this.gameName, players);
    }
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public Collection<String> getMapNames() {
    if (this.maps) {
      return Database.getGames().getMapNames(this.gameName);
    }
    return List.of();
  }

  @NotNull
  @Override
  public List<DbMap> getMaps() {
    if (this.maps) {
      return Database.getGames().getMaps(this.gameName);
    }
    return new ArrayList<>();
  }

  @Override
  public boolean containsMap(String mapName) {
    if (this.maps) {
      return Database.getGames().containsMap(this.gameName, mapName);
    }
    return false;
  }

  @NotNull
  @Override
  public Set<StatType<?>> getStats() {
    if (this.statistics) {
      return this.statisticTable.getStats(this.gameName);
    }
    return new HashSet<>();
  }

  @Nullable
  @Override
  public StatType<?> getStat(String name) {
    if (this.statistics) {
      return this.statisticTable.getStat(this.gameName, name);
    }
    return null;
  }

  @Override
  public void addStat(StatType<?> stat) {
    if (this.statistics) {
      this.statisticTable.addStat(this.gameName, stat);
    }
  }

  @Override
  public void removeStat(StatType<?> stat) {
    if (this.statistics) {
      this.statisticTable.removeStat(this.gameName, stat);
    }
  }

  @Nullable
  @Override
  public GameUserStatistic getUserStatistic(UUID uuid) {
    if (this.statistics) {
      return this.statisticTable.getUserStatistic(this.gameName, uuid);
    }
    return null;
  }

  @NotNull
  @Override
  public List<GameUserStatistic> getUserStatistics() {
    if (this.statistics) {
      return this.statisticTable.getUserStatistics(this.gameName);
    }
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type) {
    if (this.statistics) {
      return this.statisticTable.getStatOfUsers(this.gameName, period, type);
    }
    return new HashMap<>();
  }

  @Override
  public void updateServerOption(String key, String value) {
    gameServerOptionTable.updateServerOption(this.gameName, key, value);
  }

  @Override
  public void removeServerOption(String key) {
    gameServerOptionTable.removeServerOption(this.gameName, key);
  }

  @Nullable
  @Override
  public String getServerOptionValue(String key) {
    return gameServerOptionTable.getServerOptionValue(this.gameName, key);
  }

  @NotNull
  @Override
  public Map<String, String> getServerOptions() {
    return gameServerOptionTable.getServerOptions(this.gameName);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGame toDatabase() {
    return this;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGame toLocal() {
    return new DbCachedGame(this, new DbCachedGameInfo(this.getInfo()));
  }
}
