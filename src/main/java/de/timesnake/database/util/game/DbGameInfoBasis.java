package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

public interface DbGameInfoBasis {

    boolean exists();

    @NotNull
    String getName();

    @NotNull
    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    @Deprecated
    @NotNull
    String getChatColorName();

    @NotCached
    @Deprecated
    void setChatColorName(String chatColorName);

    @NotNull
    ExTextColor getTextColor();

    @NotCached
    void setTextColor(ExTextColor color);

    @NotNull
    String getItemName();

    @NotCached
    void setItem(String itemName);

    @NotNull
    String getHeadLine();

    @NotCached
    void setHeadLine(String headLine);

    @NotNull
    Integer getSlot();

    @NotCached
    void setSlot(int slot);

    @NotNull
    DbGameInfoBasis toDatabase();

    @NotNull
    DbGameInfoBasis toLocal();
}
