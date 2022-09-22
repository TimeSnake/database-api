package de.timesnake.database.core.story;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DatabaseStory extends DatabaseConnector implements de.timesnake.database.util.story.DatabaseStory {

    private final UserCheckpointsTable checkpointsTable;
    private final UserBoughtTable boughtTable;


    private final String checkpointsTableName;
    private final String boughtTableName;

    public DatabaseStory(String name, String url, String user, String password, String checkpointsTableName,
                         String boughtTableName) {
        super(name, url, user, password);
        this.checkpointsTableName = checkpointsTableName;
        this.boughtTableName = boughtTableName;
        this.checkpointsTable = new UserCheckpointsTable(this, this.checkpointsTableName);
        this.boughtTable = new UserBoughtTable(this, this.boughtTableName);
    }

    @Override
    public void createTables() {
        this.checkpointsTable.create();
        this.boughtTable.create();
    }

    @Override
    public void backupTables() {
        this.checkpointsTable.backup();
        this.boughtTable.backup();
    }

    @NotNull
    @Override
    public DbStoryUser getUser(UUID uuid) {
        return new DbStoryUser(uuid, this.boughtTable, this.checkpointsTable);
    }

}