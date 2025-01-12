/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedNonTmpGameInfo;
import de.timesnake.database.util.game.DbNonTmpGame;
import de.timesnake.library.basic.util.Availability;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbCachedNonTmpGame extends DbCachedGame implements DbNonTmpGame {

  protected DbCachedNonTmpGame(de.timesnake.database.core.game.DbNonTmpGame database) {
    super(database, new DbCachedNonTmpGameInfo(database.getInfo()));
  }

  @Override
  protected de.timesnake.database.core.game.DbNonTmpGame getDatabase() {
    return (de.timesnake.database.core.game.DbNonTmpGame) super.getDatabase();
  }

  @NotNull
  @Override
  public DbCachedNonTmpGameInfo getInfo() {
    return (DbCachedNonTmpGameInfo) super.getInfo();
  }

  @Nullable
  @Override
  public Integer getMaxPlayers() {
    return getInfo().getMaxPlayers();
  }

  @Override
  public void setMaxPlayers(int maxPlayers) {
    getInfo().setMaxPlayers(maxPlayers);
  }

  @NotNull
  @Override
  public Availability getMapAvailability() {
    return getInfo().getMapAvailability();
  }

  @Override
  public void setMapsAvailability(Availability maps) {
    getInfo().setMapsAvailability(maps);
  }

  @NotNull
  @Override
  public Availability getKitAvailability() {
    return getInfo().getKitAvailability();
  }

  @Override
  public void setKitsAvailability(Availability kits) {
    getInfo().setKitsAvailability(kits);
  }

  @Override
  public boolean hasStatistics() {
    return getInfo().hasStatistics();
  }

  @Override
  public void setStatistics(boolean statistics) {
    getInfo().setStatistics(statistics);
  }

  @Nullable
  @Override
  public String getTexturePackLink() {
    return getInfo().getTexturePackLink();
  }

  @Override
  public void setTexturePackLink(String texturePack) {
    getInfo().setTexturePackLink(texturePack);
  }

  @Override
  public Boolean hasTexturePack() {
    return getInfo().hasTexturePack();
  }

  @Nullable
  @Override
  public String getTexturePackHash() {
    return getInfo().getTexturePackHash();
  }

  @Override
  public void setTexturePackHash(String hash) {
    getInfo().setTexturePackHash(hash);
  }

  @NotNull
  @Override
  public Availability getOldPvPAvailability() {
    return getInfo().getOldPvPAvailability();
  }

  @Override
  public void setOldPvPAvailability(Availability availability) {
    getInfo().setOldPvPAvailability(availability);
  }

  @Override
  public boolean exists() {
    return getInfo().exists();
  }

  @NotNull
  @Override
  public String getName() {
    return getInfo().getName();
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return getInfo().getDisplayName();
  }

  @Override
  public void setDisplayName(String displayName) {
    getInfo().setDisplayName(displayName);
  }

  @Override
  @Deprecated
  @NotNull
  public String getChatColorName() {
    return getInfo().getChatColorName();
  }

  @Override
  @Deprecated
  public void setChatColorName(String chatColorName) {
    getInfo().setChatColorName(chatColorName);
  }

  @NotNull
  @Override
  public ExTextColor getTextColor() {
    return getInfo().getTextColor();
  }

  @Override
  public void setTextColor(ExTextColor color) {
    getInfo().setTextColor(color);
  }

  @NotNull
  @Override
  public String getItemName() {
    return getInfo().getItemName();
  }

  @Override
  public void setItem(String itemName) {
    getInfo().setItem(itemName);
  }

  @Nullable
  @Override
  public String getHeadLine() {
    return getInfo().getHeadLine();
  }

  @Override
  public void setHeadLine(String headLine) {
    getInfo().setHeadLine(headLine);
  }

  @NotNull
  @Override
  public Integer getSlot() {
    return getInfo().getSlot();
  }

  @Override
  public void setSlot(int slot) {
    getInfo().setSlot(slot);
  }

  @Override
  public boolean isEnabled() {
    return getInfo().isEnabled();
  }

  @Override
  public void setEnabled(boolean enabled) {
    getInfo().setEnabled(enabled);
  }

  @Override
  public boolean isCreationRequestable() {
    return getInfo().isCreationRequestable();
  }

  @Override
  public void setCreationRequestable(Boolean creationRequestable) {
    getInfo().setCreationRequestable(creationRequestable);
  }

  @Override
  public boolean isOwnable() {
    return getInfo().isOwnable();
  }

  @Override
  public void setOwnable(Boolean ownable) {
    getInfo().setOwnable(ownable);
  }

  @NotNull
  @Override
  public DbNonTmpGame toDatabase() {
    return getDatabase();
  }

  @NotNull
  @Override
  public DbNonTmpGame toLocal() {
    return getDatabase().toLocal();
  }
}
