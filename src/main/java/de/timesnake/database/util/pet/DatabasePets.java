/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.pet;

import java.util.Collection;
import java.util.UUID;

public interface DatabasePets {

  Collection<? extends DbUserPet> getUserPets();

  Collection<? extends DbUserPet> getPetsOfOwner(UUID uuid);

  DbUserPet getPet(UUID ownerUuid, Integer petId);
}
