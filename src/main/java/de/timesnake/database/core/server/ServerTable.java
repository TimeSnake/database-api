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

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ServerTable<Server extends DbServer> extends TableDDL {

    public ServerTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.Server.PORT);
        super.addColumn(Column.Server.NAME);
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
    public void backup() {
        Column<?>[] columns = {Column.Server.PORT, Column.Server.NAME, Column.Server.MAX_PLAYERS,
                Column.Server.FOLDER_PATH, Column.Server.PASSWORD};
        super.backup(columns);
    }

    @Nullable
    public Integer getPortFromName(String name) {
        if (name != null) {
            return super.getFirst(Column.Server.PORT, new TableEntry<>(name, Column.Server.NAME));
        }
        return null;
    }

    public Collection<String> getServerNames() {
        return super.get(Column.Server.NAME);
    }

    public Collection<Integer> getServerPorts() {
        return super.get(Column.Server.PORT);
    }

    public void addServer(int port, String name, Status.Server status, Path folderPath) {
        super.addEntrySynchronized(true, new PrimaryEntries(new TableEntry<>(port, Column.Server.PORT)),
                new TableEntry<>(status, Column.Server.STATUS), new TableEntry<>(name, Column.Server.NAME),
                new TableEntry<>(0, Column.Server.ONLINE_PLAYERS), new TableEntry<>(folderPath,
                        Column.Server.FOLDER_PATH));
    }

    public void addServer(int port, String name, Status.Server status, Path folderPath, String password) {
        super.addEntrySynchronized(true, new PrimaryEntries(new TableEntry<>(port, Column.Server.PORT)),
                new TableEntry<>(status, Column.Server.STATUS), new TableEntry<>(name, Column.Server.NAME),
                new TableEntry<>(0, Column.Server.ONLINE_PLAYERS), new TableEntry<>(folderPath,
                        Column.Server.FOLDER_PATH), new TableEntry<>(password, Column.Server.PASSWORD));
    }

    @Nullable
    public abstract Server getServer(int port);

    @Nullable
    public Server getServer(String name) {
        Integer port = this.getPortFromName(name);
        if (port == null) {
            return null;
        }
        return this.getServer(port);
    }

    public boolean containsServer(int port) {
        return super.getFirst(Column.Server.NAME, new TableEntry<>(port, Column.Server.PORT)) != null;
    }

    public boolean containsServer(String name) {
        return super.getFirst(Column.Server.PORT, new TableEntry<>(name, Column.Server.NAME)) != null;
    }

    public void removeServer(int port) {
        super.deleteEntry(new TableEntry<>(port, Column.Server.PORT));
    }

    public Collection<Server> getServers(Status.Server status) {
        List<Server> servers = new ArrayList<>();
        Collection<Integer> ports = super.get(Column.Server.PORT, new TableEntry<>(status, Column.Server.STATUS));
        for (Integer port : ports) {
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
        Collection<Integer> ports = super.get(Column.Server.PORT);
        for (Integer port : ports) {
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
}
