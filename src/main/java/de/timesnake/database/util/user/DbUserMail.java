/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
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
