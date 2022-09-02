package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;

public class DbGameInfo extends DbGameInfoBasis implements de.timesnake.database.util.game.DbGameInfo {

    public DbGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, gameName);
    }

    @Override
    public Integer getMaxPlayers() {
        return super.getFirstWithKey(Column.Game.MAX_PLAYERS);
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        super.setWithKey(maxPlayers, Column.Game.MAX_PLAYERS);
    }

    @Override
    public Type.Availability getMapAvailability() {
        return super.getFirstWithKey(Column.Game.MAPS);
    }

    @Override
    public void setMapsAvailability(Type.Availability maps) {
        super.setWithKey(maps, Column.Game.MAPS);
    }

    @Override
    public Type.Availability getKitAvailability() {
        return super.getFirstWithKey(Column.Game.KITS);
    }

    @Override
    public void setKitsAvailability(Type.Availability kits) {
        super.setWithKey(kits, Column.Game.KITS);
    }

    @Override
    public boolean hasStatistics() {
        return super.getFirstWithKey(Column.Game.STATISTICS);
    }

    @Override
    public void setStatistics(boolean statistics) {
        super.setWithKey(statistics, Column.Game.STATISTICS);
    }

    @Override
    public String getTexturePackLink() {
        return super.getFirstWithKey(Column.Game.TEXTURE_PACK_LINK);
    }

    @Override
    public void setTexturePackLink(String texturePack) {
        super.setWithKey(texturePack, Column.Game.TEXTURE_PACK_LINK);
    }

    @Override
    public Boolean hasTexturePack() {
        return super.getFirstWithKey(Column.Game.TEXTURE_PACK_LINK) != null;
    }

    @Override
    public Integer getPlayerTrackingRange() {
        return super.getFirstWithKey(Column.Game.PLAYER_TRACKING_RANGE);
    }

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {
        super.setWithKey(playerTrackingRange, Column.Game.PLAYER_TRACKING_RANGE);
    }

    @Override
    public Integer getMaxHealth() {
        return super.getFirstWithKey(Column.Game.MAX_HEALTH);
    }

    @Override
    public void setMaxHealth(Integer maxHealth) {
        super.setWithKey(maxHealth, Column.Game.MAX_HEALTH);
    }

    @Override
    public de.timesnake.database.util.game.DbGameInfo toDatabase() {
        return this;
    }

    @Override
    public de.timesnake.database.util.game.DbGameInfo toLocal() {
        return new DbCachedGameInfo(this);
    }
}
