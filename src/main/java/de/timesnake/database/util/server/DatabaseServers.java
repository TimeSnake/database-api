/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.library.basic.util.ServerType;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

public interface DatabaseServers {

  @Nullable <S extends DbServer> ServerType getServerType(int port);

  @Nullable <S extends DbServer> ServerType getServerType(String name);

  @Nullable <Server extends DbServer> Server getServer(int port);

  @Nullable <Server extends DbServer> Server getServer(String name);

  @NotNull
  Collection<Integer> getPorts();

  @Nullable <Server extends DbServer> Server getServer(ServerType type, int port);

  @Nullable <Server extends DbServer> Server getServer(ServerType type, String name);

  void removeServer(ServerType type, String name);

  boolean containsServer(ServerType type, String name);

  @NotNull <Server extends DbServer> Collection<Server> getServers(ServerType type, Status.Server status);

  @NotNull <Server extends DbServer> Collection<Server> getServers(ServerType type);

  @NotNull <Server extends DbTaskServer> Collection<Server> getServers(ServerType type, String task);

  @NotNull
  Collection<String> getServerNames(ServerType type);

  @NotNull
  Collection<Integer> getServerPorts(ServerType type);

  void addLobby(String name, int port, Status.Server status, Path folderPath);

  void addGame(String name, int port, String task, Status.Server status, Path folderPath);

  void addLounge(String name, int port, Status.Server status, Path folderPath);

  void addTempGame(String name, int port, String task, Status.Server status, Path folderPath);

  void addBuild(String name, int port, String task, Status.Server status, Path folderPath);

  @NotNull
  Set<String> getBuildWorlds();

  @Nullable
  String getBuildServerByWorld(String worldName);
}
