package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;

import java.util.Set;
import java.util.UUID;

public class InfosTable extends PlayersTable {

    public InfosTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.User.PREFIX);
        super.addColumn(Column.User.SUFFIX);
        super.addColumn(Column.User.NICK);
        super.addColumn(Column.User.TIME_COINS);
        super.addColumn(Column.User.STATUS);
        super.addColumn(Column.User.SERVICE);
        super.addColumn(Column.User.ANTI_CHEAT_MESSAGES);
        super.addColumn(Column.User.AIR_MODE);
        super.addColumn(Column.User.TASK);
        super.addColumn(Column.User.TEAM);
        super.addColumn(Column.User.KIT);
        super.addColumn(Column.User.PERMGROUP);
        super.addColumn(Column.User.SERVER);
        super.addColumn(Column.User.SERVER_LAST);
        super.addColumn(Column.User.SERVER_LOBBY);
        super.addColumn(Column.User.DATA_PROTECTION);
    }

    public void backup() {
        Column<?>[] columns = {Column.User.UUID, Column.User.NAME, Column.User.PREFIX, Column.User.SUFFIX, Column.User.NICK, Column.User.TIME_COINS, Column.User.PERMGROUP, Column.User.DATA_PROTECTION};
        super.createBackup(columns);
    }

    public void addPlayer(UUID uuid, String name, String permGroup, String server) {
        super.addPlayer(uuid, name);
        super.setSynchronized(Set.of(new TableEntry<>(Status.User.ONLINE, Column.User.STATUS), new TableEntry<>(false, Column.User.AIR_MODE), new TableEntry<>(null, Column.User.TEAM), new TableEntry<>(permGroup, Column.User.PERMGROUP), new TableEntry<>(server, Column.User.SERVER), new TableEntry<>(server, Column.User.SERVER_LAST), new TableEntry<>(server, Column.User.SERVER_LOBBY), new TableEntry<>(false, Column.User.SERVICE)), new TableEntry<>(uuid, Column.User.UUID));
    }

}
