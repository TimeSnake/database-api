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

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedGameInfo;
import de.timesnake.database.core.game.info.DbGameInfo;
import de.timesnake.database.core.game.kit.KitsTable;
import de.timesnake.database.core.game.map.MapsTable;
import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.core.game.statistic.StatisticsTable;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.game.DbKit;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DbGame implements de.timesnake.database.util.game.DbGame {

    protected final DatabaseConnector databaseConnector;
    protected final String gameName;
    protected final DbGameInfo info;
    protected Optional<KitsTable> kitsTable;
    protected Optional<MapsTable> mapsTable;
    protected Optional<StatisticsTable> statisticsTable;

    protected DbGame(DatabaseConnector databaseConnector, String gameName, DbGameInfo info) {
        this.databaseConnector = databaseConnector;
        this.gameName = gameName;
        this.info = info;
    }

    protected DbGame(DbGame game) {
        this.databaseConnector = game.databaseConnector;
        this.gameName = game.gameName;
        this.info = game.info;
        this.kitsTable = game.kitsTable;
        this.mapsTable = game.mapsTable;
        this.statisticsTable = game.statisticsTable;
    }

    @NotNull
    @Override
    public DbGameInfo getInfo() {
        return this.info;
    }

    private boolean loadStatisticsTable() {
        if (this.statisticsTable != null) {
            if (this.statisticsTable.isEmpty()) {
                return false;
            }

            if (this.statisticsTable.get() != null) {
                return true;
            }
        }

        if (this.info.hasStatistics()) {
            this.statisticsTable = Optional.of(DatabaseManager.getInstance().getGameStatistics().getGameUserStatistics(gameName));
            return true;
        } else {
            this.statisticsTable = Optional.empty();
            return false;
        }
    }

    private boolean loadKitsTable() {
        if (this.kitsTable != null) {
            if (this.kitsTable.isEmpty()) {
                return false;
            }

            if (this.kitsTable.get() != null) {
                return true;
            }
        }

        Type.Availability kitsAvailability = this.info.getKitAvailability();
        if (kitsAvailability.equals(Type.Availability.ALLOWED) || kitsAvailability.equals(Type.Availability.REQUIRED)) {
            this.kitsTable = Optional.of(DatabaseManager.getInstance().getGameKits().getGameKits(gameName));
            return true;
        } else {
            this.kitsTable = Optional.empty();
            return false;
        }
    }

    private boolean loadMapsTable() {
        if (this.mapsTable != null) {
            if (this.mapsTable.isEmpty()) {
                return false;
            }

            if (this.mapsTable.get() != null) {
                return true;
            }
        }

        Type.Availability mapsAvailability = this.info.getMapAvailability();
        if (mapsAvailability.equals(Type.Availability.ALLOWED) || mapsAvailability.equals(Type.Availability.REQUIRED)) {
            this.mapsTable = Optional.of(new de.timesnake.database.core.game.map.MapsTable(gameName));
            return true;
        } else {
            this.mapsTable = Optional.empty();
            return false;
        }
    }

    public void createTables() {
        if (this.loadStatisticsTable()) {
            this.statisticsTable.get().create();
        }

        if (this.loadKitsTable()) {
            this.kitsTable.get().create();
        }

        if (this.loadMapsTable()) {
            this.mapsTable.get().create();
        }
    }

    public void backup() {
        if (this.loadStatisticsTable()) {
            this.statisticsTable.get().backup();
        }

        if (this.loadKitsTable()) {
            this.kitsTable.get().backup();
        }

        if (this.loadMapsTable()) {
            this.mapsTable.get().backup();
        }
    }

    public void delete() {
        if (this.loadStatisticsTable()) {
            this.statisticsTable.get().delete();
        }

        if (this.loadKitsTable()) {
            this.kitsTable.get().delete();
        }

        if (this.loadMapsTable()) {
            this.mapsTable.get().delete();
        }
    }

    @Override
    public boolean exists() {
        return this.getInfo().exists();
    }

    @NotNull
    @Override
    public Collection<Integer> getKitIds() {
        if (this.loadKitsTable()) {
            return this.kitsTable.get().getKitsId();
        }
        return new ArrayList<>();
    }

    @Nullable
    @Override
    public DbKit getKit(int id) {
        if (this.loadKitsTable()) {
            return this.kitsTable.get().getKit(id);
        }
        return null;
    }

    @Nullable
    @Override
    public DbKit getKit(String name) {
        if (this.loadKitsTable()) {
            return this.kitsTable.get().getKit(name);
        }
        return null;
    }

    @Override
    public void removeKit(Integer id) {
        if (this.loadKitsTable()) {
            this.kitsTable.get().removeKit(id);
        }
    }

    @Override
    public void removeKitSynchronized(Integer id) {
        if (this.loadKitsTable()) {
            this.kitsTable.get().removeKitSynchronized(id);
        }
    }

    @Override
    public void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        if (this.loadKitsTable()) {
            this.kitsTable.get().addKit(id, name, itemType, description);
        }
    }

    @Override
    public void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        if (this.loadKitsTable()) {
            this.kitsTable.get().addKit(name, itemType, description);
        }
    }

    @NotNull
    @Override
    public Collection<DbKit> getKits() {
        Collection<DbKit> kits = new ArrayList<>();
        for (Integer id : this.getKitIds()) {
            DbKit kit = this.getKit(id);
            if (kit != null && kit.exists()) {
                kits.add(kit);
            }
        }
        return kits;
    }

    @Override
    public void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName,
                       Collection<String> description, Collection<String> info, Collection<String> authors) {
        if (this.loadMapsTable()) {
            this.mapsTable.get().addMap(name, displayName, minPlayers, maxPlayers, itemName, description, info, authors);
        }
    }

    @Override
    public void removeMap(String name) {
        if (this.loadMapsTable()) {
            this.mapsTable.get().removeMap(name);
        }
    }

    @NotNull
    @Override
    public DbMap getMap(String mapName) {
        if (this.loadMapsTable()) {
            return this.mapsTable.get().getMap(mapName);
        }
        return null;
    }


    @NotNull
    public Collection<DbMap> getMaps(Integer players) {
        if (this.loadMapsTable()) {
            return this.mapsTable.get().getMaps(players);
        }
        return new ArrayList<>();
    }


    @NotNull
    @Override
    public Collection<DbMap> getMaps() {
        if (this.loadMapsTable()) {
            return this.mapsTable.get().getMaps();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean containsMap(String mapName) {
        if (this.loadMapsTable()) {
            return this.mapsTable.get().containsMap(mapName);
        }
        return false;
    }

    @NotNull
    @Override
    public Set<StatType<?>> getStats() {
        if (this.loadStatisticsTable()) {
            return this.statisticsTable.get().getStats();
        }
        return new HashSet<>();
    }

    @Nullable
    @Override
    public StatType<?> getStat(String name) {
        if (this.loadStatisticsTable()) {
            return this.statisticsTable.get().getStat(name);
        }
        return null;
    }

    @Override
    public void addStat(StatType<?> stat) {
        if (this.loadStatisticsTable()) {
            this.statisticsTable.get().addStat(stat);
        }
    }

    @Override
    public void removeStat(StatType<?> stat) {
        if (this.loadStatisticsTable()) {
            this.statisticsTable.get().removeStat(stat);
        }
    }

    @Nullable
    @Override
    public GameUserStatistic getUserStatistic(UUID uuid) {
        if (this.loadStatisticsTable()) {
            return this.statisticsTable.get().getUserStatistic(uuid);
        }
        return null;
    }

    @NotNull
    @Override
    public Collection<GameUserStatistic> getUserStatistics() {
        if (this.loadStatisticsTable()) {
            return this.statisticsTable.get().getUserStatistics();
        }
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type) {
        if (this.loadStatisticsTable()) {
            return this.statisticsTable.get().getStatOfUsers(period, type);
        }
        return new HashMap<>();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbGame toDatabase() {
        return this;
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbGame toLocal() {
        return new DbCachedGame(this, new DbCachedGameInfo(this.getInfo()));
    }
}
