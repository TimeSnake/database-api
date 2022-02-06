package de.timesnake.database.util.group;

import java.util.Collection;

public interface DatabaseGroups {

    void addPermGroup(String name, int rank, String prefix, String colorChatName);

    boolean containsGroup(int rank);

    boolean containsGroup(String name);

    //TODO Method return update
    DbPermGroup getPermGroup(String name);

    DbPermGroup getPermGroup(int rank);

    void removePermGroup(int rank);

    void removePermGroup(String name);

    void removePermGroup(DbPermGroup group);

    Collection<String> getPermGroupNames();

    Collection<Integer> getPermGroupRanks();

    Collection<DbPermGroup> getPermGroups();
}
