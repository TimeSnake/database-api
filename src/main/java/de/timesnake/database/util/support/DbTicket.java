package de.timesnake.database.util.support;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.library.basic.util.Status;

import java.util.Date;

public interface DbTicket extends DbCached<DbTicket> {

    boolean exists();

    Integer getId();

    String getUuid();

    String getName();

    String getMessage();

    String getAnswer();

    Status.Ticket getStatus();

    Date getDate();

    String getDateString();

    void setAnswer(String answer);

    void setStatus(Status.Ticket status);

    void setMessage(String message);

}
