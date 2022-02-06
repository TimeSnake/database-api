package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;

public interface DbTempGameServer extends DbPvPServer {

    boolean areKitsEnabled();

    void setKitsEnabled(boolean kitsEnabled);

    String getMapName();

    void setMapName(String mapName);

    Integer getTwinServerPort();

    DbLoungeServer getTwinServer();

    void setTwinServerPort(Integer port);

    @Override
    Type.Server getType();

    boolean areMapsEnabled();

    void setMapsEnabled(boolean mapsEnabled);

    Integer getTeamAmount();

    void setTeamAmount(Integer integer);

    void setMaxPlayersPerTeam(Integer maxPlayersPerTeam);

    Integer getMaxPlayersPerTeam();

    boolean isTeamMerging();

    void setTeamMerging(boolean teamMerging);
}
