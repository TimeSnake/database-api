package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;

import java.util.ArrayList;
import java.util.Collection;

public class DbMapInfo extends TableQuery {

    protected DbMapInfo(DatabaseConnector databaseConnector, String nameTable, String mapName) {
        super(databaseConnector, nameTable, new TableEntry<>(mapName, Column.Game.MAP_NAME));
    }

    public String getName() {
        return (String) super.primaryEntries.get(0).getValue();
    }

    public boolean exists() {
        return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
    }

    public String getDisplayName() {
        return super.getFirstWithKey(Column.Game.MAP_DISPLAY_NAME);
    }

    public Integer getMinPlayers() {
        return super.getFirstWithKey(Column.Game.MAP_MIN_PLAYERS);
    }

    public Integer getMaxPlayers() {
        return super.getFirstWithKey(Column.Game.MAP_MAX_PLAYERS);
    }

    public String getItemName() {
        return super.getFirstWithKey(Column.Game.MAP_ITEM);
    }

    public ArrayList<String> getDescription() {
        return super.getFirstWithKey(Column.Game.MAP_DESCRIPTION);
    }

    public ArrayList<String> getInfo() {
        return super.getFirstWithKey(Column.Game.MAP_INFO);
    }

    public boolean isEnabled() {
        return super.getFirstWithKey(Column.Game.MAP_ENABLE);
    }

    public void setDisplayName(String displayName) {
        super.setWithKey(displayName, Column.Game.MAP_DISPLAY_NAME);
    }

    public void setMinPlayers(Integer minPlayers) {
        super.setWithKey(minPlayers, Column.Game.MAP_MIN_PLAYERS);
    }

    public void setMaxPlayers(Integer maxPlayers) {
        super.setWithKey(maxPlayers, Column.Game.MAP_MAX_PLAYERS);
    }

    public void setItemName(String itemName) {
        this.setWithKey(itemName, Column.Game.MAP_ITEM);
    }

    public void setDescription(Collection<String> description) {
        this.setWithKey(new DbStringArrayList(description), Column.Game.MAP_DESCRIPTION);
    }

    public void setInfo(Collection<String> info) {
        this.setWithKey(new DbStringArrayList(info), Column.Game.MAP_INFO);
    }

    public void setEnabled(boolean enable) {
        super.setWithKey(enable, Column.Game.MAP_ENABLE);
    }

}
