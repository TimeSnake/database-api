package de.timesnake.database.util.game;

import de.timesnake.database.util.object.Type;

import java.util.Collection;

public interface DatabaseGames {

    DbGame getGame(String gameName);

    boolean containsGame(String gameName);

    DbGame createGame(String name, String displayName, String chatColorName, int autoStart, int minPlayers,
                      int maxPlayers, String description, String itemName, int slot, boolean isTemporary,
                      Type.Availability kits, Type.Availability maps, Type.Availability teamMerge,
                      Boolean equalTeamSize, String texturePack, Integer... teamAmounts);

    void deleteGame(String name);

    Collection<String> getGamesName();

    Collection<DbGame> getGames();
}
