/*
 * workspace.database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.group.perm;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelGroupMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.group.DbGroupBasis;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class DbPermGroup extends DbGroupBasis implements de.timesnake.database.util.group.DbPermGroup {

    public DbPermGroup(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @Override
    public void setInheritance(String inheritGroup, SyncExecute syncExecute) {
        super.setWithKey(inheritGroup, Column.Group.INHERITANCE, syncExecute);
    }

    @Override
    public void removeInheritance() {
        super.setWithKey(null, Column.Group.INHERITANCE,
                () -> Channel.getInstance().sendMessage(new ChannelGroupMessage<>(this.getName(),
                        MessageType.Group.PERMISSION)));
    }

    @Override
    public void removeInheritance(SyncExecute syncExecute) {
        super.setWithKey(null, Column.Group.INHERITANCE, syncExecute);
    }

    @Nullable
    @Override
    public de.timesnake.database.util.group.DbPermGroup getInheritance() {
        String inherit = super.getFirstWithKey(Column.Group.INHERITANCE);
        if (inherit != null) {
            return Database.getGroups().getPermGroup(inherit);
        }
        return null;
    }

    @Override
    public void setInheritance(String inheritGroup) {
        super.setWithKey(inheritGroup, Column.Group.INHERITANCE);
    }

    @NotNull
    @Override
    public Collection<de.timesnake.database.util.group.DbPermGroup> getGroupsInherit() {
        Collection<de.timesnake.database.util.group.DbPermGroup> groups = new ArrayList<>();
        for (String name : super.get(Column.Group.NAME, new Entry<>(this.getName(),
                Column.Group.INHERITANCE))) {
            de.timesnake.database.util.group.DbPermGroup group = Database.getGroups().getPermGroup(name);
            if (group.exists()) {
                groups.add(group);
            }
        }
        return groups;
    }

    @NotNull
    @Override
    public Collection<DbPermission> getPermissions() {
        return Database.getPermissions().getPermissions(super.getName());
    }

    @Nullable
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

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbPermGroup toLocal() {
        return new DbCachedPermGroup(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbPermGroup toDatabase() {
        return this;
    }

}
