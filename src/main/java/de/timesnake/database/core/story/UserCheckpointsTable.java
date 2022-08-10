package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Set;
import java.util.UUID;

public class UserCheckpointsTable extends TableDDL {

    protected UserCheckpointsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.CHAPTER_ID, Column.Story.PART_ID);
        super.addColumn(Column.Story.SECTION_ID);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public Set<Integer> getChapterIds(UUID uuid) {
        return super.get(Column.Story.CHAPTER_ID, new TableEntry<>(uuid, Column.Story.USER_UUID));
    }

    public Set<Integer> getPartIds(UUID uuid, Integer chapterId) {
        return super.get(Column.Story.PART_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID),
                new TableEntry<>(uuid, Column.Story.USER_UUID));
    }

    public Integer getSectionId(UUID uuid, Integer chapterId, Integer partId) {
        return super.getFirst(Column.Story.SECTION_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID),
                new TableEntry<>(partId, Column.Story.PART_ID), new TableEntry<>(uuid, Column.Story.USER_UUID));
    }

    public void setSectionId(UUID uuid, Integer chapterId, Integer partId, Integer sectionId) {
        if (super.getFirst(Column.Story.SECTION_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID),
                new TableEntry<>(partId, Column.Story.PART_ID), new TableEntry<>(uuid, Column.Story.USER_UUID)) != null) {
            super.set(sectionId, Column.Story.SECTION_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID),
                    new TableEntry<>(partId, Column.Story.PART_ID), new TableEntry<>(uuid, Column.Story.USER_UUID));
        } else {
            this.addStoryUser(uuid, chapterId, partId, sectionId);
        }
    }

    public void addStoryUser(UUID uuid, Integer chapterId, Integer partId, Integer sectionId) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(chapterId, Column.Story.CHAPTER_ID),
                        new TableEntry<>(partId, Column.Story.PART_ID), new TableEntry<>(uuid, Column.Story.USER_UUID)),
                new TableEntry<>(sectionId, Column.Story.SECTION_ID));
    }

    public void removeStoryUser(UUID uuid, Integer chapterId, Integer partId) {
        super.deleteEntry(new TableEntry<>(uuid, Column.Story.USER_UUID), new TableEntry<>(chapterId,
                Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID));
    }
}
