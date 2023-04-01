/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column.Game;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.Type;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DbCachedTmpGameInfo extends DbCachedGameInfo implements
        de.timesnake.database.util.game.DbTmpGameInfo {

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

        ColumnMap map = this.getDatabase().getFirstWithKey(
                Set.of(Game.DISPLAY_NAME, Game.TEXT_COLOR, Game.HEAD_LINE, Game.ITEM, Game.SLOT,
                        Game.ENABLED, Game.MAX_PLAYERS, Game.MAPS, Game.KITS, Game.STATISTICS,
                        Game.TEXTURE_PACK_LINK, Game.PLAYER_TRACKING_RANGE, Game.MAX_HEALTH,
                        Game.VIEW_DISTANCE, Game.OLD_PVP, Game.AUTO_START_PLAYER_NUMBER,
                        Game.MIN_PLAYER_NUMBER, Game.SHOW_SELECTED_KITS, Game.TEAM_SIZES,
                        Game.TEAM_MERGE, Game.EQUAL_TEAM_SIZE_REQUIRED, Game.HIDE_TEAMS,
                        Game.DISCORD_TYPE, Game.DESCRIPTION));

        this.name = database.getName();
        this.displayName = map.get(Game.DISPLAY_NAME);
        this.textColor = map.get(Game.TEXT_COLOR);
        this.headLine = map.get(Game.HEAD_LINE);
        this.itemName = map.get(Game.ITEM);
        this.slot = map.get(Game.SLOT);
        this.enabled = map.get(Game.ENABLED);
        this.maxPlayers = map.get(Game.MAX_PLAYERS);
        this.mapAvailability = map.get(Game.MAPS);
        this.kitAvailability = map.get(Game.KITS);
        this.statistics = map.get(Game.STATISTICS);
        this.texturePackLink = map.get(Game.TEXTURE_PACK_LINK);
        this.playerTrackingRange = map.get(Game.PLAYER_TRACKING_RANGE);
        this.maxHealth = map.get(Game.MAX_HEALTH);
        this.viewDistance = map.get(Game.VIEW_DISTANCE);
        this.oldPvPAvailability = map.get(Game.OLD_PVP);
        this.autoStartPlayerNumber = map.get(Game.AUTO_START_PLAYER_NUMBER);
        this.minPlayerNumber = map.get(Game.MIN_PLAYER_NUMBER);
        this.teamSizes = map.get(Game.TEAM_SIZES);
        this.teamMerge = map.get(Game.TEAM_MERGE);
        this.equalTimeSizeRequired = map.get(Game.EQUAL_TEAM_SIZE_REQUIRED);
        this.showSelectedKits = map.get(Game.SHOW_SELECTED_KITS);
        this.hideTeams = map.get(Game.HIDE_TEAMS);
        this.discordType = map.get(Game.DISCORD_TYPE);
        this.description = map.get(Game.DESCRIPTION);
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
