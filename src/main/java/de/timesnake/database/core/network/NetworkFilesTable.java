/*
 * workspace.database-api.main
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

package de.timesnake.database.core.network;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class NetworkFilesTable extends TableDDL {

    protected NetworkFilesTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Network.FILE_NAME);

        super.addColumn(Column.Network.FILE_PATH);

        this.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
    }

    @Override
    protected void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public void addNetworkFile(String name, File filePath) {
        super.set(filePath, Column.Network.FILE_PATH, new Entry<>(name, Column.Network.FILE_NAME));
    }

    @NotNull
    public DbNetworkFile getNetworkFile(String name) {
        return new DbNetworkFile(this.databaseConnector, this.tableName, name);
    }

    public List<DbNetworkFile> getNetworkFiles() {
        List<DbNetworkFile> networkFiles = new LinkedList<>();

        for (String name : super.get(Column.Network.FILE_NAME)) {
            networkFiles.add(this.getNetworkFile(name));
        }

        return networkFiles;
    }
}
