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
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface DbLoungeMapDisplay extends DbCached<DbLoungeMapDisplay> {

    boolean exists();

    @NotNull
    String getMapName();

    @NotNull
    Integer getIndex();

    @NotNull
    Integer getX();

    void setX(Integer x);

    @NotNull
    Integer getY();

    void setY(Integer y);

    @NotNull
    Integer getZ();

    void setZ(Integer z);

    @NotNull
    BlockSide getFacing();

    void setFacing(BlockSide facing);

    @NotNull
    BlockSide getOrientation();

    void setOrientation(BlockSide orientation);

    @NotNull
    Color getTitleColor();

    void setTitleColor(Color color);

    @NotNull
    Color getStatNameColor();

    void setStatNameColor(Color color);

    @NotNull
    Color getStatFirstColor();

    void setStatFirstColor(Color color);

    @NotNull
    Color getStatSecondColor();

    void setStatSecondColor(Color color);

    @NotNull
    Color getStatThirdColor();

    void setStatThirdColor(Color color);
}
