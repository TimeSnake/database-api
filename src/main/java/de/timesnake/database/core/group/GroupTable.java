/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.channel.util.Channel;
import de.timesnake.channel.util.message.ChannelMessage;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.chat.ExTextColor;

public class GroupTable extends SimpleGroupTable {

  protected GroupTable(DatabaseConnector databaseConnector, String nameTable, Column<?>... primaryColumns) {
    super(databaseConnector, nameTable, primaryColumns);
    super.addColumn(Column.Group.PREFIX);
    super.addColumn(Column.Group.PREFIX_COLOR);
  }

  protected void addGroup(String name, Integer rank, String prefix, ExTextColor color) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(rank, Column.Group.PRIORITY)), new Entry<>(name,
            Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR));
  }


  protected void addGroup(String name, Integer rank, String prefix, ExTextColor color,
      SyncExecute syncExecute) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(rank, Column.Group.PRIORITY)), syncExecute,
        new Entry<>(name, Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR));
  }

  protected void addGroup(String name, Integer rank, String prefix, ExTextColor color,
      ChannelMessage<?, ?> channelMessage) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(rank, Column.Group.PRIORITY)),
        () -> Channel.getInstance().sendMessage(channelMessage), new Entry<>(name,
            Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
        new Entry<>(color, Column.Group.PREFIX_COLOR));
  }
}
