/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import java.time.LocalDateTime;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class DbPunishment extends DbPlayer implements de.timesnake.database.util.user.DbPunishment {

    public DbPunishment(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
        super(databaseConnector, uuid, nameTable);
    }

    @Override
    public void delete() {
        super.deleteWithKey(() -> Channel.getInstance().sendMessage(
                new ChannelUserMessage<>(this.getUniqueId(), MessageType.User.PUNISH)));
    }

    @Nullable
    @Override
    public Type.Punishment getType() {
        return super.getFirstWithKey(Column.User.PUNISH_TYPE);
    }

    @Override
    public void setType(Type.Punishment type) {
        super.setWithKey(type, Column.User.PUNISH_TYPE);
    }

    @Nullable
    @Override
    public LocalDateTime getDate() {
        return super.getFirstWithKey(Column.User.PUNISH_DATE);
    }

    @Override
    public void setDate(LocalDateTime date) {
        super.setWithKey(date, Column.User.PUNISH_DATE);
    }

    @Nullable
    @Override
    public String getCastigator() {
        return super.getFirstWithKey(Column.User.PUNISH_CASTIGATOR);
    }

    @Override
    public void setCastigator(String castigator) {
        super.setWithKey(castigator, Column.User.PUNISH_CASTIGATOR);
    }

    @Nullable
    @Override
    public String getReason() {
        return super.getFirstWithKey(Column.User.PUNISH_REASON);
    }

    @Override
    public void setReason(String reason) {
        super.setWithKey(reason, Column.User.PUNISH_REASON);
    }

    @Nullable
    @Override
    public String getServer() {
        return super.getFirstWithKey(Column.User.PUNISH_SERVER);
    }

    @Override
    public void setServer(String server) {
        super.setWithKey(server, Column.User.PUNISH_SERVER);
    }

}
