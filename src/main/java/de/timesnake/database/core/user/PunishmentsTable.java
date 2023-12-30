/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.channel.core.ServerChannel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.User;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Punishment;

import java.util.Set;
import java.util.UUID;

public class PunishmentsTable extends TableDDL {

  public PunishmentsTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, User.UUID);
    super.addColumn(User.PUNISH_TYPE);
    super.addColumn(User.PUNISH_DATE);
    super.addColumn(User.PUNISH_DURATION);
    super.addColumn(User.PUNISH_CASTIGATOR);
    super.addColumn(User.PUNISH_REASON);

    super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  public void setPunishment(Punishment punishment) {
    super.set(Set.of(new Entry<>(punishment.getType(), Column.User.PUNISH_TYPE),
            new Entry<>(punishment.getDate(), Column.User.PUNISH_DATE),
            new Entry<>(punishment.getDuration(), User.PUNISH_DURATION),
            new Entry<>(punishment.getByName(), Column.User.PUNISH_CASTIGATOR),
            new Entry<>(punishment.getReason(), Column.User.PUNISH_REASON)),
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(punishment.getUuid(), MessageType.User.PUNISH, punishment)),
        new Entry<>(punishment.getUuid(), Column.User.UUID));
  }

  public boolean contains(UUID uuid) {
    return super.getFirst(Column.User.PUNISH_TYPE, new Entry<>(uuid, Column.User.UUID)) != null;
  }

}
