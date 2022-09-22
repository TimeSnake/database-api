package de.timesnake.database.util.hungergames;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DatabaseHungerGames {
    Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems();

    Collection<de.timesnake.database.util.hungergames.DbHungerGamesItem> getItems(Integer level);

    @NotNull
    DbHungerGamesItem getItem(int id);

    void addItem(String type, Integer amount, Float chance, Integer level);

    void removeItem(String type, Integer amount);

    void removeItem(int id);

    @Nullable
    Integer getId(String type, Integer amount);
}
