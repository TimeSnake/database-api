package de.timesnake.database.util.support;

import de.timesnake.database.util.object.Status;

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
    DbTicket addTicket(String uuid, String name, String message);

    void addSystemTicket(String message);

    boolean containsTicket(int id);

    void removeTicket(int id);

    DbTicket getTicket(int id);

    DbTicket getOldestTicket(Status.Ticket... statuss);

    Collection<Integer> getTicketIds();

    Collection<Integer> getTicketIds(Status.Ticket status);

    Collection<DbTicket> getTickets(UUID uuid);

    Collection<DbTicket> getTickets();
}
