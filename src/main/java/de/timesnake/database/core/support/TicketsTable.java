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

package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;
import java.util.Date;
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

    @Override
    public void backup() {
        super.backup();
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
