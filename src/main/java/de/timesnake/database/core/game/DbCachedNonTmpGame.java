package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedNonTmpGameInfo;
import de.timesnake.database.util.game.DbNonTmpGame;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.chat.ExTextColor;

public class DbCachedNonTmpGame extends DbCachedGame implements DbNonTmpGame {

    protected DbCachedNonTmpGame(de.timesnake.database.core.game.DbNonTmpGame database) {
        super(database, new DbCachedNonTmpGameInfo(database.getInfo()));
    }

    @Override
    protected de.timesnake.database.core.game.DbNonTmpGame getDatabase() {
        return (de.timesnake.database.core.game.DbNonTmpGame) super.getDatabase();
    }

    @Override
    public DbCachedNonTmpGameInfo getInfo() {return (DbCachedNonTmpGameInfo) super.getInfo();}

    @Override
    public Integer getMaxPlayers() {return getDatabase().getMaxPlayers();}

    @Override
    public void setMaxPlayers(int maxPlayers) {getDatabase().setMaxPlayers(maxPlayers);}

    @Override
    public Type.Availability getMapAvailability() {return getDatabase().getMapAvailability();}

    @Override
    public void setMapsAvailability(Type.Availability maps) {getDatabase().setMapsAvailability(maps);}

    @Override
    public Type.Availability getKitAvailability() {return getDatabase().getKitAvailability();}

    @Override
    public void setKitsAvailability(Type.Availability kits) {getDatabase().setKitsAvailability(kits);}

    @Override
    public boolean hasStatistics() {return getDatabase().hasStatistics();}

    @Override
    public void setStatistics(boolean statistics) {getDatabase().setStatistics(statistics);}

    @Override
    public String getTexturePackLink() {return getDatabase().getTexturePackLink();}

    @Override
    public void setTexturePackLink(String texturePack) {getDatabase().setTexturePackLink(texturePack);}

    @Override
    public Boolean hasTexturePack() {return getDatabase().hasTexturePack();}

    @Override
    public Integer getPlayerTrackingRange() {return getDatabase().getPlayerTrackingRange();}

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {getDatabase().setPlayerTrackingRange(playerTrackingRange);}

    @Override
    public boolean exists() {return getDatabase().exists();}

    @Override
    public String getName() {return getDatabase().getName();}

    @Override
    public String getDisplayName() {return getDatabase().getDisplayName();}

    @Override
    public void setDisplayName(String displayName) {getDatabase().setDisplayName(displayName);}

    @Override
    @Deprecated
    public String getChatColorName() {return getDatabase().getChatColorName();}

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {getDatabase().setChatColorName(chatColorName);}

    @Override
    public ExTextColor getTextColor() {
        return getDatabase().getTextColor();
    }

    @Override
    public void setTextColor(ExTextColor color) {
        getDatabase().setTextColor(color);
    }

    @Override
    public String getItemName() {return getDatabase().getItemName();}

    @Override
    public void setItem(String itemName) {getDatabase().setItem(itemName);}

    @Override
    public String getHeadLine() {return getDatabase().getHeadLine();}

    @Override
    public void setHeadLine(String headLine) {getDatabase().setHeadLine(headLine);}

    @Override
    public Integer getSlot() {return getDatabase().getSlot();}

    @Override
    public void setSlot(int slot) {getDatabase().setSlot(slot);}

    @Override
    public boolean isGenerateable() {return getDatabase().isGenerateable();}

    @Override
    public void setGenerateable(Boolean generateable) {getDatabase().setGenerateable(generateable);}

    @Override
    public boolean isAutoDeleteAllowed() {return getDatabase().isAutoDeleteAllowed();}

    @Override
    public void allowAutoDelete(Boolean allowAutoDelete) {getDatabase().allowAutoDelete(allowAutoDelete);}

    @Override
    public boolean isOwnable() {return getDatabase().isOwnable();}

    @Override
    public void setOwnable(Boolean ownable) {getDatabase().setOwnable(ownable);}

    @Override
    public DbNonTmpGame toDatabase() {return getDatabase().toDatabase();}

    @Override
    public DbNonTmpGame toLocal() {return getDatabase().toLocal();}
}
