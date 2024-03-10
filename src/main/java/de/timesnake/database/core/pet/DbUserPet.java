/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.pet;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class DbUserPet extends TableQuery implements de.timesnake.database.util.pet.DbUserPet {

  protected DbUserPet(DatabaseConnector databaseConnector, String tableName, UUID userUuid, Integer petId) {
    super(databaseConnector, tableName, true, new Entry<>(userUuid, Column.Pet.OWNER_UUID), new Entry<>(petId,
        Column.Pet.PET_ID));
  }

  @Override
  public boolean exists() {
    return this.getFirstWithKey(Column.Pet.PET_ID) != null;
  }

  @Override
  public @NotNull UUID getOwnerId() {
    return (UUID) this.primaryEntries.get(0).getValue();
  }

  @Override
  public @NotNull Integer getPetId() {
    return (Integer) this.primaryEntries.get(1).getValue();
  }

  @Override
  public @NotNull String getPetType() {
    return this.getFirstWithKey(Column.Pet.PET_TYPE);
  }

  @Override
  public void setPetType(@NotNull String type) {
    this.setWithKey(type, Column.Pet.PET_TYPE);
  }

  @Override
  public boolean isEnabled() {
    return this.getFirstWithKey(Column.Pet.ENABLED);
  }

  @Override
  public void setEnabled(boolean enabled) {
    this.setWithKey(enabled, Column.Pet.ENABLED);
  }

  @Override
  public Map<@NotNull String, @Nullable String> getProperties() {
    return Database.getPets().getPetProperties(this.getOwnerId(), this.getPetId());
  }

  @Override
  public @Nullable String getProperty(@NotNull String key) {
    return Database.getPets().getPetProperty(this.getOwnerId(), this.getPetId(), key);
  }

  @Override
  public void setProperty(@NotNull String key, @Nullable String value) {
    Database.getPets().setPetProperty(this.getOwnerId(), this.getPetId(), key, value);
  }

  @Override
  public void removeProperties() {
    Database.getPets().removePetProperties(this.getOwnerId(), this.getPetId());
  }

  @Override
  public void removeProperty(@NotNull String key) {
    Database.getPets().removePetProperty(this.getOwnerId(), this.getPetId(), key);
  }

  @Override
  public @NotNull DbCachedUserPet toLocal() {
    return new DbCachedUserPet(this);
  }

  @Override
  public @NotNull DbUserPet toDatabase() {
    return this;
  }
}
