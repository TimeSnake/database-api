/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.channel.util.Channel;
import de.timesnake.channel.util.message.ChannelServerMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbLoungeServer;
import de.timesnake.library.basic.util.ServerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbTmpGameServer extends DbPvPServer implements
    de.timesnake.database.util.server.DbTmpGameServer {

  public DbTmpGameServer(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, name, nameTable);
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
        () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
            MessageType.Server.GAME_MAP, mapName)));
  }

  @Nullable
  @Override
  public String getTwinServerName() {
    return super.getFirstWithKey(Column.Server.TWIN_SERVER);
  }

  @Override
  public void setTwinServerName(String name) {
    super.setWithKeySynchronized(name, Column.Server.TWIN_SERVER);
  }

  @Nullable
  @Override
  public DbLoungeServer getTwinServer() {
    String name = this.getTwinServerName();
    return name == null ? null : Database.getServers().getServer(ServerType.LOUNGE, name);
  }

  @NotNull
  @Override
  public ServerType getType() {
    return ServerType.TEMP_GAME;
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
        () -> Channel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(), MessageType.Server.DISCORD,
            discordEnabled)));
  }
}
