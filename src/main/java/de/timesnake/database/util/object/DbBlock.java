package de.timesnake.database.util.object;

public class DbBlock {

    private String world;
    private int x;
    private int y;
    private int z;

    public DbBlock(String world, int x, int y, int z) {
        super();
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

}
