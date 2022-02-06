package de.timesnake.database.core.group.perm;

import de.timesnake.database.util.object.DatabaseConnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseGroups extends DatabaseConnector implements de.timesnake.database.util.group.DatabaseGroups {

    private final PermGroupsTable permGroupsTable;

    private final String permGroupsTableName;

    public DatabaseGroups(String name, String url, String user, String password, String permGroupsTableName) {
        super(name, url, user, password);
        this.permGroupsTableName = permGroupsTableName;
        this.permGroupsTable = new PermGroupsTable(this, this.permGroupsTableName);
    }

    public void createTables() {
        this.permGroupsTable.create();
    }

    public void backupTables() {
        this.permGroupsTable.backup();
    }

    @Override
    public void addPermGroup(String name, int rank, String prefix, String colorChatName) {
        this.permGroupsTable.addPermGroup(name, rank, prefix, colorChatName);
    }

    @Override
    public boolean containsGroup(int rank) {
        return this.permGroupsTable.containsPermGroup(rank);
    }

    @Override
    public boolean containsGroup(String name) {
        return this.permGroupsTable.containsPermGroup(name);
    }

    //TODO Method return update
    @Override
    public DbPermGroup getPermGroup(String name) {
        if (name != null && this.permGroupsTable.getRankFromName(name) != null) {
            return this.getPermGroup(this.permGroupsTable.getRankFromName(name));
        }
        return new DbPermGroup(this, 0, this.permGroupsTableName);
    }

    @Override
    public DbPermGroup getPermGroup(int rank) {
        return new DbPermGroup(this, rank, this.permGroupsTableName);
    }

    @Override
    public void removePermGroup(int rank) {
        this.permGroupsTable.removePermGroup(rank);
    }

    @Override
    public void removePermGroup(String name) {
        this.permGroupsTable.removePermGroup(name);
    }

    @Override
    public void removePermGroup(de.timesnake.database.util.group.DbPermGroup group) {
        this.permGroupsTable.removePermGroup(group.getRank());
    }

    @Override
    public Collection<String> getPermGroupNames() {
        return this.permGroupsTable.getPermGroupsName();
    }

    @Override
    public Collection<Integer> getPermGroupRanks() {
        return this.permGroupsTable.getPermGroupsRank();
    }

    @Override
    public Collection<de.timesnake.database.util.group.DbPermGroup> getPermGroups() {
        List<de.timesnake.database.util.group.DbPermGroup> groups = new ArrayList<>();
        for (Integer rank : this.getPermGroupRanks()) {
            DbPermGroup group = this.getPermGroup(rank);
            if (group.exists()) {
                groups.add(group);
            }
        }
        return groups;
    }
}
