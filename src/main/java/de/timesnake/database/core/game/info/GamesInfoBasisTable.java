package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;

public class GamesInfoBasisTable extends TableDDL {

    protected GamesInfoBasisTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.NAME);
        super.addColumn(Column.Game.DISPLAY_NAME);
        super.addColumn(Column.Game.CHAT_COLOR);
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

    public DbGameInfoBasis getGame(String name) {
        return new DbGameInfoBasis(this.databaseConnector, this.tableName, name);
    }

    public boolean containsGame(String name) {
        return super.getFirst(Column.Game.NAME, new TableEntry<>(name, Column.Game.NAME)) != null;
    }

    protected void removeGame(String name) {
        super.deleteEntry(new TableEntry<>(name, Column.Game.NAME));
    }

    public Collection<String> getGamesName() {
        return super.get(Column.Game.NAME);
    }
}
