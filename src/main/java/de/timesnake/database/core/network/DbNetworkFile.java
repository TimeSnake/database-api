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

package de.timesnake.database.core.network;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DbNetworkFile extends TableQuery implements de.timesnake.database.util.network.DbNetworkFile {

    protected DbNetworkFile(DatabaseConnector databaseConnector, String nameTable, String name) {
        super(databaseConnector, nameTable, new TableEntry<>(name, Column.Network.FILE_NAME));
    }

    @Override
    public boolean exists() {
        return this.getFile() != null;
    }

    @NotNull
    @Override
    public File getFile() {
        return super.getFirstWithKey(Column.Network.FILE_PATH);
    }
}
