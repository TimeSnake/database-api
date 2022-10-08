/*
 * database-api.main
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
