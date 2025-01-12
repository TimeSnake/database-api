/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.network;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;

import java.io.File;

public class DbNetworkFile extends KeyedQueryTool implements
    de.timesnake.database.util.network.DbNetworkFile {

  protected DbNetworkFile(DatabaseConnector databaseConnector, String nameTable, String name) {
    super(databaseConnector, nameTable, true, new Entry<>(name, Column.Network.FILE_NAME));
  }

  @Override
  public boolean exists() {
    return this.getFile() != null;
  }

  @Override
  public File getFile() {
    return super.getFirstWithKey(Column.Network.FILE_PATH);
  }
}
