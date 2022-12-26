/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.ColumnMap;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Set;

public class DbCachedLoungeMapDisplay implements de.timesnake.database.util.game.DbLoungeMapDisplay {

    private final DbLoungeMapDisplay display;

    private final String mapName;
    private final Integer displayIndex;
    private Integer x;
    private Integer y;
    private Integer z;
    private BlockSide facing;
    private BlockSide orientation;
    private Color titleColor;
    private Color statNameColor;
    private Color statFirstColor;
    private Color statSecondColor;
    private Color statThirdColor;

    public DbCachedLoungeMapDisplay(DbLoungeMapDisplay display) {
        this(display, display.getFirstWithKey(Set.of(Column.Game.LOUNGE_MAP_DISPLAY_INDEX,
                Column.Game.LOUNGE_MAP_DISPLAY_X,
                Column.Game.LOUNGE_MAP_DISPLAY_Y, Column.Game.LOUNGE_MAP_DISPLAY_Z,
                Column.Game.LOUNGE_MAP_DISPLAY_FACING,
                Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION, Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR,
                Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR, Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR,
                Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR, Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR)));
    }

    public DbCachedLoungeMapDisplay(DbLoungeMapDisplay display, ColumnMap map) {
        this.display = display;
        this.mapName = display.getMapName();
        this.displayIndex = display.getIndex();
        this.x = map.get(Column.Game.LOUNGE_MAP_DISPLAY_X);
        this.y = map.get(Column.Game.LOUNGE_MAP_DISPLAY_Y);
        this.z = map.get(Column.Game.LOUNGE_MAP_DISPLAY_Z);
        this.facing = map.get(Column.Game.LOUNGE_MAP_DISPLAY_FACING);
        this.orientation = map.get(Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION);
        this.titleColor = map.get(Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR);
        this.statNameColor = map.get(Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR);
        this.statFirstColor = map.get(Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR);
        this.statSecondColor = map.get(Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR);
        this.statThirdColor = map.get(Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR);
    }

    @Override
    public boolean exists() {
        return this.display.exists();
    }

    @NotNull
    @Override
    public String getMapName() {
        return mapName;
    }

    @NotNull
    @Override
    public Integer getIndex() {
        return displayIndex;
    }

    @NotNull
    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public void setX(Integer x) {
        this.x = x;
        this.display.setX(x);
    }

    @NotNull
    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
        this.display.setY(y);
    }

    @NotNull
    @Override
    public Integer getZ() {
        return z;
    }

    @Override
    public void setZ(Integer z) {
        this.z = z;
        this.display.setZ(z);
    }

    @NotNull
    @Override
    public BlockSide getFacing() {
        return facing;
    }

    @Override
    public void setFacing(BlockSide facing) {
        this.facing = facing;
        this.display.setFacing(facing);
    }

    @NotNull
    @Override
    public BlockSide getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(BlockSide orientation) {
        this.orientation = orientation;
        this.display.setOrientation(orientation);
    }

    @NotNull
    @Override
    public Color getTitleColor() {
        return this.titleColor;
    }

    @Override
    public void setTitleColor(Color color) {
        this.titleColor = color;
        this.display.setTitleColor(color);
    }

    @NotNull
    @Override
    public Color getStatNameColor() {
        return this.statNameColor;
    }

    @Override
    public void setStatNameColor(Color color) {
        this.statNameColor = color;
        this.display.setStatNameColor(color);
    }

    @NotNull
    @Override
    public Color getStatFirstColor() {
        return this.statFirstColor;
    }

    @Override
    public void setStatFirstColor(Color color) {
        this.statFirstColor = color;
        this.display.setStatFirstColor(color);
    }

    @NotNull
    @Override
    public Color getStatSecondColor() {
        return this.statSecondColor;
    }

    @Override
    public void setStatSecondColor(Color color) {
        this.statSecondColor = color;
        this.display.setStatSecondColor(color);
    }

    @NotNull
    @Override
    public Color getStatThirdColor() {
        return this.statThirdColor;
    }

    @Override
    public void setStatThirdColor(Color color) {
        this.statThirdColor = color;
        this.display.setStatThirdColor(color);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbLoungeMapDisplay toLocal() {
        return new DbCachedLoungeMapDisplay(this.display);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbLoungeMapDisplay toDatabase() {
        return this.display;
    }
}
