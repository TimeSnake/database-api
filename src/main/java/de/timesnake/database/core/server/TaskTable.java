/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbTaskServer;
import de.timesnake.library.basic.util.Status;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class TaskTable<Server extends DbTaskServer> extends ServerTable<Server> {

    public TaskTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Server.TASK);
    }

    public void addServer(String name, int port, String task, Status.Server status, Path folderPath) {
        super.addServer(name, port, status, folderPath);
        super.setSynchronized(task, Column.Server.TASK, new Entry<>(name, Column.Server.NAME));
    }

    @Override
    public void backup() {
        Column<?>[] columns = {Column.Server.NAME, Column.Server.PORT, Column.Server.MAX_PLAYERS, Column.Server.TASK,
                Column.Server.PASSWORD};
        super.backup(columns);
    }

    public List<Server> getServers(String task) {
        List<Server> servers = new ArrayList<>();
        Collection<String> names = super.get(Column.Server.NAME, new Entry<>(task, Column.Server.TASK));
        for (String name : names) {
            if (name == null) {
                continue;
            }
            Server server = this.getServer(name);
            if (server != null && server.exists()) {
                servers.add(server);
            }
        }
        return servers;
    }

}
