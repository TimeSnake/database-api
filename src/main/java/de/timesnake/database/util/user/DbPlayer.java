/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.user;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface DbPlayer {

    boolean exists();

    /**
     * Gets the {@link DbPlayer}-name
     *
     * @return the name
     */
    @NotNull
    String getName();

    /**
     * Gets the {@link UUID} of the {@link DbPlayer}
     *
     * @return the {@link UUID}
     */
    @NotNull
    UUID getUniqueId();
}
