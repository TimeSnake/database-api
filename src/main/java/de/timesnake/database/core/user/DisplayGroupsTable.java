package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.UUID;

public class DisplayGroupsTable extends TableDDL {

    public DisplayGroupsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.User.UUID, Column.User.DISPLAY_GROUP);
    }

    @Override
    protected void create() {
        super.create();
    }

    @Override
    protected void backup() {
        super.backup();
    }

    public DbDisplayGroupUser getUser(UUID uuid) {
        return new DbDisplayGroupUser(this.databaseConnector, this.tableName, uuid);
    }
}
