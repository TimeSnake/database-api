/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.Team;
import de.timesnake.database.core.group.DbGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbTeam extends DbGroup implements de.timesnake.database.util.game.DbTeam {

  public DbTeam(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, name, nameTable);
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
