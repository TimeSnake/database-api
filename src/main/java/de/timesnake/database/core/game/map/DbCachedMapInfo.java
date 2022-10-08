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
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DbStringArrayList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class DbCachedMapInfo {

    protected final DbMapInfo mapInfo;

    private final String name;
    private String displayName;
    private Integer minPlayers;
    private Integer maxPlayers;
    private String itemName;
    private ArrayList<String> description;
    private ArrayList<String> info;
    private boolean enabled;

    public DbCachedMapInfo(DbMapInfo mapInfo) {
        this.mapInfo = mapInfo;

        this.name = mapInfo.getName();

        ColumnMap columnMap = mapInfo.getFirstWithKey(Set.of(Column.Game.MAP_DISPLAY_NAME,
                Column.Game.MAP_MIN_PLAYERS, Column.Game.MAP_MAX_PLAYERS, Column.Game.MAP_ITEM,
                Column.Game.MAP_DESCRIPTION, Column.Game.MAP_INFO, Column.Game.MAP_ENABLE));

        this.displayName = columnMap.get(Column.Game.MAP_DISPLAY_NAME);
        this.minPlayers = columnMap.get(Column.Game.MAP_MIN_PLAYERS);
        this.maxPlayers = columnMap.get(Column.Game.MAP_MAX_PLAYERS);
        this.itemName = columnMap.get(Column.Game.MAP_ITEM);
        this.description = columnMap.get(Column.Game.MAP_DESCRIPTION);
        this.info = columnMap.get(Column.Game.MAP_INFO);
        this.enabled = columnMap.get(Column.Game.MAP_ENABLE);

    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public boolean exists() {
        return this.mapInfo.exists();
    }

    @NotNull
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.mapInfo.setDisplayName(displayName);
    }

    @NotNull
    public Integer getMinPlayers() {
        return this.minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
        this.mapInfo.setMinPlayers(minPlayers);
    }

    @NotNull
    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.mapInfo.setMaxPlayers(maxPlayers);
    }

    @NotNull
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        this.mapInfo.setItemName(itemName);
    }

    public ArrayList<String> getDescription() {
        return this.description;
    }

    public void setDescription(Collection<String> description) {
        this.description = new DbStringArrayList(description);
        this.mapInfo.setDescription(description);
    }

    public ArrayList<String> getInfo() {
        return this.info;
    }

    public void setInfo(Collection<String> info) {
        this.info = new DbStringArrayList(info);
        this.mapInfo.setInfo(info);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
        this.mapInfo.setEnabled(enabled);
    }

}