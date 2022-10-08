/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class TmpGamesInfoTable extends GamesInfoTable {

    public TmpGamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Game.AUTO_START_PLAYER_NUMBER);
        super.addColumn(Column.Game.MIN_PLAYER_NUMBER);
        super.addColumn(Column.Game.TEAM_SIZES);
        super.addColumn(Column.Game.TEAM_MERGE);
        super.addColumn(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
        super.addColumn(Column.Game.HIDE_TEAMS);
        super.addColumn(Column.Game.DISCORD_TYPE);
        super.addColumn(Column.Game.DESCRIPTION);
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
    public DbTmpGameInfo getGame(String name) {
        return new DbTmpGameInfo(this.databaseConnector, this.tableName, name);
    }
}
