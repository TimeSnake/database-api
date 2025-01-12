/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class UserBoughtTable extends DefinitionAndQueryTool {

  protected UserBoughtTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.BOOK_ID,
        Column.Story.CHAPTER_NAME);
  }

  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @Nullable
  public Set<String> getBoughtChapters(UUID uuid, String bookId) {
    return super.get(Column.Story.CHAPTER_NAME, new Entry<>(uuid, Column.Story.USER_UUID),
        new Entry<>(bookId, Column.Story.BOOK_ID));
  }

  public void addBoughtPart(UUID uuid, String bookId, String chapterId) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(uuid, Column.Story.USER_UUID), new Entry<>(bookId,
        Column.Story.BOOK_ID), new Entry<>(chapterId, Column.Story.CHAPTER_NAME)));
  }

  public void removeBoughtChapter(UUID uuid, String bookId, String chapterId) {
    super.deleteEntry(new Entry<>(uuid, Column.Story.USER_UUID), new Entry<>(bookId, Column.Story.BOOK_ID),
        new Entry<>(chapterId, Column.Story.CHAPTER_NAME));
  }
}
