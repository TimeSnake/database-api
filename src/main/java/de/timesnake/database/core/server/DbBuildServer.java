/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.channel.core.ServerChannel;
import de.timesnake.channel.util.message.ChannelServerMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.ServerType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class DbBuildServer extends DbTaskServer implements
    de.timesnake.database.util.server.DbBuildServer {

  private final BuildWorldTable buildWorldTable;

  public DbBuildServer(DatabaseConnector databaseConnector, String name, String nameTable,
                       BuildWorldTable buildWorldTable) {
    super(databaseConnector, name, nameTable);
    this.buildWorldTable = buildWorldTable;
  }

  @NotNull
  @Override
  public ServerType getType() {
    return ServerType.BUILD;
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
          ServerChannel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
              MessageType.Server.LOADED_WORLD, worldName));
        });
  }

  @Override
  public void removeWorld(String worldName) {
    this.buildWorldTable.removeWorld(worldName,
        () -> ServerChannel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
            MessageType.Server.UNLOADED_WORLD, worldName)));
  }

  @Override
  public void clearWorlds() {
    this.buildWorldTable.removeServer(this.getName(),
        () -> ServerChannel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
            MessageType.Server.UNLOADED_ALL_WORLDS)));
  }

  @Override
  public void clearWorlds(SyncExecute syncExecute) {
    this.buildWorldTable.removeServer(this.getName(),
        () -> {
          ServerChannel.getInstance().sendMessage(new ChannelServerMessage<>(this.getName(),
              MessageType.Server.UNLOADED_ALL_WORLDS));
          syncExecute.run();
        });
  }
}
