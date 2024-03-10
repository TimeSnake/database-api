/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.pet;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface DatabasePets {

  Collection<? extends DbUserPet> getUserPets();

  Collection<? extends DbUserPet> getPetsOfOwner(UUID uuid);

  DbUserPet getPet(UUID ownerUuid, Integer petId);

  Map<String, String> getPetProperties(UUID ownerUuid, Integer petId);

  String getPetProperty(UUID ownerUuid, Integer petId, String key);

  void setPetProperty(UUID ownerUuid, Integer petId, String key, String value);

  void removePetProperties(UUID ownerUuid, Integer petId);

  void removePetProperty(UUID ownerUuid, Integer petId, String key);
}
