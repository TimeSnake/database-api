/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbGroupBasis extends TableQuery implements de.timesnake.database.util.group.DbGroupBasis {

    protected DbGroupBasis(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, nameTable, new Entry<>(name, Column.Group.NAME));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Group.PRIORITY) != null;
    }

    @NotNull
    @Override
    public String getName() {
        return ((String) this.primaryEntries.get(0).getValue());
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Group.NAME);
    }

    @NotNull
    @Override
    public Integer getRank() {
        return super.getFirstWithKey(Column.Group.PRIORITY);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroupBasis toLocal() {
        return new DbCachedGroupBasis(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroupBasis toDatabase() {
        return this;
    }

}
