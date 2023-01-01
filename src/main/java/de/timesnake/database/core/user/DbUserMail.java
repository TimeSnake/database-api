/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DbUserMail extends TableQuery implements de.timesnake.database.util.user.DbUserMail {

    public DbUserMail(DatabaseConnector databaseConnector, String nameTable, UUID uuid, Integer id) {
        super(databaseConnector, nameTable, new Entry<>(uuid, Column.User.UUID), new Entry<>(id,
                Column.User.MAIL_ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.User.MAIL_ID) != null;
    }

    @NotNull
    @Override
    public Integer getId() {
        return (Integer) super.primaryEntries.get(1).getValue();
    }

    @Nullable
    @Override
    public UUID getUniqueId() {
        return (UUID) super.primaryEntries.get(0).getValue();
    }

    @Nullable
    @Override
    public String getName() {
        return super.getFirst(Column.User.NAME);
    }

    @Nullable
    @Override
    public UUID getSenderUniqueId() {
        return super.getFirst(Column.User.MAIL_SENDER_UUID);
    }

    @Nullable
    @Override
    public String getSenderName() {
        return super.getFirst(Column.User.MAIL_SENDER_NAME);
    }

    @Nullable
    @Override
    public String getMessage() {
        return super.getFirst(Column.User.MAIL_MESSAGE);
    }

    @Override
    public void delete() {
        super.deleteEntry();
    }
}
