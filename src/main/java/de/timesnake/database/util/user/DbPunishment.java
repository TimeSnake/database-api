/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.user;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    LocalDateTime getDate();

    /**
     * Sets tbe date of the punishment
     *
     * @param date The date to set
     */
    void setDate(LocalDateTime date);

    @NotNull
    Duration getDuration();

    @NotCached
    void setDuration(Duration duration);

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
}
