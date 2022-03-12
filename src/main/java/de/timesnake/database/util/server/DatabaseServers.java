package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;

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

    <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type, Status.Server status);

    <Server extends DbServer> Collection<Server> getServers(Type.Server<Server> type);

    <Server extends DbTaskServer> Collection<Server> getServers(Type.Server<Server> type, String task);

    Collection<String> getServerNames(Type.Server<?> type);

    Collection<Integer> getServerPorts(Type.Server<?> type);

    void addLobby(int port, String name, Status.Server status, String folderPath);

    void addGame(int port, String name, String task, Status.Server status, String folderPath);

    void addLounge(int port, String name, Status.Server status, String folderPath);

    void addTempGame(int port, String name, String task, Status.Server status, String folderPath);

    void addBuild(int port, String name, String task, Status.Server status, String folderPath);
}
