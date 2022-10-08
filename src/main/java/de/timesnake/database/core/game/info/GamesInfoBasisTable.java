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
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class GamesInfoBasisTable extends TableDDL {

    protected GamesInfoBasisTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.NAME);
        super.addColumn(Column.Game.DISPLAY_NAME);
        super.addColumn(Column.Game.TEXT_COLOR);
        super.addColumn(Column.Game.HEAD_LINE);
        super.addColumn(Column.Game.ITEM);
        super.addColumn(Column.Game.SLOT);
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
    public DbGameInfoBasis getGame(String name) {
        return new DbGameInfoBasis(this.databaseConnector, this.tableName, name);
    }

    public boolean containsGame(String name) {
        return super.getFirst(Column.Game.NAME, new TableEntry<>(name, Column.Game.NAME)) != null;
    }

    protected void removeGame(String name) {
        super.deleteEntry(new TableEntry<>(name, Column.Game.NAME));
    }

    public Collection<String> getGamesName() {
        return super.get(Column.Game.NAME);
    }
}
