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

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class DbMapInfo extends TableQuery {

    protected DbMapInfo(DatabaseConnector databaseConnector, String nameTable, String mapName) {
        super(databaseConnector, nameTable, new TableEntry<>(mapName, Column.Game.MAP_NAME));
    }

    @NotNull
    public String getName() {
        return (String) super.primaryEntries.get(0).getValue();
    }

    public boolean exists() {
        return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
    }

    @NotNull
    public String getDisplayName() {
        return super.getFirstWithKey(Column.Game.MAP_DISPLAY_NAME);
    }

    public void setDisplayName(String displayName) {
        super.setWithKey(displayName, Column.Game.MAP_DISPLAY_NAME);
    }

    @Nullable
    public Integer getMinPlayers() {
        return super.getFirstWithKey(Column.Game.MAP_MIN_PLAYERS);
    }

    public void setMinPlayers(Integer minPlayers) {
        super.setWithKey(minPlayers, Column.Game.MAP_MIN_PLAYERS);
    }

    @Nullable
    public Integer getMaxPlayers() {
        return super.getFirstWithKey(Column.Game.MAP_MAX_PLAYERS);
    }

    public void setMaxPlayers(Integer maxPlayers) {
        super.setWithKey(maxPlayers, Column.Game.MAP_MAX_PLAYERS);
    }

    @Nullable
    public String getItemName() {
        return super.getFirstWithKey(Column.Game.MAP_ITEM);
    }

    public void setItemName(String itemName) {
        this.setWithKey(itemName, Column.Game.MAP_ITEM);
    }

    public ArrayList<String> getDescription() {
        return super.getFirstWithKey(Column.Game.MAP_DESCRIPTION);
    }

    public void setDescription(Collection<String> description) {
        this.setWithKey(new DbStringArrayList(description), Column.Game.MAP_DESCRIPTION);
    }

    public ArrayList<String> getInfo() {
        return super.getFirstWithKey(Column.Game.MAP_INFO);
    }

    public void setInfo(Collection<String> info) {
        this.setWithKey(new DbStringArrayList(info), Column.Game.MAP_INFO);
    }

    public boolean isEnabled() {
        return super.getFirstWithKey(Column.Game.MAP_ENABLE);
    }

    public void setEnabled(boolean enable) {
        super.setWithKey(enable, Column.Game.MAP_ENABLE);
    }

}
