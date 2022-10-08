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
import de.timesnake.database.core.Column;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.server.DbLoungeServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbTmpGameServer extends DbPvPServer implements de.timesnake.database.util.server.DbTmpGameServer {

    public DbTmpGameServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public boolean areKitsEnabled() {
        return super.getFirstWithKey(Column.Server.KITS);
    }

    @Override
    public void setKitsEnabled(boolean areKitsEnabled) {
        super.setWithKey(areKitsEnabled, Column.Server.KITS);
    }

    @Nullable
    @Override
    public String getMapName() {
        return super.getFirstWithKey(Column.Server.MAP_NAME);
    }

    @Override
    public void setMapName(String mapName) {
        super.setWithKey(mapName, Column.Server.MAP_NAME,
                () -> NetworkChannel.getChannel().sendMessage(new ChannelServerMessage<>(this.getName(),
                        MessageType.Server.MAP, mapName)));
    }

    @Nullable
    @Override
    public Integer getTwinServerPort() {
        return super.getFirstWithKey(Column.Server.TWIN_SERVER);
    }

    @Override
    public void setTwinServerPort(Integer port) {
        super.setWithKeySynchronized(port, Column.Server.TWIN_SERVER);
    }

    @Nullable
    @Override
    public DbLoungeServer getTwinServer() {
        Integer port = this.getTwinServerPort();
        return port == null ? null : Database.getServers().getServer(Type.Server.LOUNGE, port);
    }

    @NotNull
    @Override
    public Type.Server<de.timesnake.database.util.server.DbTmpGameServer> getType() {
        return Type.Server.TEMP_GAME;
    }

    @Override
    public boolean areMapsEnabled() {
        return super.getFirstWithKey(Column.Server.MAPS);
    }

    @Override
    public void setMapsEnabled(boolean mapsEnabled) {
        super.setWithKey(mapsEnabled, Column.Server.MAPS);
    }

    @Nullable
    @Override
    public Integer getTeamAmount() {
        return super.getFirstWithKey(Column.Server.TEAM_AMOUNT);
    }

    @Override
    public void setTeamAmount(Integer amount) {
        super.setWithKey(amount, Column.Server.TEAM_AMOUNT);
    }

    @Nullable
    @Override
    public Integer getMaxPlayersPerTeam() {
        return super.getFirstWithKey(Column.Server.TEAM_MAX_PLAYERS);
    }

    @Override
    public void setMaxPlayersPerTeam(Integer maxPlayersPerTeam) {
        super.setWithKey(maxPlayersPerTeam, Column.Server.TEAM_MAX_PLAYERS);
    }

    @Override
    public boolean isTeamMerging() {
        return super.getFirstWithKey(Column.Server.TEAM_MERGING);
    }

    @Override
    public void setTeamMerging(boolean teamMerging) {
        super.setWithKey(teamMerging, Column.Server.TEAM_MERGING);
    }

    @Override
    public boolean isDiscordEnabled() {
        return super.getFirstWithKey(Column.Server.DISCORD);
    }

    @Override
    public void setDiscord(boolean discordEnabled) {
        super.setWithKey(discordEnabled, Column.Server.DISCORD,
                () -> NetworkChannel.getChannel().sendMessage(new ChannelServerMessage<>(this.getName(),
                        MessageType.Server.DISCORD, discordEnabled)));
    }
}
