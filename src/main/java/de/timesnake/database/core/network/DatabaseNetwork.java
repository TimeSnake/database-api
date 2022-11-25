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

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class DatabaseNetwork extends DatabaseConnector implements de.timesnake.database.util.network.DatabaseNetwork {

    private final NetworkFilesTable networkFilesTable;

    private final String networkFilesTableName;

    public DatabaseNetwork(String name, String url, String options, String user, String password, String networkFilesTableName) {
        super(name, url, options, user, password);
        this.networkFilesTableName = networkFilesTableName;
        this.networkFilesTable = new NetworkFilesTable(this, this.networkFilesTableName);
    }

    @Override
    public void createTables() {
        networkFilesTable.create();
    }

    @Override
    public void backupTables() {
        networkFilesTable.backup();
    }

    @Override
    public void addNetworkFile(String name, File filePath) {
        this.networkFilesTable.addNetworkFile(name, filePath);
    }

    @NotNull
    @Override
    public DbNetworkFile getNetworkFile(String name) {
        return this.networkFilesTable.getNetworkFile(name);
    }

    @NotNull
    @Override
    public List<DbNetworkFile> getNetworkFiles() {
        return this.networkFilesTable.getNetworkFiles();
    }
}
