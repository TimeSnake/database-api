/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.core.table.QueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.UnsupportedStringException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class DbKit extends KeyedQueryTool implements de.timesnake.database.util.game.DbKit {

  public DbKit(DatabaseConnector databaseConnector, String tableName, String gameName, Integer id) {
    super(databaseConnector, tableName, true,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(id, Column.Game.KIT_ID));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Game.KIT_ID) != null;
  }

  @NotNull
  @Override
  public Integer getId() {
    return super.keyEntries.get(Column.Game.KIT_ID).getValue();
  }

  @NotNull
  @Override
  public String getName() {
    return super.getFirstWithKey(Column.Game.KIT_NAME);
  }

  @Override
  public void setName(String name) {
    super.setWithKey(name, Column.Game.KIT_NAME);
  }

  @NotNull
  @Override
  public String getItemType() {
    return super.getFirstWithKey(Column.Game.KIT_ITEM);
  }

  @Override
  public void setItemType(String itemType) {
    super.setWithKey(itemType, Column.Game.KIT_ITEM);
  }

  @Override
  public Collection<String> getDescription() {
    return super.getFirstWithKey(Column.Game.KIT_DESCRIPTION);
  }

  @Override
  public void setDescription(Collection<String> description) throws UnsupportedStringException {
    for (String string : description) {
      if (string.contains(QueryTool.ENTRY_ARRAY_DELIMITER)) {
        throw new UnsupportedStringException(QueryTool.ENTRY_ARRAY_DELIMITER);
      }
    }
    super.setWithKey(new DbStringArrayList(description), Column.Game.KIT_DESCRIPTION);
  }

}
