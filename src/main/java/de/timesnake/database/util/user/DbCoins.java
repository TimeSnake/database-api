package de.timesnake.database.util.user;

public interface DbCoins {

    /**
     * Sets the coins
     *
     * @param coins The coins to set
     */
    void setCoins(float coins);

    /**
     * Add the coins to the current value
     *
     * @param coins The coins to add
     */
    void addCoins(float coins);

    /**
     * Removes the coins from the current value
     *
     * @param coins The coins to remove
     */
    void removeCoins(float coins);

    /**
     * Gets the amount of the coins
     *
     * @return The amount of the coins
     */
    float getCoins();
}
