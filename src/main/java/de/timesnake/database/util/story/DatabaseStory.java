package de.timesnake.database.util.story;

import de.timesnake.database.core.story.DbStoryUser;

import java.util.UUID;

public interface DatabaseStory {

    DbStoryUser getUser(UUID uuid);

    void addUser(UUID uuid, Integer chapterId, Integer partId);

    void removeStoryUser(UUID uuid, Integer chapterId, Integer partId);
}
