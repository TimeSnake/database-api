/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.story;

import de.timesnake.database.core.story.DbStoryUser;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface DatabaseStory {

    @NotNull
    DbStoryUser getUser(UUID uuid);
}
