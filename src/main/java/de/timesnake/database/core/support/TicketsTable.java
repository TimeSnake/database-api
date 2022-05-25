package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class TicketsTable extends Table {

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
        return super.addEntryWithAutoIdSynchronized(Column.Support.ID, new TableEntry<>(uuid, Column.Support.UUID),
                new TableEntry<>(name, Column.Support.NAME), new TableEntry<>(message, Column.Support.MESSAGE),
                new TableEntry<>(Status.Ticket.OPEN, Column.Support.STATUS), new TableEntry<>(new Date(),
                        Column.Support.DATE));
    }

    public void removeTicket(int id) {
        super.deleteEntry(new TableEntry<>(id, Column.Support.ID));
    }

    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    public Collection<Integer> getTicketIds() {
        return super.get(Column.Support.ID);
    }

    public Collection<Integer> getTicketIds(Status.Ticket status) {
        return super.get(Column.Support.ID, new TableEntry<>(status, Column.Support.STATUS));
    }

    public Collection<Integer> getTickets(UUID uuid) {
        return super.get(Column.Support.ID, new TableEntry<>(uuid.toString(), Column.Support.UUID));
    }

    public boolean containsTicket(Integer id) {
        return super.getFirst(Column.Support.UUID, new TableEntry<>(id, Column.Support.ID)) != null;
    }
}
