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
import de.timesnake.database.core.permisson.DatabasePermissions;
import de.timesnake.database.core.server.DatabaseServers;
import de.timesnake.database.core.story.DatabaseStory;
import de.timesnake.database.core.support.DatabaseSupport;
import de.timesnake.database.core.user.DatabaseUsers;
import de.timesnake.database.util.object.DatabaseConnector;

public class DatabaseManager implements de.timesnake.database.util.Database {

    /**
     * For debug
     */
    private boolean broadcast = false;

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

    private boolean isConnected = false;

    private static final DatabaseManager instance = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return instance;
    }

    @Override
    public void connect(DatabaseConfig config) throws DatabaseNotConfiguredException {
        String user = config.getString("database.user");
        String password = config.getString("database.password");

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

        servers = new DatabaseServers(config.getDatabaseName(SERVERS_NAME), config.getDatabaseUrl(SERVERS_NAME), user, password, config.getDatabaseTable(SERVERS_NAME, "lobbys"), config.getDatabaseTable(SERVERS_NAME, "games"), config.getDatabaseTable(SERVERS_NAME, "lounges"), config.getDatabaseTable(SERVERS_NAME, "tempGames"), config.getDatabaseTable(SERVERS_NAME, "builds"));

        users = new DatabaseUsers(config.getDatabaseName(USERS_NAME), config.getDatabaseUrl(USERS_NAME), user, password, config.getDatabaseTable(USERS_NAME, "info"), config.getDatabaseTable(USERS_NAME, "punishments"), config.getDatabaseTable(USERS_NAME, "mails"));

        groups = new DatabaseGroups(config.getDatabaseName(GROUPS_NAME), config.getDatabaseUrl(GROUPS_NAME), user, password, config.getDatabaseTable(GROUPS_NAME, "permGroups"));

        permissions = new DatabasePermissions(config.getDatabaseName(PERMISSIONS_NAME), config.getDatabaseUrl(PERMISSIONS_NAME), user, password, config.getDatabaseTable(PERMISSIONS_NAME, "permissions"));

        games = new DatabaseGames(config.getDatabaseName(GAMES_NAME), config.getDatabaseUrl(GAMES_NAME), user, password, config.getDatabaseTable(GAMES_NAME, "infos"));

        gameTeams = new DatabaseTeams(config.getDatabaseName(TEAMS_NAME), config.getDatabaseUrl(TEAMS_NAME), user, password);

        gameMaps = new DatabaseMaps(config.getDatabaseName(MAPS_NAME), config.getDatabaseUrl(MAPS_NAME), user, password, config.getDatabaseTable(MAPS_NAME, "info"), config.getDatabaseTable(MAPS_NAME, "locations"), config.getDatabaseTable(MAPS_NAME, "authors"));

        gameKits = new DatabaseKits(config.getDatabaseName(KITS_NAME), config.getDatabaseUrl(KITS_NAME), user, password);

        gameStatistics = new DatabaseGameStatistics(config.getDatabaseName(GAME_STATISTICS_NAME), config.getDatabaseUrl(GAME_STATISTICS_NAME), user, password, config.getDatabaseTable(GAME_STATISTICS_NAME, "user_statistics"), config.getDatabaseTable(GAME_STATISTICS_NAME, "statistic_types"));

        lounges = new DatabaseLounges(config.getDatabaseName(LOUNGES_NAME), config.getDatabaseUrl(LOUNGES_NAME), user, password, config.getDatabaseTable(LOUNGES_NAME, "maps"));

        support = new DatabaseSupport(config.getDatabaseName(SUPPORT_NAME), config.getDatabaseUrl(SUPPORT_NAME), user, password, config.getDatabaseTable(SUPPORT_NAME, "tickets"));

        hungerGames = new DatabaseHungerGames(config.getDatabaseName(HUNGER_GAMES_NAME), config.getDatabaseUrl(HUNGER_GAMES_NAME), user, password, config.getDatabaseTable(HUNGER_GAMES_NAME, "items"));

        endGame = new DatabaseEndGame(config.getDatabaseName(END_GAME_NAME), config.getDatabaseUrl(END_GAME_NAME), user, password, config.getDatabaseTable(END_GAME_NAME, "worlds"));

        decorations = new DatabaseDecoration(config.getDatabaseName(DECORATIONS_NAME), config.getDatabaseUrl(DECORATIONS_NAME), user, password, config.getDatabaseTable(DECORATIONS_NAME, "heads"));

        story = new DatabaseStory(config.getDatabaseName(STORY_NAME), config.getDatabaseUrl(STORY_NAME), user, password, config.getDatabaseTable(STORY_NAME, "users"));

        isConnected = true;
    }

    @Override
    public void createTables() {
        if (isConnected) {
            servers.createTables();
            groups.createTables();
            permissions.createTables();
            users.createTables();
            games.createTables();
            lounges.createTables();
            support.createTables();
            hungerGames.createTables();
            endGame.createTables();
            decorations.createTables();
            story.createTables();
        }

    }

    @Override
    public void closeWithBackups() {
        if (isConnected) {
            servers.backupTables();
            groups.backupTables();
            permissions.backupTables();
            users.backupTables();
            games.backupTables();
            lounges.backupTables();
            support.backupTables();
            hungerGames.backupTables();
            endGame.backupTables();
            decorations.backupTables();
            story.backupTables();
        }

        this.close();

    }

    @Override
    public void close() {
        servers.close();
        groups.close();
        permissions.close();
        users.close();
        games.close();
        support.close();
        hungerGames.close();
        endGame.close();
        gameTeams.close();
        gameMaps.close();
        gameKits.close();
        gameStatistics.close();
        decorations.close();
        story.close();

        isConnected = false;

    }

    public boolean isBroadcast() {
        return this.broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public de.timesnake.database.util.server.DatabaseServers getServers() {
        return DatabaseManager.getInstance().servers;
    }

    public DatabaseGroups getGroups() {
        return DatabaseManager.getInstance().groups;
    }

    public DatabasePermissions getPermissions() {
        return DatabaseManager.getInstance().permissions;
    }

    public DatabaseUsers getUsers() {
        return DatabaseManager.getInstance().users;
    }

    public DatabaseGames getGames() {
        return DatabaseManager.getInstance().games;
    }

    public DatabaseSupport getSupport() {
        return DatabaseManager.getInstance().support;
    }

    public DatabaseEndGame getEndGame() {
        return DatabaseManager.getInstance().endGame;
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

    public void broadcast(String message) {
        if (getInstance().broadcast) {
            System.out.println(message);
        }
    }


}
