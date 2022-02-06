package de.timesnake.database.util.user;

import de.timesnake.database.util.object.Type;

import java.util.Date;

public interface DbPunishment extends DbPlayer {

    /**
     * Deletes the punishment
     */
    void delete();

    /**
     * Gets the type of the punishment
     *
     * @return the {@link Type.Punishment}
     */
    Type.Punishment getType();

    /**
     * Gets the deletion-date of the punishment
     *
     * @return the {@link Date} of deletion
     */
    Date getDate();

    /**
     * Gets the castigator of the punishment
     *
     * @return the castigator name
     */
    String getCastigator();

    /**
     * Gets the reason of the punishment
     *
     * @return the reason
     */
    String getReason();

    /**
     * Gets the punished-server of the punishment
     *
     * @return the server name
     */
    String getServer();

    /**
     * Sets the type of the punishment
     *
     * @param type The type to set
     */
    void setType(Type.Punishment type);

    /**
     * Sets tbe date of the punishment
     *
     * @param date The date to set
     */
    void setDate(Date date);

    /**
     * Sets the castigator name of the punishment
     *
     * @param castigator The name of the castigator
     */
    void setCastigator(String castigator);

    /**
     * Sets the reason of the punishment
     *
     * @param reason The reason to set
     */
    void setReason(String reason);

    /**
     * Sets the punished-server of the punishment
     *
     * @param server The name of the server to set
     */
    void setServer(String server);
}
