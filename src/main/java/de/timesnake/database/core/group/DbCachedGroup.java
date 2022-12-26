/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbCachedGroup extends DbCachedGroupBasis implements de.timesnake.database.util.group.DbGroup {

    protected String prefix;
    protected ExTextColor color;

    public DbCachedGroup(DbGroup database) {
        super(database);
    }

    @Override
    public DbGroup getDatabase() {
        return (DbGroup) super.getDatabase();
    }

    @Nullable
    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.getDatabase().setPrefix(prefix);
    }

    @Nullable
    @Override
    public String getChatColorName() {
        return this.color.toString();
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        this.color = ExTextColor.NAMES.value(chatColorName);
        this.getDatabase().setChatColor(color);
    }

    @Nullable
    @Override
    public ExTextColor getChatColor() {
        return this.color;
    }

    @Override
    public void setChatColor(ExTextColor color) {
        this.color = color;
        this.getDatabase().setChatColor(color);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toLocal() {
        return this.getDatabase().toLocal();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toDatabase() {
        return this.getDatabase();
    }
}
