package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbNonTmpGameInfo;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.chat.ExTextColor;

public class DbNonTmpGame extends DbGame implements de.timesnake.database.util.game.DbNonTmpGame {

    public DbNonTmpGame(DatabaseConnector databaseConnector, String gameName, DbNonTmpGameInfo info) {
        super(databaseConnector, gameName, info);
    }

    @Override
    public DbNonTmpGameInfo getInfo() {
        return (DbNonTmpGameInfo) super.getInfo();
    }

    public Integer getMaxPlayers() {return getInfo().getMaxPlayers();}

    public void setMaxPlayers(int maxPlayers) {getInfo().setMaxPlayers(maxPlayers);}

    public Type.Availability getMapAvailability() {return getInfo().getMapAvailability();}

    public void setMapsAvailability(Type.Availability maps) {getInfo().setMapsAvailability(maps);}

    public Type.Availability getKitAvailability() {return getInfo().getKitAvailability();}

    public void setKitsAvailability(Type.Availability kits) {getInfo().setKitsAvailability(kits);}

    public boolean hasStatistics() {return getInfo().hasStatistics();}

    public void setStatistics(boolean statistics) {getInfo().setStatistics(statistics);}

    public String getTexturePackLink() {return getInfo().getTexturePackLink();}

    public void setTexturePackLink(String texturePack) {getInfo().setTexturePackLink(texturePack);}

    public Boolean hasTexturePack() {return getInfo().hasTexturePack();}

    public Integer getPlayerTrackingRange() {return getInfo().getPlayerTrackingRange();}

    public void setPlayerTrackingRange(Integer playerTrackingRange) {getInfo().setPlayerTrackingRange(playerTrackingRange);}

    public boolean exists() {return getInfo().exists();}

    public String getName() {return getInfo().getName();}

    public String getDisplayName() {return getInfo().getDisplayName();}

    public void setDisplayName(String displayName) {getInfo().setDisplayName(displayName);}

    @Deprecated
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Deprecated
    public void setChatColorName(String chatColorName) {getInfo().setChatColorName(chatColorName);}

    @Override
    public ExTextColor getTextColor() {
        return getInfo().getTextColor();
    }

    @Override
    public void setTextColor(ExTextColor color) {
        getInfo().setTextColor(color);
    }

    public String getItemName() {return getInfo().getItemName();}

    public void setItem(String itemName) {getInfo().setItem(itemName);}

    public String getHeadLine() {return getInfo().getHeadLine();}

    public void setHeadLine(String headLine) {getInfo().setHeadLine(headLine);}

    public Integer getSlot() {return getInfo().getSlot();}

    public void setSlot(int slot) {getInfo().setSlot(slot);}

    public boolean isGenerateable() {return getInfo().isGenerateable();}

    public void setGenerateable(Boolean generateable) {getInfo().setGenerateable(generateable);}

    public boolean isAutoDeleteAllowed() {return getInfo().isAutoDeleteAllowed();}

    public void allowAutoDelete(Boolean allowAutoDelete) {getInfo().allowAutoDelete(allowAutoDelete);}

    public boolean isOwnable() {return getInfo().isOwnable();}

    public void setOwnable(Boolean ownable) {getInfo().setOwnable(ownable);}

    @Override
    public DbNonTmpGame toDatabase() {
        return this;
    }

    @Override
    public DbCachedNonTmpGame toLocal() {
        return new DbCachedNonTmpGame(this);
    }

}
