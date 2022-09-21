package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

public interface DatabaseServers {

    <S extends DbServer> Type.Server<S> getServerType(int port);

    <S extends DbServer> Type.Server<S> getServerType(String name);

    <Server extends DbServer> Server getServer(int port);

    <Server extends DbServer> Server getServer(String name);

    Collection<Integer> getPorts();

    <Server extends DbServer> Server getServer(Type.Server<Server> type, int port);

    <Server extends DbServer> Server getServer(Type.Server<Server> type, String name);

    void removeServer(Type.Server<?> type, int port);

    boolean containsServer(Type.Server<?> type, int port);

    boolean containsServer(Type.Server<?> type, String name);

    <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type, Status.Server status);

    <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type);

    <Server extends DbTaskServer> Collection<Server> getServers(Type.Server<Server> type, String task);

    Collection<String> getServerNames(Type.Server<?> type);

    Collection<Integer> getServerPorts(Type.Server<?> type);

    void addLobby(int port, String name, Status.Server status, Path folderPath);

    void addGame(int port, String name, String task, Status.Server status, Path folderPath);

    void addLounge(int port, String name, Status.Server status, Path folderPath);

    void addTempGame(int port, String name, String task, Status.Server status, Path folderPath);

    void addBuild(int port, String name, String task, Status.Server status, Path folderPath);

    Set<String> getBuildWorlds();

    String getBuildServerByWorld(String worldName);
}
