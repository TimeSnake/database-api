/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class NonTmpGamesInfoTable extends GamesInfoTable {

    public NonTmpGamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Game.CREATION_REQUESTABLE);
        super.addColumn(Column.Game.OWNABLE);
        super.addColumn(Column.Game.ALLOW_NETHER_END);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @NotNull
    @Override
    public DbNonTmpGameInfo getGame(String name) {
        return new DbNonTmpGameInfo(this.databaseConnector, this.tableName, name);
    }
}
