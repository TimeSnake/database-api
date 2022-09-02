package de.timesnake.database.core.server;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelServerMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.object.Type;

import java.util.Collection;

public class DbBuildServer extends DbTaskServer implements de.timesnake.database.util.server.DbBuildServer {

    private final BuildWorldTable buildWorldTable;

    public DbBuildServer(DatabaseConnector databaseConnector, Integer port, String nameTable, BuildWorldTable buildWorldTable) {
        super(databaseConnector, port, nameTable);
        this.buildWorldTable = buildWorldTable;
    }

    @Override
    public Type.Server<de.timesnake.database.util.server.DbBuildServer> getType() {
        return Type.Server.BUILD;
    }

    @Override
    public Collection<String> getWorldNames() {
        return this.buildWorldTable.getWorldNames(this.getName());
    }

    @Override
    public void addWorld(String worldName) {
        this.buildWorldTable.addWorld(this.getName(), worldName,
                () -> {
                    NetworkChannel.getChannel().sendMessage(new ChannelServerMessage<>(this.getName(),
                            MessageType.Server.LOADED_WORLD, worldName));
                });
    }

    @Override
    public void removeWorld(String worldName) {
        this.buildWorldTable.removeWorld(worldName,
                () -> NetworkChannel.getChannel().sendMessage(new ChannelServerMessage<>(this.getName(),
                        MessageType.Server.UNLOADED_WORLD, worldName)));
    }

    @Override
    public void clearWorlds() {
        this.buildWorldTable.removeServer(this.getName(),
                () -> NetworkChannel.getChannel().sendMessage(new ChannelServerMessage<>(this.getName(),
                        MessageType.Server.UNLOADED_ALL_WORLDS)));
    }

    @Override
    public void clearWorlds(SyncExecute syncExecute) {
        this.buildWorldTable.removeServer(this.getName(),
                () -> {
                    NetworkChannel.getChannel().sendMessage(new ChannelServerMessage<>(this.getName(),
                            MessageType.Server.UNLOADED_ALL_WORLDS));
                    syncExecute.run();
                });
    }
}
