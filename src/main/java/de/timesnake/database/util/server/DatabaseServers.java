package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

public interface DatabaseServers {

    @Nullable <S extends DbServer> Type.Server<S> getServerType(int port);

    @Nullable <S extends DbServer> Type.Server<S> getServerType(String name);

    @Nullable <Server extends DbServer> Server getServer(int port);

    @Nullable <Server extends DbServer> Server getServer(String name);

    @NotNull
    Collection<Integer> getPorts();

    @Nullable <Server extends DbServer> Server getServer(Type.Server<Server> type, int port);

    @Nullable <Server extends DbServer> Server getServer(Type.Server<Server> type, String name);

    void removeServer(Type.Server<?> type, int port);

    boolean containsServer(Type.Server<?> type, int port);

    boolean containsServer(Type.Server<?> type, String name);

    @NotNull <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type, Status.Server status);

    @NotNull <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type);

    @NotNull <Server extends DbTaskServer> Collection<Server> getServers(Type.Server<Server> type, String task);

    @NotNull
    Collection<String> getServerNames(Type.Server<?> type);

    @NotNull
    Collection<Integer> getServerPorts(Type.Server<?> type);

    void addLobby(int port, String name, Status.Server status, Path folderPath);

    void addGame(int port, String name, String task, Status.Server status, Path folderPath);

    void addLounge(int port, String name, Status.Server status, Path folderPath);

    void addTempGame(int port, String name, String task, Status.Server status, Path folderPath);

    void addBuild(int port, String name, String task, Status.Server status, Path folderPath);

    @NotNull
    Set<String> getBuildWorlds();

    @Nullable
    String getBuildServerByWorld(String worldName);
}
