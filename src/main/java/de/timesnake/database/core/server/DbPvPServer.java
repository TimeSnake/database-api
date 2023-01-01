/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelServerMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public abstract class DbPvPServer extends DbTaskServer implements de.timesnake.database.util.server.DbPvPServer {

    public DbPvPServer(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @Override
    public boolean isOldPvP() {
        return super.getFirstWithKey(Column.Server.OLD_PVP);
    }


    @Override
    public void setPvP(boolean oldPvP) {
        super.setWithKey(oldPvP, Column.Server.OLD_PVP,
                () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
                        MessageType.Server.OLD_PVP, oldPvP)));
    }
}
