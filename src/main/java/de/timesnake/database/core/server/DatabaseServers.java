/*
 * workspace.database-api.main
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

import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.server.DbTaskServer;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DatabaseServers extends DatabaseConnector implements de.timesnake.database.util.server.DatabaseServers {

    private final ServerMap serverTables = new ServerMap();

    private final BuildWorldTable buildWorldTable;

    public DatabaseServers(String name, String url, String options, String user, String password, String lobbysTableName,
                           String gamesTableName, String loungesTableName, String tempGamesTableName,
                           String buildsTableName, String buildWorldTableName) {
        super(name, url, options, user, password, DatabaseManager.SERVERS_MAX_IDLE_CONNECTIONS);

        this.buildWorldTable = new BuildWorldTable(this, buildWorldTableName);

        this.serverTables.put(Type.Server.LOBBY, new LobbyTable(this, lobbysTableName));
        this.serverTables.put(Type.Server.GAME, new NonTmpGameTable(this, gamesTableName));
        this.serverTables.put(Type.Server.LOUNGE, new LoungeTable(this, loungesTableName));
        this.serverTables.put(Type.Server.TEMP_GAME, new TmpGameTable(this, tempGamesTableName));
        this.serverTables.put(Type.Server.BUILD, new BuildTable(this, buildsTableName, this.buildWorldTable));
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
    public <S extends DbServer> Type.@Nullable Server<S> getServerType(int port) {
        for (Map.Entry<Type.Server<? extends DbServer>, ServerTable<? extends DbServer>> entry :
                this.serverTables.entrySet()) {
            String name = entry.getValue().getNameFromPort(port);
            if (name != null && entry.getValue().getServer(name) != null && entry.getValue().getServer(name).exists()) {
                return (Type.Server<S>) entry.getKey();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public <S extends DbServer> Type.Server<S> getServerType(String name) {
        for (Map.Entry<Type.Server<? extends DbServer>, ServerTable<? extends DbServer>> entry :
                this.serverTables.entrySet()) {
            if (entry.getValue().getServer(name) != null && entry.getValue().getServer(name).exists()) {
                return (Type.Server<S>) entry.getKey();
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
        for (Type.Server<?> type : Type.Server.values()) {
            ports.addAll(this.getServerPorts(type));
        }
        return ports;
    }

    @Nullable
    @Override
    public <Server extends DbServer> Server getServer(Type.Server<Server> type, int port) {
        ServerTable<Server> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServer(table.getNameFromPort(port));
        }
        return null;
    }

    @Nullable
    @Override
    public <Server extends DbServer> Server getServer(Type.Server<Server> type, String name) {
        ServerTable<Server> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServer(name);
        }
        return null;
    }

    @Deprecated
    @Override
    public void removeServer(Type.Server<?> type, String name) {
        ServerTable<? extends DbServer> table = this.serverTables.get(type);
        if (table != null) {
            table.removeServer(name);
        }
    }

    @Override
    public boolean containsServer(Type.Server<?> type, String name) {
        ServerTable<? extends DbServer> table = this.serverTables.get(type);
        if (table != null) {
            return table.containsServer(name);
        }
        return false;
    }

    @NotNull
    @Override
    public <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type, Status.Server status) {
        ServerTable<Server> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServers(status);
        }
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type) {
        ServerTable<Server> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServers();
        }
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public <Server extends DbTaskServer> Collection<Server> getServers(Type.Server<Server> type, String task) {
        ServerTable<Server> table = this.serverTables.get(type);
        if (table instanceof TaskTable) {
            return ((TaskTable<Server>) table).getServers(task);
        }
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public Collection<String> getServerNames(Type.Server<?> type) {
        ServerTable<? extends DbServer> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServerNames();
        }
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public Collection<Integer> getServerPorts(Type.Server<?> type) {
        ServerTable<? extends DbServer> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServerPorts();
        }
        return new ArrayList<>();
    }

    @Override
    public void addLobby(String name, int port, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.LOBBY);
        if (table != null) {
            table.addServer(name, port, status, folderPath);
        }

    }

    @Override
    public void addGame(String name, int port, String task, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.GAME);
        if (table != null) {
            ((NonTmpGameTable) table).addServer(name, port, task, status, folderPath);
        }
    }

    @Override
    public void addLounge(String name, int port, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.LOUNGE);
        if (table != null) {
            table.addServer(name, port, status, folderPath);
        }
    }

    @Override
    public void addTempGame(String name, int port, String task, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.TEMP_GAME);
        if (table != null) {
            ((TmpGameTable) table).addServer(name, port, task, status, folderPath);
        }
    }

    @Override
    public void addBuild(String name, int port, String task, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.BUILD);
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

    public ServerMap getServerTables() {
        return serverTables;
    }
}
