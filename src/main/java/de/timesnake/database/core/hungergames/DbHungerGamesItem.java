/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.hungergames;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbHungerGamesItem extends TableQuery implements de.timesnake.database.util.hungergames.DbHungerGamesItem {

    public DbHungerGamesItem(DatabaseConnector databaseConnector, String nameTable, Integer itemId) {
        super(databaseConnector, nameTable, new Entry<>(itemId, Column.HungerGames.ITEM_ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_ID) != null;
    }

    @NotNull
    @Override
    public String getType() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_TYPE);
    }

    @NotNull
    @Override
    public Integer getAmount() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_AMOUNT);
    }

    @NotNull
    @Override
    public Float getChance() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_CHANCE);
    }

    @NotNull
    @Override
    public Integer getLevel() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_LEVEL);
    }

}
