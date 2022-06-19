package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.game.DbLoungeMap;
import de.timesnake.database.util.game.DbLoungeMapDisplay;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DbLocation;

import java.awt.*;
import java.util.Collection;
import java.util.Set;

public class DbCachedLoungeMap implements DbLoungeMap {

    private final de.timesnake.database.core.game.lounge.DbLoungeMap map;

    private final String mapName;
    private final String worldName;
    private final DbLocation location;

    public DbCachedLoungeMap(de.timesnake.database.core.game.lounge.DbLoungeMap map) {
        this(map, map.getFirst(Set.of(Column.Game.LOUNGE_MAP_NAME, Column.Game.LOUNGE_MAP_WORLD,
                Column.Game.LOUNGE_MAP_LOC_X, Column.Game.LOUNGE_MAP_LOC_Y, Column.Game.LOUNGE_MAP_LOC_Z,
                Column.Game.LOUNGE_MAP_LOC_YAW, Column.Game.LOUNGE_MAP_LOC_PITCH)));
    }

    public DbCachedLoungeMap(de.timesnake.database.core.game.lounge.DbLoungeMap map, ColumnMap entry) {
        this.map = map;
        this.mapName = map.getName();
        this.worldName = entry.get(Column.Game.LOUNGE_MAP_WORLD);

        this.location = new DbLocation(worldName,
                entry.get(Column.Game.LOUNGE_MAP_LOC_X), entry.get(Column.Game.LOUNGE_MAP_LOC_Y),
                entry.get(Column.Game.LOUNGE_MAP_LOC_Z), entry.get(Column.Game.LOUNGE_MAP_LOC_YAW),
                entry.get(Column.Game.LOUNGE_MAP_LOC_PITCH));
    }

    @Override
    public boolean exists() {
        return this.map.exists();
    }

    @Override
    public String getName() {
        return this.mapName;
    }

    @Override
    public String getWorldName() {
        return this.worldName;
    }

    @Override
    public Double getX() {
        return this.location.getX();
    }

    @Override
    public Double getY() {
        return this.location.getY();
    }

    @Override
    public Double getZ() {
        return this.location.getZ();
    }

    @Override
    public Float getYaw() {
        return this.location.getYaw();
    }

    @Override
    public Float getPitch() {
        return this.location.getPitch();
    }

    @Override
    public DbLocation getLocation() {
        return this.location;
    }

    @Override
    public void addMapDisplay(Integer displayIndex, Integer x, Integer y, Integer z, BlockSide facing,
                              BlockSide orientation, Color titleColor, Color statNameColor,
                              Color firstColor, Color secondColor, Color thirdColor) {
        this.map.addMapDisplay(displayIndex, x, y, z, facing, orientation, titleColor, statNameColor, firstColor,
                secondColor, thirdColor);
    }

    @Override
    public void removeMapDisplay(Integer displayIndex) {
        this.map.removeMapDisplay(displayIndex);
    }

    @Override
    public boolean containsMapDisplay(Integer displayIndex) {
        return this.map.containsMapDisplay(displayIndex);
    }

    @Override
    public DbLoungeMapDisplay getMapDisplay(Integer displayIndex) {
        return this.map.getMapDisplay(displayIndex);
    }

    @Override
    public Collection<DbLoungeMapDisplay> getMapDisplays() {
        return this.map.getMapDisplays();
    }

    @Override
    public Collection<DbLoungeMapDisplay> getCachedMapDisplays() {
        return this.map.getCachedMapDisplays();
    }

    @Override
    public DbLoungeMap toLocal() {
        return new DbCachedLoungeMap(this.map);
    }

    @Override
    public DbLoungeMap toDatabase() {
        return this.map;
    }
}