/*
 * timesnake.database-api.main
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

import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class DbStoryUser implements de.timesnake.database.util.story.DbStoryUser {

    private final UUID uuid;
    private final UserBoughtTable boughtTable;
    private final UserQuestTable questTable;

    protected DbStoryUser(UUID uuid, UserBoughtTable boughtTable, UserQuestTable questTable) {
        this.uuid = uuid;
        this.boughtTable = boughtTable;
        this.questTable = questTable;
    }

    @Nullable
    @Override
    public Set<Integer> getChapterIds() {
        return this.questTable.getBookIds(this.uuid);
    }

    @Nullable
    @Override
    public Set<String> getChapterIds(Integer bookId) {
        return this.questTable.getChapterIds(this.uuid, bookId);
    }

    @Nullable
    @Override
    public String getQuestName(Integer bookId, String chapterName) {
        return this.questTable.getQuestName(this.uuid, bookId, chapterName);
    }

    @Override
    public void setQuestName(Integer bookId, String chapterName, String questName) {
        this.questTable.setQuestName(this.uuid, bookId, chapterName, questName);
    }

    @Nullable
    @Override
    public Set<String> getBoughtChapters(Integer bookId) {
        return this.boughtTable.getBoughtChapters(this.uuid, bookId);
    }

    @Override
    public void addBoughtChapter(Integer bookId, String chapterName) {
        this.boughtTable.addBoughtPart(this.uuid, bookId, chapterName);
    }

    @Override
    public void removeBoughtChapter(Integer bookId, String chapterName) {
        this.boughtTable.removeBoughtChapter(this.uuid, bookId, chapterName);
    }
}
