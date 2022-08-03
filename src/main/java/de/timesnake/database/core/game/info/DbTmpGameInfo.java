package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.Type;

import java.util.List;

public class DbTmpGameInfo extends DbGameInfo implements de.timesnake.database.util.game.DbTmpGameInfo {

    public DbTmpGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, gameName);
    }

    @Override
    public Integer getAutoStartPlayerNumber() {
        return super.getFirstWithKey(Column.Game.AUTO_START_PLAYER_NUMBER);
    }

    @Override
    public void setAutoStartPlayerNumber(Integer number) {
        super.setWithKey(number, Column.Game.AUTO_START_PLAYER_NUMBER);
    }

    @Override
    public Integer getMinPlayerNumber() {
        return super.getFirstWithKey(Column.Game.MIN_PLAYER_NUMBER);
    }

    @Override
    public void setMinPlayerNumber(Integer number) {
        super.setWithKey(number, Column.Game.MIN_PLAYER_NUMBER);
    }

    @Override
    public List<Integer> getTeamSizes() {
        return super.getFirstWithKey(Column.Game.TEAM_SIZES);
    }

    @Override
    public void setTeamSizes(List<Integer> sizes) {
        super.setWithKey(new DbIntegerArrayList(sizes), Column.Game.TEAM_SIZES);
    }

    @Override
    public Type.Availability getTeamMergeAvailability() {
        return super.getFirstWithKey(Column.Game.TEAM_MERGE);
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

    @Override
    public Type.Discord getDiscordType() {
        return super.getFirstWithKey(Column.Game.DISCORD_TYPE);
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

    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toDatabase() {
        return this;
    }

    @Override
    public de.timesnake.database.util.game.DbTmpGameInfo toLocal() {
        return new DbCachedTmpGameInfo(this);
    }
}
