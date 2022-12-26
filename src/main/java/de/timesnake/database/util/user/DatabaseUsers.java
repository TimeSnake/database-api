/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface DatabaseUsers {

    /**
     * Adds a user to the database
     *
     * @param uuid      The uuid of the user
     * @param name      The name of the user
     * @param permGroup The perm group of the user
     * @param server    The current server of the user
     */
    void addUser(UUID uuid, String name, String permGroup, String server);

    @Nullable
    de.timesnake.database.core.user.DbUser getUserByDiscordId(Long discordId);

    @NotNull
    Collection<de.timesnake.database.core.user.DbUser> getUsers();

    /**
     * Gets a user with unique id
     *
     * @param uuid The unique id of the user
     * @return the {@link DbUser} with the id
     */
    @Nullable
    DbUser getUser(UUID uuid);

    /**
     * Gets a user with name, read description before use
     * <p>
     * Names are not unique. Use the unique id for safety.
     *
     * @param name The name of the user
     * @return the {@link DbUser} with the name
     */
    @Nullable
    DbUser getUser(String name);

    /**
     * Checks if the database contains the user id
     *
     * @param uuid The user id to check
     * @return true, if contains, else false
     */
    boolean containsUser(UUID uuid);

    /**
     * Checks if the database contains the user name
     *
     * @param name The user name to check
     * @return true, if contains, else false
     */
    boolean containsUser(String name);

    /**
     * Gets all user ids
     *
     * @return a {@link Collection} of the {@link UUID}s, which are in the database
     */
    @NotNull
    Collection<UUID> getUsersUuid();

    /**
     * Gets all user names
     *
     * @return a {@link Collection} of the names, which are in the database
     */
    @NotNull
    Collection<String> getUsersName();

}
