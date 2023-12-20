/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.pet;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.ColumnMap;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class DbCachedUserPet implements de.timesnake.database.util.pet.DbUserPet {

  private final DbUserPet pet;

  private final Integer id;
  private final UUID ownerUuid;
  private String type;
  private boolean enabled;

  public DbCachedUserPet(DbUserPet pet) {
    this.pet = pet;

    ColumnMap map = pet.getFirstWithKey(Set.of(Column.Pet.PET_TYPE, Column.Pet.ENABLED));

    this.id = pet.getPetId();
    this.ownerUuid = pet.getOwnerId();
    this.type = map.get(Column.Pet.PET_TYPE);
    this.enabled = map.get(Column.Pet.ENABLED);
  }


  @NotNull
  @Override
  public UUID getOwnerId() {
    return this.ownerUuid;
  }

  @NotNull
  @Override
  public Integer getPetId() {
    return this.id;
  }

  @NotNull
  @Override
  public String getPetType() {
    return this.type;
  }

  @Override
  public void setPetType(@NotNull String type) {
    this.type = type;
    this.pet.setPetType(type);
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    this.pet.setEnabled(enabled);
  }

  @Override
  public @NotNull DbCachedUserPet toLocal() {
    return this.pet.toLocal();
  }

  @Override
  public @NotNull DbUserPet toDatabase() {
    return this.pet;
  }
}
