package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.LocationsTable;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapLocationsTable extends LocationsTable {

    protected String gameName;

    public MapLocationsTable(DatabaseConnector databaseConnector, String gameName) {
        super(databaseConnector, gameName, Column.Game.MAP_NAME);

        this.gameName = gameName;

    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public ArrayList<DbMapLocations> getMaps() {
        ArrayList<DbMapLocations> maps = new ArrayList<>();
        ArrayList<String> mapNames = new ArrayList<>();
        for (String mapName : super.get(Column.Game.MAP_NAME)) {
            DbMapLocations map = this.getMapLocations(mapName);
            if (!mapNames.contains(mapName)) {
                maps.add(map);
                mapNames.add(mapName);
            }
        }
        return maps;
    }

    public boolean containsMap(String mapName) {
        return super.get(Column.Game.MAP_NAME).contains(mapName);
    }

    @NotNull
    public DbMapLocations getMapLocations(String mapName) {
        return new DbMapLocations(this.databaseConnector, this.gameName, mapName);
    }

    public void deleteMap(String mapName) {
        super.deleteEntry(new TableEntry<>(mapName, Column.Game.MAP_NAME));
    }
}
