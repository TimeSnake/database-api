/*
 * Copyright (C) 2022 timesnake
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
