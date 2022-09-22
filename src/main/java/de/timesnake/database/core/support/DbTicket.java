package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public class DbTicket extends TableQuery implements de.timesnake.database.util.support.DbTicket {

    public DbTicket(DatabaseConnector databaseConnector, Integer id, String nameTable) {
        super(databaseConnector, nameTable, new TableEntry<>(id, Column.Support.ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Support.ID) != null;
    }

    @NotNull
    @Override
    public Integer getId() {
        return (Integer) super.primaryEntries.get(0).getValue();
    }

    @NotNull
    @Override
    public String getUuid() {
        return super.getFirstWithKey(Column.Support.UUID);
    }

    @NotNull
    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Support.NAME);
    }

    @NotNull
    @Override
    public String getMessage() {
        return super.getFirstWithKey(Column.Support.MESSAGE);
    }

    @Override
    public void setMessage(String message) {
        super.setWithKey(message, Column.Support.MESSAGE);
    }

    @Nullable
    @Override
    public String getAnswer() {
        return super.getFirstWithKey(Column.Support.ANSWER);
    }

    @Override
    public void setAnswer(String answer) {
        super.setWithKey(answer, Column.Support.ANSWER);
    }

    @NotNull
    @Override
    public Status.Ticket getStatus() {
        return super.getFirstWithKey(Column.Support.STATUS);
    }

    @Override
    public void setStatus(Status.Ticket status) {
        super.setWithKey(status, Column.Support.STATUS);
    }

    @NotNull
    @Override
    public Date getDate() {
        return this.getFirstWithKey(Column.Support.DATE);
    }

    @NotNull
    @Override
    public String getDateString() {
        return TableDDL.DATE_FORMAT.format(this.getDate());
    }

    @NotNull
    @Override
    public de.timesnake.database.util.support.DbTicket toLocal() {
        if (!this.exists()) {
            return null;
        }
        return new DbCachedTicket(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.support.DbTicket toDatabase() {
        return this;
    }

}
