package de.timesnake.database.core.permisson;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;
import java.util.UUID;

public class PermissionsTable extends TableDDL {

    protected PermissionsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.Permission.ID);
        super.addColumn(Column.Permission.NAME);
        super.addColumn(Column.Permission.PERMISSION);
        super.addColumn(Column.Permission.MODE);
        super.addColumn(Column.Permission.SERVER);
    }

    protected void addPermission(String name, String permission, Status.Permission mode, String... servers) {
        super.addEntryWithAutoIdSynchronized(Column.Permission.ID, new TableEntry<>(name, Column.Permission.NAME),
                new TableEntry<>(permission, Column.Permission.PERMISSION),
                new TableEntry<>(new DbStringArrayList(servers), Column.Permission.SERVER), new TableEntry<>(mode,
                        Column.Permission.MODE));
    }

    protected void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute,
                                 String... servers) {
        super.addEntryWithAutoId(Column.Permission.ID, syncExecute, new TableEntry<>(name, Column.Permission.NAME),
                new TableEntry<>(permission, Column.Permission.PERMISSION),
                new TableEntry<>(new DbStringArrayList(servers), Column.Permission.SERVER), new TableEntry<>(mode,
                        Column.Permission.MODE));
    }

    protected void addPermission(UUID uuid, String permission, Status.Permission mode, String... servers) {
        this.addPermission(uuid.toString(), permission, mode,
                () -> NetworkChannel.getChannel().sendMessage(new ChannelUserMessage<>(uuid,
                        MessageType.User.PERMISSION)), servers);
    }

    protected void removePermission(String name, String permission) {
        super.deleteEntry(new TableEntry<>(permission, Column.Permission.PERMISSION), new TableEntry<>(name,
                Column.Permission.NAME));
    }

    protected void removePermission(String name, String permission, SyncExecute syncExecute) {
        super.deleteEntry(syncExecute, new TableEntry<>(permission, Column.Permission.PERMISSION),
                new TableEntry<>(name, Column.Permission.NAME));
    }

    protected void removePermission(UUID uuid, String permission) {
        this.removePermission(uuid.toString(), permission,
                () -> NetworkChannel.getChannel().sendMessage(new ChannelUserMessage<>(uuid,
                        MessageType.User.PERMISSION)));
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public boolean containsPermission(String name, String permission) {
        return super.getFirst(Column.Permission.ID, new TableEntry<>(name, Column.Permission.NAME),
                new TableEntry<>(permission, Column.Permission.PERMISSION)) != null && super.getFirst(Column.Permission.ID, new TableEntry<>(name, Column.Permission.NAME), new TableEntry<>(permission, Column.Permission.PERMISSION)) != 0;
    }

    public Integer getIdFromName(String name, String permission) {
        return super.getFirst(Column.Permission.ID, new TableEntry<>(name, Column.Permission.NAME),
                new TableEntry<>(permission, Column.Permission.PERMISSION));
    }

    public Collection<Integer> getIdsFromName(String name) {
        return super.get(Column.Permission.ID, new TableEntry<>(name, Column.Permission.NAME));
    }
}
