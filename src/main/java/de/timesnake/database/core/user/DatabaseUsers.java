/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class DatabaseUsers extends DatabaseConnector implements
    de.timesnake.database.util.user.DatabaseUsers {

  private final InfoTable infoTable;
  private final PunishmentTable punishmentTable;
  private final MailTable mailTable;
  private final DisplayGroupTable displayGroupTable;

  private final String infoTableName;
  private final String punishmentTableName;
  private final String mailsTableName;
  private final String displayGroupsTableName;

  public DatabaseUsers(String name, String url, String options, String user, String password,
      String infoTable,
      String punishmentTable, String mailsTable, String displayGroupsTable) {
    super(name, url, options, user, password, DatabaseManager.USERS_MAX_IDLE_CONNECTIONS);
    this.infoTableName = infoTable;
    this.punishmentTableName = punishmentTable;
    this.mailsTableName = mailsTable;
    this.displayGroupsTableName = displayGroupsTable;

    this.infoTable = new InfoTable(this, this.infoTableName);
    this.punishmentTable = new PunishmentTable(this, this.punishmentTableName);
    this.mailTable = new MailTable(this, this.mailsTableName);
    this.displayGroupTable = new DisplayGroupTable(this, this.displayGroupsTableName);
  }

  @Override
  public void createTables() {
    this.infoTable.create();
    this.punishmentTable.create();
    this.mailTable.create();
    this.displayGroupTable.create();
  }

  @Override
  public void saveTables() {
    this.infoTable.save();
    this.punishmentTable.save();
    this.mailTable.save();
    this.displayGroupTable.save();
  }

  @Override
  public void addUser(UUID uuid, String name, String permGroup, String server) {
    if (!this.infoTable.containsPlayer(uuid)) {
      this.infoTable.addPlayer(uuid, name, permGroup, server);
    }
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
      return new DbUser(this, uuid, this.infoTableName, this.punishmentTableName,
          this.mailsTableName,
          this.punishmentTable, this.mailTable, this.displayGroupTable);
    }
    return null;
  }

  @Nullable
  @Override
  public DbUser getUser(String name) {
    return new DbUser(this, this.infoTable.getUniqueIdFromName(name), this.infoTableName,
        this.punishmentTableName, this.mailsTableName, this.punishmentTable,
        this.mailTable,
        this.displayGroupTable);
  }

  @Override
  public boolean containsUser(UUID uuid) {
    if (uuid != null) {
      return this.infoTable.containsPlayer(uuid);
    }
    return false;
  }

  @Override
  public boolean containsUser(String name) {
    return this.infoTable.containsPlayer(name);
  }

  @NotNull
  @Override
  public Collection<UUID> getUsersUuid() {
    return this.infoTable.getPlayerUniqueIds();
  }

  @NotNull
  @Override
  public Collection<String> getUsersName() {
    return this.infoTable.getPlayerNames();
  }


}
