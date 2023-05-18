/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class TicketsTable extends TableDDL {

  protected TicketsTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable, Column.Support.ID);
    super.addColumn(Column.Support.UUID);
    super.addColumn(Column.Support.NAME);
    super.addColumn(Column.Support.MESSAGE);
    super.addColumn(Column.Support.ANSWER);
    super.addColumn(Column.Support.STATUS);
    super.addColumn(Column.Support.DATE);
  }

  protected Integer addTicket(String uuid, String name, String message) {
    return super.addEntryWithAutoIdSynchronized(Column.Support.ID,
        new Entry<>(uuid, Column.Support.UUID),
        new Entry<>(name, Column.Support.NAME),
        new Entry<>(message, Column.Support.MESSAGE),
        new Entry<>(Status.Ticket.OPEN, Column.Support.STATUS),
        new Entry<>(LocalDateTime.now(), Column.Support.DATE));
  }

  public void removeTicket(int id) {
    super.deleteEntry(new Entry<>(id, Column.Support.ID));
  }

  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  public Collection<Integer> getTicketIds() {
    return super.get(Column.Support.ID);
  }

  public Collection<Integer> getTicketIds(Status.Ticket status) {
    return super.get(Column.Support.ID, new Entry<>(status, Column.Support.STATUS));
  }

  public Collection<Integer> getTickets(UUID uuid) {
    return super.get(Column.Support.ID, new Entry<>(uuid.toString(), Column.Support.UUID));
  }

  public boolean containsTicket(Integer id) {
    return super.getFirst(Column.Support.UUID, new Entry<>(id, Column.Support.ID)) != null;
  }
}
