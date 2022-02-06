package de.timesnake.database.core.microgames;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;

import java.sql.Connection;

public class DbLadderGameMap extends TableQuery implements de.timesnake.database.util.hungergames.DbHungerGamesItem {

    public DbLadderGameMap(Connection connection, String nameTable, String itemId) {
        super(connection, nameTable, new TableEntry<>(itemId, Column.HungerGames.ITEM_ID));
    }

    @Override
    public String getType() {
        return super.getStringWithKey(Column.HungerGames.ITEM_TYPE);
    }

    @Override
    public Integer getAmount() {
        return super.getIntegerWithKey(Column.HungerGames.ITEM_AMOUNT);
    }

    @Override
    public Float getChance() {
        return Float.valueOf(super.getStringWithKey(Column.HungerGames.ITEM_CHANCE));
    }

    @Override
    public Integer getLevel() {
        return super.getIntegerWithKey(Column.HungerGames.ITEM_LEVEL);
    }
}
