package de.timesnake.database.util.group;

import de.timesnake.library.basic.util.chat.ExTextColor;

import java.util.Collection;

public interface DatabaseGroups {

    void addPermGroup(String name, int rank);

    boolean containsPermGroup(String name);

    DbPermGroup getPermGroup(String name);

    void removePermGroup(String name);

    Collection<String> getPermGroupNames();

    Collection<Integer> getPermGroupRanks();

    Collection<? extends DbPermGroup> getPermGroups();

    void addDisplayGroup(String name, int rank, String prefix, ExTextColor color);

    boolean containsDisplayGroup(String name);

    DbDisplayGroup getDisplayGroup(String name);

    void removeDisplayGroup(String name);

    Collection<String> getDisplayGroupNames();

    Collection<Integer> getDisplayGroupRanks();

    Collection<? extends DbDisplayGroup> getDisplayGroups();
}
