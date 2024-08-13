/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.core.game.info.DbTmpGameInfo;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.core.game.team.TeamsTable;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Availability;
import de.timesnake.library.basic.util.DiscordChannelType;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DbTmpGame extends DbGame implements de.timesnake.database.util.game.DbTmpGame {

  private Optional<TeamsTable> teamsTable;

  protected DbTmpGame(DatabaseConnector databaseConnector, String gameName, DbTmpGameInfo info) {
    super(databaseConnector, gameName, info);
  }

  @NotNull
  @Override
  public de.timesnake.database.core.game.info.DbTmpGameInfo getInfo() {
    return (de.timesnake.database.core.game.info.DbTmpGameInfo) super.getInfo();
  }

  private boolean loadTeamsTable() {
    if (this.teamsTable != null) {
      if (this.teamsTable.isEmpty()) {
        return false;
      }

      if (this.teamsTable.get() != null) {
        return true;
      }
    }

    Collection<Integer> teamSizes = this.getInfo().getTeamSizes();
    if (teamSizes.size() > 0 && !(teamSizes.size() == 1 && teamSizes.contains(0))) {
      this.teamsTable = Optional.of(
          DatabaseManager.getInstance().getGameTeams().getGameTeams(gameName));
      return true;
    } else {
      this.teamsTable = Optional.empty();
      return false;
    }
  }

  @Override
  public void createTables() {
    if (this.loadTeamsTable()) {
      this.teamsTable.get().create();
    }
    super.createTables();
  }

  @Override
  public void backup() {
    if (this.loadTeamsTable()) {
      this.teamsTable.get().backup();
    }
    super.backup();
  }

  @Override
  public void delete() {
    if (this.loadTeamsTable()) {
      this.teamsTable.get().delete();
    }
    super.delete();
  }


  @Override
  public void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio,
                      String colorName) {
    if (this.loadTeamsTable()) {
      this.teamsTable.get().addTeam(name, rank, prefix, color, ratio, colorName);
    }
  }

  @Override
  public void removeTeam(String name) {
    if (this.loadTeamsTable()) {
      this.teamsTable.get().removeTeam(name);
    }
  }

  @Nullable
  @Override
  public Integer getHighestRank() {
    if (this.loadTeamsTable()) {
      return this.teamsTable.get().getHighestRank();
    }
    return null;
  }

  @Override
  public boolean containsTeam(String name) {
    if (this.loadTeamsTable()) {
      return this.teamsTable.get().containsTeam(name);
    }
    return false;
  }

  @NotNull
  @Override
  public DbTeam getTeam(String name) {
    if (this.loadTeamsTable()) {
      return this.teamsTable.get().getTeam(name);
    }
    return null;
  }

  @NotNull
  @Override
  public Collection<String> getTeamNames() {
    if (this.loadTeamsTable()) {
      return this.teamsTable.get().getTeamNames();
    }
    return new ArrayList<>(0);
  }

  @NotNull
  @Override
  public Collection<Integer> getTeamRanks() {
    if (this.loadTeamsTable()) {
      return this.teamsTable.get().getTeamRanks();
    }
    return new ArrayList<>(0);
  }

  @NotNull
  @Override
  public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
    if (this.loadTeamsTable()) {
      return this.teamsTable.get().getTeams();
    }
    return new ArrayList<>(0);
  }

  @Nullable
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

  @Override
  @Nullable
  public Integer getViewDistance() {
    return getInfo().getViewDistance();
  }

  @Override
  public void setViewDistance(Integer viewDistance) {
    getInfo().setViewDistance(viewDistance);
  }

  @Override
  @NotNull
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
  public DiscordChannelType
  getDiscordType() {
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

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTmpGame toLocal() {
    return new DbCachedTmpGame(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTmpGame toDatabase() {
    return this;
  }

}
