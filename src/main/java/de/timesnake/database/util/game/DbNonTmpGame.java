package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;
import org.jetbrains.annotations.NotNull;

public interface DbNonTmpGame extends DbGame, DbNonTmpGameInfo, DbCached<DbNonTmpGame> {

    @NotNull
    @Override
    DbNonTmpGameInfo getInfo();
}
