/*
 * Copyright (C) 2023 timesnake
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
