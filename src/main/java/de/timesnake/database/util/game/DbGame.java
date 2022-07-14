package de.timesnake.database.util.game;

import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface DbGame {

    boolean exists();

    DbGameInfo getInfo();

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
    Set<StatType<?>> getStats();

    @NotCached
    StatType<?> getStat(String name);

    @NotCached
    void addStat(StatType<?> stat);

    @NotCached
    void removeStat(StatType<?> stat);

    @NotCached
    GameUserStatistic getUserStatistic(UUID uuid);

    @NotCached
    Collection<GameUserStatistic> getUserStatistics();

    @NotCached
    <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type);

    DbGame toDatabase();

    DbGame toLocal();
}
