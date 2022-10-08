/*
 * database-api.main
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

package de.timesnake.database.core.server;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelServerMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class DbBuildServer extends DbTaskServer implements de.timesnake.database.util.server.DbBuildServer {

    private final BuildWorldTable buildWorldTable;

    public DbBuildServer(DatabaseConnector databaseConnector, Integer port, String nameTable, BuildWorldTable buildWorldTable) {
        super(databaseConnector, port, nameTable);
        this.buildWorldTable = buildWorldTable;
    }

    @NotNull
    @Override
    public Type.Server<de.timesnake.database.util.server.DbBuildServer> getType() {
        return Type.Server.BUILD;
    }

    @NotNull
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
