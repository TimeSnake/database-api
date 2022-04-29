package de.timesnake.database.util.group;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;

public interface DbGroup extends DbCached<DbGroup> {

    boolean exists();

    @NotCached
    void setName(String name);

    String getName();

    @NotCached
    void setRank(int rank);

    Integer getRank();

    @NotCached
    void setPrefix(String prefix);

    String getPrefix();

    @NotCached
    void setChatColorName(String chatColorName);

    String getChatColorName();
}
