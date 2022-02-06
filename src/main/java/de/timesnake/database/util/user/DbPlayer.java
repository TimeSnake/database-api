package de.timesnake.database.util.user;

import java.util.UUID;

public interface DbPlayer {

    boolean exists();

    /**
     * Gets the {@link DbPlayer}-name
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the {@link UUID} of the {@link DbPlayer}
     *
     * @return the {@link UUID}
     */
    UUID getUniqueId();
}
