package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.UUID;

public abstract class DbPlayer extends TableQuery implements de.timesnake.database.util.user.DbPlayer {

    protected DbPlayer(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.User.UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirst(Column.User.UUID) != null;
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.User.NAME);
    }

    @Override
    public UUID getUniqueId() {
        return (UUID) super.primaryEntries.get(0).getValue();
    }

}
