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
    public Integer getMaxPlayers() {return getInfo().getMaxPlayers();}

    @Override
    public void setMaxPlayers(int maxPlayers) {getInfo().setMaxPlayers(maxPlayers);}

    @Override
    public Type.Availability getMapAvailability() {return getInfo().getMapAvailability();}

    @Override
    public void setMapsAvailability(Type.Availability maps) {getInfo().setMapsAvailability(maps);}

    @Override
    public Type.Availability getKitAvailability() {return getInfo().getKitAvailability();}

    @Override
    public void setKitsAvailability(Type.Availability kits) {getInfo().setKitsAvailability(kits);}

    @Override
    public boolean hasStatistics() {return getInfo().hasStatistics();}

    @Override
    public void setStatistics(boolean statistics) {getInfo().setStatistics(statistics);}

    @Override
    public String getTexturePackLink() {return getInfo().getTexturePackLink();}

    @Override
    public void setTexturePackLink(String texturePack) {getInfo().setTexturePackLink(texturePack);}

    @Override
    public Boolean hasTexturePack() {return getInfo().hasTexturePack();}

    @Override
    public Integer getPlayerTrackingRange() {return getInfo().getPlayerTrackingRange();}

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {getInfo().setPlayerTrackingRange(playerTrackingRange);}

    @Override
    public Integer getMaxHealth() {
        return getInfo().getMaxHealth();
    }

    @Override
    public void setMaxHealth(Integer maxHealth) {
        this.getInfo().setMaxHealth(maxHealth);
    }

    @Override
    public boolean exists() {return getInfo().exists();}

    @Override
    public String getName() {return getInfo().getName();}

    @Override
    public String getDisplayName() {return getInfo().getDisplayName();}

    @Override
    public void setDisplayName(String displayName) {getInfo().setDisplayName(displayName);}

    @Override
    @Deprecated
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Override
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

    @Override
    public String getItemName() {return getInfo().getItemName();}

    @Override
    public void setItem(String itemName) {getInfo().setItem(itemName);}

    @Override
    public String getHeadLine() {return getInfo().getHeadLine();}

    @Override
    public void setHeadLine(String headLine) {getInfo().setHeadLine(headLine);}

    @Override
    public Integer getSlot() {return getInfo().getSlot();}

    @Override
    public void setSlot(int slot) {getInfo().setSlot(slot);}

    @Override
    public boolean isGenerateable() {return getInfo().isGenerateable();}

    @Override
    public void setGenerateable(Boolean generateable) {getInfo().setGenerateable(generateable);}

    @Override
    public boolean isAutoDeleteAllowed() {return getInfo().isAutoDeleteAllowed();}

    @Override
    public void allowAutoDelete(Boolean allowAutoDelete) {getInfo().allowAutoDelete(allowAutoDelete);}

    @Override
    public boolean isOwnable() {return getInfo().isOwnable();}

    @Override
    public void setOwnable(Boolean ownable) {getInfo().setOwnable(ownable);}

    @Override
    public DbNonTmpGame toDatabase() {return getDatabase();}

    @Override
    public DbNonTmpGame toLocal() {return getDatabase().toLocal();}
}
