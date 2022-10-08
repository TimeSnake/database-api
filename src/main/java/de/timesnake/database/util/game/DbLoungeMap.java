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

import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collection;

public interface DbLoungeMap extends DbCached<DbLoungeMap> {

    boolean exists();

    @NotNull
    String getName();

    @NotNull
    String getWorldName();

    @NotNull
    Double getX();

    @NotNull
    Double getY();

    @NotNull
    Double getZ();

    @NotNull
    Float getYaw();

    @NotNull
    Float getPitch();

    @NotNull
    DbLocation getLocation();

    @NotCached
    void addMapDisplay(Integer displayIndex, Integer x, Integer y, Integer z,
                       BlockSide facing, BlockSide orientation, Color titleColor, Color statNameColor,
                       Color firstColor, Color secondColor, Color thirdColor);

    @NotCached
    void removeMapDisplay(Integer displayIndex);

    @NotCached
    boolean containsMapDisplay(Integer displayIndex);

    @NotNull
    DbLoungeMapDisplay getMapDisplay(Integer displayIndex);

    @NotCached
    @NotNull
    Collection<DbLoungeMapDisplay> getMapDisplays();

    @NotCached
    @NotNull
    Collection<DbLoungeMapDisplay> getCachedMapDisplays();
}
