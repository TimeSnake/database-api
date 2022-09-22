package de.timesnake.database.core.support;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class DatabaseSupport extends DatabaseConnector implements de.timesnake.database.util.support.DatabaseSupport {

    private final TicketsTable ticketsTable;

    private final String ticketsTableName;

    public DatabaseSupport(String name, String url, String user, String password, String ticketsTableName) {
        super(name, url, user, password);
        this.ticketsTableName = ticketsTableName;
        this.ticketsTable = new TicketsTable(this, ticketsTableName);
    }

    @Override
    public void createTables() {
        ticketsTable.create();
    }

    @Override
    public void backupTables() {
        ticketsTable.backup();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.support.DbTicket addTicket(String uuid, String name, String message) {
        return this.getTicket(this.ticketsTable.addTicket(uuid, name, message));
    }

    @Override
    public void addSystemTicket(String message) {
        this.ticketsTable.addTicket("System", "SYSTEM", message);
    }

    @Override
    public boolean containsTicket(int id) {
        return this.ticketsTable.containsTicket(id);
    }

    @Override
    public void removeTicket(int id) {
        this.ticketsTable.removeTicket(id);
    }

    @Nullable
    @Override
    public de.timesnake.database.util.support.DbTicket getTicket(int id) {
        return new DbTicket(this, id, this.ticketsTableName);
    }

    @Override
    public de.timesnake.database.util.support.DbTicket getOldestTicket(Status.Ticket... statuss) {
        de.timesnake.database.util.support.DbTicket oldestTicket = null;
        Date oldestDate = new Date();
        for (de.timesnake.database.util.support.DbTicket ticket : this.getTickets()) {
            Status.Ticket ticketStatus = ticket.getStatus();
            for (Status.Ticket status : statuss) {
                if (ticketStatus.equals(status)) {
                    Date date = ticket.getDate();
                    if (date.before(oldestDate)) {
                        oldestDate = date;
                        oldestTicket = ticket;
                    }
                    break;
                }
            }


        }
        return oldestTicket;
    }


    @NotNull
    @Override
    public Collection<Integer> getTicketIds() {
        return this.ticketsTable.getTicketIds();
    }

    @NotNull
    @Override
    public Collection<Integer> getTicketIds(Status.Ticket status) {
        return this.ticketsTable.getTicketIds(status);
    }

    @NotNull
    @Override
    public Collection<de.timesnake.database.util.support.DbTicket> getTickets(UUID uuid) {
        Collection<de.timesnake.database.util.support.DbTicket> tickets = new ArrayList<>();
        for (Integer i : this.ticketsTable.getTickets(uuid)) {
            tickets.add(this.getTicket(i));
        }
        return tickets;
    }

    @NotNull
    @Override
    public Collection<de.timesnake.database.util.support.DbTicket> getTickets() {
        Collection<de.timesnake.database.util.support.DbTicket> tickets = new ArrayList<>();
        for (Integer id : this.getTicketIds()) {
            tickets.add(this.getTicket(id));
        }
        return tickets;
    }


}
