/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.server.DbTaskServer;
import de.timesnake.library.basic.util.ServerType;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.*;

public class DatabaseServers extends DatabaseConnector implements
    de.timesnake.database.util.server.DatabaseServers {

  private final HashMap<ServerType, ServerTable<?>> serverTables = new HashMap<>();

  private final BuildWorldTable buildWorldTable;

  public DatabaseServers(String name, String url, String options, String user, String password,
      String lobbysTableName,
      String gamesTableName, String loungesTableName, String tempGamesTableName,
      String buildsTableName, String buildWorldTableName) {
    super(name, url, options, user, password, DatabaseManager.SERVERS_MAX_IDLE_CONNECTIONS);

    this.buildWorldTable = new BuildWorldTable(this, buildWorldTableName);

    this.serverTables.put(ServerType.LOBBY, new LobbyTable(this, lobbysTableName));
    this.serverTables.put(ServerType.GAME, new NonTmpGameTable(this, gamesTableName));
    this.serverTables.put(ServerType.LOUNGE, new LoungeTable(this, loungesTableName));
    this.serverTables.put(ServerType.TEMP_GAME, new TmpGameTable(this, tempGamesTableName));
    this.serverTables.put(ServerType.BUILD, new BuildTable(this, buildsTableName, this.buildWorldTable));
  }

  @Override
  public void createTables() {
    for (ServerTable<? extends DbServer> serverTable : this.serverTables.values()) {
      serverTable.create();
    }
    this.buildWorldTable.create();
  }

  @Override
  public void backupTables() {
    for (ServerTable<? extends DbServer> serverTable : this.serverTables.values()) {
      serverTable.backup();
    }
    this.buildWorldTable.backup();
  }

  @Override
  public @Nullable ServerType getServerType(int port) {
    for (Map.Entry<ServerType, ServerTable<? extends DbServer>> entry : this.serverTables.entrySet()) {
      String name = entry.getValue().getNameFromPort(port);
      if (name != null && entry.getValue().getServer(name) != null && entry.getValue()
          .getServer(name).exists()) {
        return entry.getKey();
      }
    }
    return null;
  }

  @Nullable
  @Override
  public ServerType getServerType(String name) {
    for (Map.Entry<ServerType, ServerTable<? extends DbServer>> entry : this.serverTables.entrySet()) {
      if (entry.getValue().getServer(name) != null && entry.getValue().getServer(name).exists()) {
        return entry.getKey();
      }
    }
    return null;
  }

  @Nullable
  @Override
  public <Server extends DbServer> Server getServer(String name) {
    return this.getServer(this.getServerType(name), name);
  }

  @Nullable
  @Override
  public <Server extends DbServer> Server getServer(int port) {
    return this.getServer(this.getServerType(port), port);
  }

  @NotNull
  @Override
  public Collection<Integer> getPorts() {
    ArrayList<Integer> ports = new ArrayList<>();
    for (ServerType type : ServerType.values()) {
      ports.addAll(this.getServerPorts(type));
    }
    return ports;
  }

  @Nullable
  @Override
  public <Server extends DbServer> Server getServer(ServerType type, int port) {
    ServerTable<?> table = this.serverTables.get(type);
    if (table != null) {
      return (Server) table.getServer(table.getNameFromPort(port));
    }
    return null;
  }

  @Nullable
  @Override
  public <Server extends DbServer> Server getServer(ServerType type, String name) {
    ServerTable<?> table = this.serverTables.get(type);
    if (table != null) {
      return (Server) table.getServer(name);
    }
    return null;
  }

  @Deprecated
  @Override
  public void removeServer(ServerType type, String name) {
    ServerTable<? extends DbServer> table = this.serverTables.get(type);
    if (table != null) {
      table.removeServer(name);
    }
  }

  @Override
  public boolean containsServer(ServerType type, String name) {
    ServerTable<? extends DbServer> table = this.serverTables.get(type);
    if (table != null) {
      return table.containsServer(name);
    }
    return false;
  }

  @NotNull
  @Override
  public <Server extends DbServer> Collection<Server> getServers(ServerType type, Status.Server status) {
    ServerTable<?> table = this.serverTables.get(type);
    if (table != null) {
      return (Collection<Server>) table.getServers(status);
    }
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public <Server extends DbServer> Collection<Server> getServers(ServerType type) {
    ServerTable<?> table = this.serverTables.get(type);
    if (table != null) {
      return (Collection<Server>) table.getServers();
    }
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public <Server extends DbTaskServer> Collection<Server> getServers(ServerType type, String task) {
    ServerTable<?> table = this.serverTables.get(type);
    if (table instanceof TaskTable) {
      return ((TaskTable<Server>) table).getServers(task);
    }
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public Collection<String> getServerNames(ServerType type) {
    ServerTable<? extends DbServer> table = this.serverTables.get(type);
    if (table != null) {
      return table.getServerNames();
    }
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public Collection<Integer> getServerPorts(ServerType type) {
    ServerTable<? extends DbServer> table = this.serverTables.get(type);
    if (table != null) {
      return table.getServerPorts();
    }
    return new ArrayList<>();
  }

  @Override
  public void addLobby(String name, int port, Status.Server status, Path folderPath) {
    ServerTable<? extends DbServer> table = this.serverTables.get(ServerType.LOBBY);
    if (table != null) {
      table.addServer(name, port, status, folderPath);
    }

  }

  @Override
  public void addGame(String name, int port, String task, Status.Server status, Path folderPath) {
    ServerTable<? extends DbServer> table = this.serverTables.get(ServerType.GAME);
    if (table != null) {
      ((NonTmpGameTable) table).addServer(name, port, task, status, folderPath);
    }
  }

  @Override
  public void addLounge(String name, int port, Status.Server status, Path folderPath) {
    ServerTable<? extends DbServer> table = this.serverTables.get(ServerType.LOUNGE);
    if (table != null) {
      table.addServer(name, port, status, folderPath);
    }
  }

  @Override
  public void addTempGame(String name, int port, String task, Status.Server status,
      Path folderPath) {
    ServerTable<? extends DbServer> table = this.serverTables.get(ServerType.TEMP_GAME);
    if (table != null) {
      ((TmpGameTable) table).addServer(name, port, task, status, folderPath);
    }
  }

  @Override
  public void addBuild(String name, int port, String task, Status.Server status,
      Path folderPath) {
    ServerTable<? extends DbServer> table = this.serverTables.get(ServerType.BUILD);
    if (table != null) {
      ((BuildTable) table).addServer(name, port, task, status, folderPath);
    }
  }

  @NotNull
  @Override
  public Set<String> getBuildWorlds() {
    return this.buildWorldTable.getWorldNames();
  }

  @Nullable
  @Override
  public String getBuildServerByWorld(String worldName) {
    return this.buildWorldTable.getBuildServer(worldName);
  }

  public Map<ServerType, ServerTable<?>> getServerTables() {
    return serverTables;
  }
}
