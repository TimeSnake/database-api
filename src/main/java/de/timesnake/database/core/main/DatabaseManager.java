package de.timesnake.database.core.main;

import de.timesnake.database.core.decoration.DatabaseDecoration;
import de.timesnake.database.core.endgame.DatabaseEndGame;
import de.timesnake.database.core.file.DatabaseConfig;
import de.timesnake.database.core.file.DatabaseNotConfiguredException;
import de.timesnake.database.core.game.DatabaseGames;
import de.timesnake.database.core.game.kit.DatabaseKits;
import de.timesnake.database.core.game.lounge.DatabaseLounges;
import de.timesnake.database.core.game.map.DatabaseMaps;
import de.timesnake.database.core.game.statistic.DatabaseGameStatistics;
import de.timesnake.database.core.game.team.DatabaseTeams;
import de.timesnake.database.core.group.perm.DatabaseGroups;
import de.timesnake.database.core.hungergames.DatabaseHungerGames;
import de.timesnake.database.core.network.DatabaseNetwork;
import de.timesnake.database.core.permisson.DatabasePermissions;
import de.timesnake.database.core.server.DatabaseServers;
import de.timesnake.database.core.story.DatabaseStory;
import de.timesnake.database.core.support.DatabaseSupport;
import de.timesnake.database.core.user.DatabaseUsers;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.concurrent.ConcurrentHashMap;

public class DatabaseManager implements de.timesnake.database.util.Database {

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
    private static final String END_GAME_NAME = "endgame";
    private static final String TEAMS_NAME = "game_teams";
    private static final String MAPS_NAME = "game_maps";
    private static final String KITS_NAME = "game_kits";
    private static final String GAME_STATISTICS_NAME = "game_statistics";
    private static final String LOUNGES_NAME = "game_lounges";
    private static final String DECORATIONS_NAME = "decorations";
    private static final String STORY_NAME = "story";
    private static final String NETWORK_NAME = "network";
    private static final DatabaseManager instance = new DatabaseManager();
    private final ConcurrentHashMap<String, DatabaseConnector> databasesByName = new ConcurrentHashMap<>();
    private boolean broadcast = false;
    private DatabaseServers servers;
    private DatabaseGroups groups;
    private DatabasePermissions permissions;
    private DatabaseUsers users;
    private DatabaseGames games;
    private DatabaseSupport support;
    private DatabaseHungerGames hungerGames;
    private DatabaseEndGame endGame;
    private DatabaseTeams gameTeams;
    private DatabaseMaps gameMaps;
    private DatabaseKits gameKits;
    private DatabaseGameStatistics gameStatistics;
    private DatabaseLounges lounges;
    private DatabaseDecoration decorations;
    private DatabaseStory story;
    private DatabaseNetwork network;
    private boolean isConnected = false;

