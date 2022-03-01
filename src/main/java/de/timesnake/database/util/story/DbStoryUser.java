package de.timesnake.database.util.story;

import java.util.Set;

public interface DbStoryUser {

    Set<Integer> getChapterIds();

    Set<Integer> getPartIds(Integer chapterId);

    Integer getSectionId(Integer chapterId, Integer partId);

    void setSectionId(Integer chapterId, Integer partId, Integer sectionId);

    Set<Integer> getBoughtParts(Integer chapterId);

    void addBoughtPart(Integer chapterId, Integer partId);

    void removeBoughtChapter(Integer chapterId, Integer partId);
}
