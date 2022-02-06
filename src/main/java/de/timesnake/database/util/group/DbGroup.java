package de.timesnake.database.util.group;

import de.timesnake.database.util.object.DbLocal;
import de.timesnake.database.util.object.NotLocal;

public interface DbGroup extends DbLocal<DbGroup> {

    boolean exists();

    @NotLocal
    void setName(String name);

    String getName();

    @NotLocal
    void setRank(int rank);

    Integer getRank();

    @NotLocal
    void setPrefix(String prefix);

    String getPrefix();

    @NotLocal
    void setChatColorName(String chatColorName);

    String getChatColorName();
}
