package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.database.util.server.DbLobbyServer;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.database.util.user.DataProtectionAgreement;
import de.timesnake.database.util.user.DbPunishment;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.database.util.user.DbUserMail;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class DbCachedUser implements DbUser {

    private final de.timesnake.database.core.user.DbUser user;

    private final UUID uuid;
    private final String permGroup;
    private String name;
    private String prefix;
    private String suffix;
    private String nick;
    private Float coins;
    private Status.User status;
    private boolean service;
    private boolean antiCheatMessages;
    private boolean airMode;
    private String task;
    private String team;
    private Integer kit;
    private String server;
    private String serverLast;
    private String serverLobby;
    private String dataProtection;
    private Long discordId;

    public DbCachedUser(de.timesnake.database.core.user.DbUser user) {
        ColumnMap columnMap = user.getFirstWithKey(Set.of(Column.User.NAME, Column.User.PREFIX, Column.User.SUFFIX,
                Column.User.NICK, Column.User.TIME_COINS, Column.User.STATUS, Column.User.SERVICE,
                Column.User.ANTI_CHEAT_MESSAGES, Column.User.AIR_MODE, Column.User.TASK, Column.User.TEAM,
                Column.User.KIT, Column.User.PERM_GROUP, Column.User.SERVER, Column.User.SERVER_LAST,
                Column.User.SERVER_LOBBY, Column.User.DATA_PROTECTION, Column.User.DISCORD_ID));

        this.user = user;

        this.uuid = user.getUniqueId();
        this.name = columnMap.get(Column.User.NAME);
        this.prefix = columnMap.get(Column.User.PREFIX);
        this.suffix = columnMap.get(Column.User.SUFFIX);
        this.nick = columnMap.get(Column.User.NICK);
        this.coins = columnMap.get(Column.User.TIME_COINS);
        if (this.coins == null) this.coins = 0F;
        this.status = columnMap.get(Column.User.STATUS);
        this.service = columnMap.get(Column.User.SERVICE);
        this.antiCheatMessages = columnMap.get(Column.User.ANTI_CHEAT_MESSAGES);
        this.airMode = columnMap.get(Column.User.AIR_MODE);
        this.task = columnMap.get(Column.User.TASK);
        this.team = columnMap.get(Column.User.TEAM);
        this.kit = columnMap.get(Column.User.KIT);
        this.permGroup = columnMap.get(Column.User.PERM_GROUP);
        this.server = columnMap.get(Column.User.SERVER);
        this.serverLast = columnMap.get(Column.User.SERVER_LAST);
        this.serverLobby = columnMap.get(Column.User.SERVER_LOBBY);
        this.dataProtection = columnMap.get(Column.User.DATA_PROTECTION);
        this.discordId = columnMap.get(Column.User.DISCORD_ID);
    }


    @Override
    public boolean exists() {
        return this.user.exists();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.user.setName(name);
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public void checkEntries() {
        this.user.checkEntries();
    }

    @Override
    public void setPunishment(Type.Punishment type, Date date, String castigator, String reason, String server) {
        this.user.setPunishment(type, date, castigator, reason, server);
    }

    @Override
    public boolean hasPunishment() {
        return this.user.hasPunishment();
    }

    @Override
    public DbPunishment getPunishment() {
        return this.user.getPunishment();
    }

    @Override
    public void setPunishment(DbPunishment punishment) {
        this.user.setPunishment(punishment);
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.user.setPrefix(prefix);
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
        this.user.setSuffix(suffix);
    }

    @Override
    public String getNick() {
        return this.nick;
    }

    @Override
    public void setNick(String nick) {
        this.nick = nick;
        this.user.setNick(nick);
    }

    @Override
    public boolean hasAliases() {
        return this.prefix != null || this.suffix != null || this.nick != null;
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, String... servers) {
        this.user.addPermission(permission, mode, servers);
    }

    @Override
    public void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers) {
        this.user.addPermission(permission, mode, syncExecute, servers);
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.user.hasPermission(permission);
    }

    @Override
    public DbPermission getPermission(String permission) {
        return this.user.getPermission(permission);
    }

    @Override
    public Collection<DbPermission> getPermissions() {
        return this.user.getPermissions();
    }

    @Override
    public void removePermission(String permission) {
        this.user.removePermission(permission);
    }

    @Override
    public void removePermission(String permission, SyncExecute syncExecute) {
        this.user.removePermission(permission);
    }

    @Override
    public Collection<String> getDisplayGroupNames() {return user.getDisplayGroupNames();}

    @Override
    public Collection<DbDisplayGroup> getDisplayGroups() {return user.getDisplayGroups();}

    @Override
    public void addDisplayGroup(String name) {user.addDisplayGroup(name);}

    @Override
    public void removeDisplayGroup(String name) {user.removeDisplayGroup(name);}

    @Override
    public void clearDisplayGroups() {user.clearDisplayGroups();}

    @Override
    public Status.User getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status.User status) {
        this.status = status;
        this.user.setStatus(status);
    }

    @Override
    public boolean isService() {
        return this.service;
    }

    @Override
    public void setService(boolean service) {
        this.service = service;
        this.user.setService(service);
    }

    @Override
    public boolean enabledAntiCheatMessages() {
        return this.antiCheatMessages;
    }

    @Override
    public boolean isAirMode() {
        return this.airMode;
    }

    @Override
    public void setAirMode(boolean airMode) {
        this.airMode = airMode;
        this.user.setAirMode(airMode);
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void setTask(String task) {
        this.task = task;
        this.user.setTask(task);
    }

    @Override
    public String getTeamName() {
        return this.team;
    }

    @Override
    public boolean hasPermGroup() {
        return Database.getGroups().containsPermGroup(this.permGroup);
    }

    @Override
    public DbPermGroup getPermGroup() {
        return this.user.getPermGroup();
    }

    @Override
    public void setPermGroup(String permGroup) {
        this.user.setPermGroup(permGroup);
    }

    @Override
    public void removePermGroup() {
        this.user.removePermGroup();
    }

    @Override
    public void removePermGroup(SyncExecute syncExecute) {
        this.user.removePermGroup(syncExecute);
    }

    @Override
    public DbServer getServer() {
        return Database.getServers().getServer(this.server);
    }

    @Override
    public void setServer(String server) {
        this.server = server;
        this.user.setServer(server);
    }

    @Override
    public DbServer getServerLast() {
        return Database.getServers().getServer(this.serverLast);
    }

    @Override
    public void setServerLast(String serverLast) {
        this.serverLast = serverLast;
        this.user.setServerLast(serverLast);
    }

    @Override
    public DbLobbyServer getServerLobby() {
        return Database.getServers().getServer(this.serverLobby);
    }

    @Override
    public void setServerLobby(String serverLobby) {
        this.serverLobby = serverLobby;
        this.user.setServerLobby(serverLobby);
    }

    @Override
    public void setAntiCheatMessages(boolean enable) {
        this.antiCheatMessages = enable;
        this.user.setAntiCheatMessages(enable);
    }

    @Override
    public void setStatus(Status.User status, boolean sendChannelMessage) {
        this.status = status;
        this.user.setStatus(status, sendChannelMessage);
    }

    @Override
    public void setTask(String task, boolean sendChannelMessage) {
        this.task = task;
        this.user.setTask(task, sendChannelMessage);
    }

    @Override
    public void setTeam(String team) {
        this.team = team;
        this.user.setTeam(team);
    }

    @Override
    public void setPermGroup(String permGroup, SyncExecute syncExecute) {
        this.user.setPermGroup(permGroup, syncExecute);
    }

    @Override
    public void agreeDataProtection(DataProtectionAgreement agreement) {
        this.dataProtection = agreement.toString();
        this.user.agreeDataProtection(agreement);
    }

    @Override
    public void disagreeDataProtection() {
        this.dataProtection = null;
        this.user.disagreeDataProtection();
    }

    @Override
    public DataProtectionAgreement getDataProtectionAgreement() {
        return DataProtectionAgreement.fromString(this.dataProtection);
    }

    @Override
    public boolean agreedDataProtection(String version) {
        DataProtectionAgreement agreement = this.getDataProtectionAgreement();
        return agreement != null && agreement.getVersion().equals(version);
    }

    @Override
    public Long getDiscordId() {
        return this.discordId;
    }

    @Override
    public void setDiscordId(Long id) {
        this.user.setDiscordId(id);
    }

    @Override
    public void deleteEntries() {
        this.user.deleteEntries();
    }

    @Override
    public Integer getKit() {
        return this.kit;
    }

    @Override
    public void setKit(Integer kitName) {
        this.kit = kitName;
        this.user.setKit(kitName);
    }

    @Override
    public float getCoins() {
        return this.coins;
    }

    @Override
    public void setCoins(float coins) {
        this.coins = coins;
        this.user.setCoins(coins);
    }

    @Override
    public void addCoins(float coins) {
        this.coins += coins;
        this.user.addCoins(coins);
    }

    @Override
    public void removeCoins(float coins) {
        this.coins -= coins;
        this.user.removeCoins(coins);
    }

    @Override
    public Collection<DbTicket> getTickets() {
        return this.user.getTickets();
    }

    @Override
    public DbTicket getTicket(Integer id) {
        return this.user.getTicket(id);
    }

    @Override
    public Collection<DbUserMail> getMails() {
        return this.user.getMails();
    }

    @Override
    public DbUserMail getMail(Integer id) {
        return this.user.getMail(id);
    }

    @Override
    public boolean deleteMail(Integer id) {
        return this.user.deleteMail(id);
    }

    @Override
    public Integer addMail(UUID senderUuid, String senderName, String message) throws TooLongEntryException {
        return this.user.addMail(senderUuid, senderName, message);
    }

    @Override
    public DbUser toLocal() {
        return new DbCachedUser(this.user);
    }

    @Override
    public DbUser toDatabase() {
        return this.user;
    }

}
