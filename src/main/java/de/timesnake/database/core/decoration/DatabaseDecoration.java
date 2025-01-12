/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.decoration;

import de.timesnake.database.util.decoration.DbHead;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DatabaseDecoration extends DatabaseConnector implements
    de.timesnake.database.util.decoration.DatabaseDecoration {

  private final HeadTable headTable;

  public DatabaseDecoration(String name, String url, String options, String user, String password,
      String headsTableName) {
    super(name, url, options, user, password);

    this.headTable = new HeadTable(this, headsTableName);
  }

  @Override
  public void createTables() {
    this.headTable.create();
  }

  @Override
  public void saveTables() {
    this.headTable.save();
  }

  @Nullable
  @Override
  public DbHead getHead(String tag) {
    return this.headTable.getHead(tag);
  }

  @Override
  public boolean containsHead(String tag) {
    return this.headTable.containsHead(tag);
  }

  @Override
  public boolean addHead(String tag, String name, String section) {
    return this.headTable.addHead(tag, name, section);
  }

  @Override
  public boolean removeHead(String tag) {
    return this.headTable.removeHead(tag);
  }

  @NotNull
  @Override
  public Collection<String> getHeadTags() {
    return this.headTable.getHeadTags();
  }

  @NotNull
  @Override
  public Collection<String> getHeadTags(String section) {
    return this.headTable.getHeadTags(section);
  }

  @NotNull
  @Override
  public Collection<DbHead> getHeads() {
    return this.headTable.getHeads();
  }

  @NotNull
  @Override
  public Collection<DbHead> getHeads(String section) {
    return this.headTable.getHeads(section);
  }

  @NotNull
  @Override
  public Collection<String> getSections() {
    return this.headTable.getSections();
  }

}
