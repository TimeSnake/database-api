/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MapAuthorTable extends DefinitionAndQueryTool {

  public MapAuthorTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.MAP_NAME, Column.Game.MAP_AUTHOR_UUID);
  }

  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  public void delete() {
    super.delete();
  }

  public void addMapAuthor(String gameName, String mapName, UUID authorUuid) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID)));
  }

  public void removeMapAuthor(String gameName, String mapName, UUID authorUuid) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID));
  }

  @NotNull
  public DbMapAuthor getMapAuthor(String gameName, String mapName, UUID authorUuid) {
    return new DbMapAuthor(this.databaseConnector, this.tableName, gameName, mapName, authorUuid);
  }

  public Collection<UUID> getAuthorUuids(String gameName, String mapName) {
    return super.get(Column.Game.MAP_AUTHOR_UUID,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME));
  }

  public Collection<DbMapAuthor> getAuthors(String gameName, String mapName) {
    ArrayList<DbMapAuthor> authors = new ArrayList<>();
    for (UUID author : super.get(Column.Game.MAP_AUTHOR_UUID,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME))) {
      authors.add(new DbMapAuthor(this.databaseConnector, this.tableName, gameName, mapName, author));
    }
    return authors;
  }

  public void removeMapAuthors(String gameName, String mapName) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(mapName, Column.Game.MAP_NAME));
  }
}
