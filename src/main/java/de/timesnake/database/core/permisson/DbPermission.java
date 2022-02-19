package de.timesnake.database.core.permisson;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;

public class DbPermission extends TableQuery implements de.timesnake.database.util.permission.DbPermission {

    public DbPermission(DatabaseConnector databaseConnector, int id, String nameTable) {
        super(databaseConnector, nameTable, new TableEntry<>(id, Column.Permission.ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Permission.ID) != null;
    }

    @Override
    public Integer getId() {
        return (Integer) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Permission.PERMISSION);
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Permission.PERMISSION);
    }

    @Override
    public Status.Permission getMode() {
        return super.getFirstWithKey(Column.Permission.MODE);
    }

    @Override
    public void setServers(Collection<String> servers) {
        super.setWithKey(servers != null ? new DbStringArrayList(servers) : null, Column.Permission.SERVER);
    }

    @Override
    public void setMode(Status.Permission mode) {
        super.setWithKey(mode, Column.Permission.MODE);
    }

    @Override
    public Collection<String> getServers() {
        return super.getFirstWithKey(Column.Permission.SERVER);
    }


}
