/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.story;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    public Set<Integer> getChapterIds(UUID uuid) {
        return super.get(Column.Story.CHAPTER_ID, new TableEntry<>(uuid, Column.Story.USER_UUID));
    }

    @Nullable
    public Set<Integer> getPartIds(UUID uuid, Integer chapterId) {
        return super.get(Column.Story.PART_ID, new TableEntry<>(chapterId, Column.Story.CHAPTER_ID),
                new TableEntry<>(uuid, Column.Story.USER_UUID));
    }

    @Nullable
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
