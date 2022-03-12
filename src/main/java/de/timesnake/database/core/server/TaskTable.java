package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbTaskServer;
import de.timesnake.library.basic.util.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class TaskTable<Server extends DbTaskServer> extends ServerTable<Server> {

    public TaskTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Server.TASK);
    }

    public void addServer(int port, String name, String task, Status.Server status, String folderPath) {
        super.addServer(port, name, status, folderPath);
        super.setSynchronized(task, Column.Server.TASK, new TableEntry<>(port, Column.Server.PORT));
    }

    @Override
    public void backup() {
        Column<?>[] columns = {Column.Server.PORT, Column.Server.NAME, Column.Server.MAX_PLAYERS, Column.Server.TASK, Column.Server.PASSWORD};
        super.createBackup(columns);
    }

    public List<Server> getServers(String task) {
        List<Server> servers = new ArrayList<>();
        Collection<Integer> ports = super.get(Column.Server.PORT, new TableEntry<>(task, Column.Server.TASK));
        for (Integer port : ports) {
            if (port == null) {
                continue;
            }
            Server server = this.getServer(port);
            if (server != null && server.exists()) {
                servers.add(server);
            }
        }
        return servers;
    }

}
