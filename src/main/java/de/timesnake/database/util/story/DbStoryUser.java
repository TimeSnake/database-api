package de.timesnake.database.util.story;

import java.util.Set;

public interface DbStoryUser {
    boolean exists();

    Set<Integer> getChapterIds();

    Set<Integer> getPartIds(Integer chapterId);

    Integer getSectionId(Integer chapterId, Integer partId);

    void setSectionId(Integer chapterId, Integer partId, Integer sectionId);
}
