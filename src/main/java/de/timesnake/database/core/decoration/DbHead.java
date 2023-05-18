/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.decoration;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbHead extends TableQuery implements de.timesnake.database.util.decoration.DbHead {

  protected DbHead(DatabaseConnector databaseConnector, String nameTable, String tag) {
    super(databaseConnector, nameTable, new Entry<>(tag, Column.Decoration.HEAD_TAG));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Decoration.HEAD_TAG) != null;
  }

  @Override
  public void delete() {
    super.deleteWithKey();
  }

  @NotNull
  @Override
  public String getTag() {
    return (String) super.primaryEntries.get(0).getValue();
  }

  @NotNull
  @Override
  public String getName() {
    return super.getFirstWithKey(Column.Decoration.HEAD_NAME);
  }

  @Override
  public void setName(String name) {
    super.setWithKey(name, Column.Decoration.HEAD_NAME);
  }

  @NotNull
  @Override
  public String getSection() {
    return super.getFirstWithKey(Column.Decoration.HEAD_SECTION);
  }

  @Override
  public void setSection(String section) {
    super.setWithKey(section, Column.Decoration.HEAD_SECTION);
  }
}
