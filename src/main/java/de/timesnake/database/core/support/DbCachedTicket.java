package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.library.basic.util.Status;

import java.util.Date;
import java.util.Set;

public class DbCachedTicket implements DbTicket {

    private final de.timesnake.database.core.support.DbTicket ticket;

    private final Integer id;
    private final String uuid;
    private final String name;
    private final Date date;
    private String message;
    private String answer;
    private Status.Ticket status;

    public DbCachedTicket(de.timesnake.database.core.support.DbTicket ticket) {
        ColumnMap columnMap = ticket.getFirstWithKey(Set.of(Column.Support.UUID, Column.Support.NAME,
                Column.Support.MESSAGE, Column.Support.ANSWER, Column.Support.STATUS, Column.Support.DATE));

        this.ticket = ticket;

        this.id = ticket.getId();
        this.uuid = columnMap.get(Column.Support.UUID);
        this.name = columnMap.get(Column.Support.NAME);
        this.message = columnMap.get(Column.Support.MESSAGE);
        this.answer = columnMap.get(Column.Support.ANSWER);
        this.status = columnMap.get(Column.Support.STATUS);
        this.date = columnMap.get(Column.Support.DATE);
    }

    @Override
    public boolean exists() {
        return this.ticket.exists();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
        this.ticket.setMessage(message);
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
        this.ticket.setAnswer(answer);
    }

    @Override
    public Status.Ticket getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status.Ticket status) {
        this.status = status;
        this.ticket.setStatus(status);
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getDateString() {
        return TableDDL.DATE_FORMAT.format(this.getDate());
    }

    @Override
    public DbTicket toLocal() {
        return new DbCachedTicket(this.ticket);
    }

    @Override
    public DbTicket toDatabase() {
        return this.ticket;
    }

}
