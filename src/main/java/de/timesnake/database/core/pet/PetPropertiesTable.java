/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.pet;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PetPropertiesTable extends TableDDL {

  protected PetPropertiesTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Pet.OWNER_UUID, Column.Pet.PROPERTY_PET_ID, Column.Pet.PROPERTY_KEY);
    this.addColumn(Column.Pet.PROPERTY_VALUE);
    this.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  public Map<String, String> getProperties(UUID ownerUuid, Integer petId) {
    return this.get(Set.of(Column.Pet.PROPERTY_KEY, Column.Pet.PROPERTY_VALUE),
            new Entry<>(ownerUuid, Column.Pet.OWNER_UUID), new Entry<>(petId, Column.Pet.PET_ID)).stream()
        .collect(Collectors.toMap(e -> e.get(Column.Pet.PROPERTY_KEY), e -> e.get(Column.Pet.PROPERTY_VALUE)));
  }

  public String getProperty(UUID ownerUuid, Integer petId, String key) {
    return this.getFirst(Column.Pet.PROPERTY_VALUE, new Entry<>(ownerUuid, Column.Pet.OWNER_UUID),
        new Entry<>(petId, Column.Pet.PET_ID), new Entry<>(key, Column.Pet.PROPERTY_KEY));
  }

  public void setPetProperty(UUID ownerUuid, Integer petId, String key, String value) {
    this.set(value, Column.Pet.PROPERTY_VALUE, new Entry<>(ownerUuid, Column.Pet.OWNER_UUID),
        new Entry<>(petId, Column.Pet.PET_ID), new Entry<>(key, Column.Pet.PROPERTY_KEY));
  }

  public void removePetProperties(UUID ownerUuid, Integer petId) {
    this.deleteEntry(new Entry<>(ownerUuid, Column.Pet.OWNER_UUID), new Entry<>(petId, Column.Pet.PET_ID));
  }

  public void removePetProperty(UUID ownerUuid, Integer petId, String key) {
    this.deleteEntry(new Entry<>(ownerUuid, Column.Pet.OWNER_UUID), new Entry<>(petId, Column.Pet.PET_ID),
        new Entry<>(key, Column.Pet.PROPERTY_KEY));
  }
}
