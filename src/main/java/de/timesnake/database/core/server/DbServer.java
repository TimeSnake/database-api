/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelServerMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public abstract class DbServer extends TableQuery implements
    de.timesnake.database.util.server.DbServer {

  public DbServer(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, nameTable, new Entry<>(name, Column.Server.NAME));
  }

  @NotNull
  @Override
  public String getName() {
    return (String) super.primaryEntries.get(0).getValue();
  }

  @Override
  public void setName(String name) {
    super.setWithKey(name, Column.Server.NAME);
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Server.PORT) != null;
  }

  @NotNull
  @Override
  public Integer getPort() {
    return super.getFirstWithKey(Column.Server.PORT);
  }

  @NotNull
  @Override
  public Status.Server getStatus() {
    Status.Server status = super.getFirstWithKey(Column.Server.STATUS);
    return status != null ? status : Status.Server.OFFLINE;
  }

  @Override
  public void setStatus(Status.Server status) {
    super.setWithKey(status, Column.Server.STATUS,
        () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(), MessageType.Server.STATUS,
            status)));
  }

  @NotNull
  @Override
  public Integer getOnlinePlayers() {
    return super.getFirstWithKey(Column.Server.ONLINE_PLAYERS);
  }

  @Override
  public void setOnlinePlayers(int playersOnline) {
    super.setWithKey(playersOnline, Column.Server.ONLINE_PLAYERS,
        () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
            MessageType.Server.ONLINE_PLAYERS, playersOnline)));
  }

  @Nullable
  @Override
  public Integer getMaxPlayers() {
    return super.getFirstWithKey(Column.Server.MAX_PLAYERS);
  }

  @Override
  public void setMaxPlayers(int playersMax) {
    super.setWithKey(playersMax, Column.Server.MAX_PLAYERS,
        () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
            MessageType.Server.MAX_PLAYERS, playersMax)));
  }

  @Override
  public void setStatusSynchronized(Status.Server status) {
    super.setWithKeySynchronized(status, Column.Server.STATUS);
    Channel.getInstance()
        .sendMessage(new ChannelServerMessage<>(this.getName(), MessageType.Server.STATUS, status));
  }

  @Override
  public void setOnlinePlayersSynchronized(int playersOnline) {
    super.setWithKeySynchronized(playersOnline, Column.Server.ONLINE_PLAYERS);
    Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(), MessageType.Server.ONLINE_PLAYERS,
        playersOnline));
  }

  @Nullable
  @Override
  public String getPassword() {
    return super.getFirstWithKey(Column.Server.PASSWORD);
  }

  @Override
  public void setPassword(String password) throws TooLongEntryException {
    if (password != null && password.length() > 255) {
      throw new TooLongEntryException(password, Column.Server.PASSWORD.getType());
    }
    super.setWithKey(password, Column.Server.PASSWORD,
        () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
            MessageType.Server.PASSWORD, password)));
  }

  @Override
  public boolean hasPassword() {
    return this.getPassword() != null;
  }

  @NotNull
  @Override
  public Path getFolderPath() {
    return super.getFirstWithKey(Column.Server.FOLDER_PATH);
  }

}
