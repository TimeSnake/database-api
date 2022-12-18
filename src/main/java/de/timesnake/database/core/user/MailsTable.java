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

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.user.DbUserMail;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MailsTable extends TableDDL {

    public MailsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.User.UUID, Column.User.MAIL_ID);
        super.addColumn(Column.User.NAME);
        super.addColumn(Column.User.MAIL_SENDER_UUID);
        super.addColumn(Column.User.MAIL_SENDER_NAME);
        super.addColumn(Column.User.MAIL_MESSAGE);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public Collection<UUID> getPlayerUniqueIds() {
        return super.get(Column.User.UUID);
    }

    public ArrayList<String> getPlayerNames() {
        ArrayList<String> names = new ArrayList<>();
        for (String dbName : super.get(Column.User.NAME)) {
            names.add(dbName.toLowerCase());
        }
        return names;
    }

    public Integer addMessage(UUID uuid, String name, UUID senderUuid, String senderName, String msg) throws TooLongEntryException {
        if (msg.length() > 255) {
            throw new TooLongEntryException(msg, Column.User.MAIL_MESSAGE.getType());
        }
        return super.addEntryWithAutoIdSynchronized(Column.User.MAIL_ID, new PrimaryEntries(new Entry<>(uuid,
                        Column.User.UUID)), new Entry<>(uuid, Column.User.UUID), new Entry<>(name,
                        Column.User.NAME), new Entry<>(senderUuid, Column.User.MAIL_SENDER_UUID),
                new Entry<>(senderName, Column.User.MAIL_SENDER_NAME), new Entry<>(msg,
                        Column.User.MAIL_MESSAGE));
    }

    public Collection<DbUserMail> getMails(UUID uuid) {
        Collection<DbUserMail> mails = new ArrayList<>();
        for (Integer id : super.get(Column.User.MAIL_ID, new Entry<>(uuid, Column.User.UUID))) {
            DbUserMail mail = this.getMail(uuid, id);
            if (mail != null) {
                mails.add(mail);
            }
        }
        return mails;
    }

    @Nullable
    public DbUserMail getMail(UUID uuid, Integer id) {
        String name = super.getFirst(Column.User.NAME, new Entry<>(uuid, Column.User.UUID), new Entry<>(id,
                Column.User.MAIL_ID));
        if (name != null) {
            return new de.timesnake.database.core.user.DbUserMail(this.databaseConnector, super.tableName, uuid, id);
        }
        return null;
    }

    public boolean removeMail(UUID uuid, Integer id) {
        DbUserMail mail = this.getMail(uuid, id);
        if (mail != null) {
            mail.delete();
            return true;
        }
        return false;
    }

    public boolean containsPlayer(UUID uuid) {
        return super.getFirst(Column.User.NAME, new Entry<>(uuid, Column.User.UUID)) != null;
    }

    public boolean containsPlayer(String name) {
        return super.getFirst(Column.User.UUID, new Entry<>(name, Column.User.NAME)) != null;
    }

    public UUID getUniqueIdfromName(String name) {
        return super.getFirst(Column.User.UUID, new Entry<>(name, Column.User.NAME));
    }
}
