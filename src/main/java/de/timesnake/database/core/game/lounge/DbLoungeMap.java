package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;

public class DbLoungeMap extends TableQuery implements de.timesnake.database.util.game.DbLoungeMap {

    public DbLoungeMap(DatabaseConnector databaseConnector, String nameTable, String mapName) {
        super(databaseConnector, nameTable, new TableEntry<>(mapName, Column.Game.LOUNGE_MAP_NAME));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_NAME) != null;
    }

    @Override
    public String getName() {
        return (String) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getWorldName(String locName) {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_WORLD);
    }

    @Override
    public Double getX(String locName) {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_X, new TableEntry<>(locName, Column.Game.LOUNGE_MAP_LOC_NAME));
    }

    @Override
    public Double getY(String locName) {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_Y, new TableEntry<>(locName, Column.Game.LOUNGE_MAP_LOC_NAME));
    }

    @Override
    public Double getZ(String locName) {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_Z, new TableEntry<>(locName, Column.Game.LOUNGE_MAP_LOC_NAME));
    }

    @Override
    public Float getYaw(String locName) {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_YAW, new TableEntry<>(locName, Column.Game.LOUNGE_MAP_LOC_NAME));
    }

    @Override
    public Float getPitch(String locName) {
        return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_PITCH, new TableEntry<>(locName, Column.Game.LOUNGE_MAP_LOC_NAME));
    }

    @Override
    public DbLocation getLocation(String locName) {
        return new DbLocation(this.getWorldName(locName), this.getX(locName), this.getY(locName), this.getZ(locName), this.getYaw(locName), this.getPitch(locName));
    }

}
