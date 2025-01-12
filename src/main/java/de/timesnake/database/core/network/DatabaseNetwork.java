/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.network;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public class DatabaseNetwork extends DatabaseConnector implements
    de.timesnake.database.util.network.DatabaseNetwork {

  private final NetworkFileTable networkFileTable;
  private final NetworkVariableTable networkVariableTable;

  private final String networkFilesTableName;
  private final String networkVariablesTableName;

  public DatabaseNetwork(String name, String url, String options, String user, String password,
      String networkFilesTableName, String networkVariablesTableName) {
    super(name, url, options, user, password);
    this.networkFilesTableName = networkFilesTableName;
    this.networkVariablesTableName = networkVariablesTableName;
    this.networkFileTable = new NetworkFileTable(this, this.networkFilesTableName);
    this.networkVariableTable = new NetworkVariableTable(this, this.networkVariablesTableName);
  }

  @Override
  public void createTables() {
    networkFileTable.create();
    networkVariableTable.create();
  }

  @Override
  public void saveTables() {
    networkFileTable.save();
    networkVariableTable.save();
  }

  @Override
  public void addNetworkFile(String name, File filePath) {
    this.networkFileTable.addNetworkFile(name, filePath);
  }

  @NotNull
  @Override
  public DbNetworkFile getNetworkFile(String name) {
    return this.networkFileTable.getNetworkFile(name);
  }

  @NotNull
  @Override
  public List<DbNetworkFile> getNetworkFiles() {
    return this.networkFileTable.getNetworkFiles();
  }

  public void setValue(String key, String value) {
    this.networkVariableTable.setValue(key, value);
  }

  @Nullable
  public String getValue(String key) {
    return this.networkVariableTable.getValue(key);
  }

}
