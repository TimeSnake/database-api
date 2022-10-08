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

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbGameInfo extends DbGameInfoBasis {

    @Nullable
    Integer getMaxPlayers();

    @NotCached
    void setMaxPlayers(int maxPlayers);

    @NotNull
    Type.Availability getMapAvailability();

    @NotCached
    void setMapsAvailability(Type.Availability maps);

    @NotNull
    Type.Availability getKitAvailability();

    @NotCached
    void setKitsAvailability(Type.Availability kits);

    boolean hasStatistics();

    @NotCached
    void setStatistics(boolean statistics);

    @Nullable
    String getTexturePackLink();

    @NotCached
    void setTexturePackLink(String texturePack);

    Boolean hasTexturePack();

    @Nullable
    Integer getPlayerTrackingRange();

    @NotCached
    void setPlayerTrackingRange(Integer playerTrackingRange);

    @Nullable
    Integer getMaxHealth();

    @NotCached
    void setMaxHealth(Integer maxHealth);

    @NotNull
    @Override
    DbGameInfo toDatabase();

    @NotNull
    @Override
    DbGameInfo toLocal();
}
