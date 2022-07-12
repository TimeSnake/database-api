package de.timesnake.database.core.game;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;

public class DbGameInfo extends TableQuery implements de.timesnake.database.util.game.DbGameInfo {

    protected DbGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, new TableEntry<>(gameName, Column.Game.NAME));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Game.NAME) != null;
    }

    @Override
    public void setItem(String itemName) {
        super.setWithKey(itemName, Column.Game.ITEM);
    }

    @Override
    public void setKitsAvailability(Type.Availability kits) {
        super.setWithKey(kits, Column.Game.KITS);
    }

    @Override
    public void setMapsAvailability(Type.Availability maps) {
        super.setWithKey(maps, Column.Game.MAPS);
    }

    @Override
    public void setEqualTeamSize(Boolean equalSize) {
        super.setWithKey(equalSize, Column.Game.EQUAL_TEAM_SIZE);
    }

    @Override
    public String getName() {
        return (String) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getDisplayName() {
        return super.getFirstWithKey(Column.Game.DISPLAY_NAME);
    }

    @Override
    public void setDisplayName(String displayName) {
        super.setWithKey(displayName, Column.Game.DISPLAY_NAME);
    }

    @Override
    public String getChatColorName() {
        return super.getFirstWithKey(Column.Game.CHAT_COLOR);
    }

    @Override
    public void setChatColorName(String chatColorName) {
        super.setWithKey(chatColorName, Column.Game.CHAT_COLOR);
    }

    @Override
    public Integer getAutoStart() {
        return super.getFirstWithKey(Column.Game.AUTO_START);
    }

    @Override
    public void setAutoStart(int autoStart) {
        super.setWithKey(autoStart, Column.Game.AUTO_START);
    }

    @Override
    public Integer getMaxPlayers() {
        return super.getFirstWithKey(Column.Game.MAX_PLAYERS);
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        super.setWithKey(maxPlayers, Column.Game.MAX_PLAYERS);
    }

    @Override
    public Integer getMinPlayers() {
        return super.getFirstWithKey(Column.Game.MIN_PLAYERS);
    }

    @Override
    public void setMinPlayers(int minPlayers) {
        super.setWithKey(minPlayers, Column.Game.MIN_PLAYERS);
    }

    @Override
    public String getHeadLine() {
        return super.getFirstWithKey(Column.Game.HEAD_LINE);
    }

    @Override
    public void setHeadLine(String headLine) {
        super.setWithKey(headLine, Column.Game.HEAD_LINE);
    }

    @Override
    public String getItemName() {
        return super.getFirstWithKey(Column.Game.ITEM);
    }

    @Override
    public Integer getSlot() {
        return super.getFirstWithKey(Column.Game.SLOT);
    }

    @Override
    public void setSlot(int slot) {
        super.setWithKey(slot, Column.Game.SLOT);
    }

    @Override
    public boolean isTemporary() {
        return super.getFirstWithKey(Column.Game.TEMPORARY);
    }

    @Override
    public void setTemporary(boolean isTemporary) {
        super.setWithKey(isTemporary, Column.Game.TEMPORARY);
    }

    @Override
    public Type.Availability getKitAvailability() {
        return super.getFirstWithKey(Column.Game.KITS);
    }

    @Override
    public Type.Availability getMapAvailability() {
        return super.getFirstWithKey(Column.Game.MAPS);
    }

    @Override
    public Collection<Integer> getTeamAmounts() {
        return super.getFirstWithKey(Column.Game.TEAM_AMOUNTS);
    }

    @Override
    public void setTeamAmounts(Collection<Integer> amounts) {
        super.setWithKey(new DbIntegerArrayList(amounts), Column.Game.TEAM_AMOUNTS);
    }

    @Override
    public Type.Availability getTeamMergeAvailability() {
        return super.getFirstWithKey(Column.Game.TEAM_MERGE);
    }

    @Override
    public void setTeamMergeAvailability(Type.Availability teamMerging) {
        super.setWithKey(teamMerging, Column.Game.TEAM_MERGE);
    }

    @Override
    public Boolean isEqualTeamSize() {
        return super.getFirstWithKey(Column.Game.EQUAL_TEAM_SIZE);
    }

    @Override
    public String getTexturePackLink() {
        return super.getFirstWithKey(Column.Game.TEXTURE_PACK_LINK);
    }

    @Override
    public void setTexturePackLink(String texturePack) {
        super.setWithKey(texturePack, Column.Game.TEXTURE_PACK_LINK);
    }

    @Override
    public Boolean hasTexturePack() {
        return super.getFirstWithKey(Column.Game.TEXTURE_PACK_LINK) != null;
    }

    @Override
    public Integer getPlayerTrackingRange() {
        return super.getFirstWithKey(Column.Game.PLAYER_TRACKING_RANGE);
    }

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {
        super.setWithKey(playerTrackingRange, Column.Game.PLAYER_TRACKING_RANGE);
    }

    @Override
    public List<String> getDescription() {
        return super.getFirstWithKey(Column.Game.DESCRIPTION);
    }

    @Override
    public void setDescription(Collection<String> description) {
        super.setWithKey(new DbStringArrayList(description), Column.Game.DESCRIPTION);
    }

    @Override
    public de.timesnake.database.util.game.DbGameInfo toLocal() {
        return new DbCachedGameInfo(this);
    }

    @Override
    public de.timesnake.database.util.game.DbGameInfo toDatabase() {
        return this;
    }

}
