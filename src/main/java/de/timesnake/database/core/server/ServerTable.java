/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ServerTable<Server extends DbServer> extends DefinitionAndQueryTool {

  public ServerTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable, Column.Server.NAME);
    super.addColumn(Column.Server.PORT);
    super.addColumn(Column.Server.STATUS);
    super.addColumn(Column.Server.ONLINE_PLAYERS);
    super.addColumn(Column.Server.MAX_PLAYERS);
    super.addColumn(Column.Server.FOLDER_PATH);
    super.addColumn(Column.Server.PASSWORD);
  }

  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.dropTmpTable();
  }

  @Nullable
  public String getNameFromPort(int port) {
    return super.getFirst(Column.Server.NAME, new Entry<>(port, Column.Server.PORT));
  }

  public Collection<String> getServerNames() {
    return super.get(Column.Server.NAME);
  }

  public Collection<Integer> getServerPorts() {
    return super.get(Column.Server.PORT);
  }

  public void addServer(String name, int port, Status.Server status, Path folderPath) {
    super.addEntrySynchronized(true, new PrimaryKeyEntries(new Entry<>(name, Column.Server.NAME)),
        new Entry<>(status, Column.Server.STATUS), new Entry<>(port, Column.Server.PORT),
        new Entry<>(0, Column.Server.ONLINE_PLAYERS), new Entry<>(folderPath,
            Column.Server.FOLDER_PATH));
  }

  @Nullable
  public abstract Server getServer(String name);

  public boolean containsServer(String name) {
    return super.getFirst(Column.Server.PORT, new Entry<>(name, Column.Server.NAME)) != null;
  }

  public void removeServer(String name) {
    super.deleteEntry(new Entry<>(name, Column.Server.NAME));
  }

  public Collection<Server> getServers(Status.Server status) {
    List<Server> servers = new ArrayList<>();
    Collection<String> names = super.get(Column.Server.NAME,
        new Entry<>(status, Column.Server.STATUS));
    for (String port : names) {
      if (port == null) {
        continue;
      }
      Server server = this.getServer(port);
      if (server != null) {
        servers.add(server);
      }
    }
    return servers;
  }

  public Collection<Server> getServers() {
    List<Server> servers = new ArrayList<>();
    Collection<String> names = super.get(Column.Server.NAME);
    for (String name : names) {
      if (name == null) {
        continue;
      }
      Server server = this.getServer(name);
      if (server != null) {
        servers.add(server);
      }
    }
    return servers;
  }
}
