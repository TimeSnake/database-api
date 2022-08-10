package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Nullable;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;

public interface DbPermGroup extends DbGroupBasis {

    @NotCached
    void setInheritance(String inheritGroup, SyncExecute syncExecute);

    @NotCached
    void removeInheritance();

    @NotCached
    void removeInheritance(SyncExecute syncExecute);

    /**
     * Gets the inheritance of the group
     *
     * @return the {@link DbPermGroup}, returns null if inheritance is null
     */
    @Nullable
    DbPermGroup getInheritance();

    @NotCached
    void setInheritance(String inheritGroup);

    @NotCached
    Collection<DbPermGroup> getGroupsInherit();

    @NotCached
    Collection<DbPermission> getPermissions();

    @NotCached
    DbPermission getPermission(String permission);

    @NotCached
    void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers);

    @NotCached
    void addPermission(String permission, Status.Permission mode, String... servers);

    @NotCached
    boolean hasPermission(String permission);

    @NotCached
    void removePermission(String permission);

    @NotCached
    void removePermission(String permission, SyncExecute syncExecute);

    @Override
    DbPermGroup toLocal();

    @Override
    DbPermGroup toDatabase();
}
