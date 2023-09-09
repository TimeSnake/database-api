/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.story;

import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface DbStoryUser {

  @Nullable
  Set<String> getBookIds();

  @Nullable
  Set<String> getBookIds(String bookId);

  @Nullable
  String getQuestName(String bookId, String chapterId);

  void setQuestName(String bookId, String chapterId, String sectionId);

  @Nullable
  Set<String> getBoughtChapters(String bookId);

  void addBoughtChapter(String bookId, String chapterId);

  void removeBoughtChapter(String bookId, String chapterId);
}
