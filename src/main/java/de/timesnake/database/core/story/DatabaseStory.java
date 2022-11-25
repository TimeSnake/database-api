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

package de.timesnake.database.core.story;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DatabaseStory extends DatabaseConnector implements de.timesnake.database.util.story.DatabaseStory {

    private final UserQuestTable checkpointsTable;
    private final UserBoughtTable boughtTable;


    private final String checkpointsTableName;
    private final String boughtTableName;

    public DatabaseStory(String name, String url, String options, String user, String password, String userQuestTable,
                         String boughtTableName) {
        super(name, url, options, user, password);
        this.checkpointsTableName = userQuestTable;
        this.boughtTableName = boughtTableName;
        this.checkpointsTable = new UserQuestTable(this, this.checkpointsTableName);
        this.boughtTable = new UserBoughtTable(this, this.boughtTableName);
    }

    @Override
    public void createTables() {
        this.checkpointsTable.create();
        this.boughtTable.create();
    }

    @Override
    public void backupTables() {
        this.checkpointsTable.backup();
        this.boughtTable.backup();
    }

    @NotNull
    @Override
    public DbStoryUser getUser(UUID uuid) {
        return new DbStoryUser(uuid, this.boughtTable, this.checkpointsTable);
    }

}