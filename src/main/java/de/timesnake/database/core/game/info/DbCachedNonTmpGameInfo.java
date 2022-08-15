package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.game.DbNonTmpGameInfo;
import de.timesnake.database.util.object.ColumnMap;

import java.util.Set;

public class DbCachedNonTmpGameInfo extends DbCachedGameInfo implements DbNonTmpGameInfo {

    protected boolean generateable;
    protected boolean allowAutoDelete;
    protected boolean ownable;

    public DbCachedNonTmpGameInfo(de.timesnake.database.core.game.info.DbNonTmpGameInfo database) {
        super(database);

        ColumnMap map = this.getDatabase().getFirstWithKey(Set.of(Column.Game.DISPLAY_NAME, Column.Game.TEXT_COLOR,
                Column.Game.HEAD_LINE, Column.Game.ITEM, Column.Game.SLOT, Column.Game.MAX_PLAYERS, Column.Game.MAPS,
                Column.Game.KITS, Column.Game.STATISTICS, Column.Game.TEXTURE_PACK_LINK, Column.Game.PLAYER_TRACKING_RANGE,
                Column.Game.GENERATEABLE, Column.Game.ALLOW_AUTO_DELETE, Column.Game.OWNABLE));

        this.name = database.getName();
        this.displayName = map.get(Column.Game.DISPLAY_NAME);
        this.textColor = map.get(Column.Game.TEXT_COLOR);
        this.headLine = map.get(Column.Game.HEAD_LINE);
        this.itemName = map.get(Column.Game.ITEM);
        this.slot = map.get(Column.Game.SLOT);
        this.maxPlayers = map.get(Column.Game.MAX_PLAYERS);
        this.mapAvailability = map.get(Column.Game.MAPS);
        this.kitAvailability = map.get(Column.Game.KITS);
        this.statistics = map.get(Column.Game.STATISTICS);
        this.texturePackLink = map.get(Column.Game.TEXTURE_PACK_LINK);
        this.playerTrackingRange = map.get(Column.Game.PLAYER_TRACKING_RANGE);
        this.generateable = map.get(Column.Game.GENERATEABLE);
        this.allowAutoDelete = map.get(Column.Game.ALLOW_AUTO_DELETE);
        this.ownable = map.get(Column.Game.OWNABLE);
    }

    @Override
    protected de.timesnake.database.core.game.info.DbNonTmpGameInfo getDatabase() {
        return (de.timesnake.database.core.game.info.DbNonTmpGameInfo) super.getDatabase();
    }

    @Override
    public boolean isGenerateable() {
        return this.generateable;
    }

    @Override
    public void setGenerateable(Boolean generateable) {
        this.generateable = generateable;
        this.getDatabase().setGenerateable(generateable);
    }

    @Override
    public boolean isAutoDeleteAllowed() {
        return this.allowAutoDelete;
    }

    @Override
    public void allowAutoDelete(Boolean allowAutoDelete) {
        this.allowAutoDelete = allowAutoDelete;
        this.getDatabase().allowAutoDelete(allowAutoDelete);
    }

    @Override
    public boolean isOwnable() {
        return this.ownable;
    }

    @Override
    public void setOwnable(Boolean ownable) {
        this.ownable = ownable;
        this.getDatabase().setOwnable(ownable);
    }

    @Override
    public DbNonTmpGameInfo toDatabase() {
        return this.getDatabase();
    }

    @Override
    public DbNonTmpGameInfo toLocal() {
        return this.getDatabase().toLocal();
    }
}
