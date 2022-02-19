package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotLocal;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;

public interface DbPermGroup extends DbGroup {

    @NotLocal
    void setInheritance(String inheritGroup);

    @NotLocal
    void setInheritance(String inheritGroup, SyncExecute syncExecute);

    @NotLocal
    void removeInheritance();

    @NotLocal
    void removeInheritance(SyncExecute syncExecute);

    /**
     * Gets the inheritance of the group
     *
     * @return the {@link DbPermGroup}, returns null if inheritance is null
     */
    DbPermGroup getInheritance();

    @NotLocal
    Collection<DbPermGroup> getGroupsInherit();

    @NotLocal
    Collection<DbPermission> getPermissions();

    @NotLocal
    DbPermission getPermission(String permission);

    @NotLocal
    void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers);

    @NotLocal
    void addPermission(String permission, Status.Permission mode, String... servers);

    @NotLocal
    boolean hasPermission(String permission);

    @NotLocal
    void removePermission(String permission);

    @NotLocal
    void removePermission(String permission, SyncExecute syncExecute);

    @Override
    DbPermGroup toLocal();

    @Override
    DbPermGroup toDatabase();
}
