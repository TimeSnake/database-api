package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbNonTmpGameInfo;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbNonTmpGame extends DbGame implements de.timesnake.database.util.game.DbNonTmpGame {

    public DbNonTmpGame(DatabaseConnector databaseConnector, String gameName, DbNonTmpGameInfo info) {
        super(databaseConnector, gameName, info);
    }

    @NotNull
    @Override
    public DbNonTmpGameInfo getInfo() {
        return (DbNonTmpGameInfo) super.getInfo();
    }

    @Nullable
    public Integer getMaxPlayers() {return getInfo().getMaxPlayers();}

    public void setMaxPlayers(int maxPlayers) {getInfo().setMaxPlayers(maxPlayers);}

    @NotNull
    public Type.Availability getMapAvailability() {return getInfo().getMapAvailability();}

    public void setMapsAvailability(Type.Availability maps) {getInfo().setMapsAvailability(maps);}

    @NotNull
    public Type.Availability getKitAvailability() {return getInfo().getKitAvailability();}

    public void setKitsAvailability(Type.Availability kits) {getInfo().setKitsAvailability(kits);}

    public boolean hasStatistics() {return getInfo().hasStatistics();}

    public void setStatistics(boolean statistics) {getInfo().setStatistics(statistics);}

    @Nullable
    public String getTexturePackLink() {return getInfo().getTexturePackLink();}

    public void setTexturePackLink(String texturePack) {getInfo().setTexturePackLink(texturePack);}

    public Boolean hasTexturePack() {return getInfo().hasTexturePack();}

    @Nullable
    public Integer getPlayerTrackingRange() {return getInfo().getPlayerTrackingRange();}

    public void setPlayerTrackingRange(Integer playerTrackingRange) {getInfo().setPlayerTrackingRange(playerTrackingRange);}

    @Nullable
    public Integer getMaxHealth() {return getInfo().getMaxHealth();}

    public void setMaxHealth(Integer maxHealth) {getInfo().setMaxHealth(maxHealth);}

    public boolean exists() {return getInfo().exists();}

    @NotNull
    public String getName() {return getInfo().getName();}

    @NotNull
    public String getDisplayName() {return getInfo().getDisplayName();}

    public void setDisplayName(String displayName) {getInfo().setDisplayName(displayName);}

    @Deprecated
    @NotNull
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Deprecated
    public void setChatColorName(String chatColorName) {getInfo().setChatColorName(chatColorName);}

    @NotNull
    @Override
    public ExTextColor getTextColor() {
        return getInfo().getTextColor();
    }

    @Override
    public void setTextColor(ExTextColor color) {
        getInfo().setTextColor(color);
    }

    @NotNull
    public String getItemName() {return getInfo().getItemName();}

    public void setItem(String itemName) {getInfo().setItem(itemName);}

    @NotNull
    public String getHeadLine() {return getInfo().getHeadLine();}

    public void setHeadLine(String headLine) {getInfo().setHeadLine(headLine);}

    @NotNull
    public Integer getSlot() {return getInfo().getSlot();}

    public void setSlot(int slot) {getInfo().setSlot(slot);}

    public boolean isCreationRequestable() {return getInfo().isCreationRequestable();}

    public void setCreationRequestable(Boolean creationRequestable) {getInfo().setCreationRequestable(creationRequestable);}

    public boolean isOwnable() {return getInfo().isOwnable();}

    public void setOwnable(Boolean ownable) {getInfo().setOwnable(ownable);}

    @Override
    public boolean isNetherAndEndAllowed() {
        return getInfo().isNetherAndEndAllowed();
    }

    @Override
    public void allowNetherAndEnd(Boolean allow) {
        getInfo().allowNetherAndEnd(allow);
    }


    @NotNull
    @Override
    public DbNonTmpGame toDatabase() {
        return this;
    }

    @NotNull
    @Override
    public DbCachedNonTmpGame toLocal() {
        return new DbCachedNonTmpGame(this);
    }

}
