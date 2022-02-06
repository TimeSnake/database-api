package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Status;

import java.util.Date;

public class DbTicket extends TableQuery implements de.timesnake.database.util.support.DbTicket {

    public DbTicket(DatabaseConnector databaseConnector, Integer id, String nameTable) {
        super(databaseConnector, nameTable, new TableEntry<>(id, Column.Support.ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Support.ID) != null;
    }

    @Override
    public Integer getId() {
        return (Integer) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getUuid() {
        return super.getFirstWithKey(Column.Support.UUID);
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Support.NAME);
    }

    @Override
    public String getMessage() {
        return super.getFirstWithKey(Column.Support.MESSAGE);
    }

    @Override
    public String getAnswer() {
        return super.getFirstWithKey(Column.Support.ANSWER);
    }

    @Override
    public Status.Ticket getStatus() {
        return super.getFirstWithKey(Column.Support.STATUS);
    }

    @Override
    public Date getDate() {
        return this.getFirstWithKey(Column.Support.DATE);
    }

    @Override
    public String getDateString() {
        return Table.DATE_FORMAT.format(this.getDate());
    }

    @Override
    public void setAnswer(String answer) {
        super.setWithKey(answer, Column.Support.ANSWER);
    }

    @Override
    public void setStatus(Status.Ticket status) {
        super.setWithKey(status, Column.Support.STATUS);
    }

    @Override
    public void setMessage(String message) {
        super.setWithKey(message, Column.Support.MESSAGE);
    }

    @Override
    public de.timesnake.database.util.support.DbTicket toLocal() {
        if (!this.exists()) {
            return null;
        }
        return new DbLocalTicket(this);
    }

    @Override
    public de.timesnake.database.util.support.DbTicket toDatabase() {
        return this;
    }

}
