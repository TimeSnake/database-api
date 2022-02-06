package de.timesnake.database.util.hungergames;

import java.util.Collection;

public interface DatabaseHungerGames {
    Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems();

    Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems(Integer level);

    DbHungerGamesItem getItem(int id);

    void addItem(String type, Integer amount, Float chance, Integer level);

    void removeItem(String type, Integer amount);

    void removeItem(int id);

    Integer getId(String type, Integer amount);
}
