/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.pet;

import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;
import java.util.UUID;

public class DatabasePets extends DatabaseConnector implements de.timesnake.database.util.pet.DatabasePets {

  private final UserPetsTable userPetsTable;

  private final String userPetsTableName;

  public DatabasePets(String name, String url, String options, String user, String password, String userPetsTableName) {
    super(name, url, options, user, password);

    this.userPetsTableName = userPetsTableName;

    this.userPetsTable = new UserPetsTable(this, this.userPetsTableName);
  }

  @Override
  public void createTables() {
    this.userPetsTable.create();
  }

  @Override
  public void backupTables() {
    this.userPetsTable.backup();
  }

  @Override
  public Collection<DbUserPet> getUserPets() {
    return this.userPetsTable.getUserPets();
  }

  @Override
  public Collection<DbUserPet> getPetsOfOwner(UUID uuid) {
    return this.userPetsTable.getPetsOfOwner(uuid);
  }

  @Override
  public de.timesnake.database.util.pet.DbUserPet getPet(UUID ownerUuid, Integer petId) {
    return this.userPetsTable.getPet(ownerUuid, petId);
  }
}
