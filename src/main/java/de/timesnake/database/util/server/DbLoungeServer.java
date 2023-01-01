/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbLoungeServer extends DbTaskServer {

    @Nullable
    DbTmpGameServer getTwinServer();

    @NotNull
    @Override
    Type.Server<DbLoungeServer> getType();
}
