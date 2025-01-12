/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.server_options;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameServerOptionTable extends DefinitionAndQueryTool {

  public GameServerOptionTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.SERVER_OPTION_KEY);
    super.addColumn(Column.Game.SERVER_OPTION_VALUE);

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

  public void updateServerOption(String gameName, String key, String value) {
    super.set(value, Column.Game.SERVER_OPTION_VALUE, new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(key, Column.Game.SERVER_OPTION_KEY));
  }

  public void removeServerOption(String gameName, String key) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(key, Column.Game.SERVER_OPTION_KEY));
  }

  public String getServerOptionValue(String gameName, String key) {
    return this.getFirst(Column.Game.SERVER_OPTION_VALUE, new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(key, Column.Game.SERVER_OPTION_KEY));
  }

  public Map<String, String> getServerOptions(String gameName) {
    return super.get(Set.of(Column.Game.SERVER_OPTION_KEY, Column.Game.SERVER_OPTION_VALUE),
            new Entry<>(gameName, Column.Game.GAME_NAME)).stream()
        .collect(Collectors.toMap(c -> c.get(Column.Game.SERVER_OPTION_KEY),
            c -> c.get(Column.Game.SERVER_OPTION_VALUE)));
  }
}
