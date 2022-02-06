package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public abstract class PvPTable<Server extends DbPvPServer> extends TaskTable<Server> {

    public PvPTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Server.OLD_PVP);
    }
}
