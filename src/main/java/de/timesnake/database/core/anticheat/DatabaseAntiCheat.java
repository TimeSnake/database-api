/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.anticheat;

import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;

public class DatabaseAntiCheat extends DatabaseConnector implements de.timesnake.database.util.anticheat.DatabaseAntiCheat {

  private final ChatBlackListTable chatBlackListTable;

  public DatabaseAntiCheat(String name, String url, String options, String user, String password, String chatBlackListTableName) {
    super(name, url, options, user, password);

    this.chatBlackListTable = new ChatBlackListTable(this, chatBlackListTableName);
  }

  @Override
  public void createTables() {
    this.chatBlackListTable.create();
  }

  @Override
  public void saveTables() {
    this.chatBlackListTable.save();
  }

  @Override
  public Collection<String> getWordList() {
    return this.chatBlackListTable.getWordList();
  }

  @Override
  public void addWord(String word) {
    this.chatBlackListTable.addWord(word);
  }
}
