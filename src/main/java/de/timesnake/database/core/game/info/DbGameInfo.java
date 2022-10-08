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

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbGameInfo extends DbGameInfoBasis implements de.timesnake.database.util.game.DbGameInfo {

    public DbGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, gameName);
    }

    @Nullable
    @Override
    public Integer getMaxPlayers() {
        return super.getFirstWithKey(Column.Game.MAX_PLAYERS);
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        super.setWithKey(maxPlayers, Column.Game.MAX_PLAYERS);
    }

    @NotNull
    @Override
    public Type.Availability getMapAvailability() {
        Type.Availability availability = super.getFirstWithKey(Column.Game.MAPS);
        return availability != null ? availability : Type.Availability.FORBIDDEN;
    }

    @Override
    public void setMapsAvailability(Type.Availability maps) {
        super.setWithKey(maps, Column.Game.MAPS);
    }

    @NotNull
    @Override
    public Type.Availability getKitAvailability() {
        Type.Availability availability = super.getFirstWithKey(Column.Game.KITS);
        return availability != null ? availability : Type.Availability.FORBIDDEN;
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

    @Nullable
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

    @Nullable
    @Override
    public Integer getPlayerTrackingRange() {
        return super.getFirstWithKey(Column.Game.PLAYER_TRACKING_RANGE);
    }

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {
        super.setWithKey(playerTrackingRange, Column.Game.PLAYER_TRACKING_RANGE);
    }

    @Nullable
    @Override
    public Integer getMaxHealth() {
        return super.getFirstWithKey(Column.Game.MAX_HEALTH);
    }

    @Override
    public void setMaxHealth(Integer maxHealth) {
        super.setWithKey(maxHealth, Column.Game.MAX_HEALTH);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbGameInfo toDatabase() {
        return this;
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbGameInfo toLocal() {
        return new DbCachedGameInfo(this);
    }
}
