package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Set;
import java.util.UUID;

public class UserBoughtTable extends TableDDL {

    protected UserBoughtTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.CHAPTER_ID, Column.Story.PART_ID);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public Set<Integer> getBoughtParts(UUID uuid, Integer chapterId) {
        return super.get(Column.Story.PART_ID, new TableEntry<>(uuid, Column.Story.USER_UUID),
                new TableEntry<>(chapterId, Column.Story.CHAPTER_ID));
    }

    public void addBoughtPart(UUID uuid, Integer chapterId, Integer partId) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(uuid, Column.Story.USER_UUID), new TableEntry<>(chapterId,
                Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID)));
    }

    public void removeBoughtChapter(UUID uuid, Integer chapterId, Integer partId) {
        super.deleteEntry(new TableEntry<>(uuid, Column.Story.USER_UUID), new TableEntry<>(chapterId,
                Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID));
    }
}
