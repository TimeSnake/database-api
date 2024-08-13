/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column.Game;
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

    ColumnMap map = this.getDatabase().getFirstWithKey(
        Set.of(Game.DISPLAY_NAME, Game.TEXT_COLOR, Game.HEAD_LINE, Game.ITEM, Game.SLOT,
            Game.ENABLED, Game.MAX_PLAYERS, Game.MAPS, Game.KITS, Game.STATISTICS,
            Game.TEXTURE_PACK_LINK, Game.TEXTURE_PACK_HASH, Game.PLAYER_TRACKING_RANGE, Game.MAX_HEALTH,
            Game.VIEW_DISTANCE, Game.OLD_PVP, Game.CREATION_REQUESTABLE, Game.OWNABLE,
            Game.ALLOW_NETHER_END));

    this.name = database.getName();
    this.displayName = map.get(Game.DISPLAY_NAME);
    this.textColor = map.get(Game.TEXT_COLOR);
    this.headLine = map.get(Game.HEAD_LINE);
    this.itemName = map.get(Game.ITEM);
    this.slot = map.get(Game.SLOT);
    this.enabled = map.get(Game.ENABLED);
    this.maxPlayers = map.get(Game.MAX_PLAYERS);
    this.mapAvailability = map.get(Game.MAPS);
    this.kitAvailability = map.get(Game.KITS);
    this.statistics = map.get(Game.STATISTICS);
    this.texturePackLink = map.get(Game.TEXTURE_PACK_LINK);
    this.texturePackHash = map.get(Game.TEXTURE_PACK_HASH);
    this.playerTrackingRange = map.get(Game.PLAYER_TRACKING_RANGE);
    this.maxHealth = map.get(Game.MAX_HEALTH);
    this.viewDistance = map.get(Game.VIEW_DISTANCE);
    this.oldPvPAvailability = map.get(Game.OLD_PVP);
    this.creationRequestable = map.get(Game.CREATION_REQUESTABLE);
    this.ownable = map.get(Game.OWNABLE);
    this.allowNetherAndEnd = map.get(Game.ALLOW_NETHER_END);
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
