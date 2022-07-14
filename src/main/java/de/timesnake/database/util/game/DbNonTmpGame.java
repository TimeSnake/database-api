package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;

public interface DbNonTmpGame extends DbGame, DbNonTmpGameInfo, DbCached<DbNonTmpGame> {

    @Override
    DbNonTmpGameInfo getInfo();
}
