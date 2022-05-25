package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.UUID;

public class DbMapAuthor extends TableQuery {

    protected DbMapAuthor(DatabaseConnector databaseConnector, String nameTable, String mapName, UUID authorUuid) {
        super(databaseConnector, nameTable, new TableEntry<>(mapName, Column.Game.MAP_NAME),
                new TableEntry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
    }

    public String getMapName() {
        return ((String) super.primaryEntries.get(0).getValue());
    }

    public UUID getAuthorUuid() {
        return ((UUID) super.primaryEntries.get(1).getValue());
    }

    public String getAuthorName() {
        return Database.getUsers().getUser(this.getAuthorUuid()).getName();
    }
}
