package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbLocation;

public interface DbLoungeMap {

    boolean exists();

    String getName();

    String getWorldName(String locName);

    Double getX(String locName);

    Double getY(String locName);

    Double getZ(String locName);

    Float getYaw(String locName);

    Float getPitch(String locName);

    DbLocation getLocation(String locName);

}
