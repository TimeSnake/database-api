package de.timesnake.database.core.game;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.game.DbGameInfo;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class DbLocalGameInfo implements DbGameInfo {

    private final de.timesnake.database.core.game.DbGameInfo gameInfo;

    private final String name;
    private String displayName;
    private String chatColor;
    private Integer autoStart;
    private Integer minPlayers;
    private Integer maxPlayers;
    private String headLine;
    private String item;
    private Integer slot;
    private Boolean temporary;
    private Type.Availability kits;
    private Type.Availability maps;
    private DbIntegerArrayList teamAmounts;
    private Type.Availability teamMerge;
    private List<String> description;

    public DbLocalGameInfo(de.timesnake.database.core.game.DbGameInfo gameInfo) {
        this.gameInfo = gameInfo;
        this.name = gameInfo.getName();

        ColumnMap columnMap = this.gameInfo.getFirstWithKey(Set.of(Column.Game.DISPLAY_NAME, Column.Game.CHAT_COLOR, Column.Game.AUTO_START, Column.Game.MIN_PLAYERS, Column.Game.MAX_PLAYERS, Column.Game.HEAD_LINE, Column.Game.ITEM, Column.Game.SLOT, Column.Game.TEMPORARY, Column.Game.KITS, Column.Game.MAPS, Column.Game.TEAM_AMOUNTS, Column.Game.TEAM_MERGE, Column.Game.DESCRIPTION));

        this.displayName = columnMap.get(Column.Game.DISPLAY_NAME);
        this.chatColor = columnMap.get(Column.Game.CHAT_COLOR);
        this.autoStart = columnMap.get(Column.Game.AUTO_START);
        this.minPlayers = columnMap.get(Column.Game.MIN_PLAYERS);
        this.maxPlayers = columnMap.get(Column.Game.MAX_PLAYERS);
        this.headLine = columnMap.get(Column.Game.HEAD_LINE);
        this.item = columnMap.get(Column.Game.ITEM);
        this.slot = columnMap.get(Column.Game.SLOT);
        this.temporary = columnMap.get(Column.Game.TEMPORARY);
        this.kits = columnMap.get(Column.Game.KITS);
        this.maps = columnMap.get(Column.Game.MAPS);
        this.teamAmounts = columnMap.get(Column.Game.TEAM_AMOUNTS);
        this.teamMerge = columnMap.get(Column.Game.TEAM_MERGE);
        this.description = columnMap.get(Column.Game.DESCRIPTION);
    }

    @Override
    public boolean exists() {
        return this.gameInfo.exists();
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.gameInfo.setDisplayName(displayName);
    }

    @Override
    public void setChatColorName(String chatColorName) {
        this.chatColor = chatColorName;
        this.gameInfo.setChatColorName(chatColorName);
    }

    @Override
    public void setAutoStart(int autoStart) {
        this.autoStart = autoStart;
        this.gameInfo.setAutoStart(autoStart);
    }

    @Override
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
        this.gameInfo.setMinPlayers(minPlayers);
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.gameInfo.setMaxPlayers(maxPlayers);
    }

    @Override
    public void setHeadLine(String description) {
        this.headLine = description;
        this.gameInfo.setHeadLine(description);
    }

    @Override
    public void setItem(String itemName) {
        this.item = itemName;
        this.gameInfo.setItem(itemName);
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
        this.gameInfo.setSlot(slot);
    }

    @Override
    public void setKitsAvailability(Type.Availability kits) {
        this.kits = kits;
        this.gameInfo.setKitsAvailability(kits);
    }

    @Override
    public void setMapsAvailability(Type.Availability maps) {
        this.maps = maps;
        this.gameInfo.setMapsAvailability(maps);
    }

    @Override
    public void setTemporary(boolean isTemporary) {
        this.temporary = isTemporary;
        this.gameInfo.setTemporary(isTemporary);
    }

    @Override
    public void setTeamAmounts(Collection<Integer> amounts) {
        this.teamAmounts = new DbIntegerArrayList(amounts);
        this.gameInfo.setTeamAmounts(amounts);
    }

    @Override
    public void setTeamMergeAvailability(Type.Availability teamMerging) {
        this.teamMerge = teamMerging;
        this.gameInfo.setTeamMergeAvailability(teamMerging);
    }

    @Override
    public void setDescription(Collection<String> description) {
        this.description = new DbStringArrayList(description);
        this.gameInfo.setDescription(description);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getChatColorName() {
        return this.chatColor;
    }

    @Override
    public Integer getAutoStart() {
        return this.autoStart;
    }

    @Override
    public Integer getMinPlayers() {
        return this.minPlayers;
    }

    @Override
    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public String getHeadLine() {
        return this.headLine;
    }

    @Override
    public String getItemName() {
        return this.item;
    }

    @Override
    public Integer getSlot() {
        return this.slot;
    }

    @Override
    public boolean isTemporary() {
        return this.temporary;
    }

    @Override
    public Type.Availability getKitAvailability() {
        return this.kits;
    }

    @Override
    public Type.Availability getMapAvailability() {
        return this.maps;
    }

    @Override
    public Collection<Integer> getTeamAmounts() {
        return teamAmounts;
    }

    @Override
    public Type.Availability getTeamMergeAvailability() {
        return this.teamMerge;
    }

    @Override
    public List<String> getDescription() {
        return this.description;
    }

    @Override
    public DbGameInfo toLocal() {
        return new DbLocalGameInfo(this.gameInfo);
    }

    @Override
    public DbGameInfo toDatabase() {
        return this.gameInfo;
    }
}
