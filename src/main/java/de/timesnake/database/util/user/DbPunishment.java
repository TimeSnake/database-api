/*
 * workspace.database-api.main
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

import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Date;

public interface DbPunishment extends DbPlayer {

    /**
     * Deletes the punishment
     */
    void delete();

    /**
     * Gets the type of the punishment
     *
     * @return the {@link Type.Punishment}
     */
    @Nullable
    Type.Punishment getType();

    /**
     * Sets the type of the punishment
     *
     * @param type The type to set
     */
    void setType(Type.Punishment type);

    /**
     * Gets the deletion-date of the punishment
     *
     * @return the {@link Date} of deletion
     */
    @Nullable
    LocalDateTime getDate();

    /**
     * Sets tbe date of the punishment
     *
     * @param date The date to set
     */
    void setDate(LocalDateTime date);

    /**
     * Gets the castigator of the punishment
     *
     * @return the castigator name
     */
    @Nullable
    String getCastigator();

    /**
     * Sets the castigator name of the punishment
     *
     * @param castigator The name of the castigator
     */
    void setCastigator(String castigator);

    /**
     * Gets the reason of the punishment
     *
     * @return the reason
     */
    @Nullable
    String getReason();

    /**
     * Sets the reason of the punishment
     *
     * @param reason The reason to set
     */
    void setReason(String reason);

    /**
     * Gets the punished-server of the punishment
     *
     * @return the server name
     */
    @Nullable
    String getServer();

    /**
     * Sets the punished-server of the punishment
     *
     * @param server The name of the server to set
     */
    void setServer(String server);
}
