/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.Availability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbGameInfo extends DbGameInfoBasis {

  @Nullable
  Integer getMaxPlayers();

  @NotCached
  void setMaxPlayers(int maxPlayers);

  @NotNull
  Availability getMapAvailability();

  @NotCached
  void setMapsAvailability(Availability maps);

  @NotNull
  Availability getKitAvailability();

  @NotCached
  void setKitsAvailability(Availability kits);

  boolean hasStatistics();

  @NotCached
  void setStatistics(boolean statistics);

  @Nullable
  String getTexturePackLink();

  @NotCached
  void setTexturePackLink(String texturePack);

  Boolean hasTexturePack();

  @Nullable
  String getTexturePackHash();

  @NotCached
  void setTexturePackHash(String hash);

  @NotNull
  Availability getOldPvPAvailability();

  @NotCached
  void setOldPvPAvailability(Availability availability);

  @NotNull
  @Override
  DbGameInfo toDatabase();

  @NotNull
  @Override
  DbGameInfo toLocal();
}
