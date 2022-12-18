/*
 * workspace.database-api.main
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

public class DbLocation {

    private final String worldName;
    private final float x;
    private final float y;
    private final float z;
    private final float yaw;
    private final float pitch;

    public DbLocation(String worldName, double x, double y, double z) {
        this(worldName, x, y, z, 0, 0);
    }

    public DbLocation(String worldName, double x, double y, double z, float yaw, float pitch) {
        this(worldName, ((float) x), ((float) y), ((float) z), yaw, pitch);
    }

    public DbLocation(String worldName, float x, float y, float z) {
        this(worldName, x, y, z, 0, 0);
    }

    public DbLocation(String worldName, float x, float y, float z, float yaw, float pitch) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public String getWorldName() {
        return worldName;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

}
