package de.timesnake.database.util.game;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public interface DbMap extends DbCached<DbMap> {

    boolean exists();

    @NotCached
    void delete();

    @NotNull
    String getName();

    @NotNull
    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    @Nullable
    Integer getMinPlayers();

    @Nullable
    Integer getMaxPlayers();

    @NotNull
    String getWorldName();

    @NotCached
    @NotNull
    DbGame getGame();

    @Nullable
    @NotCached
    DbLocation getLocation(Integer number);

    @Nullable
    @NotCached
    DbLocation getFirstLocation();

    @Nullable
    @NotCached
    Integer getFirstLocationNumber();

    @Nullable
    @NotCached
    DbLocation getLastLocation();

    @Nullable
    @NotCached
    Integer getLastLocationNumber();

    @NotCached
    @NotNull
    HashMap<Integer, DbLocation> getMapLocations();

    @NotCached
    void addLocation(Integer number, DbLocation location);

    @NotCached
    void deleteLocation(Integer number);

    @NotCached
    boolean containsLocation(Integer number);

    @Nullable
    String getItemName();

    @NotNull
    ArrayList<String> getDescription();

    @NotNull
    ArrayList<String> getInfo();

    @NotCached
    void setInfo(Collection<String> info);

    boolean isEnabled();

    @NotCached
    void setEnabled(boolean enable);

    @NotCached
    @NotNull
    List<UUID> getAuthors();

    @NotCached
    void setAuthors(Collection<UUID> authors);

    @NotCached
    void addAuthor(UUID author);

    @NotCached
    void removeAuthor(UUID author);

    @NotCached
    @NotNull
    List<String> getAuthorNames();

    @NotCached
    void setAuthorNames(Collection<String> authors);
}
