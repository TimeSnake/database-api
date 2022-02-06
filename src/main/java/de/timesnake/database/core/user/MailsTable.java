package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.user.DbUserMail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MailsTable extends Table {

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

    public void backup() {
        super.createBackup();
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
        return super.addEntryWithAutoIdSynchronized(Column.User.MAIL_ID, new PrimaryEntries(new TableEntry<>(uuid, Column.User.UUID)), new TableEntry<>(uuid, Column.User.UUID), new TableEntry<>(name, Column.User.NAME), new TableEntry<>(senderUuid, Column.User.MAIL_SENDER_UUID), new TableEntry<>(senderName, Column.User.MAIL_SENDER_NAME), new TableEntry<>(msg, Column.User.MAIL_MESSAGE));
    }

    public Collection<DbUserMail> getMails(UUID uuid) {
        Collection<DbUserMail> mails = new ArrayList<>();
        for (Integer id : super.get(Column.User.MAIL_ID, new TableEntry<>(uuid, Column.User.UUID))) {
            DbUserMail mail = this.getMail(uuid, id);
            if (mail != null) {
                mails.add(mail);
            }
        }
        return mails;
    }

    public DbUserMail getMail(UUID uuid, Integer id) {
        String name = super.getFirst(Column.User.NAME, new TableEntry<>(uuid, Column.User.UUID), new TableEntry<>(id, Column.User.MAIL_ID));
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
        return super.getFirst(Column.User.NAME, new TableEntry<>(uuid, Column.User.UUID)) != null;
    }

    public boolean containsPlayer(String name) {
        return super.getFirst(Column.User.UUID, new TableEntry<>(name, Column.User.NAME)) != null;
    }

    public UUID getUniqueIdfromName(String name) {
        return super.getFirst(Column.User.UUID, new TableEntry<>(name, Column.User.NAME));
    }
}
