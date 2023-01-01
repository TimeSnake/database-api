/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group.perm;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbCachedGroupBasis;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

public class DbCachedPermGroup extends DbCachedGroupBasis implements DbPermGroup {

    private String inheritGroup;

    public DbCachedPermGroup(de.timesnake.database.core.group.perm.DbPermGroup database) {
        super(database);

        ColumnMap map = this.database.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.INHERITANCE));

        this.name = map.get(Column.Group.NAME);
        this.inheritGroup = map.get(Column.Group.INHERITANCE);
    }

    @Override
    public de.timesnake.database.core.group.perm.DbPermGroup getDatabase() {
        return (de.timesnake.database.core.group.perm.DbPermGroup) super.getDatabase();
    }

    @Override
    public void setInheritance(String inheritGroup, SyncExecute syncExecute) {
        this.inheritGroup = inheritGroup;
        this.getDatabase().setInheritance(inheritGroup, syncExecute);
    }

    @Override
    public void removeInheritance() {
        this.inheritGroup = null;
        this.getDatabase().removeInheritance();
    }

    @Override
    public void removeInheritance(SyncExecute syncExecute) {
        this.inheritGroup = null;
        this.getDatabase().removeInheritance(syncExecute);
    }

    @Nullable
    @Override
    public DbPermGroup getInheritance() {
        if (this.inheritGroup != null) {
            return Database.getGroups().getPermGroup(this.inheritGroup);
        }
        return null;
    }

    @Override
    public void setInheritance(String inheritGroup) {
        this.inheritGroup = inheritGroup;
        this.getDatabase().setInheritance(inheritGroup);
    }

    @NotNull
    @Override
    public Collection<DbPermGroup> getGroupsInherit() {
        return this.getDatabase().getGroupsInherit();
    }

    @NotNull
    @Override
    public Collection<DbPermission> getPermissions() {
        return this.getDatabase().getPermissions();
    }

    @Nullable
    @Override
    public DbPermission getPermission(String permission) {
        return this.getDatabase().getPermission(permission);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers) {
        this.getDatabase().addPermission(permission, mode, syncExecute,
                servers);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, String... servers) {
        this.getDatabase().addPermission(permission, mode, servers);
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.getDatabase().hasPermission(permission);
    }

    @Override
    public void removePermission(String permission) {
        this.getDatabase().removePermission(permission);
    }

    @Override
    public void removePermission(String permission, SyncExecute syncExecute) {
        this.getDatabase().removePermission(permission, syncExecute);
    }

    @NotNull
    @Override
    public DbPermGroup toLocal() {
        return this.getDatabase().toLocal();
    }

    @NotNull
    @Override
    public DbPermGroup toDatabase() {
        return this.getDatabase();
    }
}
