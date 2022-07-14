package de.timesnake.database.core.game.info;

import de.timesnake.database.util.game.DbGameInfoBasis;

public class DbCachedGameInfoBasis implements DbGameInfoBasis {

    protected de.timesnake.database.core.game.info.DbGameInfoBasis database;

    protected String name;
    protected String displayName;
    protected String chatColorName;
    protected String itemName;
    protected String headLine;
    protected Integer slot;

    public DbCachedGameInfoBasis(de.timesnake.database.core.game.info.DbGameInfoBasis database) {
        this.database = database;
    }

    protected de.timesnake.database.core.game.info.DbGameInfoBasis getDatabase() {
        return this.database;
    }

    @Override
    public boolean exists() {
        return this.database.exists();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.database.setDisplayName(displayName);
    }

    @Override
    public String getChatColorName() {
        return chatColorName;
    }

    @Override
    public void setChatColorName(String chatColorName) {
        this.chatColorName = chatColorName;
        this.database.setChatColorName(chatColorName);
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItem(String itemName) {
        this.itemName = itemName;
        this.database.setItem(itemName);
    }

    @Override
    public String getHeadLine() {
        return headLine;
    }

    @Override
    public void setHeadLine(String headLine) {
        this.headLine = headLine;
        this.database.setHeadLine(headLine);
    }

    @Override
    public Integer getSlot() {
        return slot;
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
        this.database.setSlot(slot);
    }

    @Override
    public DbGameInfoBasis toDatabase() {
        return this.getDatabase();
    }

    @Override
    public DbGameInfoBasis toLocal() {
        return this.getDatabase().toLocal();
    }
}
