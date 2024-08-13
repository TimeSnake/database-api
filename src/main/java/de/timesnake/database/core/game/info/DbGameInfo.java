/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Column.Game;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Availability;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbGameInfo extends DbGameInfoBasis implements
    de.timesnake.database.util.game.DbGameInfo {

  public DbGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
    super(databaseConnector, nameTable, gameName);
  }

  @Nullable
  @Override
  public Integer getMaxPlayers() {
    return super.getFirstWithKey(Column.Game.MAX_PLAYERS);
  }

  @Override
  public void setMaxPlayers(int maxPlayers) {
    super.setWithKey(maxPlayers, Column.Game.MAX_PLAYERS);
  }

  @NotNull
  @Override
  public Availability getMapAvailability() {
    Availability availability = super.getFirstWithKey(Column.Game.MAPS);
    return availability != null ? availability : Availability.FORBIDDEN;
  }

  @Override
  public void setMapsAvailability(Availability maps) {
    super.setWithKey(maps, Column.Game.MAPS);
  }

  @NotNull
  @Override
  public Availability getKitAvailability() {
    Availability availability = super.getFirstWithKey(Column.Game.KITS);
    return availability != null ? availability : Availability.FORBIDDEN;
  }

  @Override
  public void setKitsAvailability(Availability kits) {
    super.setWithKey(kits, Column.Game.KITS);
  }

  @Override
  public boolean hasStatistics() {
    return super.getFirstWithKey(Column.Game.STATISTICS);
  }

  @Override
  public void setStatistics(boolean statistics) {
    super.setWithKey(statistics, Column.Game.STATISTICS);
  }

  @Nullable
  @Override
  public String getTexturePackLink() {
    return super.getFirstWithKey(Column.Game.TEXTURE_PACK_LINK);
  }

  @Override
  public void setTexturePackLink(String texturePack) {
    super.setWithKey(texturePack, Column.Game.TEXTURE_PACK_LINK);
  }

  @Override
  public Boolean hasTexturePack() {
    return super.getFirstWithKey(Column.Game.TEXTURE_PACK_LINK) != null;
  }

  @Nullable
  @Override
  public String getTexturePackHash() {
    return super.getFirstWithKey(Game.TEXTURE_PACK_HASH);
  }

  @Override
  public void setTexturePackHash(String hash) {
    super.setWithKey(hash, Game.TEXTURE_PACK_HASH);
  }

  @Nullable
  @Override
  public Integer getPlayerTrackingRange() {
    return super.getFirstWithKey(Column.Game.PLAYER_TRACKING_RANGE);
  }

  @Override
  public void setPlayerTrackingRange(Integer playerTrackingRange) {
    super.setWithKey(playerTrackingRange, Column.Game.PLAYER_TRACKING_RANGE);
  }

  @Nullable
  @Override
  public Integer getMaxHealth() {
    return super.getFirstWithKey(Column.Game.MAX_HEALTH);
  }

  @Override
  public void setMaxHealth(Integer maxHealth) {
    super.setWithKey(maxHealth, Column.Game.MAX_HEALTH);
  }

  @Nullable
  @Override
  public Integer getViewDistance() {
    return super.getFirstWithKey(Column.Game.VIEW_DISTANCE);
  }

  @Override
  public void setViewDistance(Integer viewDistance) {
    super.setWithKey(viewDistance, Column.Game.VIEW_DISTANCE);
  }

  @NotNull
  @Override
  public Availability getOldPvPAvailability() {
    Availability availability = super.getFirstWithKey(Game.OLD_PVP);
    return availability != null ? availability : Availability.FORBIDDEN;
  }

  @Override
  public void setOldPvPAvailability(Availability availability) {
    super.setWithKey(availability, Game.OLD_PVP);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGameInfo toDatabase() {
    return this;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbGameInfo toLocal() {
    return new DbCachedGameInfo(this);
  }
}
