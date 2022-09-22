package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbGroup extends DbGroupBasis {

    boolean exists();

    @NotNull
    String getName();

    @NotCached
    void setName(String name);

    @NotNull
    Integer getRank();

    @Nullable
    String getPrefix();

    @NotCached
    void setPrefix(String prefix);

    @Deprecated
    @Nullable
    String getChatColorName();

    @NotCached
    @Deprecated
    void setChatColorName(String chatColorName);

    @Nullable
    ExTextColor getChatColor();

    void setChatColor(ExTextColor color);
}
