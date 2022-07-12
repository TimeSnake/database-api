package de.timesnake.database.core.game;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseGames extends DatabaseConnector implements de.timesnake.database.util.game.DatabaseGames {

    protected final DbGamesInfoTable dbGamesInfoTable;

    public DatabaseGames(String name, String url, String user, String password, String gamesInfoTableName) {
        super(name, url, user, password);
        this.dbGamesInfoTable = new DbGamesInfoTable(this, gamesInfoTableName);
    }

    public void createTables() {
        this.dbGamesInfoTable.create();
        for (de.timesnake.database.util.game.DbGame game : this.getGames()) {
            ((DbGame) game).createTables();
        }
    }

    public void backupTables() {
        for (de.timesnake.database.util.game.DbGame game : this.getGames()) {
            ((DbGame) game).backup();
        }
        this.dbGamesInfoTable.backup();
    }

    @Override
    public de.timesnake.database.util.game.DbGame getGame(String gameName) {
        return new DbGame(this, gameName, this.dbGamesInfoTable);
    }


    @Override
    public boolean containsGame(String gameName) {
        return this.dbGamesInfoTable.containsGame(gameName);
    }

    @Override
    public de.timesnake.database.util.game.DbGame createGame(String name, String displayName, String chatColorName,
                                                             int autoStart, int minPlayers, int maxPlayers,
                                                             String description, String itemName, int slot,
                                                             boolean isTemporary, Type.Availability kits,
                                                             Type.Availability maps, Type.Availability teamMerge,
                                                             Boolean equalTeamSize, String texturePack,
                                                             Integer playerTrackingRange, Integer... teamAmounts) {

        DbGame game = new DbGame(this, name, this.dbGamesInfoTable);
        game.create(name, displayName, chatColorName, autoStart, minPlayers, maxPlayers, description, itemName, slot,
                isTemporary, kits, maps, teamMerge, equalTeamSize, texturePack, playerTrackingRange, teamAmounts);
        return game;
    }

    @Override
    public void deleteGame(String name) {
        new DbGame(this, name, this.dbGamesInfoTable).delete();
    }

    @Override
    public Collection<String> getGamesName() {
        return this.dbGamesInfoTable.getGamesName();
    }

    @Override
    public Collection<de.timesnake.database.util.game.DbGame> getGames() {
        List<de.timesnake.database.util.game.DbGame> games = new ArrayList<>();
        for (String gameName : this.getGamesName()) {
            games.add(this.getGame(gameName));
        }
        return games;
    }

}
