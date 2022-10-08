/*
 * database-api.main
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

public class GroupBasisTable extends TableDDL {

    protected GroupBasisTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.Group.NAME);
        super.addColumn(Column.Group.PRIORITY);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    protected void addGroup(String name, Integer rank) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(name, Column.Group.NAME)), new TableEntry<>(rank, Column.Group.PRIORITY));
    }

    protected void addGroup(String name, Integer rank, SyncExecute syncExecute) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(name, Column.Group.NAME)), syncExecute,
                new TableEntry<>(rank, Column.Group.PRIORITY));
    }

    protected void addGroup(String name, Integer rank, ChannelMessage<?, ?> channelMessage) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(name, Column.Group.NAME)),
                () -> NetworkChannel.getChannel().sendMessage(channelMessage), new TableEntry<>(rank, Column.Group.PRIORITY));
    }

    protected boolean containsGroup(String name) {
        return super.getFirst(Column.Group.PRIORITY, new TableEntry<>(name, Column.Group.NAME)) != null
                && super.getFirst(Column.Group.PRIORITY, new TableEntry<>(name, Column.Group.NAME)) != 0;
    }

    protected void removeGroup(String name) {
        this.deleteEntry(new TableEntry<>(name, Column.Group.NAME));
    }

    protected void removeGroup(String name, ChannelMessage<?, ?> channelMessage) {
        this.deleteEntry(() -> NetworkChannel.getChannel().sendMessage(channelMessage), new TableEntry<>(name, Column.Group.NAME));
    }

    protected Collection<String> getGroupNames() {
        return super.get(Column.Group.NAME);
    }

    protected Collection<Integer> getGroupRanks() {
        return super.get(Column.Group.PRIORITY);
    }

}
