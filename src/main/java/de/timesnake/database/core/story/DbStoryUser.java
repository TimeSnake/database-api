/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.story;

import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class DbStoryUser implements de.timesnake.database.util.story.DbStoryUser {

  private final UUID uuid;
  private final UserBoughtTable boughtTable;
  private final UserQuestTable questTable;

  protected DbStoryUser(UUID uuid, UserBoughtTable boughtTable, UserQuestTable questTable) {
    this.uuid = uuid;
    this.boughtTable = boughtTable;
    this.questTable = questTable;
  }

  @Nullable
  @Override
  public Set<String> getBookIds() {
    return this.questTable.getBookIds(this.uuid);
  }

  @Nullable
  @Override
  public Set<String> getBookIds(String bookId) {
    return this.questTable.getChapterIds(this.uuid, bookId);
  }

  @Nullable
  @Override
  public String getQuestName(String bookId, String chapterId) {
    return this.questTable.getQuestName(this.uuid, bookId, chapterId);
  }

  @Override
  public void setQuestName(String bookId, String chapterId, String questName) {
    this.questTable.setQuestName(this.uuid, bookId, chapterId, questName);
  }

  @Nullable
  @Override
  public Set<String> getBoughtChapters(String bookId) {
    return this.boughtTable.getBoughtChapters(this.uuid, bookId);
  }

  @Override
  public void addBoughtChapter(String bookId, String chapterId) {
    this.boughtTable.addBoughtPart(this.uuid, bookId, chapterId);
  }

  @Override
  public void removeBoughtChapter(String bookId, String chapterId) {
    this.boughtTable.removeBoughtChapter(this.uuid, bookId, chapterId);
  }
}
