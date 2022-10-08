/*
 * database-api.main
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

package de.timesnake.database.util.game;

import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface DbGame {

    boolean exists();

    @NotNull
    DbGameInfo getInfo();

    @NotCached
    Collection<Integer> getKitIds();

    @Nullable
    @NotCached
    DbKit getKit(int id);

    @Nullable
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
    @NotNull
    Collection<DbKit> getKits();

    @NotCached
    void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName,
                Collection<String> description, Collection<String> info, Collection<String> authors);

    @NotCached
    void removeMap(String name);

    @NotCached
    @NotNull
    DbMap getMap(String mapName);

    @NotCached
    @NotNull
    Collection<DbMap> getMaps();

    @NotCached
    @NotNull
    Collection<DbMap> getMaps(Integer players);

    @NotCached
    boolean containsMap(String mapName);

    @NotNull
    @NotCached
    Set<StatType<?>> getStats();

    @Nullable
    @NotCached
    StatType<?> getStat(String name);

    @NotCached
    void addStat(StatType<?> stat);

    @NotCached
    void removeStat(StatType<?> stat);

    @Nullable
    @NotCached
    GameUserStatistic getUserStatistic(UUID uuid);

    @NotNull
    @NotCached
    Collection<GameUserStatistic> getUserStatistics();

    @NotNull
    @NotCached
    <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type);

    @NotNull
    DbGame toDatabase();

    @NotNull
    DbGame toLocal();
}
