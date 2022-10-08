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

package de.timesnake.database.util.support;

import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface DatabaseSupport {

    /**
     * Adds a ticket to the database
     *
     * @param uuid    The uuid of the player
     * @param name    The name of the player
     * @param message The message of the ticket
     * @return the created {@link DbTicket}
     */
    @NotNull
    DbTicket addTicket(String uuid, String name, String message);

    void addSystemTicket(String message);

    boolean containsTicket(int id);

    void removeTicket(int id);

    @Nullable
    DbTicket getTicket(int id);

    @Nullable
    DbTicket getOldestTicket(Status.Ticket... statuss);

    @NotNull
    Collection<Integer> getTicketIds();

    @NotNull
    Collection<Integer> getTicketIds(Status.Ticket status);

    @NotNull
    Collection<DbTicket> getTickets(UUID uuid);

    @NotNull
    Collection<DbTicket> getTickets();
}
