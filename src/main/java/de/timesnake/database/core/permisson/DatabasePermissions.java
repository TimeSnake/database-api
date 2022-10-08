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

package de.timesnake.database.core.permisson;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedList;

public class DatabasePermissions extends DatabaseConnector implements de.timesnake.database.util.permission.DatabasePermissions {

    private final PermissionsTable permissionsTable;

    private final String permissionsTableName;

    public DatabasePermissions(String name, String url, String user, String password, String permissionsTable) {
        super(name, url, user, password);
        this.permissionsTableName = permissionsTable;
        this.permissionsTable = new PermissionsTable(this, permissionsTableName);
    }

    @Override
    public void createTables() {
        permissionsTable.create();
    }

    @Override
    public void backupTables() {
        permissionsTable.backup();
    }

    @Override
    public void addPermission(String name, String permission, Status.Permission mode, String... servers) {
        this.permissionsTable.addPermission(name, permission, mode, servers);
    }

    @Override
    public void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute,
                              String... servers) {
        this.permissionsTable.addPermission(name, permission, mode, syncExecute, servers);
    }

    @Override
    public void deletePermission(String name, String permission) {
        this.permissionsTable.removePermission(name, permission);
    }

    @Override
    public void deletePermission(String name, String permission, SyncExecute syncExecute) {
        this.permissionsTable.removePermission(name, permission, syncExecute);
    }

    @Override
    public boolean containsPermission(String name, String permission) {
        return this.permissionsTable.containsPermission(name, permission);
    }

    @Nullable
    @Override
    public de.timesnake.database.util.permission.DbPermission getPermission(String name, String permission) {
        if (this.permissionsTable.getIdFromName(name, permission) != 0) {
            return new DbPermission(this, this.permissionsTable.getIdFromName(name, permission),
                    this.permissionsTableName);
        }
        return null;
    }

    @NotNull
    @Override
    public Collection<de.timesnake.database.util.permission.DbPermission> getPermissions(String name) {
        LinkedList<de.timesnake.database.util.permission.DbPermission> permissions = new LinkedList<>();
        for (int id : this.permissionsTable.getIdsFromName(name)) {
            permissions.add(new DbPermission(this, id, this.permissionsTableName));
        }
        return permissions;
    }


}
