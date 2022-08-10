package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotCached;

public interface DbGroup extends DbGroupBasis {

    boolean exists();

    String getName();

    @NotCached
    void setName(String name);

    Integer getRank();

    String getPrefix();

    @NotCached
    void setPrefix(String prefix);

    String getChatColorName();

    @NotCached
    void setChatColorName(String chatColorName);
}
