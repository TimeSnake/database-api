package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class DbNonTmpGameInfo extends DbGameInfo implements de.timesnake.database.util.game.DbNonTmpGameInfo {

    public DbNonTmpGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, gameName);
    }

    @Override
    public boolean isGenerateable() {
        return super.getFirstWithKey(Column.Game.GENERATEABLE);
    }

    @Override
    public void setGenerateable(Boolean generateable) {
        super.setWithKey(generateable, Column.Game.GENERATEABLE);
    }

    @Override
    public boolean isAutoDeleteAllowed() {
        return super.getFirstWithKey(Column.Game.ALLOW_AUTO_DELETE);
    }

    @Override
    public void allowAutoDelete(Boolean allowAutoDelete) {
        super.setWithKey(allowAutoDelete, Column.Game.ALLOW_AUTO_DELETE);
    }

    @Override
    public boolean isOwnable() {
        return super.getFirstWithKey(Column.Game.OWNABLE);
    }

    @Override
    public void setOwnable(Boolean ownable) {
        super.setWithKey(ownable, Column.Game.OWNABLE);
    }

    @Override
    public de.timesnake.database.util.game.DbNonTmpGameInfo toDatabase() {
        return this;
    }

    @Override
    public de.timesnake.database.util.game.DbNonTmpGameInfo toLocal() {
        return new DbCachedNonTmpGameInfo(this);
    }
}
