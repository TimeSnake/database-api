package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;

public interface DbGameInfoBasis {

    boolean exists();

    String getName();

    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    @Deprecated
    String getChatColorName();

    @NotCached
    @Deprecated
    void setChatColorName(String chatColorName);

    ExTextColor getTextColor();

    @NotCached
    void setTextColor(ExTextColor color);

    String getItemName();

    @NotCached
    void setItem(String itemName);

    String getHeadLine();

    @NotCached
    void setHeadLine(String headLine);

    Integer getSlot();

    @NotCached
    void setSlot(int slot);

    DbGameInfoBasis toDatabase();

    DbGameInfoBasis toLocal();
}
