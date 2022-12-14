/*
 * Copyright (C) 2022 timesnake
 */

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
    @Override
    public Integer getMaxPlayers() {return getInfo().getMaxPlayers();}

    @Override
    public void setMaxPlayers(int maxPlayers) {getInfo().setMaxPlayers(maxPlayers);}

    @NotNull
    @Override
    public Type.Availability getMapAvailability() {return getInfo().getMapAvailability();}

    @Override
    public void setMapsAvailability(Type.Availability maps) {getInfo().setMapsAvailability(maps);}

    @NotNull
    @Override
    public Type.Availability getKitAvailability() {return getInfo().getKitAvailability();}

    @Override
    public void setKitsAvailability(Type.Availability kits) {getInfo().setKitsAvailability(kits);}

    @Override
    public boolean hasStatistics() {return getInfo().hasStatistics();}

    @Override
    public void setStatistics(boolean statistics) {getInfo().setStatistics(statistics);}

    @Nullable
    @Override
    public String getTexturePackLink() {return getInfo().getTexturePackLink();}

    @Override
    public void setTexturePackLink(String texturePack) {getInfo().setTexturePackLink(texturePack);}

    @Override
    public Boolean hasTexturePack() {return getInfo().hasTexturePack();}

    @Nullable
    @Override
    public Integer getPlayerTrackingRange() {return getInfo().getPlayerTrackingRange();}

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {getInfo().setPlayerTrackingRange(playerTrackingRange);}

    @Nullable
    @Override
    public Integer getMaxHealth() {return getInfo().getMaxHealth();}

    @Override
    public void setMaxHealth(Integer maxHealth) {getInfo().setMaxHealth(maxHealth);}

    @Nullable
    @Override
    public Integer getViewDistance() {
        return getInfo().getViewDistance();
    }

    @Override
    public void setViewDistance(Integer viewDistance) {
        getInfo().setViewDistance(viewDistance);
    }

    @Override
    public boolean exists() {return getInfo().exists();}

    @NotNull
    @Override
    public String getName() {return getInfo().getName();}

    @NotNull
    @Override
    public String getDisplayName() {return getInfo().getDisplayName();}

    @Override
    public void setDisplayName(String displayName) {getInfo().setDisplayName(displayName);}

    @Deprecated
    @NotNull
    @Override
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Deprecated
    @Override
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
    @Override
    public String getItemName() {return getInfo().getItemName();}

    @Override
    public void setItem(String itemName) {getInfo().setItem(itemName);}

    @NotNull
    @Override
    public String getHeadLine() {return getInfo().getHeadLine();}

    @Override
    public void setHeadLine(String headLine) {getInfo().setHeadLine(headLine);}

    @NotNull
    @Override
    public Integer getSlot() {return getInfo().getSlot();}

    @Override
    public void setSlot(int slot) {getInfo().setSlot(slot);}

    @Override
    public boolean isCreationRequestable() {return getInfo().isCreationRequestable();}

    @Override
    public void setCreationRequestable(Boolean creationRequestable) {getInfo().setCreationRequestable(creationRequestable);}

    @Override
    public boolean isOwnable() {return getInfo().isOwnable();}

    @Override
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
