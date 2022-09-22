package de.timesnake.database.util.support;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public interface DbTicket extends DbCached<DbTicket> {

    boolean exists();

    @NotNull
    Integer getId();

    @NotNull
    String getUuid();

    @NotNull
    String getName();

    @NotNull
    String getMessage();

    void setMessage(String message);

    @Nullable
    String getAnswer();

    void setAnswer(String answer);

    @NotNull
    Status.Ticket getStatus();

    void setStatus(Status.Ticket status);

    @NotNull
    Date getDate();

    @NotNull
    String getDateString();

}
