package de.timesnake.database.core.group.perm;

import de.timesnake.database.core.group.DbCachedGroup;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;

public class DbCachedPermGroup extends DbCachedGroup implements DbPermGroup {

    private String inheritGroup;

    public DbCachedPermGroup(de.timesnake.database.core.group.perm.DbPermGroup group) {
        super(group);

        DbPermGroup inheritGroup = group.getInheritance();

        this.inheritGroup = inheritGroup != null ? inheritGroup.getName() : null;
    }

    @Override
    public void setInheritance(String inheritGroup) {
        this.inheritGroup = inheritGroup;
        ((de.timesnake.database.core.group.perm.DbPermGroup) super.group).setInheritance(inheritGroup);
    }

    @Override
    public void setInheritance(String inheritGroup, SyncExecute syncExecute) {
        this.inheritGroup = inheritGroup;
        ((de.timesnake.database.core.group.perm.DbPermGroup) super.group).setInheritance(inheritGroup, syncExecute);
    }

    @Override
    public void removeInheritance() {
        this.inheritGroup = null;
        ((de.timesnake.database.core.group.perm.DbPermGroup) super.group).removeInheritance();
    }

    @Override
    public void removeInheritance(SyncExecute syncExecute) {
        this.inheritGroup = null;
        ((de.timesnake.database.core.group.perm.DbPermGroup) super.group).removeInheritance(syncExecute);
    }

    @Override
    public DbPermGroup getInheritance() {
        if (this.inheritGroup != null) {
            return Database.getGroups().getPermGroup(this.inheritGroup);
        }
        return null;
    }

    @Override
    public Collection<DbPermGroup> getGroupsInherit() {
        return ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).getGroupsInherit();
    }

    @Override
    public Collection<DbPermission> getPermissions() {
        return ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).getPermissions();
    }

    @Override
    public DbPermission getPermission(String permission) {
        return ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).getPermission(permission);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers) {
        ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).addPermission(permission, mode, syncExecute, servers);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, String... servers) {
        ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).addPermission(permission, mode, servers);
    }

    @Override
    public boolean hasPermission(String permission) {
        return ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).hasPermission(permission);
    }

    @Override
    public void removePermission(String permission) {
        ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).removePermission(permission);
    }

    @Override
    public void removePermission(String permission, SyncExecute syncExecute) {
        ((de.timesnake.database.core.group.perm.DbPermGroup) this.group).removePermission(permission, syncExecute);
    }

    @Override
    public DbPermGroup toLocal() {
        return new DbCachedPermGroup(((de.timesnake.database.core.group.perm.DbPermGroup) super.group));
    }

    @Override
    public DbPermGroup toDatabase() {
        return ((de.timesnake.database.core.group.perm.DbPermGroup) super.group);
    }
}