    @Override
    public void connect(DatabaseConfig config) throws DatabaseNotConfiguredException {
        String user = config.getString("database.user");
        String password = config.getString("database.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        DatabaseConnector connection = new DatabaseConnector("mysql", config.getString("database.url"), user, password);
        connection.createDatabase(config.getDatabaseName(SERVERS_NAME));
        connection.createDatabase(config.getDatabaseName(USERS_NAME));
        connection.createDatabase(config.getDatabaseName(GROUPS_NAME));
        connection.createDatabase(config.getDatabaseName(PERMISSIONS_NAME));
        connection.createDatabase(config.getDatabaseName(GAMES_NAME));
        connection.createDatabase(config.getDatabaseName(SUPPORT_NAME));
        connection.createDatabase(config.getDatabaseName(HUNGER_GAMES_NAME));
        connection.createDatabase(config.getDatabaseName(END_GAME_NAME));
        connection.createDatabase(config.getDatabaseName(TEAMS_NAME));
        connection.createDatabase(config.getDatabaseName(MAPS_NAME));
        connection.createDatabase(config.getDatabaseName(KITS_NAME));
        connection.createDatabase(config.getDatabaseName(LOUNGES_NAME));
        connection.createDatabase(config.getDatabaseName(DECORATIONS_NAME));
        connection.createDatabase(config.getDatabaseName(STORY_NAME));
        connection.createDatabase(config.getDatabaseName(GAME_STATISTICS_NAME));
        connection.createDatabase(config.getDatabaseName(NETWORK_NAME));

        servers = new DatabaseServers(config.getDatabaseName(SERVERS_NAME), config.getDatabaseUrl(SERVERS_NAME),
                user, password, "lobbys", "games", "lounges",
                "tempGames", "builds");
        this.databasesByName.put(SERVERS_NAME, servers);

        users = new DatabaseUsers(config.getDatabaseName(USERS_NAME),
                config.getDatabaseUrl(USERS_NAME), user, password, "info", "punishments", "mails");
        this.databasesByName.put(USERS_NAME, users);

        groups = new DatabaseGroups(config.getDatabaseName(GROUPS_NAME), config.getDatabaseUrl(GROUPS_NAME),
                user, password, "perm_groups");
        this.databasesByName.put(GROUPS_NAME, groups);

        permissions = new DatabasePermissions(config.getDatabaseName(PERMISSIONS_NAME),
                config.getDatabaseUrl(PERMISSIONS_NAME),
                user, password, "permissions");
        this.databasesByName.put(PERMISSIONS_NAME, permissions);

        games = new DatabaseGames(config.getDatabaseName(GAMES_NAME), config.getDatabaseUrl(GAMES_NAME),
                user, password, "infos");
        this.databasesByName.put(GAMES_NAME, games);

        gameTeams = new DatabaseTeams(config.getDatabaseName(TEAMS_NAME), config.getDatabaseUrl(TEAMS_NAME),
                user, password);
        this.databasesByName.put(TEAMS_NAME, gameTeams);

        gameMaps = new DatabaseMaps(config.getDatabaseName(MAPS_NAME), config.getDatabaseUrl(MAPS_NAME),
                user, password, "info", "locations", "authors");
        this.databasesByName.put(MAPS_NAME, gameMaps);

        gameKits = new DatabaseKits(config.getDatabaseName(KITS_NAME), config.getDatabaseUrl(KITS_NAME),
                user, password);
        this.databasesByName.put(KITS_NAME, gameKits);

        gameStatistics = new DatabaseGameStatistics(config.getDatabaseName(GAME_STATISTICS_NAME),
                config.getDatabaseUrl(GAME_STATISTICS_NAME), user, password,
                "user_statistics", "statistic_types");
        this.databasesByName.put(GAME_STATISTICS_NAME, gameStatistics);

        lounges = new DatabaseLounges(config.getDatabaseName(LOUNGES_NAME), config.getDatabaseUrl(LOUNGES_NAME),
                user, password, "maps", "map_displays");
        this.databasesByName.put(LOUNGES_NAME, lounges);

        support = new DatabaseSupport(config.getDatabaseName(SUPPORT_NAME), config.getDatabaseUrl(SUPPORT_NAME),
                user, password, "tickets");
        this.databasesByName.put(SUPPORT_NAME, support);

        hungerGames = new DatabaseHungerGames(config.getDatabaseName(HUNGER_GAMES_NAME),
                config.getDatabaseUrl(HUNGER_GAMES_NAME), user, password, "items");
        this.databasesByName.put(HUNGER_GAMES_NAME, hungerGames);

        endGame = new DatabaseEndGame(config.getDatabaseName(END_GAME_NAME), config.getDatabaseUrl(END_GAME_NAME),
                user, password, "worlds");
        this.databasesByName.put(END_GAME_NAME, endGame);

        decorations = new DatabaseDecoration(config.getDatabaseName(DECORATIONS_NAME),
                config.getDatabaseUrl(DECORATIONS_NAME), user, password, "heads");
        this.databasesByName.put(DECORATIONS_NAME, decorations);

        story = new DatabaseStory(config.getDatabaseName(STORY_NAME), config.getDatabaseUrl(STORY_NAME), user, password,
                "user_checkpoints", "user_bought");
        this.databasesByName.put(STORY_NAME, story);

        network = new DatabaseNetwork(config.getDatabaseName(NETWORK_NAME), config.getDatabaseUrl(NETWORK_NAME), user
                , password,
                "files");
        this.databasesByName.put(NETWORK_NAME, network);

        isConnected = true;
    }

    @Override
    public void createTables() {
        if (isConnected) {
            for (DatabaseConnector database : this.databasesByName.values()) {
                database.createTables();
            }
        }

    }

    @Override
    public void closeWithBackups() {
        if (isConnected) {
            for (DatabaseConnector database : this.databasesByName.values()) {
                database.backupTables();
            }
        }

        this.close();

    }

    @Override
    public void close() {
        for (DatabaseConnector database : this.databasesByName.values()) {
            database.close();
        }

        isConnected = false;

    }

    public boolean isBroadcast() {
        return this.broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
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

    public DatabaseEndGame getEndGame() {
        return endGame;
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

    public void broadcast(String message) {
        if (getInstance().broadcast) {
            System.out.println(message);
        }
    }


}
