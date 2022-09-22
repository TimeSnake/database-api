package de.timesnake.database.core.game.map;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class DbMap implements de.timesnake.database.util.game.DbMap {

    private final String gameName;

    private final String name;

    private final MapsAuthorTable authorTable;

    private final DbMapInfo info;
    private final DbMapLocations mapLocations;

    protected DbMap(String gameName, String mapName) {
        this.gameName = gameName;
        this.name = mapName;

        this.info = DatabaseManager.getInstance().getGameMaps().getMapsInfoTable(gameName).getMapInfo(mapName);
        this.mapLocations =
                DatabaseManager.getInstance().getGameMaps().getMapsSpawnsTable(gameName).getMapLocations(mapName);
        this.authorTable = DatabaseManager.getInstance().getGameMaps().getMapsAuthorTable(gameName);
    }

    @Override
    public boolean exists() {
        return this.info.exists();
    }

    @Override
    public void delete() {
        this.info.delete();
        this.mapLocations.delete();
        this.authorTable.removeMapAuthors(this.name);
    }

    protected DbMapInfo getDbInfo() {
        return this.info;
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return this.info.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        this.info.setDisplayName(displayName);
    }

    @Nullable
    @Override
    public Integer getMinPlayers() {
        return this.info.getMinPlayers();
    }

    @Nullable
    @Override
    public Integer getMaxPlayers() {
        return this.info.getMaxPlayers();
    }

    @Override
    public boolean isEnabled() {
        return this.info.isEnabled();
    }

    @Override
    public void setEnabled(boolean enable) {
        this.info.setEnabled(enable);
    }

    @NotNull
    @Override
    public String getWorldName() {
        return this.mapLocations.getWorldName();
    }

    @NotNull
    @Override
    public DbGame getGame() {
        return DatabaseManager.getInstance().getGames().getGame(this.gameName);
    }

    @Nullable
    @Override
    public DbLocation getLocation(Integer number) {
        return this.mapLocations.getLocation(number);
    }

    @Nullable
    @Override
    public DbLocation getFirstLocation() {
        return this.mapLocations.getFirstLocation();
    }

    @Nullable
    @Override
    public Integer getFirstLocationNumber() {
        return this.mapLocations.getFirstLocationNumber();
    }

    @Nullable
    @Override
    public DbLocation getLastLocation() {
        return this.mapLocations.getLastLocation();
    }

    @Nullable
    @Override
    public Integer getLastLocationNumber() {
        return this.mapLocations.getLastLocationNumber();
    }

    @NotNull
    @Override
    public HashMap<Integer, DbLocation> getMapLocations() {
        return this.mapLocations.getLocations();
    }

    @Override
    public void addLocation(Integer number, DbLocation location) {
        this.mapLocations.addLocation(number, location);
    }

    @Override
    public void deleteLocation(Integer number) {
        this.mapLocations.deleteLocation(number);
    }

    @Override
    public boolean containsLocation(Integer number) {
        return this.getLocation(number) != null;
    }

    @Nullable
    @Override
    public String getItemName() {
        return this.info.getItemName();
    }

    @NotNull
    @Override
    public ArrayList<String> getDescription() {
        return this.info.getDescription();
    }

    @NotNull
    @Override
    public ArrayList<String> getInfo() {
        return this.info.getInfo();
    }

    @Override
    public void setInfo(Collection<String> info) {
        this.info.setInfo(info);
    }

    @NotNull
    @Override
    public List<UUID> getAuthors() {
        return this.authorTable.getAuthors(this.name).stream().map((DbMapAuthor::getAuthorUuid)).collect(Collectors.toList());
    }

    @Override
    public void setAuthors(Collection<UUID> authors) {
        authors.forEach(this::addAuthor);
    }

    @Override
    public void addAuthor(UUID author) {
        this.authorTable.addMapAuthor(this.name, author);
    }

    @Override
    public void removeAuthor(UUID author) {
        this.authorTable.removeMapAuthor(this.name, author);
    }

    @NotNull
    @Override
    public List<String> getAuthorNames() {
        return this.authorTable.getAuthors(this.name).stream().map((DbMapAuthor::getAuthorName)).collect(Collectors.toList());
    }

    @Override
    public void setAuthorNames(Collection<String> authors) {
        for (String name : authors) {
            UUID uuid = Database.getUsers().getUser(name).getUniqueId();
            if (uuid == null) {
                continue;
            }
            this.authorTable.addMapAuthor(this.name, uuid);
        }
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbMap toLocal() {
        return new DbCachedMap(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbMap toDatabase() {
        return this;
    }
}
