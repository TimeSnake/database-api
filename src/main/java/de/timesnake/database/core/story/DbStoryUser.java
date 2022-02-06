package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Set;
import java.util.UUID;

public class DbStoryUser extends TableQuery implements de.timesnake.database.util.story.DbStoryUser {

    protected DbStoryUser(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.Story.USER_UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Story.CHAPTER_ID) != null;
    }

    @Override
    public Set<Integer> getChapterIds() {
        return super.getWithKey(Column.Story.CHAPTER_ID);
    }

    @Override
    public Set<Integer> getPartIds(Integer chapterId) {
        return super.getWithKey(Column.Story.CHAPTER_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID));
    }

    @Override
    public Integer getSectionId(Integer chapterId, Integer partId) {
        return super.getFirstWithKey(Column.Story.CHAPTER_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID));
    }

    @Override
    public void setSectionId(Integer chapterId, Integer partId, Integer sectionId) {
        super.setWithKey(sectionId, Column.Story.SECTION_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID), new TableEntry<>(partId, Column.Story.PART_ID));
    }
}
