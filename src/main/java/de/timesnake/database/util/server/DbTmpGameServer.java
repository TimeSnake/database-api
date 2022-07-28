package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;

public interface DbTmpGameServer extends DbPvPServer {

    boolean areKitsEnabled();

    void setKitsEnabled(boolean kitsEnabled);

    String getMapName();

    void setMapName(String mapName);

    Integer getTwinServerPort();

    void setTwinServerPort(Integer port);

    DbLoungeServer getTwinServer();

    @Override
    Type.Server<DbTmpGameServer> getType();

    boolean areMapsEnabled();

    void setMapsEnabled(boolean mapsEnabled);

    Integer getTeamAmount();

    void setTeamAmount(Integer integer);

    Integer getMaxPlayersPerTeam();

    void setMaxPlayersPerTeam(Integer maxPlayersPerTeam);

    boolean isTeamMerging();

    void setTeamMerging(boolean teamMerging);

    boolean isDiscordEnabled();

    void setDiscord(boolean discordEnabled);
}
