package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;

public interface DbNonTmpGameInfo extends DbGameInfo {

    boolean isGenerateable();

    @NotCached
    void setGenerateable(Boolean generateable);

    boolean isAutoDeleteAllowed();

    @NotCached
    void allowAutoDelete(Boolean allowAutoDelete);

    boolean isOwnable();

    @NotCached
    void setOwnable(Boolean ownable);

    @Override
    DbNonTmpGameInfo toDatabase();

    @Override
    DbNonTmpGameInfo toLocal();
}
