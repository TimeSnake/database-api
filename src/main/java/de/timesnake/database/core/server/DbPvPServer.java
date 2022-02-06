package de.timesnake.database.core.server;

import de.timesnake.channel.api.message.ChannelServerMessage;
import de.timesnake.channel.main.NetworkChannel;
import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public abstract class DbPvPServer extends DbTaskServer implements de.timesnake.database.util.server.DbPvPServer {

    public DbPvPServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public boolean isOldPvP() {
        return super.getFirstWithKey(Column.Server.OLD_PVP);
    }


    @Override
    public void setPvP(boolean oldPvP) {
        super.setWithKey(oldPvP, Column.Server.OLD_PVP, () -> NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getPvPMessage(this.getPort(), oldPvP)));
    }
}
