package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.group.BasicGroupsTable;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.ArrayList;
import java.util.Collection;

public class TeamsTable extends BasicGroupsTable {

    public TeamsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Team.RATIO);
        super.addColumn(Column.Team.COLOR);
        super.addColumn(Column.Team.PRIVATE_CHAT);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName) {
        super.addGroup(name, rank, prefix, colorChatName, () -> {
            super.set(ratio, Column.Team.RATIO, new TableEntry<>(rank, Column.Group.RANK));
            super.set(colorName, Column.Team.COLOR, new TableEntry<>(rank, Column.Group.RANK));
        });
    }

    public void removeTeam(String name) {
        super.removeGroup(name);
    }

    public void removeTeam(int rank) {
        super.removeGroup(rank);
    }

    public Integer getHighestRank() {
        return super.getHighestInteger(Column.Group.RANK);
    }

    public boolean containsTeam(int rank) {
        return super.containsGroup(rank);
    }

    public boolean containsTeam(String name) {
        return super.containsGroup(name);
    }

    public DbTeam getTeam(String name) {
        return this.getTeam(super.getRankFromName(name));
    }

    public DbTeam getTeam(int rank) {
        return new DbTeam(super.databaseConnector, rank, super.tableName);
    }

    public Collection<String> getTeamNames() {
        return super.getGroupsName();
    }

    public Collection<Integer> getTeamRanks() {
        return super.getGroupsRank();
    }

    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        Collection<de.timesnake.database.util.game.DbTeam> teams = new ArrayList<>();
        for (int rank : this.getTeamRanks()) {
            teams.add(this.getTeam(rank));
        }
        return teams;
    }

}
