package de.timesnake.database.core.group;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelMessage;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

public class GroupsTable extends GroupBasisTable {

    protected GroupsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Group.PREFIX);
        super.addColumn(Column.Group.PREFIX_COLOR);
    }

    protected void addGroup(String name, Integer rank, String prefix, String colorChatName) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(rank, Column.Group.PRIORITY)), new TableEntry<>(name,
                        Column.Group.NAME), new TableEntry<>(prefix, Column.Group.PREFIX),
                new TableEntry<>(colorChatName, Column.Group.PREFIX_COLOR));
    }

    protected void addGroup(String name, Integer rank, String prefix, String colorChatName, SyncExecute syncExecute) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(rank, Column.Group.PRIORITY)), syncExecute,
                new TableEntry<>(name, Column.Group.NAME), new TableEntry<>(prefix, Column.Group.PREFIX),
                new TableEntry<>(colorChatName, Column.Group.PREFIX_COLOR));
    }

    protected void addGroup(String name, Integer rank, String prefix, String colorChatName,
                            ChannelMessage<?, ?> channelMessage) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(rank, Column.Group.PRIORITY)),
                () -> NetworkChannel.getChannel().sendMessage(channelMessage), new TableEntry<>(name,
                        Column.Group.NAME), new TableEntry<>(prefix, Column.Group.PREFIX),
                new TableEntry<>(colorChatName, Column.Group.PREFIX_COLOR));
    }
}
