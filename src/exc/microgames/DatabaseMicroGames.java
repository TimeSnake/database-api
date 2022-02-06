package de.timesnake.database.core.microgames;

import de.timesnake.database.util.object.DatabaseConnection;

public class DatabaseMicroGames extends DatabaseConnection {
    private final LadderGameMapsTable ladderGameMapsTable;

    public DatabaseMicroGames(String name, String url, String user, String password, String ladderGameMapsTableName) {
        super(name, url, user, password);

        this.ladderGameMapsTable = new LadderGameMapsTable(this.connection, ladderGameMapsTableName);
    }

    public void createTables() {
        this.ladderGameMapsTable.create();
    }

    public void backupTables() {
        this.ladderGameMapsTable.backup();
    }


}
