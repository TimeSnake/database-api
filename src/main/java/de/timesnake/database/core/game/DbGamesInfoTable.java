package de.timesnake.database.core.game;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.Type;

import java.util.Arrays;
import java.util.Collection;

public class DbGamesInfoTable extends Table {

    protected DbGamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.Game.NAME);
        super.addColumn(Column.Game.DISPLAY_NAME);
        super.addColumn(Column.Game.CHAT_COLOR);
        super.addColumn(Column.Game.AUTO_START);
        super.addColumn(Column.Game.MIN_PLAYERS);
        super.addColumn(Column.Game.MAX_PLAYERS);
        super.addColumn(Column.Game.HEAD_LINE);
        super.addColumn(Column.Game.ITEM);
        super.addColumn(Column.Game.SLOT);
        super.addColumn(Column.Game.TEMPORARY);
        super.addColumn(Column.Game.KITS);
        super.addColumn(Column.Game.MAPS);
        super.addColumn(Column.Game.TEAM_AMOUNTS);
        super.addColumn(Column.Game.TEAM_MERGE);
        super.addColumn(Column.Game.EQUAL_TEAM_SIZE);
        super.addColumn(Column.Game.TEXTURE_PACK_LINK);
        super.addColumn(Column.Game.PLAYER_TRACKING_RANGE);
        super.addColumn(Column.Game.DESCRIPTION);
    }

    @Override
    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    public void addGame(String name, String displayName, String chatColorName, int autoStart, int minPlayers,
                        int maxPlayers, String headLine, String itemName, int slot, boolean isTemporary,
                        Type.Availability kits, Type.Availability maps, Type.Availability teamMerge,
                        Boolean teamEqualSize, String texturePack, Integer playerTrackingRange,
                        Integer... teamAmounts) {
        super.addEntrySynchronized(new PrimaryEntries(new TableEntry<>(name, Column.Game.NAME)),
                new TableEntry<>(displayName, Column.Game.DISPLAY_NAME),
                new TableEntry<>(chatColorName, Column.Game.CHAT_COLOR),
                new TableEntry<>(autoStart, Column.Game.AUTO_START),
                new TableEntry<>(minPlayers, Column.Game.MIN_PLAYERS),
                new TableEntry<>(maxPlayers, Column.Game.MAX_PLAYERS),
                new TableEntry<>(headLine, Column.Game.HEAD_LINE),
                new TableEntry<>(itemName, Column.Game.ITEM),
                new TableEntry<>(slot, Column.Game.SLOT),
                new TableEntry<>(kits, Column.Game.KITS),
                new TableEntry<>(maps, Column.Game.MAPS),
                new TableEntry<>(isTemporary, Column.Game.TEMPORARY),
                new TableEntry<>(teamMerge, Column.Game.TEAM_MERGE),
                new TableEntry<>(teamEqualSize, Column.Game.EQUAL_TEAM_SIZE),
                new TableEntry<>(texturePack, Column.Game.TEXTURE_PACK_LINK),
                new TableEntry<>(playerTrackingRange, Column.Game.PLAYER_TRACKING_RANGE),
                new TableEntry<>(new DbIntegerArrayList(Arrays.asList(teamAmounts)), Column.Game.TEAM_AMOUNTS));
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
