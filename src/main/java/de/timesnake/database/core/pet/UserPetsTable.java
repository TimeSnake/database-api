/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.pet;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class UserPetsTable extends TableDDL {

  protected UserPetsTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Pet.PET_ID);

    this.addColumn(Column.Pet.OWNER_UUID);
    this.addColumn(Column.Pet.PET_TYPE);
    this.addColumn(Column.Pet.ENABLED);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  public Collection<DbUserPet> getUserPets() {
    return this.get(Set.of(Column.Pet.OWNER_UUID, Column.Pet.PET_ID)).stream()
        .map(m -> this.getPet(m.get(Column.Pet.OWNER_UUID), m.get(Column.Pet.PET_ID)))
        .toList();
  }

  public Collection<DbUserPet> getPetsOfOwner(UUID uuid) {
    return this.get(Column.Pet.PET_ID, new Entry<>(uuid, Column.Pet.OWNER_UUID)).stream()
        .map(id -> this.getPet(uuid, id))
        .toList();
  }

  public DbUserPet getPet(UUID ownerUuid, Integer petId) {
    return new DbUserPet(this.databaseConnector, this.tableName, ownerUuid, petId);
  }
}
