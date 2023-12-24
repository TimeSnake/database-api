/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.anticheat;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;

public class ChatBlackListTable extends TableDDL {

  protected ChatBlackListTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.AntiCheat.ID);

    super.addColumn(Column.AntiCheat.WORD);
  }

  @Override
  protected void create() {
    super.create();
  }

  @Override
  protected void backup() {
    super.backup();
  }

  public Collection<String> getWordList() {
    return this.get(Column.AntiCheat.WORD);
  }

  public void addWord(String word) {
    super.addEntryWithAutoId(Column.AntiCheat.ID, new Entry<>(word, Column.AntiCheat.WORD));
  }
}
