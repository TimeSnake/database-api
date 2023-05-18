/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core;

import de.timesnake.database.core.decoration.DatabaseDecoration;
import de.timesnake.database.core.game.DatabaseGames;
import de.timesnake.database.core.game.kit.DatabaseKits;
import de.timesnake.database.core.game.lounge.DatabaseLounges;
import de.timesnake.database.core.game.map.DatabaseMaps;
import de.timesnake.database.core.game.statistic.DatabaseGameStatistics;
import de.timesnake.database.core.game.team.DatabaseTeams;
import de.timesnake.database.core.group.DatabaseGroups;
import de.timesnake.database.core.hungergames.DatabaseHungerGames;
import de.timesnake.database.core.network.DatabaseNetwork;
import de.timesnake.database.core.permisson.DatabasePermissions;
import de.timesnake.database.core.server.DatabaseServers;
import de.timesnake.database.core.story.DatabaseStory;
import de.timesnake.database.core.support.DatabaseSupport;
import de.timesnake.database.core.user.DatabaseUsers;
import de.timesnake.database.util.object.DatabaseConnector;
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
    private static final String GAMES_NAME = "game_info";
    private static final String SUPPORT_NAME = "support";
    private static final String HUNGER_GAMES_NAME = "hungergames";
    private static final String TEAMS_NAME = "game_teams";
    private static final String MAPS_NAME = "game_maps";
    private static final String KITS_NAME = "game_kits";
    private static final String GAME_STATISTICS_NAME = "game_statistics";
    private static final String LOUNGES_NAME = "game_lounges";
    private static final String DECORATIONS_NAME = "decorations";
    private static final String STORY_NAME = "story";
    private static final String NETWORK_NAME = "network";


    private final ConcurrentHashMap<String, DatabaseConnector> databasesByName = new ConcurrentHashMap<>();

    private DatabaseServers servers;
    private DatabaseGroups groups;
    private DatabasePermissions permissions;
    private DatabaseUsers users;
    private DatabaseGames games;
    private DatabaseSupport support;
    private DatabaseHungerGames hungerGames;
    private DatabaseTeams gameTeams;
    private DatabaseMaps gameMaps;
    private DatabaseKits gameKits;
    private DatabaseGameStatistics gameStatistics;
    private DatabaseLounges lounges;
    private DatabaseDecoration decorations;
    private DatabaseStory story;
    private DatabaseNetwork network;

    private boolean isConnected = false;

    public DatabaseManager() {

    }

    @Override
    public boolean connect(DatabaseConfig config) throws DatabaseNotConfiguredException {
        LOGGER.info("Connecting to database...");

        String user = config.getString("database.user");
        String password = config.getString("database.password");
        String options = config.getString("database.options");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            DatabaseConnector connection = new DatabaseConnector("mysql",
                    config.getString("database.url"),
                    options, user, password);
            connection.connect();

            connection.createDatabase(config.getDatabaseName(SERVERS_NAME));
            connection.createDatabase(config.getDatabaseName(USERS_NAME));
            connection.createDatabase(config.getDatabaseName(GROUPS_NAME));
            connection.createDatabase(config.getDatabaseName(PERMISSIONS_NAME));
            connection.createDatabase(config.getDatabaseName(GAMES_NAME));
            connection.createDatabase(config.getDatabaseName(SUPPORT_NAME));
            connection.createDatabase(config.getDatabaseName(HUNGER_GAMES_NAME));
            connection.createDatabase(config.getDatabaseName(TEAMS_NAME));
            connection.createDatabase(config.getDatabaseName(MAPS_NAME));
            connection.createDatabase(config.getDatabaseName(KITS_NAME));
            connection.createDatabase(config.getDatabaseName(LOUNGES_NAME));
            connection.createDatabase(config.getDatabaseName(DECORATIONS_NAME));
            connection.createDatabase(config.getDatabaseName(STORY_NAME));
            connection.createDatabase(config.getDatabaseName(GAME_STATISTICS_NAME));
            connection.createDatabase(config.getDatabaseName(NETWORK_NAME));

            servers = new DatabaseServers(config.getDatabaseName(SERVERS_NAME),
                    config.getDatabaseUrl(SERVERS_NAME),
                    options, user, password, "lobbys", "non_tmp_games", "lounges",
                    "tmp_games", "builds", "build_worlds");
            servers.connect();
            this.databasesByName.put(SERVERS_NAME, servers);

            users = new DatabaseUsers(config.getDatabaseName(USERS_NAME),
                    config.getDatabaseUrl(USERS_NAME), options, user, password,
                    "info", "punishments", "mails", "display_groups");
            users.connect();
            this.databasesByName.put(USERS_NAME, users);

            groups = new DatabaseGroups(config.getDatabaseName(GROUPS_NAME),
                    config.getDatabaseUrl(GROUPS_NAME),
                    options, user, password,
                    "perm_groups", "display_groups");
            groups.connect();
            this.databasesByName.put(GROUPS_NAME, groups);

            permissions = new DatabasePermissions(config.getDatabaseName(PERMISSIONS_NAME),
                    config.getDatabaseUrl(PERMISSIONS_NAME),
                    options, user, password, "permissions");
            permissions.connect();
            this.databasesByName.put(PERMISSIONS_NAME, permissions);

            games = new DatabaseGames(config.getDatabaseName(GAMES_NAME),
                    config.getDatabaseUrl(GAMES_NAME),
                    options, user, password, "non_tmp_infos", "tmp_infos");
            games.connect();
            this.databasesByName.put(GAMES_NAME, games);

            gameTeams = new DatabaseTeams(config.getDatabaseName(TEAMS_NAME),
                    config.getDatabaseUrl(TEAMS_NAME),
                    options, user, password);
            gameTeams.connect();
            this.databasesByName.put(TEAMS_NAME, gameTeams);

            gameMaps = new DatabaseMaps(config.getDatabaseName(MAPS_NAME),
                    config.getDatabaseUrl(MAPS_NAME),
                    options, user, password, "info", "locations", "authors");
            gameMaps.connect();
            this.databasesByName.put(MAPS_NAME, gameMaps);

            gameKits = new DatabaseKits(config.getDatabaseName(KITS_NAME),
                    config.getDatabaseUrl(KITS_NAME),
                    options, user, password);
            gameKits.connect();
            this.databasesByName.put(KITS_NAME, gameKits);

            gameStatistics = new DatabaseGameStatistics(
                    config.getDatabaseName(GAME_STATISTICS_NAME),
                    config.getDatabaseUrl(GAME_STATISTICS_NAME), options, user, password,
                    "user_statistics", "statistic_types");
            gameStatistics.connect();
            this.databasesByName.put(GAME_STATISTICS_NAME, gameStatistics);

            lounges = new DatabaseLounges(config.getDatabaseName(LOUNGES_NAME),
                    config.getDatabaseUrl(LOUNGES_NAME),
                    options, user, password, "maps", "map_displays");
            lounges.connect();
            this.databasesByName.put(LOUNGES_NAME, lounges);

            support = new DatabaseSupport(config.getDatabaseName(SUPPORT_NAME),
                    config.getDatabaseUrl(SUPPORT_NAME),
                    options, user, password, "tickets");
            support.connect();
            this.databasesByName.put(SUPPORT_NAME, support);

            hungerGames = new DatabaseHungerGames(config.getDatabaseName(HUNGER_GAMES_NAME),
                    config.getDatabaseUrl(HUNGER_GAMES_NAME), options, user, password, "items");
            hungerGames.connect();
            this.databasesByName.put(HUNGER_GAMES_NAME, hungerGames);

            decorations = new DatabaseDecoration(config.getDatabaseName(DECORATIONS_NAME),
                    config.getDatabaseUrl(DECORATIONS_NAME), options, user, password, "heads");
            decorations.connect();
            this.databasesByName.put(DECORATIONS_NAME, decorations);

            story = new DatabaseStory(config.getDatabaseName(STORY_NAME),
                    config.getDatabaseUrl(STORY_NAME),
                    options, user, password, "user_quests", "user_bought");
            story.connect();
            this.databasesByName.put(STORY_NAME, story);

            network = new DatabaseNetwork(config.getDatabaseName(NETWORK_NAME),
                    config.getDatabaseUrl(NETWORK_NAME),
                    options, user, password,
                    "files");
            network.connect();
            this.databasesByName.put(NETWORK_NAME, network);

            isConnected = true;
            LOGGER.info("Connected to database");
            return true;
        } catch (SQLException e) {
            LOGGER.warning("Could not connect to database: " + e.getMessage());
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
        LOGGER.info("Created tables");
    }

    @Override
    public void closeWithBackups() {
        if (isConnected) {
            for (DatabaseConnector database : this.databasesByName.values()) {
                database.backupTables();
            }
        }

        this.close();
        LOGGER.info("Saved tables and closed connections");
    }

    @Override
    public void close() {
        for (DatabaseConnector database : this.databasesByName.values()) {
            database.close();
        }

        isConnected = false;
        LOGGER.info("Closed connections");
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

    public DatabaseKits getGameKits() {
        return gameKits;
    }

    public DatabaseMaps getGameMaps() {
        return gameMaps;
    }

    public DatabaseTeams getGameTeams() {
        return gameTeams;
    }

    public DatabaseGameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public DatabaseLounges getLounges() {
        return lounges;
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


}
