/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.server;

import org.jetbrains.annotations.Nullable;

public interface DbTaskServer extends DbServer {

    @Nullable
    String getTask();

    void setTask(String task);

    void setTaskSynchronized(String task);
}
