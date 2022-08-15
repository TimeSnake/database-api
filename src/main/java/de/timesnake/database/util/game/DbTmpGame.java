package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;

import java.util.Collection;

public interface DbTmpGame extends DbGame, DbTmpGameInfo, DbCached<DbTmpGame> {

    @Override
    DbTmpGameInfo getInfo();

    @NotCached
    void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio, String colorName);

    @NotCached
    void removeTeam(String name);

    @NotCached
    Integer getHighestRank();

    @NotCached
    boolean containsTeam(String name);

    @NotCached
    de.timesnake.database.core.game.team.DbTeam getTeam(String name);

    @NotCached
    Collection<String> getTeamNames();

    @NotCached
    Collection<Integer> getTeamRanks();

    @NotCached
    Collection<de.timesnake.database.util.game.DbTeam> getTeams();

}
