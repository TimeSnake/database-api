package de.timesnake.database.core.game;

import de.timesnake.database.core.game.kit.KitsTable;
import de.timesnake.database.core.game.map.MapsTable;
import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.core.game.statistic.StatisticsTable;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.core.game.team.TeamsTable;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.game.DbKit;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class DbGame extends DbGameInfo implements de.timesnake.database.util.game.DbGame {

    private final TeamsTable teamsTable;
    private final DbGamesInfoTable infoTable;
    private final KitsTable kitsTable;
    private final MapsTable mapsTable;
    private final StatisticsTable statisticsTable;

    protected DbGame(DatabaseConnector databaseConnector, String gameName, DbGamesInfoTable infoTable) {
        super(databaseConnector, infoTable.getTitle(), gameName);
        this.infoTable = infoTable;
        this.teamsTable = DatabaseManager.getInstance().getGameTeams().getGameTeams(gameName);
        this.statisticsTable = DatabaseManager.getInstance().getGameStatistics().getGameUserStatistics(gameName);

        Type.Availability kitsAvailability = super.getKitAvailability();
        if (kitsAvailability == null) {
            kitsAvailability = Type.Availability.FORBIDDEN;
        }
        if (kitsAvailability.equals(Type.Availability.ALLOWED) || kitsAvailability.equals(Type.Availability.REQUIRED)) {
            this.kitsTable = DatabaseManager.getInstance().getGameKits().getGameKits(gameName);
        } else {
            this.kitsTable = null;
        }

        Type.Availability mapsAvailability = super.getMapAvailability();
        if (mapsAvailability == null) {
            mapsAvailability = Type.Availability.FORBIDDEN;
        }
        if (mapsAvailability.equals(Type.Availability.ALLOWED) || mapsAvailability.equals(Type.Availability.REQUIRED)) {
            this.mapsTable = new de.timesnake.database.core.game.map.MapsTable(gameName);
        } else {
            this.mapsTable = null;
        }
    }

    protected DbGame(DbGame game) {
        super(game.databaseConnector, game.infoTable.getTitle(), game.getName());
        this.teamsTable = game.teamsTable;
        this.infoTable = game.infoTable;
        this.kitsTable = game.kitsTable;
        this.mapsTable = game.mapsTable;
        this.statisticsTable = game.statisticsTable;
    }

    protected void create(String name, String displayName, String chatColorName, int autoStart, int minPlayers, int maxPlayers, String description, String itemName, int slot, boolean isTemporary, Type.Availability kits, Type.Availability maps, Type.Availability teamMerge, Integer... teamAmounts) {
        this.infoTable.addGame(name, displayName, chatColorName, autoStart, minPlayers, maxPlayers, description, itemName, slot, isTemporary, kits, maps, teamMerge, teamAmounts);
        this.createTables();
    }

    public void createTables() {
        this.teamsTable.create();
        this.statisticsTable.create();

        Type.Availability kitsAvailability = super.getKitAvailability();
        if (kitsAvailability == null) {
            kitsAvailability = Type.Availability.FORBIDDEN;
            super.setKitsAvailability(kitsAvailability);
        }
        if (kitsAvailability.equals(Type.Availability.ALLOWED) || kitsAvailability.equals(Type.Availability.REQUIRED)) {
            this.kitsTable.create();
        }
        Type.Availability mapsAvailability = super.getMapAvailability();
        if (mapsAvailability == null) {
            mapsAvailability = Type.Availability.FORBIDDEN;
            super.setMapsAvailability(mapsAvailability);
        }
        if (mapsAvailability.equals(Type.Availability.ALLOWED) || mapsAvailability.equals(Type.Availability.REQUIRED)) {
            this.mapsTable.create();
        }
    }

    public void backup() {
        this.teamsTable.backup();
        this.statisticsTable.backup();

        Type.Availability kitsAvailability = super.getKitAvailability();
        if (kitsAvailability == null) {
            kitsAvailability = Type.Availability.FORBIDDEN;
        }
        if (kitsAvailability.equals(Type.Availability.ALLOWED) || kitsAvailability.equals(Type.Availability.REQUIRED)) {
            this.kitsTable.createBackup();
        }
        Type.Availability mapsAvailability = super.getMapAvailability();
        if (mapsAvailability == null) {
            mapsAvailability = Type.Availability.FORBIDDEN;
        }
        if (mapsAvailability.equals(Type.Availability.ALLOWED) || mapsAvailability.equals(Type.Availability.REQUIRED)) {
            this.mapsTable.backup();
        }
    }

    @Override
    public void delete() {
        this.teamsTable.delete();
        this.statisticsTable.delete();

        if (this.teamsTable != null) {
            this.kitsTable.delete();
        }
        this.infoTable.removeGame(super.getName());
        if (this.mapsTable != null) {
            this.mapsTable.delete();
        }
    }

    @Override
    public void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName) {
        this.teamsTable.addTeam(name, rank, prefix, colorChatName, ratio, colorName);
    }

    @Override
    public void removeTeam(String name) {
        this.teamsTable.removeTeam(name);
    }

    @Override
    public void removeTeam(int rank) {
        this.teamsTable.removeTeam(rank);
    }

    @Override
    public Integer getHighestRank() {
        return this.teamsTable.getHighestRank();
    }

    @Override
    public boolean containsTeam(int rank) {
        return this.teamsTable.containsTeam(rank);
    }

    @Override
    public boolean containsTeam(String name) {
        return this.teamsTable.containsTeam(name);
    }

    @Override
    public DbTeam getTeam(String name) {
        return this.teamsTable.getTeam(name);
    }

    @Override
    public DbTeam getTeam(int rank) {
        return this.teamsTable.getTeam(rank);
    }

    @Override
    public Collection<String> getTeamNames() {
        return this.teamsTable.getTeamNames();
    }

    @Override
    public Collection<Integer> getTeamRanks() {
        return this.teamsTable.getTeamRanks();
    }

    @Override
    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        return this.teamsTable.getTeams();
    }

    @Override
    public Collection<Integer> getKitIds() {
        if (this.kitsTable != null) {
            return this.kitsTable.getKitsId();
        }
        return new ArrayList<>();
    }

    @Override
    public DbKit getKit(int id) {
        if (this.kitsTable != null) {
            return this.kitsTable.getKit(id);
        }
        return null;
    }

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
    public void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName, Collection<String> description, Collection<String> info, Collection<String> authors) {
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

    @Override
    public DbMap getMap(String mapName) {
        if (this.mapsTable != null) {
            return this.mapsTable.getMap(mapName);
        }
        return null;
    }

    @Override
    public Collection<DbMap> getMaps() {
        if (this.mapsTable != null) {
            return this.mapsTable.getMaps();
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<DbMap> getMaps(Integer players) {
        if (this.mapsTable != null) {
            return this.mapsTable.getMaps(players);
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

    @Override
    public Set<Stat<?>> getStats() {
        return statisticsTable.getStats();
    }

    @Override
    public <ValueType> Stat<ValueType> getStat(String name, Stat.Type<ValueType> type) {
        return statisticsTable.getStat(name, type);
    }

    @Override
    public void addStat(Stat<?> stat) {
        statisticsTable.addStat(stat);
    }

    @Override
    public void removeStat(Stat<?> stat) {
        statisticsTable.removeStat(stat);
    }

    @Override
    public GameUserStatistic getUserStatistic(UUID uuid) {
        return this.statisticsTable.getUserStatistic(uuid);
    }

    @Override
    public Collection<GameUserStatistic> getUserStatistics() {
        return this.statisticsTable.getUserStatistics();
    }

    @Override
    public de.timesnake.database.util.game.DbGame toLocal() {
        return new DbLocalGame(this);
    }

    @Override
    public de.timesnake.database.util.game.DbGame toDatabase() {
        return this;
    }

}
