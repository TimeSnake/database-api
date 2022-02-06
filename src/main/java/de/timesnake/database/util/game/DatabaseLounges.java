package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbLocation;

import java.util.Collection;

public interface DatabaseLounges {

    void addMap(String name, String locName, DbLocation spawn);

    void removeMap(String name);

    boolean containsMap(String name);

    DbLoungeMap getMap(String name);

    Collection<DbLoungeMap> getMaps();
}
