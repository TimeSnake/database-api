/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.support;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class DatabaseSupport extends DatabaseConnector implements
    de.timesnake.database.util.support.DatabaseSupport {

  private final TicketTable ticketTable;

  private final String ticketsTableName;

  public DatabaseSupport(String name, String url, String options, String user, String password,
      String ticketsTableName) {
    super(name, url, options, user, password);
    this.ticketsTableName = ticketsTableName;
    this.ticketTable = new TicketTable(this, ticketsTableName);
  }

  @Override
  public void createTables() {
    ticketTable.create();
  }

  @Override
  public void saveTables() {
    ticketTable.save();
  }

  @NotNull
  @Override
  public de.timesnake.database.util.support.DbTicket addTicket(String uuid, String name,
      String message) {
    return this.getTicket(this.ticketTable.addTicket(uuid, name, message));
  }

  @Override
  public void addSystemTicket(String message) {
    this.ticketTable.addTicket("System", "SYSTEM", message);
  }

  @Override
  public boolean containsTicket(int id) {
    return this.ticketTable.containsTicket(id);
  }

  @Override
  public void removeTicket(int id) {
    this.ticketTable.removeTicket(id);
  }

  @Nullable
  @Override
  public de.timesnake.database.util.support.DbTicket getTicket(int id) {
    return new DbTicket(this, id, this.ticketsTableName);
  }

  @Override
  public de.timesnake.database.util.support.DbTicket getOldestTicket(Status.Ticket... statuss) {
    de.timesnake.database.util.support.DbTicket oldestTicket = null;
    LocalDateTime oldestDate = LocalDateTime.now();
    for (de.timesnake.database.util.support.DbTicket ticket : this.getTickets()) {
      Status.Ticket ticketStatus = ticket.getStatus();
      for (Status.Ticket status : statuss) {
        if (ticketStatus.equals(status)) {
          LocalDateTime date = ticket.getDate();
          if (date.isBefore(oldestDate)) {
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
    return this.ticketTable.getTicketIds();
  }

  @NotNull
  @Override
  public Collection<Integer> getTicketIds(Status.Ticket status) {
    return this.ticketTable.getTicketIds(status);
  }

  @NotNull
  @Override
  public Collection<de.timesnake.database.util.support.DbTicket> getTickets(UUID uuid) {
    Collection<de.timesnake.database.util.support.DbTicket> tickets = new ArrayList<>();
    for (Integer i : this.ticketTable.getTickets(uuid)) {
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
