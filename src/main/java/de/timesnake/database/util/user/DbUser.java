/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.user;

import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.database.util.server.DbLobbyServer;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.library.basic.util.Status;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbUser extends DbPlayer, DbCached<DbUser> {

  @NotCached
  void setPunishment(Type.Punishment type, LocalDateTime date, Duration duration,
      String castigator, String reason);

  /**
   * Checks if the user has a punishment
   * <p>
   * If punishments-table contains the user-uuid
   *
   * @return true if the user has a punishment
   */
  @NotCached
  boolean hasPunishment();

  /**
   * Gets the user punishment
   *
   * @return the {@link DbPunishment}
   */
  @NotCached
  @NotNull
  DbPunishment getPunishment();

  /**
   * Sets the user punishment
   *
   * @param punishment The {@link DbPunishment} to set
   */
  @NotCached
  void setPunishment(DbPunishment punishment);

  /**
   * Gets the user prefix
   *
   * @return the prefix
   */
  @Nullable
  String getPrefix();

  /**
   * Sets the user prefix
   *
   * @param prefix The prefix to set
   */
  void setPrefix(String prefix);

  /**
   * Gets the user suffix
   *
   * @return the suffix
   */
  @Nullable
  String getSuffix();

  /**
   * Sets the user suffix
   *
   * @param suffix The suffix to set
   */
  void setSuffix(String suffix);

  /**
   * Gets the user nick
   *
   * @return the nick
   */
  @Nullable
  String getNick();

  /**
   * Sets the user nick
   *
   * @param nick The nick to set
   */
  void setNick(String nick);

  /**
   * Checks if the user has a alias
   * <p>
   * If aliases-table contains the user-uuid
   *
   * @return true if the user has aliases
   */
  boolean hasAliases();

  /**
   * Adds a permission to the user
   *
   * @param permission The permission to set
   * @param mode       The {@link Status.Permission} to set
   * @param servers    The servers to set
   */
  @NotCached
  void addPermission(String permission, Status.Permission mode, String... servers);

  /**
   * Adds a permission to the user
   *
   * @param permission  The permission to set
   * @param mode        The {@link Status.Permission} to set
   * @param servers     The servers to set
   * @param syncExecute
   */
  @NotCached
  void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute,
      String... servers);


  /**
   * Checks if the user has the permission
   *
   * @param permission The name to permission to check
   * @return true if the user has the permission
   */
  @NotCached
  boolean hasPermission(String permission);

  /**
   * Gets the {@link DbPermission} from the user
   *
   * @param permission The name of the permission to get
   * @return the {@link DbPermission}
   */
  @Nullable
  @NotCached
  DbPermission getPermission(String permission);

  /**
   * Gets all permissions from the user
   *
   * @return a {@link DbPermission}-{@link List}
   */
  @NotCached
  @NotNull
  Collection<DbPermission> getPermissions();

  /**
   * Removes a permission from the user
   *
   * @param permission The name of the permission to remove
   */
  @NotCached
  void removePermission(String permission);

  /**
   * Removes a permission from the user
   *
   * @param permission  The name of the permission to remove
   * @param syncExecute The object to execute sync
   */
  @NotCached
  void removePermission(String permission, SyncExecute syncExecute);

  // display group

  @NotCached
  @NotNull
  Collection<String> getDisplayGroupNames();

  @NotCached
  @NotNull
  Collection<DbDisplayGroup> getDisplayGroups();

  @NotCached
  void addDisplayGroup(String name);

  @NotCached
  void removeDisplayGroup(String name);

  @NotCached
  void clearDisplayGroups();

  /**
   * Gets the status of the user
   *
   * @return the {@link Status.User}
   */
  @NotNull
  Status.User getStatus();

  /**
   * Sets the status of the user
   *
   * @param status The {@link Status.User} to set
   */
  @NotCached
  void setStatus(Status.User status);

  boolean isService();

  @NotCached
  void setService(boolean service);

  boolean enabledAntiCheatMessages();

  boolean isAirMode();

  @NotCached
  void setAirMode(boolean airMode);

  /**
   * Gets the task of the user
   *
   * @return the task
   */
  @Nullable
  String getTask();

  /**
   * Sets the task of the user
   *
   * @param task The task to set
   */
  @NotCached
  void setTask(String task);

  /**
   * Gets the name of the team of the user
   *
   * @return the team-name
   */
  @Nullable
  String getTeamName();

  /**
   * Checks if the user has a permission-group
   * <p>
   * If permission-group is not null
   *
   * @return true if the user has a permission-group
   */
  @NotCached
  boolean hasPermGroup();

  /**
   * Gets the permission-group of the user
   *
   * @return the {@link DbPermGroup}
   */
  @Nullable
  DbPermGroup getPermGroup();

  /**
   * Sets the permission-group of the user
   *
   * @param permGroup The name of the permission-group to set
   */
  @NotCached
  void setPermGroup(String permGroup);

  /**
   * Removes the permission-group of the user
   * <p>
   * Sets the permission-group to null
   */
  @NotCached
  void removePermGroup();

  /**
   * Removes the permission-group of the user
   * <p>
   * Sets the permission-group to null
   */
  @NotCached
  void removePermGroup(SyncExecute syncExecute);

  /**
   * Gets the server on which the user is online
   *
   * @return the {@link DbServer}
   */
  @Nullable
  DbServer getServer();

  /**
   * Sets the server of the user
   *
   * @param server The name of the server to set
   */
  @NotCached
  void setServer(String server);

  /**
   * Gets the server on which the user was last
   *
   * @return the last {@link DbServer}
   */
  @Nullable
  DbServer getServerLast();

  /**
   * Sets the last server of the user
   *
   * @param serverLast The name of the server to set
   */
  @NotCached
  void setServerLast(String serverLast);

  /**
   * Gets the lobby-server on which the user is online or was last
   *
   * @return {@link DbLobbyServer}
   */
  @Nullable
  DbLobbyServer getServerLobby();

  /**
   * Sets the lobby of the user
   *
   * @param serverLobby The name of the lobby to set
   */
  @NotCached
  void setServerLobby(String serverLobby);

  /**
   * Sets the name of the user
   *
   * @param name The name to set
   */
  @NotCached
  void setName(String name);

  void setAntiCheatMessages(boolean enable);

  /**
   * Sets the status of the user
   *
   * @param status             The {@link Status.User} to set
   * @param sendChannelMessage Set true to send a channel-message
   */
  @NotCached
  void setStatus(Status.User status, boolean sendChannelMessage);

  /**
   * Sets the task of the user
   *
   * @param task               The task to set
   * @param sendChannelMessage Set true to send a channel-message
   */
  @NotCached
  void setTask(String task, boolean sendChannelMessage);

  /**
   * Sets the team of the user
   *
   * @param team The name of the team to set
   */
  @NotCached
  void setTeam(String team);

  /**
   * Sets the permission-group of the user
   *
   * @param permGroup   The name of the permission-group to set
   * @param syncExecute The code that run sync
   */
  @NotCached
  void setPermGroup(String permGroup, SyncExecute syncExecute);

  @NotCached
  void agreePrivacyPolicy(LocalDateTime dateTime);

  @NotCached
  void disagreePrivacyPolicy();

  @Nullable
  LocalDateTime getPrivacyPolicyDateTime();

  boolean agreedPrivacyPolicy();

  @Nullable
  Long getDiscordId();

  @NotCached
  void setDiscordId(Long id);

  /**
   * Removes the user from all tables, punishment excluded
   */
  @NotCached
  void deleteEntries();

  /**
   * Gets the kit of the user
   *
   * @return the id of the kit
   */
  @Nullable
  Integer getKit();

  /**
   * Sets the kit of the user
   *
   * @param kitName The id of the kit to set
   */
  @NotCached
  void setKit(Integer kitName);

  //coins

  /**
   * Gets the coins-value of the user
   *
   * @return the value of the coins
   */
  float getCoins();

  /**
   * Sets the coins-value of the user
   *
   * @param coins The value of the coins to set
   */
  @NotCached
  void setCoins(float coins);

  /**
   * Adds a coins-value to the coins of the user
   *
   * @param coins The value of the coins to add
   */
  @NotCached
  void addCoins(float coins);

  /**
   * Removes a coins-value from the coins of the user
   *
   * @param coins The value of the coins to remove
   */
  @NotCached
  void removeCoins(float coins);

  //support

  /**
   * Gets the created tickets of the user
   *
   * @return the {@link DbTicket}-{@link List}
   */
  @NotCached
  @NotNull
  Collection<DbTicket> getTickets();

  /**
   * Gets a ticket of the user
   *
   * @param id The id of the ticket to get
   * @return the {@link DbTicket}
   */
  @Nullable
  @NotCached
  DbTicket getTicket(Integer id);

  //mails

  /**
   * Gets all mails for the user
   *
   * @return a {@link Collection} of {@link DbUserMail}s send to the user
   */
  @NotCached
  @NotNull
  Collection<DbUserMail> getMails();

  /**
   * Gets a mail for the user with id
   *
   * @param id The id of the mail to get
   * @return {@link DbUserMail} if exists, else null
   */
  @Nullable
  @NotCached
  DbUserMail getMail(Integer id);

  /**
   * Deletes a mail for the user with id
   *
   * @param id The id of the mail to delete
   * @return true if mail existed before, else false
   */
  @NotCached
  boolean deleteMail(Integer id);


  /**
   * Adds a mail for the user
   *
   * @param senderUuid The uuid of the sender of the mail
   * @param senderName The name of the sender of the mail
   * @param message    The message of the mail
   * @return the id of the mail
   * @throws TooLongEntryException The max message length is 255
   */
  @NotCached
  Integer addMail(UUID senderUuid, String senderName, String message)
      throws TooLongEntryException;

}
