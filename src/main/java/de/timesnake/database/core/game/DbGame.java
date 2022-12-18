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
    protected KitsTable kitsTable;
    protected MapsTable mapsTable;
    protected StatisticsTable statisticsTable;

    protected DbGame(DatabaseConnector databaseConnector, String gameName, DbGameInfo info) {
        this.databaseConnector = databaseConnector;
        this.gameName = gameName;
        this.info = info;
        this.initTables();
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

    private void initTables() {
        if (this.info.hasStatistics()) {
            this.statisticsTable = DatabaseManager.getInstance().getGameStatistics().getGameUserStatistics(gameName);
        }

        Type.Availability kitsAvailability = this.info.getKitAvailability();
        if (kitsAvailability.equals(Type.Availability.ALLOWED) || kitsAvailability.equals(Type.Availability.REQUIRED)) {
            this.kitsTable = DatabaseManager.getInstance().getGameKits().getGameKits(gameName);
        }

        Type.Availability mapsAvailability = this.info.getMapAvailability();
        if (mapsAvailability.equals(Type.Availability.ALLOWED) || mapsAvailability.equals(Type.Availability.REQUIRED)) {
            this.mapsTable = new de.timesnake.database.core.game.map.MapsTable(gameName);
        }
    }

    public void createTables() {
        if (this.info.hasStatistics()) {
            this.statisticsTable.create();
        }

        Type.Availability kitsAvailability = this.info.getKitAvailability();
        if (kitsAvailability.equals(Type.Availability.ALLOWED) || kitsAvailability.equals(Type.Availability.REQUIRED)) {
            this.kitsTable.create();
        }

        Type.Availability mapsAvailability = this.info.getMapAvailability();
        if (mapsAvailability.equals(Type.Availability.ALLOWED) || mapsAvailability.equals(Type.Availability.REQUIRED)) {
            this.mapsTable.create();
        }
    }

    public void backup() {
        if (this.statisticsTable != null) {
            this.statisticsTable.backup();
        }

        if (this.kitsTable != null) {
            this.kitsTable.backup();
        }

        if (this.mapsTable != null) {
            this.mapsTable.backup();
        }
    }

    public void delete() {
        if (this.statisticsTable != null) {
            this.statisticsTable.delete();
        }

        if (this.kitsTable != null) {
            this.kitsTable.delete();
        }

        if (this.mapsTable != null) {
            this.mapsTable.delete();
        }
    }

    @Override
    public boolean exists() {
        return this.getInfo().exists();
    }

    @NotNull
    @Override
    public List<Integer> getKitIds() {
        if (this.kitsTable != null) {
            return this.kitsTable.getKitsId();
        }
        return new ArrayList<>();
    }

    @Nullable
    @Override
    public DbKit getKit(int id) {
        if (this.kitsTable != null) {
            return this.kitsTable.getKit(id);
        }
        return null;
    }

    @Nullable
    @Override
    public DbKit getKit(String name) {
        if (this.kitsTable != null) {
            return this.kitsTable.getKit(name);
        }
        return null;
    }

    @Override
    public void removeKit(Integer id) {
        if (this.kitsTable != null) {
            this.kitsTable.removeKit(id);
        }
    }

    @Override
    public void removeKitSynchronized(Integer id) {
        if (this.kitsTable != null) {
            this.kitsTable.removeKitSynchronized(id);
        }
    }

    @Override
    public void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        if (this.kitsTable != null) {
            this.kitsTable.addKit(id, name, itemType, description);
        }
    }

    @Override
    public void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        if (this.kitsTable != null) {
            this.kitsTable.addKit(name, itemType, description);
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
                       List<String> description, List<String> info, List<String> authors) {
        if (this.mapsTable != null) {
            this.mapsTable.addMap(name, displayName, minPlayers, maxPlayers, itemName, description, info, authors);
        }
    }

    @Override
    public void removeMap(String name) {
        if (this.mapsTable != null) {
            this.mapsTable.removeMap(name);
        }
    }

    @NotNull
    @Override
    public DbMap getMap(String mapName) {
        if (this.mapsTable != null) {
            return this.mapsTable.getMap(mapName);
        }
        return null;
    }


    @NotNull
    public List<DbMap> getMaps(Integer players) {
        if (this.mapsTable != null) {
            return this.mapsTable.getMaps(players);
        }
        return new ArrayList<>();
    }


    @NotNull
    @Override
    public List<DbMap> getMaps() {
        if (this.mapsTable != null) {
            return this.mapsTable.getMaps();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean containsMap(String mapName) {
        if (this.mapsTable != null) {
            return this.mapsTable.containsMap(mapName);
        }
        return false;
    }

    @NotNull
    @Override
    public Set<StatType<?>> getStats() {
        if (this.statisticsTable != null) {
            return this.statisticsTable.getStats();
        }
        return new HashSet<>();
    }

    @Nullable
    @Override
    public StatType<?> getStat(String name) {
        if (this.statisticsTable != null) {
            return this.statisticsTable.getStat(name);
        }
        return null;
    }

    @Override
    public void addStat(StatType<?> stat) {
        if (this.statisticsTable != null) {
            this.statisticsTable.addStat(stat);
        }
    }

    @Override
    public void removeStat(StatType<?> stat) {
        if (this.statisticsTable != null) {
            this.statisticsTable.removeStat(stat);
        }
    }

    @Nullable
    @Override
    public GameUserStatistic getUserStatistic(UUID uuid) {
        if (this.statisticsTable != null) {
            return this.statisticsTable.getUserStatistic(uuid);
        }
        return null;
    }

    @NotNull
    @Override
    public List<GameUserStatistic> getUserStatistics() {
        if (this.statisticsTable != null) {
            return this.statisticsTable.getUserStatistics();
        }
        return new ArrayList<>();
    }

    @NotNull
    @Override
    public <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type) {
        if (this.statisticsTable != null) {
            return this.statisticsTable.getStatOfUsers(period, type);
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
