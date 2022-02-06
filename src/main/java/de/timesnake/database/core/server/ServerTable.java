package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Status;
import de.timesnake.database.util.server.DbServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ServerTable<Server extends DbServer> extends Table {

    public ServerTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.Server.PORT);
        super.addColumn(Column.Server.NAME);
        super.addColumn(Column.Server.STATUS);
        super.addColumn(Column.Server.ONLINE_PLAYERS);
        super.addColumn(Column.Server.MAX_PLAYERS);
        super.addColumn(Column.Server.PASSWORD);
    }

    public void create() {
        super.create();
    }

    public void backup() {
        Column<?>[] columns = {Column.Server.PORT, Column.Server.NAME, Column.Server.MAX_PLAYERS, Column.Server.PASSWORD};
        super.createBackup(columns);
    }

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

    public void addServer(int port, String name, Status.Server status) {
        super.addEntrySynchronized(new PrimaryEntries(new TableEntry<>(port, Column.Server.PORT)), new TableEntry<>(status, Column.Server.STATUS), new TableEntry<>(name, Column.Server.NAME), new TableEntry<>(0, Column.Server.ONLINE_PLAYERS));
    }

    public void addServer(int port, String name, Status.Server status, String password) {
        super.addEntrySynchronized(new PrimaryEntries(new TableEntry<>(port, Column.Server.PORT)), new TableEntry<>(status, Column.Server.STATUS), new TableEntry<>(name, Column.Server.NAME), new TableEntry<>(0, Column.Server.ONLINE_PLAYERS), new TableEntry<>(password, Column.Server.PASSWORD));
    }

    public abstract Server getServer(int port);

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
