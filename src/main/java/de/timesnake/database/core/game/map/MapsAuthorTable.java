/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public class MapsAuthorTable extends TableDDL {

  protected MapsAuthorTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.MAP_NAME, Column.Game.MAP_AUTHOR_UUID);
  }

  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  public void delete() {
    super.delete();
  }

  public void addMapAuthor(String mapName, UUID authorUuid) {
    super.addEntry(new PrimaryEntries(new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID)));
  }

  public void removeMapAuthor(String mapName, UUID authorUuid) {
    super.deleteEntry(new Entry<>(mapName, Column.Game.MAP_NAME), new Entry<>(authorUuid,
        Column.Game.MAP_AUTHOR_UUID));
  }

  @NotNull
  public DbMapAuthor getMapAuthor(String mapName, UUID authorUuid) {
    return new DbMapAuthor(this.databaseConnector, this.tableName, mapName, authorUuid);
  }

  public Collection<UUID> getAuthorUuids(String mapName) {
    return super.get(Column.Game.MAP_AUTHOR_UUID, new Entry<>(mapName, Column.Game.MAP_NAME));
  }

  public Collection<DbMapAuthor> getAuthors(String mapName) {
    ArrayList<DbMapAuthor> authors = new ArrayList<>();
    for (UUID author : super.get(Column.Game.MAP_AUTHOR_UUID,
        new Entry<>(mapName, Column.Game.MAP_NAME))) {
      authors.add(new DbMapAuthor(this.databaseConnector, this.tableName, mapName, author));
    }
    return authors;
  }

  public void removeMapAuthors(String mapName) {
    super.deleteEntry(new Entry<>(mapName, Column.Game.MAP_NAME));
  }
}
