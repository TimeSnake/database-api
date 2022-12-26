/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class DbCachedTmpGameInfo extends DbCachedGameInfo implements de.timesnake.database.util.game.DbTmpGameInfo {

    protected Integer autoStartPlayerNumber;
    protected Integer minPlayerNumber;
    protected List<Integer> teamSizes;
    protected Type.Availability teamMerge;
    protected boolean equalTimeSizeRequired;
    protected boolean showSelectedKits;
    protected boolean hideTeams;
    protected Type.Discord discordType;
    protected List<String> description;

    public DbCachedTmpGameInfo(DbTmpGameInfo database) {
        super(database);

        ColumnMap map = this.getDatabase().getFirstWithKey(Set.of(Column.Game.DISPLAY_NAME, Column.Game.TEXT_COLOR,
                Column.Game.HEAD_LINE, Column.Game.ITEM, Column.Game.SLOT, Column.Game.MAX_PLAYERS, Column.Game.MAPS,
                Column.Game.KITS, Column.Game.STATISTICS, Column.Game.TEXTURE_PACK_LINK, Column.Game.PLAYER_TRACKING_RANGE,
                Column.Game.MAX_HEALTH, Column.Game.VIEW_DISTANCE,
                Column.Game.AUTO_START_PLAYER_NUMBER, Column.Game.MIN_PLAYER_NUMBER, Column.Game.SHOW_SELECTED_KITS,
                Column.Game.TEAM_SIZES, Column.Game.TEAM_MERGE, Column.Game.EQUAL_TEAM_SIZE_REQUIRED, Column.Game.HIDE_TEAMS,
                Column.Game.DISCORD_TYPE, Column.Game.DESCRIPTION));

        this.name = database.getName();
        this.displayName = map.get(Column.Game.DISPLAY_NAME);
        this.textColor = map.get(Column.Game.TEXT_COLOR);
        this.headLine = map.get(Column.Game.HEAD_LINE);
        this.itemName = map.get(Column.Game.ITEM);
        this.slot = map.get(Column.Game.SLOT);
        this.maxPlayers = map.get(Column.Game.MAX_PLAYERS);
        this.mapAvailability = map.get(Column.Game.MAPS);
        this.kitAvailability = map.get(Column.Game.KITS);
        this.statistics = map.get(Column.Game.STATISTICS);
        this.texturePackLink = map.get(Column.Game.TEXTURE_PACK_LINK);
        this.playerTrackingRange = map.get(Column.Game.PLAYER_TRACKING_RANGE);
        this.maxHealth = map.get(Column.Game.MAX_HEALTH);
        this.viewDistance = map.get(Column.Game.VIEW_DISTANCE);
        this.autoStartPlayerNumber = map.get(Column.Game.AUTO_START_PLAYER_NUMBER);
        this.minPlayerNumber = map.get(Column.Game.MIN_PLAYER_NUMBER);
        this.teamSizes = map.get(Column.Game.TEAM_SIZES);
        this.teamMerge = map.get(Column.Game.TEAM_MERGE);
        this.equalTimeSizeRequired = map.get(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
        this.showSelectedKits = map.get(Column.Game.SHOW_SELECTED_KITS);
        this.hideTeams = map.get(Column.Game.HIDE_TEAMS);
        this.discordType = map.get(Column.Game.DISCORD_TYPE);
        this.description = map.get(Column.Game.DESCRIPTION);
    }

    @Override
    protected DbTmpGameInfo getDatabase() {
        return (DbTmpGameInfo) super.getDatabase();
    }

    @NotNull
    @Override
    public Integer getAutoStartPlayerNumber() {
        return this.autoStartPlayerNumber;
    }

    @Override
    public void setAutoStartPlayerNumber(Integer number) {
        this.autoStartPlayerNumber = number;
        this.getDatabase().setAutoStartPlayerNumber(number);
    }

    @NotNull
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
    public boolean showSelectedKits() {
        return this.showSelectedKits;
    }

    @Override
    public void setShowSelectedKits(boolean showSelectedKits) {
        this.showSelectedKits = showSelectedKits;
        this.getDatabase().setShowSelectedKits(showSelectedKits);
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

    @NotNull
    @Override
    public Type.Availability getTeamMergeAvailability() {
        return this.teamMerge != null ? this.teamMerge : Type.Availability.FORBIDDEN;
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
    public boolean hideTeams() {
        return this.hideTeams;
    }

    @Override
    public void setHideTeams(boolean hide) {
        this.hideTeams = hide;
        this.getDatabase().setHideTeams(hideTeams);
    }

    @NotNull
    @Override
    public Type.Discord getDiscordType() {
        return this.discordType != null ? this.discordType : Type.Discord.FORBIDDEN;
    }

    @Override
    public void setDiscordType(Type.Discord type) {
        this.discordType = type;
        this.getDatabase().setDiscordType(type);
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

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toDatabase() {
        return this.getDatabase();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toLocal() {
        return this.getDatabase().toLocal();
    }
}
