/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.core.table.QueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.UnsupportedStringException;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KitTable extends DefinitionAndQueryTool {

  public KitTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.KIT_ID);
    super.addColumn(Column.Game.KIT_NAME);
    super.addColumn(Column.Game.KIT_ITEM);
    super.addColumn(Column.Game.KIT_DESCRIPTION);
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

  public List<Integer> getKitIds(String gameName) {
    return new ArrayList<>(super.get(Column.Game.KIT_ID, new Entry<>(gameName, Column.Game.GAME_NAME)));
  }

  public de.timesnake.database.util.game.DbKit getKit(String gameName, int id) {
    return new DbKit(this.databaseConnector, tableName, gameName, id);
  }

  @Nullable
  public de.timesnake.database.util.game.DbKit getKit(String gameName, String name) {
    Integer id = super.getFirst(Column.Game.KIT_ID,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(name, Column.Game.KIT_NAME));
    if (id != null) {
      return this.getKit(gameName, id);
    }
    return null;
  }

  public void removeKit(String gameName, Integer id) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(id, Column.Game.KIT_ID));
  }


  public void removeKitSynchronized(String gameName, Integer id) {
    super.deleteEntrySynchronized(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(id, Column.Game.KIT_ID));
  }

  public void addKit(String gameName, Integer id, String name, String itemType, Collection<String> description)
      throws UnsupportedStringException {
    for (String string : description) {
      if (string.contains(QueryTool.ENTRY_ARRAY_DELIMITER)) {
        throw new UnsupportedStringException(QueryTool.ENTRY_ARRAY_DELIMITER);
      }
    }
    super.addEntry(new PrimaryKeyEntries(new Entry<>(gameName, Column.Game.GAME_NAME),
            new Entry<>(id, Column.Game.KIT_ID)),
        new Entry<>(name, Column.Game.KIT_NAME),
        new Entry<>(itemType, Column.Game.KIT_ITEM),
        new Entry<>(new DbStringArrayList(description), Column.Game.KIT_DESCRIPTION));
  }
}
