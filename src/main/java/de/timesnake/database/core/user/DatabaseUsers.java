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

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class DatabaseUsers extends DatabaseConnector implements de.timesnake.database.util.user.DatabaseUsers {

    private final InfosTable infosTable;
    private final PunishmentsTable punishmentsTable;
    private final MailsTable mailsTable;
    private final DisplayGroupsTable displayGroupsTable;

    private final String infoTableName;
    private final String punishmentTableName;
    private final String mailsTableName;
    private final String displayGroupsTableName;

    public DatabaseUsers(String name, String url, String user, String password, String infoTable,
                         String punishmentTable, String mailsTable, String displayGroupsTable) {
        super(name, url, user, password, DatabaseManager.USERS_MAX_IDLE_CONNECTIONS);
        this.infoTableName = infoTable;
        this.punishmentTableName = punishmentTable;
        this.mailsTableName = mailsTable;
        this.displayGroupsTableName = displayGroupsTable;

        this.infosTable = new InfosTable(this, this.infoTableName);
        this.punishmentsTable = new PunishmentsTable(this, this.punishmentTableName);
        this.mailsTable = new MailsTable(this, this.mailsTableName);
        this.displayGroupsTable = new DisplayGroupsTable(this, this.displayGroupsTableName);
    }

    @Override
    public void createTables() {
        this.infosTable.create();
        this.punishmentsTable.create();
        this.mailsTable.create();
        this.displayGroupsTable.create();
    }

    @Override
    public void backupTables() {
        this.infosTable.backup();
        this.punishmentsTable.backup();
        this.mailsTable.backup();
        this.displayGroupsTable.backup();
    }

    @Override
    public void addUser(UUID uuid, String name, String permGroup, String server) {
        if (!this.infosTable.containsPlayer(uuid)) {
            this.infosTable.addPlayer(uuid, name, permGroup, server);
        }
        if (!this.punishmentsTable.contains(uuid)) {
            this.punishmentsTable.addPlayer(uuid, name);
        }
    }

    @Nullable
    @Override
    public DbUser getUserByDiscordId(Long discordId) {
        return this.getUser(this.infosTable.getFirst(Column.User.UUID, new TableEntry<>(discordId, Column.User.DISCORD_ID)));
    }

    @NotNull
    @Override
    public Collection<DbUser> getUsers() {
        Collection<DbUser> users = new HashSet<>();
        for (UUID uuid : this.getUsersUuid()) {
            users.add(this.getUser(uuid));
        }
        return users;
    }

    @Nullable
    @Override
    public DbUser getUser(UUID uuid) {
        if (uuid != null) {
            return new DbUser(this, uuid, this.infoTableName, this.punishmentTableName, this.mailsTableName,
                    this.punishmentsTable, this.mailsTable, this.displayGroupsTable);
        }
        return null;
    }

    @Nullable
    @Override
    public DbUser getUser(String name) {
        return new DbUser(this, this.infosTable.getUniqueIdFromName(name), this.infoTableName,
                this.punishmentTableName, this.mailsTableName, this.punishmentsTable, this.mailsTable,
                this.displayGroupsTable);
    }

    @Override
    public boolean containsUser(UUID uuid) {
        if (uuid != null) {
            return this.infosTable.containsPlayer(uuid);
        }
        return false;
    }

    @Override
    public boolean containsUser(String name) {
        return this.infosTable.containsPlayer(name);
    }

    @NotNull
    @Override
    public Collection<UUID> getUsersUuid() {
        return this.infosTable.getPlayerUniqueIds();
    }

    @NotNull
    @Override
    public Collection<String> getUsersName() {
        return this.infosTable.getPlayerNames();
    }


}
