/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.pet;

import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class DatabasePets extends DatabaseConnector implements de.timesnake.database.util.pet.DatabasePets {

  private final UserPetTable userPetsTable;
  private final PetPropertyTable petPropertyTable;

  private final String userPetsTableName;
  private final String petPropertiesTableName;

  public DatabasePets(String name, String url, String options, String user, String password, String userPetsTableName,
                      String petPropertiesTableName) {
    super(name, url, options, user, password);

    this.userPetsTableName = userPetsTableName;
    this.petPropertiesTableName = petPropertiesTableName;

    this.userPetsTable = new UserPetTable(this, this.userPetsTableName);
    this.petPropertyTable = new PetPropertyTable(this, this.petPropertiesTableName);
  }

  @Override
  public void createTables() {
    this.userPetsTable.create();
    this.petPropertyTable.create();
  }

  @Override
  public void saveTables() {
    this.userPetsTable.save();
    this.petPropertyTable.save();
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

  @Override
  public de.timesnake.database.util.pet.DbUserPet createPet(UUID ownerUuid, String type) {
    Integer id = this.userPetsTable.createPet(ownerUuid, type);
    if (id == null) {
      return null;
    }
    return this.getPet(ownerUuid, id);
  }

  @Override
  public void deletePet(UUID ownerUuid, Integer petId) {
    this.userPetsTable.deletePet(ownerUuid, petId);
  }

  @Override
  public Map<String, String> getPetProperties(UUID ownerUuid, Integer petId) {
    return this.petPropertyTable.getProperties(ownerUuid, petId);
  }

  @Override
  public String getPetProperty(UUID ownerUuid, Integer petId, String key) {
    return this.petPropertyTable.getProperty(ownerUuid, petId, key);
  }

  @Override
  public void setPetProperty(UUID ownerUuid, Integer petId, String key, String value) {
    this.petPropertyTable.setPetProperty(ownerUuid, petId, key, value);
  }

  @Override
  public void removePetProperties(UUID ownerUuid, Integer petId) {
    this.petPropertyTable.removePetProperties(ownerUuid, petId);
  }

  @Override
  public void removePetProperty(UUID ownerUuid, Integer petId, String key) {
    this.petPropertyTable.removePetProperty(ownerUuid, petId, key);
  }
}
