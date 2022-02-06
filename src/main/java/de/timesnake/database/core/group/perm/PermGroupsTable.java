package de.timesnake.database.core.group.perm;

import de.timesnake.channel.api.message.ChannelGroupMessage;
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
        super.addGroup(name, rank, prefix, colorChatName, ChannelGroupMessage.getAliasMessage(name));
    }

    public Collection<String> getPermGroupsName() {
        return super.getGroupsName();
    }

    public Collection<Integer> getPermGroupsRank() {
        return super.getGroupsRank();
    }

    public void removePermGroup(String name) {
        super.removeGroup(name, ChannelGroupMessage.getAliasMessage(name));
    }

    public void removePermGroup(int rank) {
        super.removeGroup(rank, ChannelGroupMessage.getAliasMessage(Database.getGroups().getPermGroup(rank).getName()));
    }

    public boolean containsPermGroup(int rank) {
        return super.containsGroup(rank);
    }

    public boolean containsPermGroup(String name) {
        return super.containsGroup(name);
    }

}
