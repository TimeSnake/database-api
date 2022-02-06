package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbLocal;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotLocal;

import java.util.*;

public interface DbMap extends DbLocal<DbMap> {

    boolean exists();

    @NotLocal
    void delete();

    String getName();

    String getDisplayName();

    Integer getMinPlayers();

    Integer getMaxPlayers();

    String getWorldName();

    @NotLocal
    DbGame getGame();

    @NotLocal
    DbLocation getLocation(Integer number);

    @NotLocal
    DbLocation getFirstLocation();

    @NotLocal
    Integer getFirstLocationNumber();

    @NotLocal
    DbLocation getLastLocation();

    @NotLocal
    Integer getLastLocationNumber();

    @NotLocal
    HashMap<Integer, DbLocation> getMapLocations();

    @NotLocal
    void addLocation(Integer number, DbLocation location);

    @NotLocal
    void deleteLocation(Integer number);

    @NotLocal
    boolean containsLocation(Integer number);

    String getItemName();

    ArrayList<String> getDescription();

    ArrayList<String> getInfo();

    boolean isEnabled();

    @NotLocal
    void setDisplayName(String displayName);

    @NotLocal
    void setInfo(Collection<String> info);

    @NotLocal
    List<UUID> getAuthors();

    @NotLocal
    List<String> getAuthorNames();

    @NotLocal
    void setAuthors(Collection<UUID> authors);

    @NotLocal
    void setAuthorNames(Collection<String> authors);

    void setEnabled(boolean enable);
}
