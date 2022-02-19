package de.timesnake.database.core.permisson;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;

import java.util.ArrayList;

public class DatabasePermissions extends DatabaseConnector implements de.timesnake.database.util.permission.DatabasePermissions {

    private final PermissionsTable permissionsTable;

    private final String permissionsTableName;

    public DatabasePermissions(String name, String url, String user, String password, String permissionsTable) {
        super(name, url, user, password);
        this.permissionsTableName = permissionsTable;
        this.permissionsTable = new PermissionsTable(this, permissionsTableName);
    }

    public void createTables() {
        permissionsTable.create();
    }

    public void backupTables() {
        permissionsTable.backup();
    }

    @Override
    public void addPermission(String name, String permission, Status.Permission mode, String... servers) {
        this.permissionsTable.addPermission(name, permission, mode, servers);
    }

    @Override
    public void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute, String... servers) {
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

    @Override
    public de.timesnake.database.util.permission.DbPermission getPermission(String name, String permission) {
        if (this.permissionsTable.getIdFromName(name, permission) != 0) {
            return new DbPermission(this, this.permissionsTable.getIdFromName(name, permission), this.permissionsTableName);
        }
        return null;
    }

    @Override
    public ArrayList<de.timesnake.database.util.permission.DbPermission> getPermissions(String name) {
        ArrayList<de.timesnake.database.util.permission.DbPermission> permissions = new ArrayList<>();
        for (int id : this.permissionsTable.getIdsFromName(name)) {
            permissions.add(new DbPermission(this, id, this.permissionsTableName));
        }
        return permissions;
    }


}
