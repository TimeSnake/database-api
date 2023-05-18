/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.story;

import de.timesnake.database.core.story.DbStoryUser;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public interface DatabaseStory {

  @NotNull
  DbStoryUser getUser(UUID uuid);
}
