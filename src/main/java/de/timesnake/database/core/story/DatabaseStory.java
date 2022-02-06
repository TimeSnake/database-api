package de.timesnake.database.core.story;

import de.timesnake.database.util.object.DatabaseConnector;

import java.util.UUID;

public class DatabaseStory extends DatabaseConnector implements de.timesnake.database.util.story.DatabaseStory {

    private final StoryUserTable userTable;

    private final String userTableName;

    public DatabaseStory(String name, String url, String user, String password, String userTableName) {
        super(name, url, user, password);
        this.userTableName = userTableName;
        this.userTable = new StoryUserTable(this, userTableName);
    }

    public void createTables() {
        this.userTable.create();
    }

    public void backupTables() {
        this.userTable.backup();
    }

    @Override
    public DbStoryUser getUser(UUID uuid) {
        return this.userTable.getStoryUser(uuid);
    }

    public void addUser(UUID uuid, Integer chapterId, Integer partId) {
        this.userTable.addStoryUser(uuid, chapterId, partId);
    }

    public void removeStoryUser(UUID uuid, Integer chapterId, Integer partId) {
        this.userTable.removeStoryUser(uuid, chapterId, partId);
    }
}