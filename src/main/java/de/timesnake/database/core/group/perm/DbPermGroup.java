package de.timesnake.database.core.group.perm;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelGroupMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.group.DbGroup;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;

import java.util.ArrayList;
import java.util.Collection;

public class DbPermGroup extends DbGroup implements de.timesnake.database.util.group.DbPermGroup {

    public DbPermGroup(DatabaseConnector databaseConnector, int rank, String nameTable) {
        super(databaseConnector, rank, nameTable);
    }

    @Override
    public void setInheritance(String inheritGroup, SyncExecute syncExecute) {
        super.setWithKey(inheritGroup, Column.PermGroup.INHERITANCE, syncExecute);
    }

    @Override
    public void removeInheritance() {
        super.setWithKey(null, Column.PermGroup.INHERITANCE,
                () -> NetworkChannel.getChannel().sendMessage(new ChannelGroupMessage<>(this.getName(),
                        MessageType.Group.PERMISSION)));
    }

    @Override
    public void removeInheritance(SyncExecute syncExecute) {
        super.setWithKey(null, Column.PermGroup.INHERITANCE, syncExecute);
    }

    @Override
    public de.timesnake.database.util.group.DbPermGroup getInheritance() {
        String inherit = super.getFirstWithKey(Column.PermGroup.INHERITANCE);
        if (inherit != null) {
            return Database.getGroups().getPermGroup(inherit);
        }
        return null;
    }

    @Override
    public void setInheritance(String inheritGroup) {
        super.setWithKey(inheritGroup, Column.PermGroup.INHERITANCE);
    }

    @Override
    public Collection<de.timesnake.database.util.group.DbPermGroup> getGroupsInherit() {
        Collection<de.timesnake.database.util.group.DbPermGroup> groups = new ArrayList<>();
        for (Integer rank : super.get(Column.Group.RANK, new TableEntry<>(this.getName(),
                Column.PermGroup.INHERITANCE))) {
            de.timesnake.database.util.group.DbPermGroup group = Database.getGroups().getPermGroup(rank);
            if (group.exists()) {
                groups.add(group);
            }
        }
        return groups;
    }

    @Override
    public Collection<DbPermission> getPermissions() {
        return Database.getPermissions().getPermissions(super.getName());
    }

    @Override
    public DbPermission getPermission(String permission) {
        return Database.getPermissions().getPermission(super.getName(), permission);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers) {
        Database.getPermissions().addPermission(super.getName(), permission, mode, syncExecute, servers);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, String... servers) {
        Database.getPermissions().addPermission(super.getName(), permission, mode, servers);
    }

    @Override
    public boolean hasPermission(String permission) {
        return Database.getPermissions().containsPermission(super.getName(), permission);
    }

    @Override
    public void removePermission(String permission) {
        Database.getPermissions().deletePermission(super.getName(), permission);
    }

    @Override
    public void removePermission(String permission, SyncExecute syncExecute) {
        Database.getPermissions().deletePermission(super.getName(), permission, syncExecute);
    }

    @Override
    public de.timesnake.database.util.group.DbPermGroup toLocal() {
        return new DbCachedPermGroup(this);
    }

    @Override
    public de.timesnake.database.util.group.DbPermGroup toDatabase() {
        return this;
    }

}
