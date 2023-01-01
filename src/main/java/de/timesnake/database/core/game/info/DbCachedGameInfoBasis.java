/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.util.game.DbGameInfoBasis;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

public class DbCachedGameInfoBasis implements DbGameInfoBasis {

    protected de.timesnake.database.core.game.info.DbGameInfoBasis database;

    protected String name;
    protected String displayName;
    protected ExTextColor textColor;
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

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @NotNull
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
    @Deprecated
    @NotNull
    public String getChatColorName() {
        return this.textColor.toString();
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        this.textColor = ExTextColor.NAMES.value(chatColorName);
        this.database.setTextColor(this.textColor);
    }

    @NotNull
    @Override
    public ExTextColor getTextColor() {
        return this.textColor;
    }

    @Override
    public void setTextColor(ExTextColor color) {
        this.textColor = color;
        this.database.setTextColor(this.textColor);
    }

    @NotNull
    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItem(String itemName) {
        this.itemName = itemName;
        this.database.setItem(itemName);
    }

    @NotNull
    @Override
    public String getHeadLine() {
        return headLine;
    }

    @Override
    public void setHeadLine(String headLine) {
        this.headLine = headLine;
        this.database.setHeadLine(headLine);
    }

    @NotNull
    @Override
    public Integer getSlot() {
        return slot;
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
        this.database.setSlot(slot);
    }

    @NotNull
    @Override
    public DbGameInfoBasis toDatabase() {
        return this.getDatabase();
    }

    @NotNull
    @Override
    public DbGameInfoBasis toLocal() {
        return this.getDatabase().toLocal();
    }
}
