package de.timesnake.database.util.server;

public interface DbTaskServer extends DbServer {

    String getTask();

    void setTask(String task);

    void setTaskSynchronized(String task);
}
