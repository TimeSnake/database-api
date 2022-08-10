package de.timesnake.database.util.group;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;

public interface DbGroupBasis extends DbCached<DbGroupBasis> {
    boolean exists();

    String getName();

    @NotCached
    void setName(String name);

    Integer getRank();

}
