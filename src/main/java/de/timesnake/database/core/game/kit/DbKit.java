/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.UnsupportedStringException;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public class DbKit extends TableQuery implements de.timesnake.database.util.game.DbKit {

  public DbKit(DatabaseConnector databaseConnector, Integer id, String nameTable) {
    super(databaseConnector, nameTable, new Entry<>(id, Column.Game.KIT_ID));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Game.KIT_ID) != null;
  }

  @NotNull
  @Override
  public Integer getId() {
    return (Integer) super.primaryEntries.get(0).getValue();
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
      if (string.contains(Table.ENTRY_ARRAY_DELIMITER)) {
        throw new UnsupportedStringException(Table.ENTRY_ARRAY_DELIMITER);
      }
    }
    super.setWithKey(new DbStringArrayList(description), Column.Game.KIT_DESCRIPTION);
  }

}
