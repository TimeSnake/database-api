package de.timesnake.database.core.network;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DbNetworkFile extends TableQuery implements de.timesnake.database.util.network.DbNetworkFile {

    protected DbNetworkFile(DatabaseConnector databaseConnector, String nameTable, String name) {
        super(databaseConnector, nameTable, new TableEntry<>(name, Column.Network.FILE_NAME));
    }

    @Override
    public boolean exists() {
        return this.getFile() != null;
    }

    @NotNull
    @Override
    public File getFile() {
        return super.getFirstWithKey(Column.Network.FILE_PATH);
    }
}
