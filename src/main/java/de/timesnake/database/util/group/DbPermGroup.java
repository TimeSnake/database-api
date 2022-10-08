/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.util.group;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @NotNull
    Collection<DbPermGroup> getGroupsInherit();

    @NotCached
    @NotNull
    Collection<DbPermission> getPermissions();

    @Nullable
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

    @NotNull
    @Override
    DbPermGroup toLocal();

    @NotNull
    @Override
    DbPermGroup toDatabase();
}
