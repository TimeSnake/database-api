/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.channel.core.ServerChannel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.database.util.server.DbLobbyServer;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.database.util.user.DbUserMail;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class DbUser extends DbPlayer implements de.timesnake.database.util.user.DbUser {

  private final PunishmentsTable punishmentsTable;
  private final MailsTable mailsTable;
  private final DbDisplayGroupUser dbDisplayGroupUser;

  private final String punishmentTableName;
  private final String mailsTableName;

  public DbUser(DatabaseConnector databaseConnector, UUID uuid, String infoTable,
      String punishmentsTableName,
      String mailsTableName, PunishmentsTable punishmentsTable, MailsTable mailsTable,
      DisplayGroupsTable displayGroupsTable) {
    super(databaseConnector, uuid, infoTable);

    this.punishmentsTable = punishmentsTable;
    this.mailsTable = mailsTable;

    this.punishmentTableName = punishmentsTableName;
    this.mailsTableName = mailsTableName;

    this.dbDisplayGroupUser = displayGroupsTable.getUser(this.getUniqueId());
  }

  //punishment

  @Override
  public void setPunishment(Type.Punishment type, LocalDateTime date, Duration duration,
      String castigator, String reason) {
    this.punishmentsTable.setPunishment(super.getUniqueId(), this.getName(), type, date,
        duration, castigator, reason);
  }

  @Override
  public boolean hasPunishment() {
    return this.punishmentsTable.contains(super.getUniqueId());
  }

  @NotNull
  @Override
  public de.timesnake.database.util.user.DbPunishment getPunishment() {
    return new DbPunishment(this.databaseConnector, super.getUniqueId(),
        this.punishmentTableName);
  }

  @Override
  public void setPunishment(de.timesnake.database.util.user.DbPunishment punishment) {
    this.punishmentsTable.setPunishment(punishment);
  }

  //alias

  @Nullable
  @Override
  public String getPrefix() {
    return super.getFirstWithKey(Column.User.PREFIX);
  }

  @Override
  public void setPrefix(String prefix) {
    super.setWithKey(prefix, Column.User.PREFIX,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.ALIAS)));
  }

  @Nullable
  @Override
  public String getSuffix() {
    return super.getFirstWithKey(Column.User.SUFFIX);
  }

  @Override
  public void setSuffix(String suffix) {
    super.setWithKey(suffix, Column.User.SUFFIX,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.ALIAS)));
  }

  @Nullable
  @Override
  public String getNick() {
    return super.getFirstWithKey(Column.User.NICK);
  }

  @Override
  public void setNick(String nick) {
    super.setWithKey(nick, Column.User.NICK,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.ALIAS)));
  }

  @Override
  public boolean hasAliases() {
    return super.getFirstWithKey(Column.User.PREFIX) != null
        || super.getFirstWithKey(Column.User.SUFFIX) != null
        || super.getFirstWithKey(Column.User.NICK) != null;
  }

  //permissions

  @Override
  public void addPermission(String permission, Status.Permission mode, String... servers) {
    Database.getPermissions()
        .addPermission(this.getUniqueId().toString(), permission, mode, servers);
  }

  @Override
  public void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute,
      String... servers) {
    Database.getPermissions()
        .addPermission(this.getUniqueId().toString(), permission, mode, syncExecute,
            servers);
  }

  @Override
  public boolean hasPermission(String permission) {
    return Database.getPermissions()
        .containsPermission(super.getUniqueId().toString(), permission);
  }

  @Nullable
  @Override
  public DbPermission getPermission(String permission) {
    return Database.getPermissions().getPermission(super.getUniqueId().toString(), permission);
  }

  @NotNull
  @Override
  public Collection<DbPermission> getPermissions() {
    return Database.getPermissions().getPermissions(super.getUniqueId().toString());
  }

  @Override
  public void removePermission(String permission) {
    Database.getPermissions().deletePermission(this.getUniqueId().toString(), permission);
  }

  @Override
  public void removePermission(String permission, SyncExecute syncExecute) {
    Database.getPermissions()
        .deletePermission(this.getUniqueId().toString(), permission, syncExecute);
  }

  // display group
  @NotNull
  @Override
  public Collection<String> getDisplayGroupNames() {
    return this.dbDisplayGroupUser.getDisplayGroupNames();
  }

  @NotNull
  @Override
  public Collection<DbDisplayGroup> getDisplayGroups() {
    return this.dbDisplayGroupUser.getDisplayGroups();
  }

  @Override
  public void addDisplayGroup(String name) {
    this.dbDisplayGroupUser.addDisplayGroup(name);
  }

  @Override
  public void removeDisplayGroup(String name) {
    this.dbDisplayGroupUser.removeDisplayGroup(name);
  }

  @Override
  public void clearDisplayGroups() {
    this.dbDisplayGroupUser.clearDisplayGroups();
  }

  //info

  @NotNull
  @Override
  public Status.User getStatus() {
    Status.User status = super.getFirstWithKey(Column.User.STATUS);
    return status != null ? status : Status.User.OFFLINE;
  }

  @Override
  public void setStatus(Status.User status) {
    super.setWithKey(status, Column.User.STATUS,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.STATUS, status)));
  }

  @Override
  public boolean isService() {
    return super.getFirstWithKey(Column.User.SERVICE);
  }

  @Override
  public void setService(boolean service) {
    super.setWithKey(service, Column.User.SERVICE,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.SERVICE, service)));
  }

  @Override
  public boolean enabledAntiCheatMessages() {
    return super.getFirstWithKey(Column.User.ANTI_CHEAT_MESSAGES);
  }

  @Override
  public boolean isAirMode() {
    return super.getFirstWithKey(Column.User.AIR_MODE);
  }

  @Override
  public void setAirMode(boolean airMode) {
    super.setWithKey(airMode, Column.User.AIR_MODE);
  }

  @Nullable
  @Override
  public String getTask() {
    return super.getFirstWithKey(Column.User.TASK);
  }

  @Override
  public void setTask(String task) {
    super.setWithKey(task, Column.User.TASK,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.TASK, task)));
  }

  @Nullable
  @Override
  public String getTeamName() {
    return super.getFirstWithKey(Column.User.TEAM);
  }

  @Override
  public boolean hasPermGroup() {
    return Database.getGroups()
        .containsPermGroup(super.getFirstWithKey(Column.User.PERM_GROUP));
  }

  @Nullable
  @Override
  public DbPermGroup getPermGroup() {
    String groupName = super.getFirstWithKey(Column.User.PERM_GROUP);
    if (groupName != null) {
      return Database.getGroups().getPermGroup(groupName);
    }
    return null;
  }

  @Override
  public void setPermGroup(String permGroup) {
    super.setWithKey(permGroup, Column.User.PERM_GROUP);
  }

  @Override
  public void removePermGroup() {
    this.setWithKey(null, Column.User.PERM_GROUP);
  }

  @Override
  public void removePermGroup(SyncExecute syncExecute) {
    super.setWithKey(null, Column.User.PERM_GROUP, syncExecute);
  }


  @Nullable
  @Override
  public DbServer getServer() {
    return Database.getServers().getServer(super.getFirstWithKey(Column.User.SERVER));
  }

  @Override
  public void setServer(String server) {
    super.setWithKey(server, Column.User.SERVER);
  }

  @Nullable
  @Override
  public DbServer getServerLast() {
    return Database.getServers().getServer(super.getFirstWithKey(Column.User.SERVER_LAST));
  }

  @Override
  public void setServerLast(String serverLast) {
    super.setWithKey(serverLast, Column.User.SERVER_LAST);
  }

  @Nullable
  @Override
  public DbLobbyServer getServerLobby() {
    return Database.getServers().getServer(super.getFirstWithKey(Column.User.SERVER_LOBBY));
  }

  @Override
  public void setServerLobby(String serverLobby) {
    super.setWithKey(serverLobby, Column.User.SERVER_LOBBY);
  }

  @Override
  public void setName(String name) {
    super.setWithKey(name, Column.User.NAME);
  }

  @Override
  public void setAntiCheatMessages(boolean enable) {
    super.setWithKey(enable, Column.User.ANTI_CHEAT_MESSAGES);
  }

  @Override
  public void setStatus(Status.User status, boolean sendChannelMessage) {
    if (sendChannelMessage) {
      super.setWithKey(status, Column.User.STATUS,
          () -> ServerChannel.getInstance().sendMessage(
              new de.timesnake.channel.util.message.ChannelUserMessage<>(
                  this.getUniqueId(), MessageType.User.STATUS, status)));
    } else {
      super.setWithKey(status, Column.User.STATUS);
    }
  }

  @Override
  public void setTask(String task, boolean sendChannelMessage) {
    if (sendChannelMessage) {
      super.setWithKey(task, Column.User.TASK,
          () -> ServerChannel.getInstance()
              .sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
                  MessageType.User.TASK, task)));
    } else {
      super.setWithKey(task, Column.User.TASK);
    }
  }

  @Override
  public void setTeam(String team) {
    super.setWithKey(team, Column.User.TEAM,
        () -> ServerChannel.getInstance().sendMessage(new ChannelUserMessage<>(this.getUniqueId(),
            MessageType.User.TEAM, team)));
  }

  @Override
  public void setPermGroup(String permGroup, SyncExecute syncExecute) {
    super.setWithKey(permGroup, Column.User.PERM_GROUP, syncExecute);
  }

  @Override
  public void agreePrivacyPolicy(LocalDateTime dateTime) {
    super.setWithKey(dateTime, Column.User.PRIVACY_POLICY);
  }

  @Override
  public void disagreePrivacyPolicy() {
    super.setWithKey(null, Column.User.PRIVACY_POLICY);
  }

  @Override
  public LocalDateTime getPrivacyPolicyDateTime() {
    return super.getFirstWithKey(Column.User.PRIVACY_POLICY);
  }

  @Override
  public boolean agreedPrivacyPolicy() {
    return this.getPrivacyPolicyDateTime() != null;
  }

  @Nullable
  @Override
  public Long getDiscordId() {
    return super.getFirstWithKey(Column.User.DISCORD_ID);
  }

  @Override
  public void setDiscordId(Long id) {
    super.setWithKey(id, Column.User.DISCORD_ID);
  }

  @Override
  public void deleteEntries() {
    super.deleteWithKey(new Entry<>(super.getUniqueId(), Column.User.UUID));
  }

  @Nullable
  @Override
  public Integer getKit() {
    return super.getFirstWithKey(Column.User.KIT);
  }

  @Override
  public void setKit(Integer kitName) {
    super.setWithKey(kitName, Column.User.KIT);
  }

  @Override
  public void addCoins(float coins) {
    this.setCoins(this.getCoins() + coins);
  }

  @Override
  public void removeCoins(float coins) {
    this.setCoins(this.getCoins() - coins);
  }

  @Override
  public float getCoins() {
    Float coins = super.getFirstWithKey(Column.User.TIME_COINS);
    return coins != null ? coins : 0;
  }

  //coins
  @Override
  public void setCoins(float coins) {
    super.setWithKey(coins, Column.User.TIME_COINS);
  }

  //support
  @NotNull
  @Override
  public Collection<DbTicket> getTickets() {
    return Database.getSupport().getTickets(this.getUniqueId());
  }

  @Nullable
  @Override
  public DbTicket getTicket(Integer id) {
    return Database.getSupport().getTicket(id);
  }

  //mails
  @NotNull
  @Override
  public Collection<de.timesnake.database.util.user.DbUserMail> getMails() {
    return this.mailsTable.getMails(this.getUniqueId());
  }

  @Nullable
  @Override
  public DbUserMail getMail(Integer id) {
    return this.mailsTable.getMail(this.getUniqueId(), id);
  }

  @Override
  public boolean deleteMail(Integer id) {
    return this.mailsTable.removeMail(this.getUniqueId(), id);
  }

  @Override
  public Integer addMail(UUID senderUuid, String senderName, String message)
      throws TooLongEntryException {
    return this.mailsTable.addMessage(this.getUniqueId(), this.getName(), senderUuid,
        senderName, message);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.user.DbUser toLocal() {
    return new DbCachedUser(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.user.DbUser toDatabase() {
    return this;
  }

}