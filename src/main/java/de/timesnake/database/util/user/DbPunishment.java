package de.timesnake.database.util.user;

import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.Nullable;

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
    @Nullable
    Type.Punishment getType();

    /**
     * Sets the type of the punishment
     *
     * @param type The type to set
     */
    void setType(Type.Punishment type);

    /**
     * Gets the deletion-date of the punishment
     *
     * @return the {@link Date} of deletion
     */
    @Nullable
    Date getDate();

    /**
     * Sets tbe date of the punishment
     *
     * @param date The date to set
     */
    void setDate(Date date);

    /**
     * Gets the castigator of the punishment
     *
     * @return the castigator name
     */
    @Nullable
    String getCastigator();

    /**
     * Sets the castigator name of the punishment
     *
     * @param castigator The name of the castigator
     */
    void setCastigator(String castigator);

    /**
     * Gets the reason of the punishment
     *
     * @return the reason
     */
    @Nullable
    String getReason();

    /**
     * Sets the reason of the punishment
     *
     * @param reason The reason to set
     */
    void setReason(String reason);

    /**
     * Gets the punished-server of the punishment
     *
     * @return the server name
     */
    @Nullable
    String getServer();

    /**
     * Sets the punished-server of the punishment
     *
     * @param server The name of the server to set
     */
    void setServer(String server);
}
