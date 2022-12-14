/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;

public interface DbLobbyServer extends DbServer {

    @NotNull
    @Override
    Type.Server<DbLobbyServer> getType();
}
