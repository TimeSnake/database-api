/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.network;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class NetworkFileTable extends DefinitionAndQueryTool {

  protected NetworkFileTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Network.FILE_NAME);

    super.addColumn(Column.Network.FILE_PATH);

    this.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  protected void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  public void addNetworkFile(String name, File filePath) {
    super.set(filePath, Column.Network.FILE_PATH, new Entry<>(name, Column.Network.FILE_NAME));
  }

  @NotNull
  public DbNetworkFile getNetworkFile(String name) {
    return new DbNetworkFile(this.databaseConnector, this.tableName, name);
  }

  public List<DbNetworkFile> getNetworkFiles() {
    List<DbNetworkFile> networkFiles = new LinkedList<>();

    for (String name : super.get(Column.Network.FILE_NAME)) {
      networkFiles.add(this.getNetworkFile(name));
    }

    return networkFiles;
  }
}
