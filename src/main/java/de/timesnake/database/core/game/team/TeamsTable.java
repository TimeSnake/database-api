package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.group.GroupsTable;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.ArrayList;
import java.util.Collection;

public class TeamsTable extends GroupsTable {

    public TeamsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName);
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
            super.set(ratio, Column.Team.RATIO, new TableEntry<>(rank, Column.Group.PRIORITY));
            super.set(colorName, Column.Team.COLOR, new TableEntry<>(rank, Column.Group.PRIORITY));
        });
    }

    public void removeTeam(String name) {
        super.removeGroup(name);
    }

    public Integer getHighestRank() {
        return super.getHighestInteger(Column.Group.PRIORITY);
    }

    public boolean containsTeam(String name) {
        return super.containsGroup(name);
    }

    public DbTeam getTeam(String name) {
        return new DbTeam(this.databaseConnector, name, this.tableName);
    }

    public Collection<String> getTeamNames() {
        return super.getGroupNames();
    }

    public Collection<Integer> getTeamRanks() {
        return super.getGroupRanks();
    }

    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        Collection<de.timesnake.database.util.game.DbTeam> teams = new ArrayList<>();
        for (String name : this.getTeamNames()) {
            teams.add(this.getTeam(name));
        }
        return teams;
    }

}
