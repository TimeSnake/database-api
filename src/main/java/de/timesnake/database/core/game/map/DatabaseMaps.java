package de.timesnake.database.core.game.map;

import de.timesnake.database.util.object.DatabaseConnector;

public class DatabaseMaps extends DatabaseConnector {

    private final String infoTableName;
    private final String spawnsTableName;
    private final String authorsTableName;

    public DatabaseMaps(String name, String url, String user, String password, String infoTableName,
                        String spawnsTableName, String authorsTableName) {
        super(name, url, user, password);
        this.infoTableName = infoTableName;
        this.spawnsTableName = spawnsTableName;
        this.authorsTableName = authorsTableName;
    }

    public void addGame(String gameName) {
        new MapLocationsTable(this, gameName).create();
    }

    public void deleteGameMaps(String gameName) {
        new MapLocationsTable(this, gameName).delete();
    }

    public MapsInfoTable getMapsInfoTable(String gameName) {
        return new MapsInfoTable(this, gameName + "_" + this.infoTableName);
    }

    public MapLocationsTable getMapsSpawnsTable(String gameName) {
        return new MapLocationsTable(this, gameName + "_" + this.spawnsTableName);
    }

    public MapsAuthorTable getMapsAuthorTable(String gameName) {
        return new MapsAuthorTable(this, gameName + "_" + this.authorsTableName);
    }

}
