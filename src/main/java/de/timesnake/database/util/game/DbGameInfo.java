/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbGameInfo extends DbGameInfoBasis {

  @Nullable
  Integer getMaxPlayers();

  @NotCached
  void setMaxPlayers(int maxPlayers);

  @NotNull
  Type.Availability getMapAvailability();

  @NotCached
  void setMapsAvailability(Type.Availability maps);

  @NotNull
  Type.Availability getKitAvailability();

  @NotCached
  void setKitsAvailability(Type.Availability kits);

  boolean hasStatistics();

  @NotCached
  void setStatistics(boolean statistics);

  @Nullable
  String getTexturePackLink();

  @NotCached
  void setTexturePackLink(String texturePack);

  Boolean hasTexturePack();

  @Nullable
  Integer getPlayerTrackingRange();

  @NotCached
  void setPlayerTrackingRange(Integer playerTrackingRange);

  @Nullable
  Integer getMaxHealth();

  @NotCached
  void setMaxHealth(Integer maxHealth);

  @Nullable
  Integer getViewDistance();

  @NotCached
  void setViewDistance(Integer viewDistance);

  @NotNull
  Type.Availability getOldPvPAvailability();

  @NotCached
  void setOldPvPAvailability(Type.Availability availability);

  @NotNull
  @Override
  DbGameInfo toDatabase();

  @NotNull
  @Override
  DbGameInfo toLocal();
}
