package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DbStringArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class DbLocalMapInfo {

    protected final DbMapInfo mapInfo;

    private final String name;
    private String displayName;
    private Integer minPlayers;
    private Integer maxPlayers;
    private String itemName;
    private ArrayList<String> description;
    private ArrayList<String> info;
    private boolean enabled;

    public DbLocalMapInfo(DbMapInfo mapInfo) {
        this.mapInfo = mapInfo;

        this.name = mapInfo.getName();

        ColumnMap columnMap = mapInfo.getFirstWithKey(Set.of(Column.Game.MAP_DISPLAY_NAME, Column.Game.MAP_MIN_PLAYERS, Column.Game.MAP_MAX_PLAYERS, Column.Game.MAP_ITEM, Column.Game.MAP_DESCRIPTION, Column.Game.MAP_INFO, Column.Game.MAP_ENABLE));

        this.displayName = columnMap.get(Column.Game.MAP_DISPLAY_NAME);
        this.minPlayers = columnMap.get(Column.Game.MAP_MIN_PLAYERS);
        this.maxPlayers = columnMap.get(Column.Game.MAP_MAX_PLAYERS);
        this.itemName = columnMap.get(Column.Game.MAP_ITEM);
        this.description = columnMap.get(Column.Game.MAP_DESCRIPTION);
        this.info = columnMap.get(Column.Game.MAP_INFO);
        this.enabled = columnMap.get(Column.Game.MAP_ENABLE);

    }

    public String getName() {
        return this.name;
    }

    public boolean exists() {
        return this.mapInfo.exists();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Integer getMinPlayers() {
        return this.minPlayers;
    }

    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ArrayList<String> getDescription() {
        return this.description;
    }

    public ArrayList<String> getInfo() {
        return this.info;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.mapInfo.setDisplayName(displayName);
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
        this.mapInfo.setMinPlayers(minPlayers);
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.mapInfo.setMaxPlayers(maxPlayers);
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        this.mapInfo.setItemName(itemName);
    }

    public void setDescription(Collection<String> description) {
        this.description = new DbStringArrayList(description);
        this.mapInfo.setDescription(description);
    }

    public void setInfo(Collection<String> info) {
        this.info = new DbStringArrayList(info);
        this.mapInfo.setInfo(info);
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
        this.mapInfo.setEnabled(enabled);
    }

}