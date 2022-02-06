package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public abstract class DbTaskServer extends DbServer implements de.timesnake.database.util.server.DbTaskServer {

    public DbTaskServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public String getTask() {
        return super.getFirstWithKey(Column.Server.TASK);
    }

    @Override
    public void setTask(String task) {
        super.setWithKey(task, Column.Server.TASK);
    }

    @Override
    public void setTaskSynchronized(String task) {
        super.setWithKeySynchronized(task, Column.Server.TASK);
    }
}
