package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;

public interface DbGameInfoBasis {

    boolean exists();

    String getName();

    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    String getChatColorName();

    @NotCached
    void setChatColorName(String chatColorName);

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
