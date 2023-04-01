/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column.Game;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class GamesInfoTable extends GamesInfoBasisTable {

    protected GamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Game.MAX_PLAYERS);
        super.addColumn(Game.KITS);
        super.addColumn(Game.MAPS);
        super.addColumn(Game.STATISTICS);
        super.addColumn(Game.TEXTURE_PACK_LINK);
        super.addColumn(Game.PLAYER_TRACKING_RANGE);
        super.addColumn(Game.MAX_HEALTH);
        super.addColumn(Game.VIEW_DISTANCE);
        super.addColumn(Game.OLD_PVP);
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
    public DbGameInfo getGame(String name) {
        return new DbGameInfo(this.databaseConnector, this.tableName, name);
    }
}
