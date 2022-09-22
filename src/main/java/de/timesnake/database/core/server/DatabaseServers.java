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

    public DatabaseServers(String name, String url, String user, String password, String lobbysTableName,
                           String gamesTableName, String loungesTableName, String tempGamesTableName,
                           String buildsTableName, String buildWorldTableName) {
        super(name, url, user, password, DatabaseManager.SERVERS_MAX_IDLE_CONNECTIONS);

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

    @Nullable
    @Override
    public <S extends DbServer> Type.Server<S> getServerType(int port) {
        for (Map.Entry<Type.Server<? extends DbServer>, ServerTable<? extends DbServer>> entry :
                this.serverTables.entrySet()) {
            if (entry.getValue().getServer(port) != null && entry.getValue().getServer(port).exists()) {
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
    public <Server extends DbServer> Server getServer(int port) {
        return this.getServer(this.getServerType(port), port);
    }

    @Nullable
    @Override
    public <Server extends DbServer> Server getServer(String name) {
        return this.getServer(this.getServerType(name), name);
    }

    @NotNull
    @Override
    public Collection<Integer> getPorts() {
        ArrayList<Integer> ports = new ArrayList<>();
        for (Type.Server<?> type : Type.Server.TYPES_BY_STRING.values()) {
            ports.addAll(this.getServerPorts(type));
        }
        return ports;
    }

    @Nullable
    @Override
    public <Server extends DbServer> Server getServer(Type.Server<Server> type, int port) {
        ServerTable<Server> table = this.serverTables.get(type);
        if (table != null) {
            return table.getServer(port);
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

    @Override
    public void removeServer(Type.Server<?> type, int port) {
        ServerTable<? extends DbServer> table = this.serverTables.get(type);
        if (table != null) {
            table.removeServer(port);
        }
    }

    @Override
    public boolean containsServer(Type.Server<?> type, int port) {
        ServerTable<? extends DbServer> table = this.serverTables.get(type);
        if (table != null) {
            return table.containsServer(port);
        }
        return false;
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
    public void addLobby(int port, String name, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.LOBBY);
        if (table != null) {
            table.addServer(port, name, status, folderPath);
        }

    }

    @Override
    public void addGame(int port, String name, String task, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.GAME);
        if (table != null) {
            ((NonTmpGameTable) table).addServer(port, name, task, status, folderPath);
        }
    }

    @Override
    public void addLounge(int port, String name, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.LOUNGE);
        if (table != null) {
            table.addServer(port, name, status, folderPath);
        }
    }

    @Override
    public void addTempGame(int port, String name, String task, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.TEMP_GAME);
        if (table != null) {
            ((TmpGameTable) table).addServer(port, name, task, status, folderPath);
        }
    }

    @Override
    public void addBuild(int port, String name, String task, Status.Server status, Path folderPath) {
        ServerTable<? extends DbServer> table = this.serverTables.get(Type.Server.BUILD);
        if (table != null) {
            ((BuildTable) table).addServer(port, name, task, status, folderPath);
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
}
