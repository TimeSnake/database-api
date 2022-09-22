package de.timesnake.database.core.story;

import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class DbStoryUser implements de.timesnake.database.util.story.DbStoryUser {

    private final UUID uuid;
    private final UserBoughtTable boughtTable;
    private final UserCheckpointsTable checkpointsTable;

    protected DbStoryUser(UUID uuid, UserBoughtTable boughtTable, UserCheckpointsTable checkpointsTable) {
        this.uuid = uuid;
        this.boughtTable = boughtTable;
        this.checkpointsTable = checkpointsTable;
    }

    @Nullable
    @Override
    public Set<Integer> getChapterIds() {
        return this.checkpointsTable.getChapterIds(this.uuid);
    }

    @Nullable
    @Override
    public Set<Integer> getPartIds(Integer chapterId) {
        return this.checkpointsTable.getPartIds(this.uuid, chapterId);
    }

    @Nullable
    @Override
    public Integer getSectionId(Integer chapterId, Integer partId) {
        return this.checkpointsTable.getSectionId(this.uuid, chapterId, partId);
    }

    @Override
    public void setSectionId(Integer chapterId, Integer partId, Integer sectionId) {
        this.checkpointsTable.setSectionId(this.uuid, chapterId, partId, sectionId);
    }

    @Nullable
    @Override
    public Set<Integer> getBoughtParts(Integer chapterId) {
        return this.boughtTable.getBoughtParts(this.uuid, chapterId);
    }

    @Override
    public void addBoughtPart(Integer chapterId, Integer partId) {
        this.boughtTable.addBoughtPart(this.uuid, chapterId, partId);
    }

    @Override
    public void removeBoughtChapter(Integer chapterId, Integer partId) {
        this.boughtTable.removeBoughtChapter(this.uuid, chapterId, partId);
    }
}
