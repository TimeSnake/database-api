/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.user.DbUserMail;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MailTable extends DefinitionAndQueryTool {

  public MailTable(DatabaseConnector databaseConnector, String nameTable) {
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
  public void save() {
    super.save();
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

  public Integer addMessage(UUID uuid, String name, UUID senderUuid, String senderName, String msg)
      throws TooLongEntryException {
    if (msg.length() > 255) {
      throw new TooLongEntryException(msg, Column.User.MAIL_MESSAGE.getType());
    }
    return super.addEntryWithAutoIdSynchronized(Column.User.MAIL_ID,
        new PrimaryKeyEntries(new Entry<>(uuid,
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
    String name = super.getFirst(Column.User.NAME, new Entry<>(uuid, Column.User.UUID),
        new Entry<>(id,
            Column.User.MAIL_ID));
    if (name != null) {
      return new de.timesnake.database.core.user.DbUserMail(this.databaseConnector, super.tableName,
          uuid, id);
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
