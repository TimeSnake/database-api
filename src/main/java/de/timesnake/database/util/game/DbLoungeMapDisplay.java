package de.timesnake.database.util.game;

import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.DbCached;

import java.awt.*;

public interface DbLoungeMapDisplay extends DbCached<DbLoungeMapDisplay> {

    boolean exists();

    String getMapName();

    Integer getIndex();

    Integer getX();

    void setX(Integer x);

    Integer getY();

    void setY(Integer y);

    Integer getZ();

    void setZ(Integer z);

    BlockSide getFacing();

    void setFacing(BlockSide facing);

    BlockSide getOrientation();

    void setOrientation(BlockSide orientation);

    Color getTitleColor();

    void setTitleColor(Color color);

    Color getStatNameColor();

    void setStatNameColor(Color color);

    Color getStatFirstColor();

    void setStatFirstColor(Color color);

    Color getStatSecondColor();

    void setStatSecondColor(Color color);

    Color getStatThirdColor();

    void setStatThirdColor(Color color);
}
