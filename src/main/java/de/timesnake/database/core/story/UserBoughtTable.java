/*
 * workspace.database-api.main
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
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class UserBoughtTable extends TableDDL {

    protected UserBoughtTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Story.USER_UUID, Column.Story.BOOK_ID, Column.Story.CHAPTER_NAME);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @Nullable
    public Set<String> getBoughtChapters(UUID uuid, Integer bookId) {
        return super.get(Column.Story.CHAPTER_NAME, new Entry<>(uuid, Column.Story.USER_UUID),
                new Entry<>(bookId, Column.Story.BOOK_ID));
    }

    public void addBoughtPart(UUID uuid, Integer bookId, String chapterName) {
        super.addEntry(new PrimaryEntries(new Entry<>(uuid, Column.Story.USER_UUID), new Entry<>(bookId,
                Column.Story.BOOK_ID), new Entry<>(chapterName, Column.Story.CHAPTER_NAME)));
    }

    public void removeBoughtChapter(UUID uuid, Integer bookId, String chapterName) {
        super.deleteEntry(new Entry<>(uuid, Column.Story.USER_UUID), new Entry<>(bookId,
                Column.Story.BOOK_ID), new Entry<>(chapterName, Column.Story.CHAPTER_NAME));
    }
}
