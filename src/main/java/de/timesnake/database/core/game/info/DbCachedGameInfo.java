package de.timesnake.database.core.game.info;

import de.timesnake.database.util.game.DbGameInfo;
import de.timesnake.database.util.object.Type;

public class DbCachedGameInfo extends DbCachedGameInfoBasis implements DbGameInfo {

    protected Integer maxPlayers;
    protected Type.Availability mapAvailability;
    protected Type.Availability kitAvailability;
    protected Boolean statistics;
    protected String texturePackLink;
    protected Integer playerTrackingRange;


    public DbCachedGameInfo(de.timesnake.database.core.game.info.DbGameInfo database) {
        super(database);
    }

    @Override
    protected de.timesnake.database.core.game.info.DbGameInfo getDatabase() {
        return (de.timesnake.database.core.game.info.DbGameInfo) super.getDatabase();
    }

    @Override
    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.getDatabase().setMaxPlayers(maxPlayers);
    }

    @Override
    public Type.Availability getMapAvailability() {
        return this.mapAvailability;
    }

    @Override
    public void setMapsAvailability(Type.Availability maps) {
        this.mapAvailability = maps;
        this.getDatabase().setMapsAvailability(maps);
    }

    @Override
    public Type.Availability getKitAvailability() {
        return this.kitAvailability;
    }

    @Override
    public void setKitsAvailability(Type.Availability kits) {
        this.kitAvailability = kits;
        this.getDatabase().setKitsAvailability(kits);
    }

    @Override
    public boolean hasStatistics() {
        return this.statistics;
    }

    @Override
    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
        this.getDatabase().setStatistics(statistics);
    }

    @Override
    public String getTexturePackLink() {
        return this.texturePackLink;
    }

    @Override
    public void setTexturePackLink(String texturePack) {
        this.texturePackLink = texturePack;
        this.getDatabase().setTexturePackLink(texturePackLink);
    }

    @Override
    public Boolean hasTexturePack() {
        return this.texturePackLink != null;
    }

    @Override
    public Integer getPlayerTrackingRange() {
        return this.playerTrackingRange;
    }

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {
        this.playerTrackingRange = playerTrackingRange;
        this.getDatabase().setPlayerTrackingRange(playerTrackingRange);
    }

    @Override
    public DbGameInfo toDatabase() {
        return this.getDatabase();
    }

    @Override
    public DbGameInfo toLocal() {
        return this.getDatabase().toLocal();
    }
}
