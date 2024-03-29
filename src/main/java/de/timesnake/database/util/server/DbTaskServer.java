/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import org.jetbrains.annotations.Nullable;

public interface DbTaskServer extends DbServer {

  @Nullable
  String getTask();

  void setTask(String task);

  void setTaskSynchronized(String task);
}
