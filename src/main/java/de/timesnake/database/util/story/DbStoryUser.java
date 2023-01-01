/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.story;

import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface DbStoryUser {

    @Nullable
    Set<Integer> getChapterIds();

    @Nullable
    Set<String> getChapterIds(Integer bookId);

    @Nullable
    String getQuestName(Integer bookId, String chapterName);

    void setQuestName(Integer bookId, String chapterName, String sectionId);

    @Nullable
    Set<String> getBoughtChapters(Integer bookId);

    void addBoughtChapter(Integer bookId, String chapterName);

    void removeBoughtChapter(Integer bookId, String chapterName);
}
