package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.Type;

import java.util.List;
import java.util.Set;

public class DbCachedTmpGameInfo extends DbCachedGameInfo implements de.timesnake.database.util.game.DbTmpGameInfo {

    protected Integer autoStartPlayerNumber;
    protected Integer minPlayerNumber;
    protected List<Integer> teamSizes;
    protected Type.Availability teamMerge;
    protected Boolean equalTimeSizeRequired;
    protected List<String> description;

    public DbCachedTmpGameInfo(DbTmpGameInfo database) {
        super(database);

        ColumnMap map = this.getDatabase().getFirstWithKey(Set.of(Column.Game.DISPLAY_NAME, Column.Game.CHAT_COLOR,
                Column.Game.HEAD_LINE, Column.Game.ITEM, Column.Game.SLOT, Column.Game.MAX_PLAYERS, Column.Game.MAPS,
                Column.Game.KITS, Column.Game.STATISTICS, Column.Game.TEXTURE_PACK_LINK, Column.Game.PLAYER_TRACKING_RANGE,
                Column.Game.AUTO_START_PLAYER_NUMBER, Column.Game.MIN_PLAYER_NUMBER, Column.Game.TEAM_SIZES,
                Column.Game.TEAM_MERGE, Column.Game.EQUAL_TEAM_SIZE_REQUIRED, Column.Game.DESCRIPTION));

        this.name = database.getName();
        this.displayName = map.get(Column.Game.DISPLAY_NAME);
        this.chatColorName = map.get(Column.Game.CHAT_COLOR);
        this.headLine = map.get(Column.Game.HEAD_LINE);
        this.itemName = map.get(Column.Game.ITEM);
        this.slot = map.get(Column.Game.SLOT);
        this.maxPlayers = map.get(Column.Game.MAX_PLAYERS);
        this.mapAvailability = map.get(Column.Game.MAPS);
        this.kitAvailability = map.get(Column.Game.KITS);
        this.statistics = map.get(Column.Game.STATISTICS);
        this.texturePackLink = map.get(Column.Game.TEXTURE_PACK_LINK);
        this.playerTrackingRange = map.get(Column.Game.PLAYER_TRACKING_RANGE);
        this.autoStartPlayerNumber = map.get(Column.Game.AUTO_START_PLAYER_NUMBER);
        this.minPlayerNumber = map.get(Column.Game.MIN_PLAYER_NUMBER);
        this.teamSizes = map.get(Column.Game.TEAM_SIZES);
        this.teamMerge = map.get(Column.Game.TEAM_MERGE);
        this.equalTimeSizeRequired = map.get(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
        this.description = map.get(Column.Game.DESCRIPTION);
    }

    @Override
    protected DbTmpGameInfo getDatabase() {
        return (DbTmpGameInfo) super.getDatabase();
    }

    @Override
    public Integer getAutoStartPlayerNumber() {
        return this.autoStartPlayerNumber;
    }

    @Override
    public void setAutoStartPlayerNumber(Integer number) {
        this.autoStartPlayerNumber = number;
        this.getDatabase().setAutoStartPlayerNumber(number);
    }

    @Override
    public Integer getMinPlayerNumber() {
        return this.minPlayerNumber;
    }

    @Override
    public void setMinPlayerNumber(Integer number) {
        this.minPlayerNumber = number;
        this.getDatabase().setMinPlayerNumber(number);
    }

    @Override
    public List<Integer> getTeamSizes() {
        return this.teamSizes;
    }

    @Override
    public void setTeamSizes(List<Integer> sizes) {
        this.teamSizes = sizes;
        this.getDatabase().setTeamSizes(sizes);
    }

    @Override
    public Type.Availability getTeamMergeAvailability() {
        return this.teamMerge;
    }

    @Override
    public void setTeamMergeAvailability(Type.Availability availability) {
        this.teamMerge = availability;
        this.getDatabase().setTeamMergeAvailability(availability);
    }

    @Override
    public boolean isEqualTeamSizeRequired() {
        return this.equalTimeSizeRequired;
    }

    @Override
    public void requireEqualTeamSize(boolean require) {
        this.equalTimeSizeRequired = require;
        this.getDatabase().requireEqualTeamSize(require);
    }

    @Override
    public List<String> getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(List<String> description) {
        this.description = description;
        this.getDatabase().setDescription(description);
    }

    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toDatabase() {
        return this.getDatabase();
    }

    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toLocal() {
        return this.getDatabase().toLocal();
    }
}
