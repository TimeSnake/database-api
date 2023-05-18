/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class UserQuestTable extends TableDDL {

  protected UserQuestTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.BOOK_ID,
        Column.Story.CHAPTER_NAME);
    super.addColumn(Column.Story.QUEST_NAME);
  }

  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  @Nullable
  public Set<Integer> getBookIds(UUID uuid) {
    return super.get(Column.Story.BOOK_ID, new Entry<>(uuid, Column.Story.USER_UUID));
  }

  @Nullable
  public Set<String> getChapterIds(UUID uuid, Integer bookId) {
    return super.get(Column.Story.CHAPTER_NAME, new Entry<>(bookId, Column.Story.BOOK_ID),
        new Entry<>(uuid, Column.Story.USER_UUID));
  }

  @Nullable
  public String getQuestName(UUID uuid, Integer storyId, String chapterName) {
    return super.getFirst(Column.Story.QUEST_NAME, new Entry<>(storyId, Column.Story.BOOK_ID),
        new Entry<>(chapterName, Column.Story.CHAPTER_NAME),
        new Entry<>(uuid, Column.Story.USER_UUID));
  }

  public void setQuestName(UUID uuid, Integer bookId, String chapterName, String questName) {
    if (super.getFirst(Column.Story.QUEST_NAME, new Entry<>(bookId, Column.Story.BOOK_ID),
        new Entry<>(chapterName, Column.Story.CHAPTER_NAME),
        new Entry<>(uuid, Column.Story.USER_UUID)) != null) {
      super.set(questName, Column.Story.QUEST_NAME, new Entry<>(bookId, Column.Story.BOOK_ID),
          new Entry<>(chapterName, Column.Story.CHAPTER_NAME),
          new Entry<>(uuid, Column.Story.USER_UUID));
    } else {
      this.addStoryUser(uuid, bookId, chapterName, questName);
    }
  }

  public void addStoryUser(UUID uuid, Integer bookId, String chapterName, String questName) {
    super.addEntry(new PrimaryEntries(new Entry<>(bookId, Column.Story.BOOK_ID),
            new Entry<>(chapterName, Column.Story.CHAPTER_NAME),
            new Entry<>(uuid, Column.Story.USER_UUID)),
        new Entry<>(questName, Column.Story.QUEST_NAME));
  }

  public void removeStoryUser(UUID uuid, Integer bookId, String chapterName) {
    super.deleteEntry(new Entry<>(uuid, Column.Story.USER_UUID), new Entry<>(bookId,
        Column.Story.BOOK_ID), new Entry<>(chapterName, Column.Story.CHAPTER_NAME));
  }
}
