package de.timesnake.database.core.server;

import de.timesnake.channel.api.message.ChannelServerMessage;
import de.timesnake.channel.main.NetworkChannel;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Status;
import de.timesnake.database.util.object.TooLongEntryException;

public abstract class DbServer extends TableQuery implements de.timesnake.database.util.server.DbServer {

    public DbServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, nameTable, new TableEntry<>(port, Column.Server.PORT));
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Server.NAME);
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Server.PORT) != null;
    }

    @Override
    public Integer getPort() {
        return (Integer) super.primaryEntries.get(0).getValue();
    }

    @Override
    public Status.Server getStatus() {
        return super.getFirstWithKey(Column.Server.STATUS);
    }

    @Override
    public Integer getOnlinePlayers() {
        return super.getFirstWithKey(Column.Server.ONLINE_PLAYERS);
    }

    @Override
    public Integer getMaxPlayers() {
        return super.getFirstWithKey(Column.Server.MAX_PLAYERS);
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Server.NAME);
    }

    @Override
    public void setStatus(Status.Server status) {
        super.setWithKey(status, Column.Server.STATUS, () -> NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getStatusMessage(this.getPort(), status)));
    }

    @Override
    public void setStatusSynchronized(Status.Server status) {
        super.setWithKeySynchronized(status, Column.Server.STATUS);
        NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getStatusMessage(this.getPort(), status));
    }

    @Override
    public void setOnlinePlayers(int playersOnline) {
        super.setWithKey(playersOnline, Column.Server.ONLINE_PLAYERS, () -> NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getOnlinePlayersMessage(this.getPort(), playersOnline)));
    }

    @Override
    public void setOnlinePlayersSynchronized(int playersOnline) {
        super.setWithKeySynchronized(playersOnline, Column.Server.ONLINE_PLAYERS);
        NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getOnlinePlayersMessage(this.getPort(), playersOnline));
    }

    @Override
    public void setMaxPlayers(int playersMax) {
        super.setWithKey(playersMax, Column.Server.MAX_PLAYERS, () -> NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getMaxPlayersMessage(this.getPort(), playersMax)));
    }

    @Override
    public void setPassword(String password) throws TooLongEntryException {
        if (password.length() > 255) {
            throw new TooLongEntryException(password, Column.Server.PASSWORD.getType());
        }
        super.setWithKey(password, Column.Server.PASSWORD, () -> NetworkChannel.getChannel().sendMessage(ChannelServerMessage.getPasswordMessage(this.getPort(), password)));
    }

    @Override
    public String getPassword() {
        return super.getFirstWithKey(Column.Server.PASSWORD);
    }

    @Override
    public boolean hasPassword() {
        return this.getPassword() != null;
    }

}
