/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.User;
import de.timesnake.database.core.Entry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.user.DbPunishment;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class PunishmentsTable extends PlayersTable {

    public PunishmentsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(User.PUNISH_TYPE);
        super.addColumn(User.PUNISH_DATE);
        super.addColumn(User.PUNISH_DURATION);
        super.addColumn(User.PUNISH_CASTIGATOR);
        super.addColumn(User.PUNISH_REASON);

        super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
    }

    @Override
    public void backup() {
        super.backup();
    }

    public void setPunishment(UUID uuid, String name, Type.Punishment type, LocalDateTime date,
            Duration duration, String castigator, String reason) {
        super.set(Set.of(new Entry<>(name, Column.User.NAME),
                        new Entry<>(type, Column.User.PUNISH_TYPE),
                        new Entry<>(date, Column.User.PUNISH_DATE),
                        new Entry<>(duration, User.PUNISH_DURATION),
                        new Entry<>(castigator, Column.User.PUNISH_CASTIGATOR),
                        new Entry<>(reason, Column.User.PUNISH_REASON)),
                () -> Channel.getInstance()
                        .sendMessage(new ChannelUserMessage<>(uuid, MessageType.User.PUNISH)),
                new Entry<>(uuid, Column.User.UUID));
    }

    public void setPunishment(DbPunishment punishment) {
        this.setPunishment(punishment.getUniqueId(), punishment.getName(), punishment.getType(),
                punishment.getDate(), punishment.getDuration(), punishment.getCastigator(),
                punishment.getReason());
    }

    public boolean contains(UUID uuid) {
        return super.getFirst(Column.User.PUNISH_TYPE, new Entry<>(uuid, Column.User.UUID)) != null;
    }

}
