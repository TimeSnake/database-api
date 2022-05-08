package de.timesnake.database.util.game;

import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotCached;

import java.awt.*;
import java.util.Collection;

public interface DbLoungeMap extends DbCached<DbLoungeMap> {

    boolean exists();

    String getName();

    String getWorldName();

    Double getX();

    Double getY();

    Double getZ();

    Float getYaw();

    Float getPitch();

    DbLocation getLocation();

    @NotCached
    void addMapDisplay(Integer displayIndex, Integer x, Integer y, Integer z,
                       BlockSide facing, BlockSide orientation, Color titleColor, Color statNameColor,
                       Color firstColor, Color secondColor, Color thirdColor);

    @NotCached
    void removeMapDisplay(Integer displayIndex);

    @NotCached
    boolean containsMapDisplay(Integer displayIndex);

    DbLoungeMapDisplay getMapDisplay(Integer displayIndex);

    @NotCached
    Collection<DbLoungeMapDisplay> getMapDisplays();

    @NotCached
    Collection<DbLoungeMapDisplay> getCachedMapDisplays();
}
