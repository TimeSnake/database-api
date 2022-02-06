package de.timesnake.database.core.hungergames;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;

public class DbHungerGamesItem extends TableQuery implements de.timesnake.database.util.hungergames.DbHungerGamesItem {

    public DbHungerGamesItem(DatabaseConnector databaseConnector, String nameTable, Integer itemId) {
        super(databaseConnector, nameTable, new TableEntry<>(itemId, Column.HungerGames.ITEM_ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_ID) != null;
    }

    @Override
    public String getType() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_TYPE);
    }

    @Override
    public Integer getAmount() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_AMOUNT);
    }

    @Override
    public Float getChance() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_CHANCE);
    }

    @Override
    public Integer getLevel() {
        return super.getFirstWithKey(Column.HungerGames.ITEM_LEVEL);
    }

}
