/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.support;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.library.basic.util.Status;
import java.time.LocalDateTime;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbCachedTicket implements DbTicket {

  private final de.timesnake.database.core.support.DbTicket ticket;

  private final Integer id;
  private final String uuid;
  private final String name;
  private final LocalDateTime date;
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

  @NotNull
  @Override
  public Integer getId() {
    return id;
  }

  @NotNull
  @Override
  public String getUuid() {
    return uuid;
  }

  @NotNull
  @Override
  public String getName() {
    return name;
  }

  @NotNull
  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public void setMessage(String message) {
    this.message = message;
    this.ticket.setMessage(message);
  }

  @Nullable
  @Override
  public String getAnswer() {
    return answer;
  }

  @Override
  public void setAnswer(String answer) {
    this.answer = answer;
    this.ticket.setAnswer(answer);
  }

  @NotNull
  @Override
  public Status.Ticket getStatus() {
    return status;
  }

  @Override
  public void setStatus(Status.Ticket status) {
    this.status = status;
    this.ticket.setStatus(status);
  }

  @NotNull
  @Override
  public LocalDateTime getDate() {
    return date;
  }

  @NotNull
  @Override
  public DbTicket toLocal() {
    return new DbCachedTicket(this.ticket);
  }

  @NotNull
  @Override
  public DbTicket toDatabase() {
    return this.ticket;
  }

}
