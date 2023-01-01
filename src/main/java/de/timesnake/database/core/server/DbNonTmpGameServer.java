/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DbNonTmpGameServer extends DbPvPServer implements de.timesnake.database.util.server.DbNonTmpGameServer {

    public DbNonTmpGameServer(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @Nullable
    @Override
    public String getGameInfo() {
        return super.getFirstWithKey(Column.Server.GAME_INFO);
    }

    @Override
    public void setGameInfo(String info) {
        super.setWithKey(info, Column.Server.GAME_INFO);
    }

    @Nullable
    @Override
    public UUID getOwnerUuid() {
        return super.getFirstWithKey(Column.Server.OWNER);
    }

    @Override
    public void setOwnerUuid(UUID uuid) {
        super.setWithKey(uuid, Column.Server.OWNER);
    }

    @Override
    public boolean hasOwner() {
        return this.getOwnerUuid() != null;
    }

    @NotNull
    @Override
    public Type.Server<de.timesnake.database.util.server.DbNonTmpGameServer> getType() {
        return Type.Server.GAME;
    }
}
