package de.timesnake.database.core.group;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelMessage;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

import java.util.Collection;

public class BasicGroupsTable extends TableDDL {

    protected BasicGroupsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.Group.RANK);
        super.addColumn(Column.Group.NAME);
        super.addColumn(Column.Group.PREFIX);
        super.addColumn(Column.Group.CHAT_COLOR);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    protected void addGroup(String name, Integer rank, String prefix, String colorChatName) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(rank, Column.Group.RANK)), new TableEntry<>(name,
                        Column.Group.NAME), new TableEntry<>(prefix, Column.Group.PREFIX),
                new TableEntry<>(colorChatName.toUpperCase(), Column.Group.CHAT_COLOR));
    }

    protected void addGroup(String name, Integer rank, String prefix, String colorChatName, SyncExecute syncExecute) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(rank, Column.Group.RANK)), syncExecute,
                new TableEntry<>(name, Column.Group.NAME), new TableEntry<>(prefix, Column.Group.PREFIX),
                new TableEntry<>(colorChatName.toUpperCase(), Column.Group.CHAT_COLOR));
    }

    protected void addGroup(String name, Integer rank, String prefix, String colorChatName,
                            ChannelMessage channelMessage) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(rank, Column.Group.RANK)),
                () -> NetworkChannel.getChannel().sendMessage(channelMessage), new TableEntry<>(name,
                        Column.Group.NAME), new TableEntry<>(prefix, Column.Group.PREFIX),
                new TableEntry<>(colorChatName.toUpperCase(), Column.Group.CHAT_COLOR));
    }

    protected boolean containsGroup(int rank) {
        return super.getFirst(Column.Group.NAME, new TableEntry<>(rank, Column.Group.RANK)) != null;
    }

    protected boolean containsGroup(String name) {
        return super.getFirst(Column.Group.RANK, new TableEntry<>(name, Column.Group.NAME)) != null && super.getFirst(Column.Group.RANK, new TableEntry<>(name, Column.Group.NAME)) != 0;
    }

    protected void removeGroup(String name) {
        this.removeGroup(this.getRankFromName(name));
    }

    protected void removeGroup(int rank) {
        super.deleteEntry(new TableEntry<>(rank, Column.Group.RANK));
    }

    protected void removeGroup(String name, ChannelMessage channelMessage) {
        this.removeGroup(this.getRankFromName(name), channelMessage);
    }

    protected void removeGroup(int rank, ChannelMessage channelMessage) {
        super.deleteEntry(() -> NetworkChannel.getChannel().sendMessage(channelMessage), new TableEntry<>(rank,
                Column.Group.RANK));
    }

    protected Collection<String> getGroupsName() {
        return super.get(Column.Group.NAME);
    }

    protected Collection<Integer> getGroupsRank() {
        return super.get(Column.Group.RANK);
    }

    public Integer getRankFromName(String name) {
        return super.getFirst(Column.Group.RANK, new TableEntry<>(name, Column.Group.NAME));
    }

}
