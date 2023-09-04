/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.channel.core.ServerChannel;
import de.timesnake.channel.util.message.ChannelMessage;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.chat.ExTextColor;

public class GroupsTable extends GroupBasisTable {

  protected GroupsTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable);
    super.addColumn(Column.Group.PREFIX);
    super.addColumn(Column.Group.PREFIX_COLOR);
  }

  protected void addGroup(String name, Integer rank, String prefix, ExTextColor color) {
    super.addEntry(new PrimaryEntries(new Entry<>(rank, Column.Group.PRIORITY)), new Entry<>(name,
            Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR));
  }


  protected void addGroup(String name, Integer rank, String prefix, ExTextColor color,
      SyncExecute syncExecute) {
    super.addEntry(new PrimaryEntries(new Entry<>(rank, Column.Group.PRIORITY)), syncExecute,
        new Entry<>(name, Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR));
  }

  protected void addGroup(String name, Integer rank, String prefix, ExTextColor color,
      ChannelMessage<?, ?> channelMessage) {
    super.addEntry(new PrimaryEntries(new Entry<>(rank, Column.Group.PRIORITY)),
        () -> ServerChannel.getInstance().sendMessage(channelMessage), new Entry<>(name,
            Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR));
  }
}
