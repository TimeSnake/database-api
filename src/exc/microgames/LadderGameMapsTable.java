package de.timesnake.database.core.microgames;

import de.timesnake.database.core.LocationsTable;

import java.sql.Connection;

public class LadderGameMapsTable extends LocationsTable {

    public LadderGameMapsTable(Connection connection, String tableName) {
        super(connection, tableName);
    }
}
