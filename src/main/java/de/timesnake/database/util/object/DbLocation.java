/*
 * Copyright (C) 2023 timesnake
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
