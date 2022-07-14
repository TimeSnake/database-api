package de.timesnake.database.core;

import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;

import java.util.HashMap;

public class LocationsTable extends TableDDL {

    public LocationsTable(DatabaseConnector databaseConnector, String tableName, Column<String> primaryColumn) {
        super(databaseConnector, tableName, primaryColumn, Column.Location.NUMBER);
        super.addColumn(Column.Location.WORLD);
        super.addColumn(Column.Location.X);
        super.addColumn(Column.Location.Y);
        super.addColumn(Column.Location.Z);
        super.addColumn(Column.Location.YAW);
        super.addColumn(Column.Location.PITCH);
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

    public boolean containsWorld(String worldName) {
        return super.get(Column.Location.WORLD).contains(worldName);
    }

    public void deleteWorld(String worldName) {
        super.deleteEntry(new TableEntry<>(worldName, Column.Location.WORLD));
    }

    public de.timesnake.database.util.object.DbLocation getLocation(Integer number, String worldName) {
        if (number == null || worldName == null) {
            return null;
        }
        TableEntry<Integer> numberEntry = new TableEntry<>(number, Column.Location.NUMBER);
        TableEntry<String> worldEntry = new TableEntry<>(worldName, Column.Location.WORLD);

        return new de.timesnake.database.util.object.DbLocation(worldName, super.getFirst(Column.Location.X,
                numberEntry, worldEntry), super.getFirst(Column.Location.Y, numberEntry, worldEntry),
                super.getFirst(Column.Location.Z, numberEntry, worldEntry), super.getFirst(Column.Location.YAW,
                numberEntry, worldEntry), super.getFirst(Column.Location.PITCH, numberEntry, worldEntry));
    }

    public de.timesnake.database.util.object.DbLocation getFirstLocation(String worldName) {
        return this.getLocation(this.getFirstLocationNumber(worldName), worldName);
    }

    public Integer getFirstLocationNumber(String worldName) {
        return super.getLowestInteger(Column.Location.NUMBER, new TableEntry<>(worldName, Column.Location.WORLD));
    }

    public de.timesnake.database.util.object.DbLocation getLastSpawn(String worldName) {
        return this.getLocation(this.getLastLocationNumber(worldName), worldName);
    }

    public Integer getLastLocationNumber(String worldName) {
        return super.getHighestInteger(Column.Location.NUMBER, new TableEntry<>(worldName, Column.Location.WORLD));
    }

    public HashMap<Integer, DbLocation> getLocations(String worldName) {
        HashMap<Integer, de.timesnake.database.util.object.DbLocation> spawns = new HashMap<>();
        for (Integer number : super.get(Column.Location.NUMBER, new TableEntry<>(worldName, Column.Location.WORLD))) {
            spawns.put(number, this.getLocation(number, worldName));
        }
        return spawns;
    }

    public void addLocation(Integer number, de.timesnake.database.util.object.DbLocation location,
                            TableEntry<?> primaryEntries) {
        if (number == null) {
            return;
        }
        super.addEntry(new PrimaryEntries(new TableEntry<>(number, Column.Location.NUMBER),
                        new TableEntry<>(location.getWorldName(), Column.Location.WORLD), primaryEntries),
                new TableEntry<>(number, Column.Location.NUMBER), new TableEntry<>(location.getWorldName(),
                        Column.Location.WORLD), new TableEntry<>(location.getX(), Column.Location.X),
                new TableEntry<>(location.getY(), Column.Location.Y), new TableEntry<>(location.getZ(),
                        Column.Location.Z), new TableEntry<>(location.getYaw(), Column.Location.YAW),
                new TableEntry<>(location.getPitch(), Column.Location.PITCH));
    }

    public void deleteSpawn(Integer number, String worldName) {
        if (number == null || worldName == null) {
            return;
        }
        super.deleteEntry(new TableEntry<>(number, Column.Location.NUMBER), new TableEntry<>(worldName,
                Column.Location.WORLD));
    }

    public boolean containsSpawn(Integer number, String worldName) {
        return this.getLocation(number, worldName).getWorldName() != null;
    }


}
