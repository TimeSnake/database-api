/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core;

import de.timesnake.database.core.anticheat.DatabaseAntiCheat;
import de.timesnake.database.core.decoration.DatabaseDecoration;
import de.timesnake.database.core.game.DatabaseGames;
import de.timesnake.database.core.group.DatabaseGroups;
import de.timesnake.database.core.network.DatabaseNetwork;
import de.timesnake.database.core.permisson.DatabasePermissions;
import de.timesnake.database.core.pet.DatabasePets;
import de.timesnake.database.core.server.DatabaseServers;
import de.timesnake.database.core.story.DatabaseStory;
import de.timesnake.database.core.support.DatabaseSupport;
import de.timesnake.database.core.survival_games.DatabaseSurvivalGames;
import de.timesnake.database.core.user.DatabaseUsers;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseManager implements de.timesnake.database.util.Database {

  public static final int DEFAULT_MAX_IDLE_CONNECTIONS = 5;
  public static final int USERS_MAX_IDLE_CONNECTIONS = 15;
  public static final int SERVERS_MAX_IDLE_CONNECTIONS = 10;

  private static DatabaseManager instance = new DatabaseManager();

  public static void setInstance(DatabaseManager databaseManager) {
    instance = databaseManager;
  }

  public static DatabaseManager getInstance() {
    return instance;
  }

  private static final String SERVERS_NAME = "servers";
  private static final String USERS_NAME = "users";
  private static final String GROUPS_NAME = "groups";
  private static final String PERMISSIONS_NAME = "permissions";
  private static final String GAMES_NAME = "games";
  private static final String SUPPORT_NAME = "support";
  private static final String HUNGER_GAMES_NAME = "survival_games";
  private static final String GAME_STATISTICS_NAME = "game_statistics";
  private static final String DECORATIONS_NAME = "decorations";
  private static final String STORY_NAME = "story";
  private static final String NETWORK_NAME = "network";
  private static final String PET_NAME = "pets";
  private static final String ANTI_CHEAT_NAME = "anticheat";


  private final ConcurrentHashMap<String, DatabaseConnector> databasesByName = new ConcurrentHashMap<>();

  private DatabaseServers servers;
  private DatabaseGroups groups;
  private DatabasePermissions permissions;
  private DatabaseUsers users;
  private DatabaseGames games;
  private DatabaseSupport support;
  private DatabaseSurvivalGames hungerGames;
  private DatabaseDecoration decorations;
  private DatabaseStory story;
  private DatabaseNetwork network;
  private DatabasePets pets;
  private DatabaseAntiCheat antiCheat;

  private boolean isConnected = false;

  public DatabaseManager() {

  }

  @Override
  public boolean connect(DatabaseConfig config) throws DatabaseNotConfiguredException {
    Database.LOGGER.info("Connecting to database...");

    String user = config.getString("database.user");
    if (user == null) {
      user = "root";
    }
    String password = config.getString("database.password");
    if (password == null) {
      password = System.getenv("MYSQL_ROOT_PASSWORD");
    }
    String options = config.getString("database.options");
    String url = config.getString("database.url");

    DatabaseManager.loadDrivers();

    try {
      DatabaseConnector connection = new DatabaseConnector("mysql", url, options, user, password);
      connection.connect();

      connection.createDatabase(SERVERS_NAME, USERS_NAME, GROUPS_NAME, PERMISSIONS_NAME, GAMES_NAME, SUPPORT_NAME,
          HUNGER_GAMES_NAME, DECORATIONS_NAME, STORY_NAME,
          GAME_STATISTICS_NAME, NETWORK_NAME, PET_NAME, ANTI_CHEAT_NAME);

      servers = new DatabaseServers(SERVERS_NAME, url, options, user, password, "lobby", "non_tmp_game", "lounge",
          "tmp_game", "build", "build_world");
      servers.connect();
      this.databasesByName.put(SERVERS_NAME, servers);

      users = new DatabaseUsers(USERS_NAME, url, options, user, password, "info", "punishment", "mail",
          "display_group");
      users.connect();
      this.databasesByName.put(USERS_NAME, users);

      groups = new DatabaseGroups(GROUPS_NAME, url, options, user, password, "perm_group", "display_group");
      groups.connect();
      this.databasesByName.put(GROUPS_NAME, groups);

      permissions = new DatabasePermissions(PERMISSIONS_NAME, url, options, user, password, "permission");
      permissions.connect();
      this.databasesByName.put(PERMISSIONS_NAME, permissions);

      games = new DatabaseGames(GAMES_NAME, url, options, user, password,
          "non_tmp_game",
          "tmp_game",
          "map",
          "map_location",
          "map_author",
          "map_property",
          "team",
          "kit",
          "statistic_type",
          "statistic_user",
          "server_option",
          "lounge_map",
          "lounge_map_display");
      games.connect();
      this.databasesByName.put(GAMES_NAME, games);

      support = new DatabaseSupport(SUPPORT_NAME, url, options, user, password, "ticket");
      support.connect();
      this.databasesByName.put(SUPPORT_NAME, support);

      hungerGames = new DatabaseSurvivalGames(HUNGER_GAMES_NAME, url, options, user, password, "item");
      hungerGames.connect();
      this.databasesByName.put(HUNGER_GAMES_NAME, hungerGames);

      decorations = new DatabaseDecoration(DECORATIONS_NAME, url, options, user, password, "head");
      decorations.connect();
      this.databasesByName.put(DECORATIONS_NAME, decorations);

      story = new DatabaseStory(STORY_NAME, url, options, user, password, "user_quest", "user_bought");
      story.connect();
      this.databasesByName.put(STORY_NAME, story);

      network = new DatabaseNetwork(NETWORK_NAME, url, options, user, password, "file", "variable");
      network.connect();
      this.databasesByName.put(NETWORK_NAME, network);

      pets = new DatabasePets(PET_NAME, url, options, user, password, "user_pet", "pet_property");
      pets.connect();
      this.databasesByName.put(PET_NAME, pets);

      antiCheat = new DatabaseAntiCheat(ANTI_CHEAT_NAME, url, options, user, password, "word_blacklist");
      antiCheat.connect();
      this.databasesByName.put(ANTI_CHEAT_NAME, antiCheat);

      isConnected = true;
      Database.LOGGER.info("Connected to database");
      return true;
    } catch (SQLException e) {
      Database.LOGGER.warn("Could not connect to database: {}", e.getMessage());
      return false;
    }
  }

  @Override
  public void createTables() {
    if (isConnected) {
      for (DatabaseConnector database : this.databasesByName.values()) {
        database.createTables();
      }
    }
    Database.LOGGER.info("Created tables");
  }

  @Override
  public void closeWithBackups() {
    if (isConnected) {
      for (DatabaseConnector database : this.databasesByName.values()) {
        database.saveTables();
      }
    }

    this.close();
    Database.LOGGER.info("Saved tables and closed connections");
  }

  @Override
  public void close() {
    for (DatabaseConnector database : this.databasesByName.values()) {
      database.close();
    }

    isConnected = false;
    Database.LOGGER.info("Closed connections");
  }

  public void handleSQLException(SQLException e) {
    e.printStackTrace();
  }

  public de.timesnake.database.util.server.DatabaseServers getServers() {
    return servers;
  }

  public DatabaseGroups getGroups() {
    return groups;
  }

  public DatabasePermissions getPermissions() {
    return permissions;
  }

  public DatabaseUsers getUsers() {
    return users;
  }

  public DatabaseGames getGames() {
    return games;
  }

  public DatabaseSupport getSupport() {
    return support;
  }

  public de.timesnake.database.util.hungergames.DatabaseHungerGames getHungerGames() {
    return hungerGames;
  }

  public DatabaseDecoration getDecorations() {
    return decorations;
  }

  public DatabaseStory getStory() {
    return story;
  }

  public DatabaseNetwork getNetwork() {
    return network;
  }

  public DatabasePets getPets() {
    return pets;
  }

  public DatabaseAntiCheat getAntiCheat() {
    return antiCheat;
  }

  public static void loadDrivers() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      Database.LOGGER.warn("Unable to load database driver class");
      e.printStackTrace();
    }

    try {
      DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
    } catch (SQLException throwables) {
      Database.LOGGER.warn("Unable to register database driver");
      throwables.printStackTrace();
    }
  }

}
