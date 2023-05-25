/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.network;

import de.timesnake.database.util.object.DatabaseConnector;
import java.io.File;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DatabaseNetwork extends DatabaseConnector implements
    de.timesnake.database.util.network.DatabaseNetwork {

  private final NetworkFilesTable networkFilesTable;
  private final NetworkVariablesTable networkVariablesTable;

  private final String networkFilesTableName;
  private final String networkVariablesTableName;

  public DatabaseNetwork(String name, String url, String options, String user, String password,
      String networkFilesTableName, String networkVariablesTableName) {
    super(name, url, options, user, password);
    this.networkFilesTableName = networkFilesTableName;
    this.networkVariablesTableName = networkVariablesTableName;
    this.networkFilesTable = new NetworkFilesTable(this, this.networkFilesTableName);
    this.networkVariablesTable = new NetworkVariablesTable(this, this.networkVariablesTableName);
  }

  @Override
  public void createTables() {
    networkFilesTable.create();
    networkVariablesTable.create();
  }

  @Override
  public void backupTables() {
    networkFilesTable.backup();
    networkVariablesTable.backup();
  }

  @Override
  public void addNetworkFile(String name, File filePath) {
    this.networkFilesTable.addNetworkFile(name, filePath);
  }

  @NotNull
  @Override
  public DbNetworkFile getNetworkFile(String name) {
    return this.networkFilesTable.getNetworkFile(name);
  }

  @NotNull
  @Override
  public List<DbNetworkFile> getNetworkFiles() {
    return this.networkFilesTable.getNetworkFiles();
  }

  public void setValue(String key, String value) {
    this.networkVariablesTable.setValue(key, value);
  }

  @Nullable
  public String getValue(String key) {
    return this.networkVariablesTable.getValue(key);
  }

}
