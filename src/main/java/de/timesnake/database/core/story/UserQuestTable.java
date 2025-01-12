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

public class UserQuestTable extends DefinitionAndQueryTool {

  protected UserQuestTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.BOOK_ID,
        Column.Story.CHAPTER_NAME);
    super.addColumn(Column.Story.QUEST_NAME);
  }

  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @Nullable
  public Set<String> getBookIds(UUID uuid) {
    return super.get(Column.Story.BOOK_ID, new Entry<>(uuid, Column.Story.USER_UUID));
  }

  @Nullable
  public Set<String> getChapterIds(UUID uuid, String bookId) {
    return super.get(Column.Story.CHAPTER_NAME, new Entry<>(bookId, Column.Story.BOOK_ID),
        new Entry<>(uuid, Column.Story.USER_UUID));
  }

  @Nullable
  public String getQuestName(UUID uuid, String storyId, String chapterId) {
    return super.getFirst(Column.Story.QUEST_NAME, new Entry<>(storyId, Column.Story.BOOK_ID),
        new Entry<>(chapterId, Column.Story.CHAPTER_NAME),
        new Entry<>(uuid, Column.Story.USER_UUID));
  }

  public void setQuestName(UUID uuid, String bookId, String chapterId, String questName) {
    if (super.getFirst(Column.Story.QUEST_NAME, new Entry<>(bookId, Column.Story.BOOK_ID),
        new Entry<>(chapterId, Column.Story.CHAPTER_NAME),
        new Entry<>(uuid, Column.Story.USER_UUID)) != null) {
      super.set(questName, Column.Story.QUEST_NAME, new Entry<>(bookId, Column.Story.BOOK_ID),
          new Entry<>(chapterId, Column.Story.CHAPTER_NAME),
          new Entry<>(uuid, Column.Story.USER_UUID));
    } else {
      this.addStoryUser(uuid, bookId, chapterId, questName);
    }
  }

  public void addStoryUser(UUID uuid, String bookId, String chapterId, String questName) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(bookId, Column.Story.BOOK_ID),
            new Entry<>(chapterId, Column.Story.CHAPTER_NAME),
            new Entry<>(uuid, Column.Story.USER_UUID)),
        new Entry<>(questName, Column.Story.QUEST_NAME));
  }

  public void removeStoryUser(UUID uuid, String bookId, String chapterId) {
    super.deleteEntry(new Entry<>(uuid, Column.Story.USER_UUID), new Entry<>(bookId, Column.Story.BOOK_ID),
        new Entry<>(chapterId, Column.Story.CHAPTER_NAME));
  }
}
