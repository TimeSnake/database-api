package de.timesnake.database.util.user;

import java.util.UUID;

public interface DbUserMail {

    /**
     * Gets the id of the mail
     *
     * @return the id
     */
    Integer getId();

    /**
     * Gets the uuid of the receiver of the mail
     *
     * @return the uuid of the receiver
     */
    UUID getUniqueId();

    /**
     * Gets the name of the receiver of the mail
     *
     * @return the name of the receiver
     */
    String getName();

    /**
     * Gets the uuid of the sender of the mail
     *
     * @return the uuid of the sender
     */
    UUID getSenderUniqueId();

    /**
     * Gets the name of the sneder of the mail
     *
     * @return the name of the sender
     */
    String getSenderName();

    /**
     * Gets the message of the mail
     *
     * @return the message of the mail
     */
    String getMessage();

    /**
     * Deletes the mail
     */
    void delete();
}
