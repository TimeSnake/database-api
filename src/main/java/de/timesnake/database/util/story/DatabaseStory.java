package de.timesnake.database.util.story;

import de.timesnake.database.core.story.DbStoryUser;

import java.util.UUID;

public interface DatabaseStory {

    DbStoryUser getUser(UUID uuid);
}
