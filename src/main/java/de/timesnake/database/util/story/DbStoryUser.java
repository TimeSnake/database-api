package de.timesnake.database.util.story;

import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface DbStoryUser {

    @Nullable
    Set<Integer> getChapterIds();

    @Nullable
    Set<Integer> getPartIds(Integer chapterId);

    @Nullable
    Integer getSectionId(Integer chapterId, Integer partId);

    void setSectionId(Integer chapterId, Integer partId, Integer sectionId);

    @Nullable
    Set<Integer> getBoughtParts(Integer chapterId);

    void addBoughtPart(Integer chapterId, Integer partId);

    void removeBoughtChapter(Integer chapterId, Integer partId);
}
