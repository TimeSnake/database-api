/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.Team;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbTeam extends KeyedQueryTool implements de.timesnake.database.util.game.DbTeam {

  public DbTeam(DatabaseConnector databaseConnector, String nameTable, String gameName, String name) {
    super(databaseConnector, nameTable, true,
        new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(name, Column.Group.NAME));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Group.PRIORITY) != null;
  }

  @NotNull
  @Override
  public String getName() {
    return this.keyEntries.get(Column.Group.NAME).getValue();
  }

  @NotNull
  @Override
  public Integer getPriority() {
    return super.getFirstWithKey(Column.Group.PRIORITY);
  }

  @Override
  public void setPriority(int priority) {
    super.setWithKey(priority, Column.Group.PRIORITY);
  }

  @NotNull
  @Override
  public Float getRatio() {
    return this.getFirstWithKey(Column.Team.RATIO);
  }

  @Override
  public void setRatio(float ratio) {
    this.setWithKey(ratio, Column.Team.RATIO);
  }

  @Nullable
  @Override
  public String getPrefix() {
    return super.getFirstWithKey(Column.Group.PREFIX);
  }

  @Override
  public void setPrefix(String prefix) {
    super.setWithKey(prefix, Column.Group.PREFIX);
  }

  @Override
  @Deprecated
  @Nullable
  public String getChatColorName() {
    ExTextColor color = this.getChatColor();
    return color != null ? color.toString() : null;
  }

  @Override
  @Deprecated
  public void setChatColorName(String chatColorName) {
    super.setWithKey(ExTextColor.NAMES.value(chatColorName), Column.Group.PREFIX_COLOR);
  }

  @Nullable
  @Override
  public ExTextColor getChatColor() {
    return super.getFirstWithKey(Column.Group.PREFIX_COLOR);
  }

  @Override
  public void setChatColor(ExTextColor color) {
    super.setWithKey(color, Column.Group.PREFIX_COLOR);
  }

  @Override
  public void setColor(String colorName) {
    this.setWithKey(this.parseColor(colorName), Column.Team.COLOR);
  }

  @NotNull
  @Override
  public String getColorName() {
    return this.parseColor(this.getFirstWithKey(Column.Team.COLOR));
  }

  @Override
  public boolean hasPrivateChat() {
    return this.getFirstWithKey(Column.Team.PRIVATE_CHAT);
  }

  @Override
  public void setPrivateChat(Boolean privateChat) {
    this.setWithKey(privateChat, Column.Team.PRIVATE_CHAT);
  }

  @Override
  public Integer getMinSize() {
    return this.getFirstWithKey(Team.MIN_SIZE);
  }

  @Override
  public void setMinSize(Integer size) {
    this.setWithKey(size, Team.MIN_SIZE);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTeam toLocal() {
    return new DbCachedTeam(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTeam toDatabase() {
    return this;
  }

}
