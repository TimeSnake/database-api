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

public class GamesInfoTable extends GamesInfoBasisTable {

    protected GamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Game.MAX_PLAYERS);
        super.addColumn(Column.Game.KITS);
        super.addColumn(Column.Game.MAPS);
        super.addColumn(Column.Game.STATISTICS);
        super.addColumn(Column.Game.TEXTURE_PACK_LINK);
        super.addColumn(Column.Game.PLAYER_TRACKING_RANGE);
        super.addColumn(Column.Game.MAX_HEALTH);
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
