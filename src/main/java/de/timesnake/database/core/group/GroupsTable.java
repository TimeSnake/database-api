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

package de.timesnake.database.core.group;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelMessage;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.chat.ExTextColor;

public class GroupsTable extends GroupBasisTable {

    protected GroupsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Group.PREFIX);
        super.addColumn(Column.Group.PREFIX_COLOR);
    }

    protected void addGroup(String name, Integer rank, String prefix, ExTextColor color) {
        super.addEntry(new PrimaryEntries(new Entry<>(rank, Column.Group.PRIORITY)), new Entry<>(name,
                        Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
                new Entry<>(color, Column.Group.PREFIX_COLOR));
    }


    protected void addGroup(String name, Integer rank, String prefix, ExTextColor color, SyncExecute syncExecute) {
        super.addEntry(new PrimaryEntries(new Entry<>(rank, Column.Group.PRIORITY)), syncExecute,
                new Entry<>(name, Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
                new Entry<>(color, Column.Group.PREFIX_COLOR));
    }

    protected void addGroup(String name, Integer rank, String prefix, ExTextColor color,
                            ChannelMessage<?, ?> channelMessage) {
        super.addEntry(new PrimaryEntries(new Entry<>(rank, Column.Group.PRIORITY)),
                () -> Channel.getInstance().sendMessage(channelMessage), new Entry<>(name,
                        Column.Group.NAME), new Entry<>(prefix, Column.Group.PREFIX),
                new Entry<>(color, Column.Group.PREFIX_COLOR));
    }
}
