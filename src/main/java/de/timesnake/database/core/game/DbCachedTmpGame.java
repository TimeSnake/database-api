/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedTmpGameInfo;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.util.game.DbTmpGame;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class DbCachedTmpGame extends DbCachedGame implements de.timesnake.database.util.game.DbTmpGame {

    protected DbCachedTmpGame(de.timesnake.database.core.game.DbTmpGame game) {
        super(game, new DbCachedTmpGameInfo(game.getInfo()));
    }

    @NotNull
    @Override
    public DbCachedTmpGameInfo getInfo() {
        return (DbCachedTmpGameInfo) super.getInfo();
    }

    @Override
    protected de.timesnake.database.core.game.DbTmpGame getDatabase() {
        return (de.timesnake.database.core.game.DbTmpGame) super.getDatabase();
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
    public Integer getMaxHealth() {
        return getInfo().getMaxHealth();
    }

    @Override
    public void setMaxHealth(Integer maxHealth) {
        getInfo().setMaxHealth(maxHealth);
    }

    @Nullable
    @Override
    public Integer getViewDistance() {return getInfo().getViewDistance();}

    @Override
    public void setViewDistance(Integer viewDistance) {getInfo().setViewDistance(viewDistance);}

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

    @Override
    @Deprecated
    @NotNull
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Override
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

    @NotNull
    @Override
    public Integer getAutoStartPlayerNumber() {return getInfo().getAutoStartPlayerNumber();}

    @Override
    public void setAutoStartPlayerNumber(Integer number) {getInfo().setAutoStartPlayerNumber(number);}

    @NotNull
    @Override
    public Integer getMinPlayerNumber() {return getInfo().getMinPlayerNumber();}

    @Override
    public void setMinPlayerNumber(Integer number) {getInfo().setMinPlayerNumber(number);}

    @Override
    public List<Integer> getTeamSizes() {return getInfo().getTeamSizes();}

    @Override
    public void setTeamSizes(List<Integer> sizes) {getInfo().setTeamSizes(sizes);}

    @NotNull
    @Override
    public Type.Availability getTeamMergeAvailability() {return getInfo().getTeamMergeAvailability();}

    @Override
    public void setTeamMergeAvailability(Type.Availability availability) {getInfo().setTeamMergeAvailability(availability);}

    @Override
    public boolean isEqualTeamSizeRequired() {return getInfo().isEqualTeamSizeRequired();}

    @Override
    public void requireEqualTeamSize(boolean require) {getInfo().requireEqualTeamSize(require);}

    @Override
    public boolean hideTeams() {
        return getInfo().hideTeams();
    }

    @Override
    public void setHideTeams(boolean hide) {
        getInfo().setHideTeams(hide);
    }

    @NotNull
    @Override
    public Type.Discord getDiscordType() {
        return getInfo().getDiscordType();
    }

    @Override
    public void setDiscordType(Type.Discord type) {
        getInfo().setDiscordType(type);
    }

    @Override
    public List<String> getDescription() {return getInfo().getDescription();}

    @Override
    public void setDescription(List<String> description) {getInfo().setDescription(description);}

    @Override
    public void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio, String colorName) {
        this.getDatabase().addTeam(name, rank, prefix, color, ratio, colorName);
    }

    @Override
    public void removeTeam(String name) {
        this.getDatabase().removeTeam(name);
    }

    @Nullable
    @Override
    public Integer getHighestRank() {
        return this.getDatabase().getHighestRank();
    }

    @Override
    public boolean containsTeam(String name) {
        return this.getDatabase().containsTeam(name);
    }

    @NotNull
    @Override
    public DbTeam getTeam(String name) {
        return this.getDatabase().getTeam(name);
    }

    @NotNull
    @Override
    public Collection<String> getTeamNames() {
        return this.getDatabase().getTeamNames();
    }

    @NotNull
    @Override
    public Collection<Integer> getTeamRanks() {
        return this.getDatabase().getTeamRanks();
    }

    @NotNull
    @Override
    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        return this.getDatabase().getTeams();
    }

    @NotNull
    @Override
    public DbTmpGame toDatabase() {
        return this.getDatabase();
    }

    @NotNull
    @Override
    public DbTmpGame toLocal() {
        return this.getDatabase().toLocal();
    }
}
