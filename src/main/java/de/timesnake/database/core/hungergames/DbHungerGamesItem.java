/*
 * workspace.database-api.main
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

package de.timesnake.database.core.hungergames;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbHungerGamesItem extends TableQuery implements de.timesnake.database.util.hungergames.DbHungerGamesItem {

    public DbHungerGamesItem(DatabaseConnector databaseConnector, String nameTable, Integer itemId) {
        super(databaseConnector, nameTable, new Entry<>(itemId, Column.HungerGames.ITEM_ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_ID) != null;
    }

    @NotNull
    @Override
    public String getType() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_TYPE);
    }

    @NotNull
    @Override
    public Integer getAmount() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_AMOUNT);
    }

    @NotNull
    @Override
    public Float getChance() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_CHANCE);
    }

    @NotNull
    @Override
    public Integer getLevel() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_LEVEL);
    }

}
