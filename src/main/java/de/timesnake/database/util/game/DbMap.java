package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotCached;

import java.util.*;

public interface DbMap extends DbCached<DbMap> {

    boolean exists();

    @NotCached
    void delete();

    String getName();

    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    Integer getMinPlayers();

    Integer getMaxPlayers();

    String getWorldName();

    @NotCached
    DbGame getGame();

    @NotCached
    DbLocation getLocation(Integer number);

    @NotCached
    DbLocation getFirstLocation();

    @NotCached
    Integer getFirstLocationNumber();

    @NotCached
    DbLocation getLastLocation();

    @NotCached
    Integer getLastLocationNumber();

    @NotCached
    HashMap<Integer, DbLocation> getMapLocations();

    @NotCached
    void addLocation(Integer number, DbLocation location);

    @NotCached
    void deleteLocation(Integer number);

    @NotCached
    boolean containsLocation(Integer number);

    String getItemName();

    ArrayList<String> getDescription();

    ArrayList<String> getInfo();

    @NotCached
    void setInfo(Collection<String> info);

    boolean isEnabled();

    @NotCached
    void setEnabled(boolean enable);

    @NotCached
    List<UUID> getAuthors();

    @NotCached
    void setAuthors(Collection<UUID> authors);

    @NotCached
    List<String> getAuthorNames();

    @NotCached
    void setAuthorNames(Collection<String> authors);
}
