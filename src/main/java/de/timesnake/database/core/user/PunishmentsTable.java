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
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.user.DbPunishment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PunishmentsTable extends PlayersTable {

    public PunishmentsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.User.PUNISH_TYPE);
        super.addColumn(Column.User.PUNISH_DATE);
        super.addColumn(Column.User.PUNISH_CASTIGATOR);
        super.addColumn(Column.User.PUNISH_REASON);
        super.addColumn(Column.User.PUNISH_SERVER);
    }

    @Override
    public void backup() {
        super.backup();
    }

    public void setPunishment(UUID uuid, String name, Type.Punishment type, Date date, String castigator,
                              String reason, String server) {
        if (super.getFirst(Column.Support.NAME, new TableEntry<>(uuid, Column.User.UUID)) == null) {
            super.addPlayer(uuid, name);
        }
        super.set(type.getDatabaseValue(), Column.User.PUNISH_TYPE, new TableEntry<>(uuid, Column.User.UUID));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        super.set(df.format(date), Column.User.PUNISH_DATE, new TableEntry<>(uuid, Column.User.UUID));
        super.set(castigator, Column.User.PUNISH_CASTIGATOR, new TableEntry<>(uuid, Column.User.UUID));
        super.set(reason, Column.User.PUNISH_REASON, new TableEntry<>(uuid, Column.User.UUID));
        super.set(server, Column.User.PUNISH_SERVER,
                () -> Channel.getInstance().sendMessage(new ChannelUserMessage<>(uuid, MessageType.User.PUNISH)), new TableEntry<>(uuid, Column.User.UUID));
    }

    public void setPunishment(DbPunishment punishment) {
        this.setPunishment(punishment.getUniqueId(), punishment.getName(), punishment.getType(), punishment.getDate()
                , punishment.getCastigator(), punishment.getReason(), punishment.getServer());
    }

    public boolean contains(UUID uuid) {
        return super.getFirst(Column.User.PUNISH_TYPE, new TableEntry<>(uuid, Column.User.UUID)) != null;
    }

}
