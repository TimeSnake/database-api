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

package de.timesnake.database.core.user;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.user.DbPunishment;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class PunishmentsTable extends PlayersTable {

    public PunishmentsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.User.PUNISH_TYPE);
        super.addColumn(Column.User.PUNISH_DATE);
        super.addColumn(Column.User.PUNISH_CASTIGATOR);
        super.addColumn(Column.User.PUNISH_REASON);
        super.addColumn(Column.User.PUNISH_SERVER);

        super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
    }

    @Override
    public void backup() {
        super.backup();
    }

    public void setPunishment(UUID uuid, String name, Type.Punishment type, LocalDateTime date, String castigator,
                              String reason, String server) {
        super.set(Set.of(new Entry<>(name, Column.User.NAME),
                        new Entry<>(type, Column.User.PUNISH_TYPE),
                        new Entry<>(date, Column.User.PUNISH_DATE),
                        new Entry<>(castigator, Column.User.PUNISH_CASTIGATOR),
                        new Entry<>(reason, Column.User.PUNISH_REASON),
                        new Entry<>(server, Column.User.PUNISH_SERVER)),
                () -> Channel.getInstance().sendMessage(new ChannelUserMessage<>(uuid, MessageType.User.PUNISH)),
                new Entry<>(uuid, Column.User.UUID));
    }

    public void setPunishment(DbPunishment punishment) {
        this.setPunishment(punishment.getUniqueId(), punishment.getName(), punishment.getType(), punishment.getDate()
                , punishment.getCastigator(), punishment.getReason(), punishment.getServer());
    }

    public boolean contains(UUID uuid) {
        return super.getFirst(Column.User.PUNISH_TYPE, new Entry<>(uuid, Column.User.UUID)) != null;
    }

}
