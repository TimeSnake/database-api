/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.Team;
import de.timesnake.database.core.group.DbCachedGroup;
import de.timesnake.database.util.object.ColumnMap;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DbCachedTeam extends DbCachedGroup implements de.timesnake.database.util.game.DbTeam {

  private float ratio;
  private String colorName;
  private Boolean privateChat;
  private Integer minSize;

  public DbCachedTeam(DbTeam team) {
    super(team);

    ColumnMap map = team.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.PREFIX,
        Column.Group.PREFIX_COLOR, Column.Team.RATIO, Column.Team.COLOR,
        Column.Team.PRIVATE_CHAT, Team.MIN_SIZE));

    this.name = map.get(Column.Group.NAME);
    this.prefix = map.get(Column.Group.PREFIX);
    this.color = map.get(Column.Group.PREFIX_COLOR);
    this.ratio = map.get(Column.Team.RATIO);
    this.colorName = map.get(Column.Team.COLOR);
    this.privateChat = map.get(Column.Team.PRIVATE_CHAT);
    this.minSize = map.get(Team.MIN_SIZE);
  }

  @Override
  public DbTeam getDatabase() {
    return (DbTeam) super.getDatabase();
  }

  @NotNull
  @Override
  public Float getRatio() {
    return this.ratio;
  }

  @Override
  public void setRatio(float ratio) {
    this.ratio = ratio;
    this.getDatabase().setRatio(ratio);
  }

  @Override
  public void setColor(String colorName) {
    this.colorName = colorName;
    this.getDatabase().setColor(colorName);
  }

  @NotNull
  @Override
  public String getColorName() {
    return this.colorName;
  }

  @Override
  public boolean hasPrivateChat() {
    return this.privateChat;
  }

  @Override
  public void setPrivateChat(Boolean privateChat) {
    this.privateChat = privateChat;
    this.getDatabase().setPrivateChat(privateChat);
  }

  @Override
  public Integer getMinSize() {
    return this.minSize;
  }

  @Override
  public void setMinSize(Integer size) {
    this.minSize = size;
    this.getDatabase().setMinSize(size);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTeam toLocal() {
    return this.getDatabase().toLocal();
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTeam toDatabase() {
    return this.getDatabase();
  }
}
