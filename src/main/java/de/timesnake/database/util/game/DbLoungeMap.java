/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotCached;
import java.awt.Color;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface DbLoungeMap extends DbCached<DbLoungeMap> {

    boolean exists();

    @NotNull
    String getName();

    @NotNull
    String getWorldName();

    @NotNull
    Float getX();

    @NotNull
    Float getY();

    @NotNull
    Float getZ();

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
