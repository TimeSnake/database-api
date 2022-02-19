package de.timesnake.database.core.group.perm;

import de.timesnake.channel.util.message.ChannelGroupMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.BasicGroupsTable;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;

public class PermGroupsTable extends BasicGroupsTable {

    public PermGroupsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.PermGroup.INHERITANCE);
    }

    public void addPermGroup(String name, int rank, String prefix, String colorChatName) {
        super.addGroup(name, rank, prefix, colorChatName, new ChannelGroupMessage<>(name, MessageType.Group.ALIAS));
    }

    public Collection<String> getPermGroupsName() {
        return super.getGroupsName();
    }

    public Collection<Integer> getPermGroupsRank() {
        return super.getGroupsRank();
    }

    public void removePermGroup(String name) {
        super.removeGroup(name, new ChannelGroupMessage<>(name, MessageType.Group.ALIAS));
    }

    public void removePermGroup(int rank) {
        super.removeGroup(rank, new ChannelGroupMessage<>(Database.getGroups().getPermGroup(rank).getName(), MessageType.Group.ALIAS));
    }

    public boolean containsPermGroup(int rank) {
        return super.containsGroup(rank);
    }

    public boolean containsPermGroup(String name) {
        return super.containsGroup(name);
    }

}
