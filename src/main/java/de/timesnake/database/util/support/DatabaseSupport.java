/*
 * Copyright (C) 2023 timesnake
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
