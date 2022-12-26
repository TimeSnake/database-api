/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
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
        super.addColumn(Column.User.PERM_GROUP);
        super.addColumn(Column.User.SERVER);
        super.addColumn(Column.User.SERVER_LAST);
        super.addColumn(Column.User.SERVER_LOBBY);
        super.addColumn(Column.User.PRIVACY_POLICY);
        super.addColumn(Column.User.DISCORD_ID);
    }

    @Override
    public void backup() {
        Column<?>[] columns = {Column.User.UUID, Column.User.NAME, Column.User.PREFIX, Column.User.SUFFIX,
                Column.User.NICK, Column.User.TIME_COINS, Column.User.PERM_GROUP, Column.User.PRIVACY_POLICY,
                Column.User.DISCORD_ID};
        super.backup(columns);
    }

    public void addPlayer(UUID uuid, String name, String permGroup, String server) {
        super.addPlayer(uuid, name);
        super.setSynchronized(Set.of(new Entry<>(Status.User.ONLINE, Column.User.STATUS), new Entry<>(false
                , Column.User.AIR_MODE), new Entry<>(null, Column.User.TEAM), new Entry<>(permGroup,
                Column.User.PERM_GROUP), new Entry<>(server, Column.User.SERVER), new Entry<>(server,
                Column.User.SERVER_LAST), new Entry<>(server, Column.User.SERVER_LOBBY), new Entry<>(false,
                Column.User.SERVICE)), new Entry<>(uuid, Column.User.UUID));
    }

}
