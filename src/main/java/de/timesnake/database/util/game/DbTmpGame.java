package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;

import java.util.Collection;

public interface DbTmpGame extends DbGame, DbTmpGameInfo, DbCached<DbTmpGame> {

    @Override
    DbTmpGameInfo getInfo();

    @NotCached
    void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName);

    @NotCached
    void removeTeam(String name);

    @NotCached
    void removeTeam(int rank);

    @NotCached
    Integer getHighestRank();

    @NotCached
    boolean containsTeam(int rank);

    @NotCached
    boolean containsTeam(String name);

    @NotCached
    de.timesnake.database.core.game.team.DbTeam getTeam(String name);

    @NotCached
    de.timesnake.database.core.game.team.DbTeam getTeam(int rank);

    @NotCached
    Collection<String> getTeamNames();

    @NotCached
    Collection<Integer> getTeamRanks();

    @NotCached
    Collection<de.timesnake.database.util.game.DbTeam> getTeams();

}
