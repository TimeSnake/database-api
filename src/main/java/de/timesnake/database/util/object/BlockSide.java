/*
 * Copyright (C) 2023 timesnake
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
