/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.Nullable;

public interface DbGroup extends DbGroupBasis {

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
}
