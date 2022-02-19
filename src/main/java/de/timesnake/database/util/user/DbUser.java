package de.timesnake.database.util.user;

import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.object.*;
import de.timesnake.database.util.permission.DbPermission;
import de.timesnake.database.util.server.DbLobbyServer;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.support.DbTicket;
import de.timesnake.library.basic.util.Status;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface DbUser extends DbPlayer, DbLocal<DbUser> {

    /**
     * Checks if all tables contains the user
     */
    @NotLocal
    void checkEntries();

    /**
     * Sets the user punishment
     *
     * @param type       The {@link Type.Punishment} to set
     * @param date       The {@link Date} to set
     * @param castigator The castigator to set
     * @param reason     The reason to set
     * @param server     The server to set
     */
    @NotLocal
    void setPunishment(Type.Punishment type, Date date, String castigator, String reason, String server);

    /**
     * Sets the user punishment
     *
     * @param punishment The {@link DbPunishment} to set
     */
    @NotLocal
    void setPunishment(DbPunishment punishment);

    /**
     * Checks if the user has a punishment
     * <p>
     * If punishments-table contains the user-uuid
     *
     * @return true if the user has a punishment
     */
    @NotLocal
    boolean hasPunishment();

    /**
     * Gets the user punishment
     *
     * @return the {@link DbPunishment}
     */
    @NotLocal
    DbPunishment getPunishment();

    /**
     * Gets the user prefix
     *
     * @return the prefix
     */
    String getPrefix();

    /**
     * Gets the user suffix
     *
     * @return the suffix
     */
    String getSuffix();

    /**
     * Gets the user nick
     *
     * @return the nick
     */
    String getNick();

    /**
     * Sets the user prefix
     *
     * @param prefix The prefix to set
     */
    void setPrefix(String prefix);

    /**
     * Sets the user suffix
     *
     * @param suffix The suffix to set
     */
    void setSuffix(String suffix);

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
    @NotLocal
    void addPermission(String permission, Status.Permission mode, String... servers);

    /**
     * Adds a permission to the user
     *
     * @param permission  The permission to set
     * @param mode        The {@link Status.Permission} to set
     * @param servers     The servers to set
     * @param syncExecute
     */
    @NotLocal
    void addPermission(String permission, Status.Permission mode, SyncExecute syncExecute, String... servers);


    /**
     * Checks if the user has the permission
     *
     * @param permission The name to permission to check
     * @return true if the user has the permission
     */
    @NotLocal
    boolean hasPermission(String permission);

    /**
     * Gets the {@link DbPermission} from the user
     *
     * @param permission The name of the permission to get
     * @return the {@link DbPermission}
     */
    @NotLocal
    DbPermission getPermission(String permission);

    /**
     * Gets all permissions from the user
     *
     * @return a {@link DbPermission}-{@link List}
     */
    @NotLocal
    Collection<DbPermission> getPermissions();

    /**
     * Removes a permission from the user
     *
     * @param permission The name of the permission to remove
     */
    @NotLocal
    void removePermission(String permission);

    /**
     * Removes a permission from the user
     *
     * @param permission  The name of the permission to remove
     * @param syncExecute The object to execute sync
     */
    @NotLocal
    void removePermission(String permission, SyncExecute syncExecute);

    /**
     * Gets the status of the user
     *
     * @return the {@link Status.User}
     */
    Status.User getStatus();

    boolean isService();

    boolean enabledAntiCheatMessages();

    boolean isAirMode();

    /**
     * Gets the task of the user
     *
     * @return the task
     */
    String getTask();

    /**
     * Gets the name of the team of the user
     *
     * @return the team-name
     */
    String getTeamName();

    /**
     * Checks if the user has a permission-group
     * <p>
     * If permission-group is not null
     *
     * @return true if the user has a permission-group
     */
    @NotLocal
    boolean hasPermGroup();

    /**
     * Gets the permission-group of the user
     *
     * @return the {@link DbPermGroup}
     */
    DbPermGroup getPermGroup();

    /**
     * Removes the permission-group of the user
     * <p>
     * Sets the permission-group to null
     */
    @NotLocal
    void removePermGroup();

    /**
     * Removes the permission-group of the user
     * <p>
     * Sets the permission-group to null
     */
    @NotLocal
    void removePermGroup(SyncExecute syncExecute);

    /**
     * Gets the server on which the user is online
     *
     * @return the {@link DbServer}
     */
    DbServer getServer();

    /**
     * Gets the server on which the user was last
     *
     * @return the last {@link DbServer}
     */
    DbServer getServerLast();

    /**
     * Gets the lobby-server on which the user is online or was last
     *
     * @return {@link DbLobbyServer}
     */
    DbLobbyServer getServerLobby();

    /**
     * Sets the name of the user
     *
     * @param name The name to set
     */
    @NotLocal
    void setName(String name);

    /**
     * Sets the status of the user
     *
     * @param status The {@link Status.User} to set
     */
    @NotLocal
    void setStatus(Status.User status);

    @NotLocal
    void setService(boolean service);

    void setAntiCheatMessages(boolean enable);

    @NotLocal
    void setAirMode(boolean airMode);

    /**
     * Sets the status of the user
     *
     * @param status             The {@link Status.User} to set
     * @param sendChannelMessage Set true to send a channel-message
     */
    @NotLocal
    void setStatus(Status.User status, boolean sendChannelMessage);

    /**
     * Sets the task of the user
     *
     * @param task The task to set
     */
    @NotLocal
    void setTask(String task);

    /**
     * Sets the task of the user
     *
     * @param task               The task to set
     * @param sendChannelMessage Set true to send a channel-message
     */
    @NotLocal
    void setTask(String task, boolean sendChannelMessage);

    /**
     * Sets the team of the user
     *
     * @param team The name of the team to set
     */
    @NotLocal
    void setTeam(String team);

    /**
     * Sets the permission-group of the user
     *
     * @param permGroup The name of the permission-group to set
     */
    @NotLocal
    void setPermGroup(String permGroup);

    /**
     * Sets the permission-group of the user
     *
     * @param permGroup   The name of the permission-group to set
     * @param syncExecute The code that run sync
     */
    @NotLocal
    void setPermGroup(String permGroup, SyncExecute syncExecute);

    /**
     * Sets the server of the user
     *
     * @param server The name of the server to set
     */
    @NotLocal
    void setServer(String server);

    /**
     * Sets the last server of the user
     *
     * @param serverLast The name of the server to set
     */
    @NotLocal
    void setServerLast(String serverLast);

    /**
     * Sets the lobby of the user
     *
     * @param serverLobby The name of the lobby to set
     */
    @NotLocal
    void setServerLobby(String serverLobby);

    /**
     * Sets that the user agreed the data-protection
     * <p>
     * Sets the string of the data-protection-agreement.
     * For more details check out the {@link DataProtectionAgreement}
     *
     * @param agreement The {@link DataProtectionAgreement} to agree
     */
    @NotLocal
    void agreeDataProtection(DataProtectionAgreement agreement);

    /**
     * Sets that the user disagreed the data-protection
     * <p>
     * Sets the data-protection to null
     */
    @NotLocal
    void disagreeDataProtection();

    /**
     * Gets the data-protection-agreement of the user
     *
     * @return the {@link DataProtectionAgreement}
     */
    DataProtectionAgreement getDataProtectionAgreement();

    /**
     * Checks if the user agreed the data-protection
     * <p>
     * Checks if data-protection is null and checks the version
     *
     * @param version The version to check
     * @return true of the user agreed the data-protection
     */
    boolean agreedDataProtection(String version);

    Long getDiscordId();

    @NotLocal
    void setDiscordId(Long id);

    /**
     * Removes the user from all tables, punishment excluded
     */
    @NotLocal
    void deleteEntries();

    /**
     * Sets the kit of the user
     *
     * @param kitName The id of the kit to set
     */
    @NotLocal
    void setKit(Integer kitName);

    /**
     * Gets the kit of the user
     *
     * @return the id of the kit
     */
    Integer getKit();

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
    @NotLocal
    void setCoins(float coins);

    /**
     * Adds a coins-value to the coins of the user
     *
     * @param coins The value of the coins to add
     */
    @NotLocal
    void addCoins(float coins);

    /**
     * Removes a coins-value from the coins of the user
     *
     * @param coins The value of the coins to remove
     */
    @NotLocal
    void removeCoins(float coins);

    //support

    /**
     * Gets the created tickets of the user
     *
     * @return the {@link DbTicket}-{@link List}
     */
    @NotLocal
    Collection<DbTicket> getTickets();

    /**
     * Gets a ticket of the user
     *
     * @param id The id of the ticket to get
     * @return the {@link DbTicket}
     */
    @NotLocal
    DbTicket getTicket(Integer id);

    //mails

    /**
     * Gets all mails for the user
     *
     * @return a {@link Collection} of {@link DbUserMail}s send to the user
     */
    @NotLocal
    Collection<DbUserMail> getMails();

    /**
     * Gets a mail for the user with id
     *
     * @param id The id of the mail to get
     * @return {@link DbUserMail} if exists, else null
     */
    @NotLocal
    DbUserMail getMail(Integer id);

    /**
     * Deletes a mail for the user with id
     *
     * @param id The id of the mail to delete
     * @return true if mail existed before, else false
     */
    @NotLocal
    boolean deleteMail(Integer id);


    /**
     * Adds a mail for the user
     *
     * @param senderUuid The uuid of the sender of the mail
     * @param senderName The name of the sender of the mail
     * @param message    The message of the mail
     * @return the id of the mail
     *
     * @throws TooLongEntryException The max message length is 255
     */
    @NotLocal
    Integer addMail(UUID senderUuid, String senderName, String message) throws TooLongEntryException;

}
