package de.timesnake.database.core.game.map;

import de.timesnake.database.util.game.DbGame;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.object.DbLocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DbCachedMap extends DbCachedMapInfo implements DbMap {

    private final de.timesnake.database.core.game.map.DbMap map;

    public DbCachedMap(de.timesnake.database.core.game.map.DbMap map) {
        super(map.getDbInfo());
        this.map = map;
    }

    @Override
    public void delete() {
        this.map.delete();
    }

    @Override
    public String getWorldName() {
        return this.map.getWorldName();
    }

    @Override
    public DbGame getGame() {
        return this.map.getGame();
    }

    @Override
    public DbLocation getLocation(Integer number) {
        return this.map.getLocation(number);
    }

    @Override
    public DbLocation getFirstLocation() {
        return this.map.getFirstLocation();
    }

    @Override
    public Integer getFirstLocationNumber() {
        return this.map.getFirstLocationNumber();
    }

    @Override
    public DbLocation getLastLocation() {
        return this.map.getLastLocation();
    }

    @Override
    public Integer getLastLocationNumber() {
        return this.map.getLastLocationNumber();
    }

    @Override
    public HashMap<Integer, DbLocation> getMapLocations() {
        return this.map.getMapLocations();
    }

    @Override
    public void addLocation(Integer number, DbLocation location) {
        this.map.addLocation(number, location);
    }

    @Override
    public void deleteLocation(Integer number) {
        this.map.deleteLocation(number);
    }

    @Override
    public boolean containsLocation(Integer number) {
        return this.map.containsLocation(number);
    }

    @Override
    public List<UUID> getAuthors() {
        return this.map.getAuthors();
    }

    @Override
    public List<String> getAuthorNames() {
        return this.map.getAuthorNames();
    }

    @Override
    public void setAuthors(Collection<UUID> authors) {
        this.map.setAuthors(authors);
    }

    @Override
    public void setAuthorNames(Collection<String> authors) {
        this.map.setAuthorNames(authors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DbMap toLocal() {
        return new DbCachedMap(this.map);
    }

    @Override
    public DbMap toDatabase() {
        return this.map;
    }
}
