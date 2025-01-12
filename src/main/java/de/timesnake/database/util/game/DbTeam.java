/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbTeam extends DbCached<DbTeam> {

  @NotCached
  boolean exists();

  @NotNull
  String getName();

  @NotNull
  Integer getPriority();

  @NotCached
  void setPriority(int priority);

  @Nullable
  String getPrefix();

  @NotCached
  void setPrefix(String prefix);

  @Deprecated
  @Nullable
  String getChatColorName();

  @NotCached
  @Deprecated
  void setChatColorName(String chatColorName);

  @Nullable
  ExTextColor getChatColor();

  void setChatColor(ExTextColor color);

  @NotNull
  Float getRatio();

  @NotCached
  void setRatio(float ratio);

  @NotCached
  void setColor(String colorName);

  @NotNull
  String getColorName();

  boolean hasPrivateChat();

  @NotCached
  void setPrivateChat(Boolean privateChat);

  Integer getMinSize();

  @NotCached
  void setMinSize(Integer size);

  @NotNull
  @Override
  DbTeam toLocal();

  @NotNull
  @Override
  DbTeam toDatabase();

  default String parseColor(String colorName) {
    switch (colorName.toUpperCase()) {
      case "AQUA":
        return "AQUA";
      case "BLACK":
        return "BLACK";
      case "BLUE":
        return "BLUE";
      case "FUCHSIA":
        return "FUCHSIA";
      case "GRAY":
        return "GRAY";
      case "GREEN":
        return "GREEN";
      case "LIME":
        return "LIME";
      case "MAROON":
        return "MAROON";
      case "NAVY":
        return "NAVY";
      case "OLIVE":
        return "OLIVE";
      case "ORANGE":
        return "ORANGE";
      case "PURBLE":
        return "PURPLE";
      case "RED":
        return "RED";
      case "SILVER":
        return "SILVER";
      case "TEAL":
        return "TEAL";
      case "WHITE":
        return "WHITE";
      case "YELLOW":
        return "YELLOW";
      default:
    }
    return "WHITE";
  }
}
