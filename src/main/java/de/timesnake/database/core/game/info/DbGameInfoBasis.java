/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.Game;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

public class DbGameInfoBasis extends TableQuery implements
    de.timesnake.database.util.game.DbGameInfoBasis {

  public DbGameInfoBasis(DatabaseConnector databaseConnector, String nameTable, String gameName) {
    super(databaseConnector, nameTable, new Entry<>(gameName, Column.Game.NAME));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Game.NAME) != null;
  }

  @NotNull
  @Override
  public String getName() {
    return (String) super.primaryEntries.get(0).getValue();
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return super.getFirstWithKey(Column.Game.DISPLAY_NAME);
  }

  @Override
  public void setDisplayName(String displayName) {
    super.setWithKey(displayName, Column.Game.DISPLAY_NAME);
  }

  @Override
  @Deprecated
  @NotNull
  public String getChatColorName() {
    return this.getTextColor().toString();
  }

  @Override
  @Deprecated
  public void setChatColorName(String chatColorName) {
    this.setTextColor(ExTextColor.NAMES.value(chatColorName));
  }

  @NotNull
  @Override
  public ExTextColor getTextColor() {
    return super.getFirstWithKey(Column.Game.TEXT_COLOR);
  }

  @Override
  public void setTextColor(ExTextColor color) {
    super.setWithKey(color, Column.Game.TEXT_COLOR);
  }

  @NotNull
  @Override
  public String getItemName() {
    return super.getFirstWithKey(Column.Game.ITEM);
  }

  @Override
  public void setItem(String itemName) {
    super.setWithKey(itemName, Column.Game.ITEM);
  }

  @NotNull
  @Override
  public String getHeadLine() {
    return super.getFirstWithKey(Column.Game.HEAD_LINE);
  }

  @Override
  public void setHeadLine(String headLine) {
    super.setWithKey(headLine, Column.Game.HEAD_LINE);
  }

  @NotNull
  @Override
  public Integer getSlot() {
    return super.getFirstWithKey(Column.Game.SLOT);
  }

  @Override
  public void setSlot(int slot) {
    super.setWithKey(slot, Column.Game.SLOT);
  }

  @Override
  public boolean isEnabled() {
    return super.getFirstWithKey(Game.ENABLED);
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setWithKey(enabled, Game.ENABLED);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGameInfoBasis toDatabase() {
    return this;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGameInfoBasis toLocal() {
    return new DbCachedGameInfoBasis(this);
  }

}
