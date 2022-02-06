package de.timesnake.database.core.user;

import de.timesnake.channel.api.message.ChannelUserMessage;
import de.timesnake.channel.main.NetworkChannel;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.*;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.database.util.server.DbLobbyServer;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.database.util.user.DataProtectionAgreement;
import de.timesnake.database.util.user.DbUserMail;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class DbUser extends DbPlayer implements de.timesnake.database.util.user.DbUser {

    private final PunishmentsTable punishmentsTable;
    private final MailsTable mailsTable;

    private final String punishmentTableName;
    private final String mailsTableName;

    public DbUser(DatabaseConnector databaseConnector, UUID uuid, String infoTable, String punishmentsTableName, String mailsTableName, PunishmentsTable punishmentsTable, MailsTable mailsTable) {
        super(databaseConnector, uuid, infoTable);

        this.punishmentsTable = punishmentsTable;
        this.mailsTable = mailsTable;

        this.punishmentTableName = punishmentsTableName;
        this.mailsTableName = mailsTableName;
    }

    @Override
    public void checkEntries() {
        UUID uuid = super.getUniqueId();
        if (!this.punishmentsTable.containsPlayer(uuid)) {
            this.punishmentsTable.addPlayer(uuid, this.getName());
        }
    }

    //punishment

    @Override
    public void setPunishment(Type.Punishment type, Date date, String castigator, String reason, String server) {
        this.punishmentsTable.setPunishment(super.getUniqueId(), this.getName(), type, date, castigator, reason, server);
    }

    @Override
    public void setPunishment(de.timesnake.database.util.user.DbPunishment punishment) {
        this.punishmentsTable.setPunishment(punishment);
    }

    @Override
    public boolean hasPunishment() {
        return this.punishmentsTable.contains(super.getUniqueId());
    }

    @Override
    public de.timesnake.database.util.user.DbPunishment getPunishment() {
        return new DbPunishment(this.databaseConnector, super.getUniqueId(), this.punishmentTableName);
    }

    //alias

    @Override
    public String getPrefix() {
        return super.getFirstWithKey(Column.User.PREFIX);
    }

    @Override
    public String getSuffix() {
        return super.getFirstWithKey(Column.User.SUFFIX);
    }

    @Override
    public String getNick() {
        return super.getFirstWithKey(Column.User.NICK);
    }

    @Override
    public void setPrefix(String prefix) {
        super.setWithKey(prefix, Column.User.PREFIX, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getAliasMessage(this.getUniqueId())));
    }

    @Override
    public void setSuffix(String suffix) {
        super.setWithKey(suffix, Column.User.SUFFIX, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getAliasMessage(this.getUniqueId())));
    }

    @Override
    public void setNick(String nick) {
        super.setWithKey(nick, Column.User.NICK, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getAliasMessage(this.getUniqueId())));
    }

    @Override
    public boolean hasAliases() {
        return super.getFirstWithKey(Column.User.PREFIX) != null || super.getFirstWithKey(Column.User.SUFFIX) != null || super.getFirstWithKey(Column.User.NICK) != null;
    }

    //permissions

    @Override
    public void addPermission(String permission, Status.Permission mode, String... servers) {
        Database.getPermissions().addPermission(this.getUniqueId().toString(), permission, mode, servers);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers) {
        Database.getPermissions().addPermission(this.getUniqueId().toString(), permission, mode, syncExecute, servers);
    }

    @Override
    public boolean hasPermission(String permission) {
        return Database.getPermissions().containsPermission(super.getUniqueId().toString(), permission);
    }

    @Override
    public DbPermission getPermission(String permission) {
        return Database.getPermissions().getPermission(super.getUniqueId().toString(), permission);
    }

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
        Database.getPermissions().deletePermission(this.getUniqueId().toString(), permission, syncExecute);
    }

    //info

    @Override
    public Status.User getStatus() {
        return super.getFirstWithKey(Column.User.STATUS);
    }

    @Override
    public boolean isService() {
        return super.getFirstWithKey(Column.User.SERVICE);
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
    public String getTask() {
        return super.getFirstWithKey(Column.User.TASK);
    }

    @Override
    public String getTeamName() {
        return super.getFirstWithKey(Column.User.TEAM);
    }

    @Override
    public boolean hasPermGroup() {
        return Database.getGroups().containsGroup(super.getFirstWithKey(Column.User.PERMGROUP));
    }

    @Override
    public DbPermGroup getPermGroup() {
        String groupName = super.getFirstWithKey(Column.User.PERMGROUP);
        if (groupName != null) {
            return Database.getGroups().getPermGroup(groupName);
        }
        return null;
    }

    @Override
    public void removePermGroup() {
        super.setWithKey(null, Column.User.PERMGROUP, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getGroupMessage(this.getUniqueId(), null)));

    }

    @Override
    public void removePermGroup(SyncExecute syncExecute) {
        super.setWithKey(null, Column.User.PERMGROUP, () -> {
            NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getGroupMessage(this.getUniqueId(), null));
            syncExecute.run();
        });
    }

    @Override
    public DbServer getServer() {
        return Database.getServers().getServer(super.getFirstWithKey(Column.User.SERVER));
    }

    @Override
    public DbServer getServerLast() {
        return Database.getServers().getServer(super.getFirstWithKey(Column.User.SERVER_LAST));
    }

    @Override
    public DbLobbyServer getServerLobby() {
        return Database.getServers().getServer(super.getFirstWithKey(Column.User.SERVER_LOBBY));
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.User.NAME);
    }

    @Override
    public void setStatus(Status.User status) {
        super.setWithKey(status, Column.User.STATUS, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getStatusMessage(this.getUniqueId(), status)));
    }

    @Override
    public void setService(boolean service) {
        super.setWithKey(service, Column.User.SERVICE, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getServiceMessage(this.getUniqueId(), service)));
    }

    @Override
    public void setAntiCheatMessages(boolean enable) {
        super.set(enable, Column.User.ANTI_CHEAT_MESSAGES);
    }

    @Override
    public void setAirMode(boolean airMode) {
        super.setWithKey(airMode, Column.User.AIR_MODE);
    }

    @Override
    public void setStatus(Status.User status, boolean sendChannelMessage) {
        if (sendChannelMessage) {
            super.setWithKey(status, Column.User.STATUS, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getStatusMessage(this.getUniqueId(), status)));
        } else {
            super.setWithKey(status, Column.User.STATUS);
        }
    }

    @Override
    public void setTask(String task) {
        super.setWithKey(task, Column.User.TASK, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getTaskMessage(this.getUniqueId(), task)));
    }

    @Override
    public void setTask(String task, boolean sendChannelMessage) {
        if (sendChannelMessage) {
            super.setWithKey(task, Column.User.TASK, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getTaskMessage(this.getUniqueId(), task)));
        } else {
            super.setWithKey(task, Column.User.TASK);
        }
    }

    @Override
    public void setTeam(String team) {
        super.setWithKey(team, Column.User.TEAM, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getTeamMessage(this.getUniqueId(), team)));
    }

    @Override
    public void setPermGroup(String permGroup, SyncExecute syncExecute) {
        super.setWithKey(permGroup, Column.User.PERMGROUP, syncExecute);
    }

    @Override
    public void setPermGroup(String permGroup) {
        super.setWithKey(permGroup, Column.User.PERMGROUP);
    }

    @Override
    public void setServer(String server) {
        super.setWithKey(server, Column.User.SERVER);
    }

    @Override
    public void setServerLast(String serverLast) {
        super.setWithKey(serverLast, Column.User.SERVER_LAST);
    }

    @Override
    public void setServerLobby(String serverLobby) {
        super.setWithKey(serverLobby, Column.User.SERVER_LOBBY);
    }

    @Override
    public void agreeDataProtection(de.timesnake.database.util.user.DataProtectionAgreement agreement) {
        super.setWithKey(agreement.toString(), Column.User.DATA_PROTECTION);
    }

    @Override
    public void disagreeDataProtection() {
        super.setWithKey(null, Column.User.DATA_PROTECTION);
    }

    @Override
    public de.timesnake.database.util.user.DataProtectionAgreement getDataProtectionAgreement() {
        return DataProtectionAgreement.fromString(super.getFirstWithKey(Column.User.DATA_PROTECTION));
    }

    @Override
    public boolean agreedDataProtection(String version) {
        DataProtectionAgreement agreement = this.getDataProtectionAgreement();
        return agreement != null && agreement.getVersion().equals(version);
    }

    @Override
    public void deleteEntries() {
        super.deleteWithKey(new TableEntry<>(super.getUniqueId(), Column.User.UUID));
    }

    @Override
    public void setKit(Integer kitName) {
        super.setWithKey(kitName, Column.User.KIT);
    }

    @Override
    public Integer getKit() {
        return super.getFirstWithKey(Column.User.KIT);
    }

    //coins
    @Override
    public void setCoins(float coins) {
        super.setWithKey(coins, Column.User.TIME_COINS);
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

    //support
    @Override
    public Collection<DbTicket> getTickets() {
        return Database.getSupport().getTickets(this.getUniqueId());
    }

    @Override
    public DbTicket getTicket(Integer id) {
        return Database.getSupport().getTicket(id);
    }

    //mails
    @Override
    public Collection<de.timesnake.database.util.user.DbUserMail> getMails() {
        return this.mailsTable.getMails(this.getUniqueId());
    }

    @Override
    public DbUserMail getMail(Integer id) {
        return this.mailsTable.getMail(this.getUniqueId(), id);
    }

    @Override
    public boolean deleteMail(Integer id) {
        return this.mailsTable.removeMail(this.getUniqueId(), id);
    }

    @Override
    public Integer addMail(UUID senderUuid, String senderName, String message) throws TooLongEntryException {
        return this.mailsTable.addMessage(this.getUniqueId(), this.getName(), senderUuid, senderName, message);
    }

    @Override
    public de.timesnake.database.util.user.DbUser toLocal() {
        return new DbLocalUser(this);
    }

    @Override
    public de.timesnake.database.util.user.DbUser toDatabase() {
        return this;
    }

}