/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbTmpGameServer extends DbPvPServer {

    boolean areKitsEnabled();

    void setKitsEnabled(boolean kitsEnabled);

    @Nullable
    String getMapName();

    void setMapName(String mapName);

    @Nullable
    String getTwinServerName();

    void setTwinServerName(String name);

    @Nullable
    DbLoungeServer getTwinServer();

    @NotNull
    @Override
    Type.Server<DbTmpGameServer> getType();

    boolean areMapsEnabled();

    void setMapsEnabled(boolean mapsEnabled);

    @Nullable
    Integer getTeamAmount();

    void setTeamAmount(Integer integer);

    @Nullable
    Integer getMaxPlayersPerTeam();

    void setMaxPlayersPerTeam(Integer maxPlayersPerTeam);

    boolean isTeamMerging();

    void setTeamMerging(boolean teamMerging);

    boolean isDiscordEnabled();

    void setDiscord(boolean discordEnabled);
}
