package de.timesnake.database.util.game;

import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.Tuple;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface DbGame extends DbGameInfo {

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
    DbTeam getTeam(String name);

    @NotCached
    DbTeam getTeam(int rank);

    @NotCached
    Collection<String> getTeamNames();

    @NotCached
    Collection<Integer> getTeamRanks();

    @NotCached
    Collection<DbTeam> getTeams();

    @NotCached
    Collection<Integer> getKitIds();

    @NotCached
    DbKit getKit(int id);

    @NotCached
    DbKit getKit(String name);

    @NotCached
    void removeKit(Integer id);

    @NotCached
    void removeKitSynchronized(Integer id);

    @NotCached
    void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException;

    @NotCached
    void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException;

    @NotCached
    Collection<DbKit> getKits();

    @NotCached
    void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName,
                Collection<String> description, Collection<String> info, Collection<String> authors);

    @NotCached
    void removeMap(String name);

    @NotCached
    DbMap getMap(String mapName);

    @NotCached
    Collection<DbMap> getMaps();

    @NotCached
    Collection<DbMap> getMaps(Integer players);

    @NotCached
    boolean containsMap(String mapName);

    @NotCached
    Set<Stat<?>> getStats();

    @NotCached
    <ValueType> Stat<ValueType> getStat(String name, Stat.Type<ValueType> type);

    @NotCached
    void addStat(Stat<?> stat);

    @NotCached
    void removeStat(Stat<?> stat);

    @NotCached
    GameUserStatistic getUserStatistic(UUID uuid);

    @NotCached
    Collection<GameUserStatistic> getUserStatistics();

    @NotCached
    <Value> Collection<Tuple<UUID, Value>> getStatOfUsers(Stat<Value> type);

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
