package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;

public interface DbGroup extends DbGroupBasis {

    boolean exists();

    String getName();

    @NotCached
    void setName(String name);

    Integer getRank();

    String getPrefix();

    @NotCached
    void setPrefix(String prefix);

    @Deprecated
    String getChatColorName();

    @NotCached
    @Deprecated
    void setChatColorName(String chatColorName);

    ExTextColor getChatColor();

    void setChatColor(ExTextColor color);
}
