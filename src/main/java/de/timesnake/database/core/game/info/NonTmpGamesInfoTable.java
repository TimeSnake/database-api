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
