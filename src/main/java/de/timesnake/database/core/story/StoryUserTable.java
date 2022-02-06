package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.UUID;

public class StoryUserTable extends Table {

    protected StoryUserTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.CHAPTER_ID, Column.Story.PART_ID);

        super.addColumn(Column.Story.PART_ID);
        super.addColumn(Column.Story.SECTION_ID);
    }

    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    public DbStoryUser getStoryUser(UUID uuid) {
        return new DbStoryUser(this.databaseConnector, this.tableName, uuid);
    }

    public void addStoryUser(UUID uuid, Integer chapterId, Integer partId) {
        super.deleteEntry(new TableEntry<>(uuid, Column.Story.USER_UUID), new TableEntry<>(chapterId, Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID));
    }

    public void removeStoryUser(UUID uuid, Integer chapterId, Integer partId) {
        super.deleteEntry(new TableEntry<>(uuid, Column.Story.USER_UUID), new TableEntry<>(chapterId, Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID));
    }
}
