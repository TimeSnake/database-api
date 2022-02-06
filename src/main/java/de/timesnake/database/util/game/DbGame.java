package de.timesnake.database.util.game;

import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.object.NotLocal;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface DbGame extends DbGameInfo {

    @NotLocal
    void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName);

    @NotLocal
    void removeTeam(String name);

    @NotLocal
    void removeTeam(int rank);

    @NotLocal
    Integer getHighestRank();

    @NotLocal
    boolean containsTeam(int rank);

    @NotLocal
    boolean containsTeam(String name);

    @NotLocal
    DbTeam getTeam(String name);

    @NotLocal
    DbTeam getTeam(int rank);

    @NotLocal
    Collection<String> getTeamNames();

    @NotLocal
    Collection<Integer> getTeamRanks();

    @NotLocal
    Collection<DbTeam> getTeams();

    @NotLocal
    Collection<Integer> getKitIds();

    @NotLocal
    DbKit getKit(int id);

    @NotLocal
    DbKit getKit(String name);

    @NotLocal
    void removeKit(Integer id);

    @NotLocal
    void removeKitSynchronized(Integer id);

    @NotLocal
    void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException;

    @NotLocal
    void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException;

    @NotLocal
    Collection<DbKit> getKits();

    @NotLocal
    void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName, Collection<String> description, Collection<String> info, Collection<String> authors);

    @NotLocal
    void removeMap(String name);

    @NotLocal
    DbMap getMap(String mapName);

    @NotLocal
    Collection<DbMap> getMaps();

    @NotLocal
    Collection<DbMap> getMaps(Integer players);

    @NotLocal
    boolean containsMap(String mapName);

    @NotLocal
    Set<Stat<?>> getStats();

    @NotLocal
    <ValueType> Stat<ValueType> getStat(String name, Stat.Type<ValueType> type);

    @NotLocal
    void addStat(Stat<?> stat);

    @NotLocal
    void removeStat(Stat<?> stat);

    @NotLocal
    GameUserStatistic getUserStatistic(UUID uuid);

    @NotLocal
    Collection<GameUserStatistic> getUserStatistics();

    /**
     * {@inheritDoc}
     */
    @Override
    DbGame toLocal();

    /**
     * {@inheritDoc}
     */
    @Override
    DbGame toDatabase();
}
