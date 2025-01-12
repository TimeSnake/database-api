/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.Team;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class DbCachedTeam implements de.timesnake.database.util.game.DbTeam {

  private final DbTeam database;

  private final String name;
  private int priority;
  private float ratio;
  private String prefix;
  private ExTextColor color;
  private String colorName;
  private Boolean privateChat;
  private Integer minSize;

  public DbCachedTeam(DbTeam team) {
    this.database = team;

    ColumnMap map = team.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.PRIORITY, Column.Group.PREFIX,
        Column.Group.PREFIX_COLOR, Column.Team.RATIO, Column.Team.COLOR,
        Column.Team.PRIVATE_CHAT, Team.MIN_SIZE));

    this.name = map.get(Column.Group.NAME);
    this.priority = map.get(Column.Group.PRIORITY);
    this.prefix = map.get(Column.Group.PREFIX);
    this.color = map.get(Column.Group.PREFIX_COLOR);
    this.ratio = map.get(Column.Team.RATIO);
    this.colorName = map.get(Column.Team.COLOR);
    this.privateChat = map.get(Column.Team.PRIVATE_CHAT);
    this.minSize = map.get(Team.MIN_SIZE);
  }

  @Override
  public boolean exists() {
    return this.database.exists();
  }

  @NotNull
  @Override
  public String getName() {
    return this.name;
  }

  @NotNull
  @Override
  public Integer getPriority() {
    return this.priority;
  }

  @Override
  public void setPriority(int priority) {
    this.priority = priority;
    this.database.setPriority(priority);
  }

  @NotNull
  @Override
  public Float getRatio() {
    return this.ratio;
  }

  @Override
  public void setRatio(float ratio) {
    this.ratio = ratio;
    this.database.setRatio(ratio);
  }

  @Nullable
  @Override
  public String getPrefix() {
    return this.prefix;
  }

  @Override
  public void setPrefix(String prefix) {
    this.prefix = prefix;
    this.database.setPrefix(prefix);
  }

  @Nullable
  @Override
  public String getChatColorName() {
    return this.color.toString();
  }

  @Override
  @Deprecated
  public void setChatColorName(String chatColorName) {
    this.color = ExTextColor.NAMES.value(chatColorName);
    this.database.setChatColor(color);
  }

  @Nullable
  @Override
  public ExTextColor getChatColor() {
    return this.color;
  }

  @Override
  public void setChatColor(ExTextColor color) {
    this.color = color;
    this.database.setChatColor(color);
  }

  @Override
  public void setColor(String colorName) {
    this.colorName = colorName;
    this.database.setColor(colorName);
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
    this.database.setPrivateChat(privateChat);
  }

  @Override
  public Integer getMinSize() {
    return this.minSize;
  }

  @Override
  public void setMinSize(Integer size) {
    this.minSize = size;
    this.database.setMinSize(size);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTeam toLocal() {
    return this.database.toLocal();
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTeam toDatabase() {
    return this.database;
  }
}
