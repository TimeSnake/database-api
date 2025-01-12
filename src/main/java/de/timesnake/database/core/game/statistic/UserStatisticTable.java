/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.statistic;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class UserStatisticTable extends DefinitionAndQueryTool {

  protected UserStatisticTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.STAT_USER_UUID, Column.Game.STAT_USER_TYPE);
    super.addColumn(Column.Game.STAT_USER_VALUE_QUARTER);
    super.addColumn(Column.Game.STAT_USER_VALUE_ALL_TIME);

    super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @Override
  public void delete() {
    super.delete();
  }

  @NotNull
  public GameUserStatistic getStatistic(String gameName, UUID uuid) {
    return new GameUserStatistic(this.databaseConnector, this.tableName, gameName, uuid);
  }

  public List<GameUserStatistic> getStatistics(String gameName) {
    return super.get(Column.Game.STAT_USER_UUID, new Entry<>(gameName, Column.Game.GAME_NAME)).stream()
        .map(uuid -> this.getStatistic(gameName, uuid))
        .collect(Collectors.toList());
  }

  public <Value> Map<UUID, Value> getStatOfUsers(String gameName, StatPeriod period, StatType<Value> stat) {
    HashMap<UUID, Value> result = new HashMap<>();
    Column<String> valueColumn = GameUserStatistic.getPeriodColumn(period);

    Set<ColumnMap> maps = super.get(Set.of(Column.Game.STAT_USER_UUID, valueColumn),
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(stat.getName(), Column.Game.STAT_USER_TYPE));

    for (ColumnMap map : maps) {
      UUID uuid = map.get(Column.Game.STAT_USER_UUID);
      Value value = stat.valueOf(map.get(valueColumn));

      result.put(uuid, value);
    }

    return result;
  }
}
