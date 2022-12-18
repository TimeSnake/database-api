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

package de.timesnake.database.core.permisson;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.Nullable;

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
        super.addEntryWithAutoIdSynchronized(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME),
                new Entry<>(permission, Column.Permission.PERMISSION),
                new Entry<>(new DbStringArrayList(servers), Column.Permission.SERVER), new Entry<>(mode,
                        Column.Permission.MODE));
    }

    protected void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute,
                                 String... servers) {
        super.addEntryWithAutoId(Column.Permission.ID, syncExecute, new Entry<>(name, Column.Permission.NAME),
                new Entry<>(permission, Column.Permission.PERMISSION),
                new Entry<>(new DbStringArrayList(servers), Column.Permission.SERVER), new Entry<>(mode,
                        Column.Permission.MODE));
    }

    protected void addPermission(UUID uuid, String permission, Status.Permission mode, String... servers) {
        this.addPermission(uuid.toString(), permission, mode,
                () -> Channel.getInstance().sendMessage(new ChannelUserMessage<>(uuid,
                        MessageType.User.PERMISSION)), servers);
    }

    protected void removePermission(String name, String permission) {
        super.deleteEntry(new Entry<>(permission, Column.Permission.PERMISSION), new Entry<>(name,
                Column.Permission.NAME));
    }

    protected void removePermission(String name, String permission, SyncExecute syncExecute) {
        super.deleteEntry(syncExecute, new Entry<>(permission, Column.Permission.PERMISSION),
                new Entry<>(name, Column.Permission.NAME));
    }

    protected void removePermission(UUID uuid, String permission) {
        this.removePermission(uuid.toString(), permission,
                () -> Channel.getInstance().sendMessage(new ChannelUserMessage<>(uuid,
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
        return super.getFirst(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME),
                new Entry<>(permission, Column.Permission.PERMISSION)) != null && super.getFirst(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME), new Entry<>(permission, Column.Permission.PERMISSION)) != 0;
    }

    public Integer getIdFromName(String name, String permission) {
        return super.getFirst(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME),
                new Entry<>(permission, Column.Permission.PERMISSION));
    }

    @Nullable
    public Collection<Integer> getIdsFromName(String name) {
        return super.get(Column.Permission.ID, new Entry<>(name, Column.Permission.NAME));
    }
}
