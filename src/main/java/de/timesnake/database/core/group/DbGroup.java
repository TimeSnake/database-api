/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbGroup extends DbGroupBasis implements de.timesnake.database.util.group.DbGroup {


    protected DbGroup(DatabaseConnector databaseConnector, String name, String tableName) {
        super(databaseConnector, name, tableName);
    }

    @Nullable
    @Override
    public String getPrefix() {
        return super.getFirstWithKey(Column.Group.PREFIX);
    }

    @Override
    public void setPrefix(String prefix) {
        super.setWithKey(prefix, Column.Group.PREFIX);
    }

    @Override
    @Deprecated
    @Nullable
    public String getChatColorName() {
        ExTextColor color = this.getChatColor();
        return color != null ? color.toString() : null;
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        super.setWithKey(ExTextColor.NAMES.value(chatColorName), Column.Group.PREFIX_COLOR);
    }

    @Nullable
    @Override
    public ExTextColor getChatColor() {
        return super.getFirstWithKey(Column.Group.PREFIX_COLOR);
    }

    @Override
    public void setChatColor(ExTextColor color) {
        super.setWithKey(color, Column.Group.PREFIX_COLOR);
    }


    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toLocal() {
        return new DbCachedGroup(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toDatabase() {
        return this;
    }

}
