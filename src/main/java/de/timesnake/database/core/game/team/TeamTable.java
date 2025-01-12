/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeamTable extends DefinitionAndQueryTool {

  public TeamTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Group.NAME);
    super.addColumn(Column.Group.PRIORITY);
    super.addColumn(Column.Team.RATIO);
    super.addColumn(Column.Group.PREFIX);
    super.addColumn(Column.Group.PREFIX_COLOR);
    super.addColumn(Column.Team.COLOR);
    super.addColumn(Column.Team.PRIVATE_CHAT);
    super.addColumn(Column.Team.MIN_SIZE);
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

  public void addTeam(String gameName, String name, int rank, String prefix, ExTextColor color, float ratio,
                      String colorName) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(gameName, Column.Game.GAME_NAME),
            new Entry<>(name, Column.Group.NAME)),
        new Entry<>(ratio, Column.Team.RATIO),
        new Entry<>(rank, Column.Group.PRIORITY),
        new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR),
        new Entry<>(colorName, Column.Team.COLOR));
  }

  public void removeTeam(String gameName, String name) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(name, Column.Group.NAME));
  }

  @Nullable
  public Integer getHighestRank(String gameName) {
    return super.getHighestInteger(Column.Group.PRIORITY, new Entry<>(gameName, Column.Game.GAME_NAME));
  }

  public boolean containsTeam(String gameName, String name) {
    return super.getFirst(Column.Group.PRIORITY, new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(name, Column.Group.NAME)) != null;
  }

  @NotNull
  public DbTeam getTeam(String gameName, String name) {
    return new DbTeam(this.databaseConnector, this.tableName, gameName, name);
  }

  public Collection<String> getTeamNames(String gameName) {
    return super.get(Column.Group.NAME, new Entry<>(gameName, Column.Game.GAME_NAME));
  }

  public List<de.timesnake.database.util.game.DbTeam> getTeams(String gameName) {
    List<de.timesnake.database.util.game.DbTeam> teams = new ArrayList<>();
    for (String name : this.getTeamNames(gameName)) {
      teams.add(this.getTeam(gameName, name));
    }
    return teams;
  }
}
