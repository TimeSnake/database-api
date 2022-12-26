/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class GamesInfoBasisTable extends TableDDL {

    protected GamesInfoBasisTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.NAME);
        super.addColumn(Column.Game.DISPLAY_NAME);
        super.addColumn(Column.Game.TEXT_COLOR);
        super.addColumn(Column.Game.HEAD_LINE);
        super.addColumn(Column.Game.ITEM);
        super.addColumn(Column.Game.SLOT);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @NotNull
    public DbGameInfoBasis getGame(String name) {
        return new DbGameInfoBasis(this.databaseConnector, this.tableName, name);
    }

    public boolean containsGame(String name) {
        return super.getFirst(Column.Game.NAME, new Entry<>(name, Column.Game.NAME)) != null;
    }

    protected void removeGame(String name) {
        super.deleteEntry(new Entry<>(name, Column.Game.NAME));
    }

    public Collection<String> getGamesName() {
        return super.get(Column.Game.NAME);
    }
}
