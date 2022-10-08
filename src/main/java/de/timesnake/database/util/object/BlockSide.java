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

package de.timesnake.database.util.object;

public enum BlockSide {

    NORTH(0, 0, -1),
    EAST(1, 0, 0),
    SOUTH(0, 0, 1),
    WEST(-1, 0, 0),
    UP(0, 1, 0),
    DOWN(0, -1, 0);

    private final int modX;
    private final int modY;
    private final int modZ;

    BlockSide(int modX, int modY, int modZ) {
        this.modX = modX;
        this.modY = modY;
        this.modZ = modZ;
    }

    public int getModX() {
        return this.modX;
    }

    public int getModY() {
        return this.modY;
    }

    public int getModZ() {
        return this.modZ;
    }
}
