package de.timesnake.database.core.game;

import de.timesnake.database.core.game.statistic.GameUserStatistic;
import de.timesnake.database.util.game.DbGame;
import de.timesnake.database.util.game.DbKit;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.game.DbTeam;
import de.timesnake.database.util.object.UnsupportedStringException;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class DbLocalGame extends DbLocalGameInfo implements DbGame {

    private final de.timesnake.database.core.game.DbGame game;

    public DbLocalGame(de.timesnake.database.core.game.DbGame game) {
        super(game);

        this.game = game;
    }

    @Override
    public void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName) {
        this.game.addTeam(name, rank, prefix, colorChatName, ratio, colorName);
    }

    @Override
    public void removeTeam(String name) {
        this.game.removeTeam(name);
    }

    @Override
    public void removeTeam(int rank) {
        this.game.removeTeam(rank);
    }

    @Override
    public Integer getHighestRank() {
        return this.game.getHighestRank();
    }

    @Override
    public boolean containsTeam(int rank) {
        return this.game.containsTeam(rank);
    }

    @Override
    public boolean containsTeam(String name) {
        return this.game.containsTeam(name);
    }

    @Override
    public DbTeam getTeam(String name) {
        return this.game.getTeam(name);
    }

    @Override
    public DbTeam getTeam(int rank) {
        return this.game.getTeam(rank);
    }

    @Override
    public Collection<String> getTeamNames() {
        return this.game.getTeamNames();
    }

    @Override
    public Collection<Integer> getTeamRanks() {
        return this.game.getTeamRanks();
    }

    @Override
    public Collection<DbTeam> getTeams() {
        return this.game.getTeams();
    }

    @Override
    public Collection<Integer> getKitIds() {
        return this.game.getKitIds();
    }

    @Override
    public DbKit getKit(int id) {
        return this.game.getKit(id);
    }

    @Override
    public DbKit getKit(String name) {
        return this.game.getKit(name);
    }

    @Override
    public void removeKit(Integer id) {
        this.game.removeKit(id);
    }

    @Override
    public void removeKitSynchronized(Integer id) {
        this.game.removeKitSynchronized(id);
    }

    @Override
    public void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        this.game.addKit(id, name, itemType, description);
    }

    @Override
    public void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        this.game.addKit(name, itemType, description);
    }

    @Override
    public Collection<DbKit> getKits() {
        return this.game.getKits();
    }

    @Override
    public void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName, Collection<String> description, Collection<String> info, Collection<String> authors) {
        this.game.addMap(name, displayName, minPlayers, maxPlayers, itemName, description, info, authors);
    }

    @Override
    public void removeMap(String name) {
        this.game.removeMap(name);
    }

    @Override
    public DbMap getMap(String mapName) {
        return this.game.getMap(mapName);
    }

    @Override
    public Collection<DbMap> getMaps() {
        return this.game.getMaps();
    }

    @Override
    public Collection<DbMap> getMaps(Integer players) {
        return this.game.getMaps(players);
    }

    @Override
    public boolean containsMap(String mapName) {
        return this.game.containsMap(mapName);
    }

    @Override
    public Set<Stat<?>> getStats() {
        return this.game.getStats();
    }

    @Override
    public <ValueType> Stat<ValueType> getStat(String name, Stat.Type<ValueType> type) {
        return this.game.getStat(name, type);
    }

    @Override
    public void addStat(Stat<?> stat) {
        this.game.addStat(stat);
    }

    @Override
    public void removeStat(Stat<?> stat) {
        this.game.removeStat(stat);
    }

    @Override
    public GameUserStatistic getUserStatistic(UUID uuid) {
        return this.game.getUserStatistic(uuid);
    }

    @Override
    public Collection<GameUserStatistic> getUserStatistics() {
        return this.game.getUserStatistics();
    }

    @Override
    public DbGame toLocal() {
        return new DbLocalGame(this.game);
    }

    @Override
    public DbGame toDatabase() {
        return this.game;
    }
}
