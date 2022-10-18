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

package de.timesnake.database.core.game.info;

import de.timesnake.database.util.game.DbGameInfo;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbCachedGameInfo extends DbCachedGameInfoBasis implements DbGameInfo {

    protected Integer maxPlayers;
    protected Type.Availability mapAvailability;
    protected Type.Availability kitAvailability;
    protected Boolean statistics;
    protected String texturePackLink;
    protected Integer playerTrackingRange;
    protected Integer maxHealth;
    protected Integer viewDistance;

    public DbCachedGameInfo(de.timesnake.database.core.game.info.DbGameInfo database) {
        super(database);
    }

    @Override
    protected de.timesnake.database.core.game.info.DbGameInfo getDatabase() {
        return (de.timesnake.database.core.game.info.DbGameInfo) super.getDatabase();
    }

    @Nullable
    @Override
    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.getDatabase().setMaxPlayers(maxPlayers);
    }

    @NotNull
    @Override
    public Type.Availability getMapAvailability() {
        return this.mapAvailability;
    }

    @Override
    public void setMapsAvailability(Type.Availability maps) {
        this.mapAvailability = maps;
        this.getDatabase().setMapsAvailability(maps);
    }

    @NotNull
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

    @Nullable
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

    @Nullable
    @Override
    public Integer getPlayerTrackingRange() {
        return this.playerTrackingRange;
    }

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {
        this.playerTrackingRange = playerTrackingRange;
        this.getDatabase().setPlayerTrackingRange(playerTrackingRange);
    }

    @Nullable
    @Override
    public Integer getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
        this.getDatabase().setMaxHealth(maxHealth);
    }

    @Nullable
    @Override
    public Integer getViewDistance() {
        return this.viewDistance;
    }

    @Override
    public void setViewDistance(Integer viewDistance) {
        this.viewDistance = viewDistance;
        this.getDatabase().setViewDistance(viewDistance);
    }

    @NotNull
    @Override
    public DbGameInfo toDatabase() {
        return this.getDatabase();
    }

    @NotNull
    @Override
    public DbGameInfo toLocal() {
        return this.getDatabase().toLocal();
    }
}
