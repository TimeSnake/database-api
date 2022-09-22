package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.game.DbNonTmpGameInfo;
import de.timesnake.database.util.object.ColumnMap;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DbCachedNonTmpGameInfo extends DbCachedGameInfo implements DbNonTmpGameInfo {

    protected boolean creationRequestable;
    protected boolean ownable;
    protected boolean allowNetherAndEnd;

    public DbCachedNonTmpGameInfo(de.timesnake.database.core.game.info.DbNonTmpGameInfo database) {
        super(database);

        ColumnMap map = this.getDatabase().getFirstWithKey(Set.of(Column.Game.DISPLAY_NAME, Column.Game.TEXT_COLOR,
                Column.Game.HEAD_LINE, Column.Game.ITEM, Column.Game.SLOT, Column.Game.MAX_PLAYERS, Column.Game.MAPS,
                Column.Game.KITS, Column.Game.STATISTICS, Column.Game.TEXTURE_PACK_LINK, Column.Game.PLAYER_TRACKING_RANGE,
                Column.Game.CREATION_REQUESTABLE, Column.Game.OWNABLE, Column.Game.ALLOW_NETHER_END));

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
        this.maxHealth = map.get(Column.Game.MAX_HEALTH);
        this.creationRequestable = map.get(Column.Game.CREATION_REQUESTABLE);
        this.ownable = map.get(Column.Game.OWNABLE);
        this.allowNetherAndEnd = map.get(Column.Game.ALLOW_NETHER_END);
    }

    @Override
    protected de.timesnake.database.core.game.info.DbNonTmpGameInfo getDatabase() {
        return (de.timesnake.database.core.game.info.DbNonTmpGameInfo) super.getDatabase();
    }

    @Override
    public boolean isCreationRequestable() {
        return this.creationRequestable;
    }

    @Override
    public void setCreationRequestable(Boolean creationRequestable) {
        this.creationRequestable = creationRequestable;
        this.getDatabase().setCreationRequestable(creationRequestable);
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
    public boolean isNetherAndEndAllowed() {
        return this.allowNetherAndEnd;
    }

    @Override
    public void allowNetherAndEnd(Boolean allow) {
        this.allowNetherAndEnd = allow;
        this.getDatabase().allowNetherAndEnd(allow);
    }

    @NotNull
    @Override
    public DbNonTmpGameInfo toDatabase() {
        return this.getDatabase();
    }

    @NotNull
    @Override
    public DbNonTmpGameInfo toLocal() {
        return this.getDatabase().toLocal();
    }
}
