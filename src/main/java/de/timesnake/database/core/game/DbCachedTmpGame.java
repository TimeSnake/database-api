/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedTmpGameInfo;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.util.game.DbTmpGame;
import de.timesnake.library.basic.util.Availability;
import de.timesnake.library.basic.util.DiscordChannelType;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class DbCachedTmpGame extends DbCachedGame implements
    de.timesnake.database.util.game.DbTmpGame {

  protected DbCachedTmpGame(de.timesnake.database.core.game.DbTmpGame game) {
    super(game, new DbCachedTmpGameInfo(game.getInfo()));
  }

  @NotNull
  @Override
  public DbCachedTmpGameInfo getInfo() {
    return (DbCachedTmpGameInfo) super.getInfo();
  }

  @Override
  protected de.timesnake.database.core.game.DbTmpGame getDatabase() {
    return (de.timesnake.database.core.game.DbTmpGame) super.getDatabase();
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
  public Integer getPlayerTrackingRange() {
    return getInfo().getPlayerTrackingRange();
  }

  @Override
  public void setPlayerTrackingRange(Integer playerTrackingRange) {
    getInfo().setPlayerTrackingRange(playerTrackingRange);
  }

  @Nullable
  @Override
  public Integer getMaxHealth() {
    return getInfo().getMaxHealth();
  }

  @Override
  public void setMaxHealth(Integer maxHealth) {
    getInfo().setMaxHealth(maxHealth);
  }

  @Nullable
  @Override
  public Integer getViewDistance() {
    return getInfo().getViewDistance();
  }

  @Override
  public void setViewDistance(Integer viewDistance) {
    getInfo().setViewDistance(viewDistance);
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

  @NotNull
  @Override
  public Integer getAutoStartPlayerNumber() {
    return getInfo().getAutoStartPlayerNumber();
  }

  @Override
  public void setAutoStartPlayerNumber(Integer number) {
    getInfo().setAutoStartPlayerNumber(number);
  }

  @NotNull
  @Override
  public Integer getMinPlayerNumber() {
    return getInfo().getMinPlayerNumber();
  }

  @Override
  public void setMinPlayerNumber(Integer number) {
    getInfo().setMinPlayerNumber(number);
  }

  @Override
  public boolean showSelectedKits() {
    return getInfo().showSelectedKits();
  }

  @Override
  public void setShowSelectedKits(boolean showSelectedKits) {
    getInfo().setShowSelectedKits(showSelectedKits);
  }

  @Override
  public List<Integer> getTeamSizes() {
    return getInfo().getTeamSizes();
  }

  @Override
  public void setTeamSizes(List<Integer> sizes) {
    getInfo().setTeamSizes(sizes);
  }

  @NotNull
  @Override
  public Availability getTeamMergeAvailability() {
    return getInfo().getTeamMergeAvailability();
  }

  @Override
  public void setTeamMergeAvailability(Availability availability) {
    getInfo().setTeamMergeAvailability(availability);
  }

  @Override
  public boolean isEqualTeamSizeRequired() {
    return getInfo().isEqualTeamSizeRequired();
  }

  @Override
  public void requireEqualTeamSize(boolean require) {
    getInfo().requireEqualTeamSize(require);
  }

  @Override
  public boolean hideTeams() {
    return getInfo().hideTeams();
  }

  @Override
  public void setHideTeams(boolean hide) {
    getInfo().setHideTeams(hide);
  }

  @NotNull
  @Override
  public DiscordChannelType getDiscordType() {
    return getInfo().getDiscordType();
  }

  @Override
  public void setDiscordType(DiscordChannelType type) {
    getInfo().setDiscordType(type);
  }

  @Override
  public List<String> getDescription() {
    return getInfo().getDescription();
  }

  @Override
  public void setDescription(List<String> description) {
    getInfo().setDescription(description);
  }

  @Override
  public void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio,
                      String colorName) {
    this.getDatabase().addTeam(name, rank, prefix, color, ratio, colorName);
  }

  @Override
  public void removeTeam(String name) {
    this.getDatabase().removeTeam(name);
  }

  @Nullable
  @Override
  public Integer getHighestRank() {
    return this.getDatabase().getHighestRank();
  }

  @Override
  public boolean containsTeam(String name) {
    return this.getDatabase().containsTeam(name);
  }

  @NotNull
  @Override
  public DbTeam getTeam(String name) {
    return this.getDatabase().getTeam(name);
  }

  @NotNull
  @Override
  public Collection<String> getTeamNames() {
    return this.getDatabase().getTeamNames();
  }

  @NotNull
  @Override
  public Collection<Integer> getTeamRanks() {
    return this.getDatabase().getTeamRanks();
  }

  @NotNull
  @Override
  public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
    return this.getDatabase().getTeams();
  }

  @NotNull
  @Override
  public DbTmpGame toDatabase() {
    return this.getDatabase();
  }

  @NotNull
  @Override
  public DbTmpGame toLocal() {
    return this.getDatabase().toLocal();
  }
}
