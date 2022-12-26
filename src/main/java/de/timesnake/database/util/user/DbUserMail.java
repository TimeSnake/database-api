/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface DbUserMail {

    /**
     * Gets the id of the mail
     *
     * @return the id
     */
    @NotNull
    Integer getId();

    /**
     * Gets the uuid of the receiver of the mail
     *
     * @return the uuid of the receiver
     */
    @Nullable
    UUID getUniqueId();

    /**
     * Gets the name of the receiver of the mail
     *
     * @return the name of the receiver
     */
    @Nullable
    String getName();

    /**
     * Gets the uuid of the sender of the mail
     *
     * @return the uuid of the sender
     */
    @Nullable
    UUID getSenderUniqueId();

    /**
     * Gets the name of the sneder of the mail
     *
     * @return the name of the sender
     */
    @Nullable
    String getSenderName();

    /**
     * Gets the message of the mail
     *
     * @return the message of the mail
     */
    @Nullable
    String getMessage();

    /**
     * Deletes the mail
     */
    void delete();
}
