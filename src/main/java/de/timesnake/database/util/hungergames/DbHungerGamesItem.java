package de.timesnake.database.util.hungergames;

public interface DbHungerGamesItem {

    boolean exists();

    String getType();

    Integer getAmount();

    Float getChance();

    Integer getLevel();
}
