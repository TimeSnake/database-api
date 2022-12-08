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

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DbTmpGameInfo extends DbGameInfo implements de.timesnake.database.util.game.DbTmpGameInfo {

    public DbTmpGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, gameName);
    }

    @NotNull
    @Override
    public Integer getAutoStartPlayerNumber() {
        return super.getFirstWithKey(Column.Game.AUTO_START_PLAYER_NUMBER);
    }

    @Override
    public void setAutoStartPlayerNumber(Integer number) {
        super.setWithKey(number, Column.Game.AUTO_START_PLAYER_NUMBER);
    }

    @NotNull
    @Override
    public Integer getMinPlayerNumber() {
        return super.getFirstWithKey(Column.Game.MIN_PLAYER_NUMBER);
    }

    @Override
    public void setMinPlayerNumber(Integer number) {
        super.setWithKey(number, Column.Game.MIN_PLAYER_NUMBER);
    }

    @Override
    public boolean showSelectedKits() {
        return super.getFirstWithKey(Column.Game.SHOW_SELECTED_KITS);
    }

    @Override
    public void setShowSelectedKits(boolean showSelectedKits) {
        super.setWithKey(showSelectedKits, Column.Game.SHOW_SELECTED_KITS);
    }

    @Override
    public List<Integer> getTeamSizes() {
        return super.getFirstWithKey(Column.Game.TEAM_SIZES);
    }

    @Override
    public void setTeamSizes(List<Integer> sizes) {
        super.setWithKey(new DbIntegerArrayList(sizes), Column.Game.TEAM_SIZES);
    }

    @NotNull
    @Override
    public Type.Availability getTeamMergeAvailability() {
        Type.Availability availability = super.getFirstWithKey(Column.Game.TEAM_MERGE);
        return availability != null ? availability : Type.Availability.FORBIDDEN;
    }

    @Override
    public void setTeamMergeAvailability(Type.Availability availability) {
        super.setWithKey(availability, Column.Game.TEAM_MERGE);
    }

    @Override
    public boolean isEqualTeamSizeRequired() {
        return super.getFirstWithKey(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
    }

    @Override
    public void requireEqualTeamSize(boolean require) {
        super.setWithKey(require, Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
    }

    @Override
    public boolean hideTeams() {
        return super.getFirstWithKey(Column.Game.HIDE_TEAMS);
    }

    @Override
    public void setHideTeams(boolean hide) {
        super.setWithKey(hide, Column.Game.HIDE_TEAMS);
    }

    @NotNull
    @Override
    public Type.Discord getDiscordType() {
        Type.Discord discord = super.getFirstWithKey(Column.Game.DISCORD_TYPE);
        return discord != null ? discord : Type.Discord.FORBIDDEN;
    }

    @Override
    public void setDiscordType(Type.Discord type) {
        super.setWithKey(type, Column.Game.DISCORD_TYPE);
    }

    @Override
    public List<String> getDescription() {
        return super.getFirstWithKey(Column.Game.DESCRIPTION);
    }

    @Override
    public void setDescription(List<String> description) {
        super.setWithKey(new DbStringArrayList(description), Column.Game.DESCRIPTION);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toDatabase() {
        return this;
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toLocal() {
        return new DbCachedTmpGameInfo(this);
    }
}
