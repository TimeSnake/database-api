/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public abstract class DbTaskServer extends DbServer implements
    de.timesnake.database.util.server.DbTaskServer {

  public DbTaskServer(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, name, nameTable);
  }

  @Nullable
  @Override
  public String getTask() {
    return super.getFirstWithKey(Column.Server.TASK);
  }

  @Override
  public void setTask(String task) {
    super.setWithKey(task, Column.Server.TASK);
  }

  @Override
  public void setTaskSynchronized(String task) {
    super.setWithKeySynchronized(task, Column.Server.TASK);
  }
}
