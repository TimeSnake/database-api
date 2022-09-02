package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;

public interface DbGameInfo extends DbGameInfoBasis {

    Integer getMaxPlayers();

    @NotCached
    void setMaxPlayers(int maxPlayers);

    Type.Availability getMapAvailability();

    @NotCached
    void setMapsAvailability(Type.Availability maps);

    Type.Availability getKitAvailability();

    @NotCached
    void setKitsAvailability(Type.Availability kits);

    boolean hasStatistics();

    @NotCached
    void setStatistics(boolean statistics);

    String getTexturePackLink();

    @NotCached
    void setTexturePackLink(String texturePack);

    Boolean hasTexturePack();

    Integer getPlayerTrackingRange();

    @NotCached
    void setPlayerTrackingRange(Integer playerTrackingRange);

    Integer getMaxHealth();

    @NotCached
    void setMaxHealth(Integer maxHealth);

    @Override
    DbGameInfo toDatabase();

    @Override
    DbGameInfo toLocal();
}
