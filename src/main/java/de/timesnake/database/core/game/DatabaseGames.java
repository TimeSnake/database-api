/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.NonTmpGameInfoTable;
import de.timesnake.database.core.game.info.TmpGameInfoTable;
import de.timesnake.database.core.game.kit.KitTable;
import de.timesnake.database.core.game.lounge.DbLoungeMapDisplayTable;
import de.timesnake.database.core.game.lounge.DbLoungeMapTable;
import de.timesnake.database.core.game.map.*;
import de.timesnake.database.core.game.server_options.GameServerOptionTable;
import de.timesnake.database.core.game.statistic.StatisticTable;
import de.timesnake.database.core.game.team.TeamTable;
import de.timesnake.database.util.game.DbLoungeMap;
import de.timesnake.database.util.game.DbTeam;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DatabaseGames extends DatabaseConnector implements
    de.timesnake.database.util.game.DatabaseGames {

  protected final NonTmpGameInfoTable nonTmpGamesInfoTable;
  protected final TmpGameInfoTable tmpGamesInfoTable;

  protected final MapInfoTable mapInfoTable;
  protected final MapLocationTable mapLocationTable;
  protected final MapAuthorTable mapAuthorTable;
  protected final MapPropertyTable mapPropertyTable;

  protected final TeamTable teamTable;
  protected final KitTable kitTable;
  protected final StatisticTable statisticTable;

  protected final GameServerOptionTable gameServerOptionTable;

  protected final DbLoungeMapTable loungeMapTable;
  protected final DbLoungeMapDisplayTable loungeMapDisplayTable;

  public DatabaseGames(String name, String url, String options, String user, String password,
                       String nonTmpGamesInfoTableName, String tmpGamesInfoTableName, String mapInfoTableName,
                       String mapLocationTableName, String mapAuthorTableName, String mapPropertyTableName,
                       String teamTableName, String kitsTableName, String statisticsTypesTableName,
                       String statisticsUserTableName, String gameServerOptionTableName,
                       String loungeMapTableName, String loungeMapDisplayTableName) {
    super(name, url, options, user, password);
    this.nonTmpGamesInfoTable = new NonTmpGameInfoTable(this, nonTmpGamesInfoTableName);
    this.tmpGamesInfoTable = new TmpGameInfoTable(this, tmpGamesInfoTableName);

    this.mapInfoTable = new MapInfoTable(this, mapInfoTableName);
    this.mapLocationTable = new MapLocationTable(this, mapLocationTableName);
    this.mapAuthorTable = new MapAuthorTable(this, mapAuthorTableName);
    this.mapPropertyTable = new MapPropertyTable(this, mapPropertyTableName);

    this.teamTable = new TeamTable(this, teamTableName);
    this.kitTable = new KitTable(this, kitsTableName);
    this.statisticTable = new StatisticTable(this, statisticsTypesTableName, statisticsUserTableName);

    this.gameServerOptionTable = new GameServerOptionTable(this, gameServerOptionTableName);

    this.loungeMapTable = new DbLoungeMapTable(this, loungeMapTableName);
    this.loungeMapDisplayTable = new DbLoungeMapDisplayTable(this, loungeMapDisplayTableName);
  }

  @Override
  public void createTables() {
    this.nonTmpGamesInfoTable.create();
    this.tmpGamesInfoTable.create();

    this.mapInfoTable.create();
    this.mapLocationTable.create();
    this.mapAuthorTable.create();
    this.mapPropertyTable.create();

    this.teamTable.create();
    this.kitTable.create();
    this.statisticTable.create();

    this.gameServerOptionTable.create();

    this.loungeMapTable.create();
    this.loungeMapDisplayTable.create();
  }

  @Override
  public void saveTables() {
    this.nonTmpGamesInfoTable.save();
    this.tmpGamesInfoTable.save();

    this.mapInfoTable.save();
    this.mapLocationTable.save();
    this.mapAuthorTable.save();
    this.mapPropertyTable.save();

    this.teamTable.save();
    this.kitTable.save();
    this.statisticTable.save();

    this.gameServerOptionTable.save();

    this.loungeMapTable.save();
    this.loungeMapDisplayTable.save();
  }

  @Override
  public DbGame getGame(String gameName) {
    if (this.nonTmpGamesInfoTable.containsGame(gameName)) {
      return new DbNonTmpGame(this, gameName, this.nonTmpGamesInfoTable.getGame(gameName),
          this.kitTable, this.statisticTable, this.gameServerOptionTable);
    } else if (this.tmpGamesInfoTable.containsGame(gameName)) {
      return new DbTmpGame(this, gameName, this.tmpGamesInfoTable.getGame(gameName),
          this.kitTable, this.statisticTable, this.gameServerOptionTable, this.teamTable);
    }
    return null;
  }

  @Override
  public boolean containsGame(String gameName) {
    if (this.nonTmpGamesInfoTable.containsGame(gameName)) {
      return true;
    } else return this.tmpGamesInfoTable.containsGame(gameName);
  }

  @Override
  public Collection<String> getGamesName() {
    List<String> names = new ArrayList<>();
    names.addAll(this.nonTmpGamesInfoTable.getGamesName());
    names.addAll(this.tmpGamesInfoTable.getGamesName());
    return names;
  }

  @Override
  public Collection<DbGame> getGames() {
    List<DbGame> games = new LinkedList<>();
    for (String gameName : this.getGamesName()) {
      games.add(this.getGame(gameName));
    }
    return games;
  }

  public void addGameMap(String gameName, String name) {
    this.mapInfoTable.addMapInfo(gameName, name);
  }

  public void removeMap(String gameName, String name) {
    this.mapInfoTable.removeMapInfo(gameName, name);
    this.mapLocationTable.removeMapLocations(gameName, name);
    this.mapPropertyTable.removeProperties(gameName, name);
    this.mapAuthorTable.removeMapAuthors(gameName, name);
  }

  @NotNull
  public de.timesnake.database.util.game.DbMap getMap(String gameName, String mapName) {
    return new DbMap(gameName, mapName, this.mapInfoTable.getMapInfo(gameName, mapName),
        this.mapLocationTable.getMapLocations(gameName, mapName),
        this.mapAuthorTable, this.mapPropertyTable);
  }

  public List<de.timesnake.database.util.game.DbMap> getMaps(String gameName) {
    List<de.timesnake.database.util.game.DbMap> maps = new ArrayList<>();
    for (DbMapInfo info : this.mapInfoTable.getMaps(gameName)) {
      maps.add(this.getMap(gameName, info.getName()));
    }
    return maps;
  }

  public List<de.timesnake.database.util.game.DbMap> getMaps(String gameName, Integer players) {
    List<de.timesnake.database.util.game.DbMap> maps = new ArrayList<>();
    for (DbMapInfo info : this.mapInfoTable.getMaps(gameName, players)) {
      maps.add(this.getMap(gameName, info.getName()));
    }
    return maps;
  }

  public boolean containsMap(String gameName, String mapName) {
    return this.mapInfoTable.containsMapInfo(gameName, mapName);
  }

  public Set<String> getMapNames(String gameName) {
    return this.mapInfoTable.getMapNames(gameName);
  }

  @NotNull
  public List<DbTeam> getGameTeams(String gameName) {
    return this.teamTable.getTeams(gameName);
  }

  public void deleteGameTeams(DbGame game) {
    TeamTable teamTable = new TeamTable(this, game.getInfo().getName());
    teamTable.delete();
  }

  @Override
  public void addLoungeMap(String name, DbLocation spawn) {
    this.loungeMapTable.addMap(name, spawn);
  }

  @Override
  public void removeLoungeMap(String name) {
    this.loungeMapTable.removeMap(name);
  }


  @Override
  public boolean containsLoungeMap(String name) {
    return this.loungeMapTable.containsMap(name, this.loungeMapDisplayTable);
  }


  @NotNull
  @Override
  public de.timesnake.database.util.game.DbLoungeMap getLoungeMap(String name) {
    return this.loungeMapTable.getMap(name, this.loungeMapDisplayTable);
  }

  @NotNull
  @Override
  public Collection<DbLoungeMap> getLoungeMaps() {
    return this.loungeMapTable.getMaps(this.loungeMapDisplayTable);
  }

  @NotNull
  @Override
  public Collection<DbLoungeMap> getCachedLoungeMaps() {
    return this.loungeMapTable.getCachedMaps(this.loungeMapDisplayTable);
  }

}
