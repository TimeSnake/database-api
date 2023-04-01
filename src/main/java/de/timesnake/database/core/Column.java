/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core;

import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;
import de.timesnake.library.chat.ExTextColor;
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Column<Value> {

    private final String name;
    private final ColumnType<Value> columnType;

    Column(String name, ColumnType<Value> columnType) {
        this.name = name;
        this.columnType = columnType;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public ColumnType<Value> getType() {
        return this.columnType;
    }

    @Nullable
    public Value parseFromResultSet(ResultSet rs) throws SQLException {
        return this.columnType.parseValueFromResultSet(rs, this);
    }


    public static class Server<T> extends Column<T> {

        public static final Server<String> NAME = new Server<>("name", ColumnType.VARCHAR(32));
        public static final Server<Integer> PORT = new Server<>("port",
                ColumnType.INTEGER.notNullable().unique());
        public static final Server<Status.Server> STATUS = new Server<>("status",
                ColumnType.STATUS());
        public static final Server<Integer> MAX_PLAYERS = new Server<>("max_players",
                ColumnType.INTEGER);
        public static final Server<Integer> ONLINE_PLAYERS = new Server<>("online_players",
                MAX_PLAYERS.getType());
        public static final Server<Boolean> OLD_PVP = new Server<>("old_pvp", ColumnType.BOOLEAN);
        public static final Server<String> TASK = new Server<>("task", ColumnType.VARCHAR(32));

        public static final Server<UUID> OWNER = new Server<>("owner_uuid", User.UUID.getType());

        public static final Server<String> GAME_INFO = new Server<>("info",
                ColumnType.VARCHAR(255));
        public static final Server<String> MAP_NAME = new Server<>("map_name",
                ColumnType.VARCHAR(32));
        public static final Server<Boolean> MAPS = new Server<>("maps", ColumnType.BOOLEAN);
        public static final Server<Boolean> KITS = new Server<>("kits", ColumnType.BOOLEAN);
        public static final Server<Integer> TEAM_AMOUNT = new Server<>("team_amount",
                ColumnType.INTEGER);
        public static final Server<Integer> TEAM_MAX_PLAYERS = new Server<>("team_max_players",
                MAX_PLAYERS.getType());
        public static final Server<Boolean> TEAM_MERGING = new Server<>("team_merging",
                ColumnType.BOOLEAN);
        public static final Server<String> TWIN_SERVER = new Server<>("twin_server",
                NAME.getType());
        public static final Server<String> PASSWORD = new Server<>("password",
                ColumnType.VARCHAR(255));
        public static final Server<Path> FOLDER_PATH = new Server<>("folder_path", ColumnType.PATH);
        public static final Server<Boolean> DISCORD = new Server<>("discord", ColumnType.BOOLEAN);

        public static final Server<String> BUILD_WORLD = new Server<>("world",
                ColumnType.VARCHAR(64));


        Server(String name, ColumnType<T> type) {
            super(name, type);
        }
    }

    public static class User<T> extends Column<T> {

        public static final User<UUID> UUID = new User<>("uuid", ColumnType.UUID);
        public static final User<String> NAME = new User<>("name",
                ColumnType.VARCHAR(16).notNullable());
        public static final User<Status.User> STATUS = new User<>("status", ColumnType.STATUS());
        public static final User<Boolean> ANTI_CHEAT_MESSAGES = new User<>("anti_cheat_messages",
                ColumnType.BOOLEAN);
        public static final User<Boolean> SERVICE = new User<>("service", ColumnType.BOOLEAN);
        public static final User<Boolean> AIR_MODE = new User<>("air_mode", ColumnType.BOOLEAN);
        public static final User<String> TASK = new User<>("task", ColumnType.VARCHAR(32));
        public static final User<String> TEAM = new User<>("team", ColumnType.VARCHAR(16));
        public static final User<String> PERM_GROUP = new User<>("perm_group",
                Group.NAME.getType().notUnique().nullable());
        public static final User<String> SERVER = new User<>("server",
                Server.NAME.getType().notUnique().nullable());
        public static final User<String> SERVER_LAST = new User<>("server_last",
                Server.NAME.getType().notUnique().nullable());
        public static final User<String> SERVER_LOBBY = new User<>("server_lobby",
                Server.NAME.getType().notUnique().nullable());
        public static final User<Integer> KIT = new User<>("kit",
                Game.KIT_ID.getType().notUnique().nullable());

        public static final User<String> PREFIX = new User<>("prefix", ColumnType.VARCHAR(16));
        public static final User<String> SUFFIX = new User<>("suffix", ColumnType.VARCHAR(16));
        public static final User<String> NICK = new User<>("nick", ColumnType.VARCHAR(16));

        public static final User<Type.Punishment> PUNISH_TYPE = new User<>("type",
                ColumnType.TYPE());
        public static final User<LocalDateTime> PUNISH_DATE = new User<>("date",
                ColumnType.LOCAL_DATE_TIME);
        public static final User<String> PUNISH_CASTIGATOR = new User<>("castigator",
                User.NAME.getType().nullable());
        public static final User<String> PUNISH_REASON = new User<>("reason",
                ColumnType.VARCHAR(255));
        public static final User<String> PUNISH_SERVER = new User<>("server",
                ColumnType.VARCHAR(255));

        public static final User<Float> TIME_COINS = new User<>("coins", ColumnType.FLOAT);

        public static final User<LocalDateTime> PRIVACY_POLICY = new User<>(
                "privacy_policy_date_time", ColumnType.LOCAL_DATE_TIME);

        public static final User<Long> DISCORD_ID = new User<>("discord_id", ColumnType.LONG);

        public static final User<Integer> MAIL_ID = new User<>("id", ColumnType.INTEGER);
        public static final User<UUID> MAIL_SENDER_UUID = new User<>("sender_uuid", UUID.getType());
        public static final User<String> MAIL_SENDER_NAME = new User<>("sender_name",
                NAME.getType());
        public static final User<String> MAIL_MESSAGE = new User<>("message",
                ColumnType.VARCHAR(255));

        public static final User<String> DISPLAY_GROUP = new User<>("display_group",
                Group.NAME.getType().notUnique().notNullable());

        User(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Permission<T> extends Column<T> {

        public static final Permission<Integer> ID = new Permission<>("id",
                ColumnType.INTEGER(true));
        public static final Permission<String> NAME = new Permission<>("name",
                ColumnType.VARCHAR(42));
        public static final Permission<String> PERMISSION = new Permission<>("permission",
                ColumnType.VARCHAR(255));
        public static final Permission<Status.Permission> MODE = new Permission<>("mode",
                ColumnType.STATUS());
        public static final Permission<List<String>> SERVER = new Permission<>("server",
                ColumnType.STRING_LIST(255));

        Permission(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Support<T> extends Column<T> {

        public static final Support<Integer> ID = new Support<>("id", ColumnType.INTEGER(true));
        public static final Support<String> UUID = new Support<>("uuid", ColumnType.VARCHAR(36));
        public static final Support<String> NAME = new Support<>("name", User.NAME.getType());
        public static final Support<String> MESSAGE = new Support<>("message",
                ColumnType.VARCHAR(255));
        public static final Support<Status.Ticket> STATUS = new Support<>("status",
                ColumnType.STATUS());
        public static final Support<String> ANSWER = new Support<>("answer",
                ColumnType.VARCHAR(255));
        public static final Support<LocalDateTime> DATE = new Support<>("date",
                ColumnType.LOCAL_DATE_TIME);

        Support(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Group<T> extends Column<T> {

        public static final Group<String> NAME = new Group<>("name", ColumnType.VARCHAR(16));
        public static final Group<Integer> PRIORITY = new Group<>("priority",
                ColumnType.INTEGER.notNullable().unique());
        public static final Group<String> PREFIX = new Group<>("prefix", ColumnType.VARCHAR(16));
        public static final Group<ExTextColor> PREFIX_COLOR = new Group<>("prefix_color",
                ColumnType.TEXT_COLOR);

        public static final Group<String> INHERITANCE = new Group<>("inheritance",
                Group.NAME.getType().notUnique().nullable());

        public static final Group<Boolean> SHOW_ALWAYS = new Group<>("show_always",
                ColumnType.BOOLEAN);

        Group(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Team<T> extends Column<T> {

        // all from group
        public static final Team<Float> RATIO = new Team<>("ratio", ColumnType.FLOAT);
        public static final Team<String> COLOR = new Team<>("color", ColumnType.VARCHAR(20));
        public static final Team<Boolean> PRIVATE_CHAT = new Team<>("private_chat",
                ColumnType.BOOLEAN);
        public static final Team<Integer> MIN_SIZE = new Team<>("min_size", ColumnType.INTEGER);

        Team(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Game<T> extends Column<T> {

        // info
        public static final Game<String> NAME = new Game<>("name", ColumnType.VARCHAR(32));
        public static final Game<String> DISPLAY_NAME = new Game<>("display_name",
                ColumnType.VARCHAR(32));
        public static final Game<ExTextColor> TEXT_COLOR = new Game<>("text_color",
                ColumnType.TEXT_COLOR);
        public static final Game<String> HEAD_LINE = new Game<>("head_line",
                ColumnType.VARCHAR(255));
        public static final Game<String> ITEM = new Game<>("item", ColumnType.VARCHAR(128));
        public static final Game<Integer> SLOT = new Game<>("slot", ColumnType.INTEGER.unique());
        public static final Game<Boolean> ENABLED = new Game<>("enabled", ColumnType.BOOLEAN);

        public static final Game<Integer> MAX_PLAYERS = new Game<>("max_players",
                Server.MAX_PLAYERS.getType());
        public static final Game<Type.Availability> MAPS = new Game<>("maps", ColumnType.TYPE());
        public static final Game<Type.Availability> KITS = new Game<>("kits", ColumnType.TYPE());
        public static final Game<Boolean> STATISTICS = new Game<>("statistics", ColumnType.BOOLEAN);
        public static final Game<String> TEXTURE_PACK_LINK = new Game<>("texture_pack_link",
                ColumnType.VARCHAR(255));
        public static final Game<Integer> PLAYER_TRACKING_RANGE = new Game<>(
                "player_tracking_range", ColumnType.INTEGER);
        public static final Game<Integer> MAX_HEALTH = new Game<>("max_health", ColumnType.INTEGER);
        public static final Game<Integer> VIEW_DISTANCE = new Game<>("view_distance",
                ColumnType.INTEGER);
        public static final Game<Type.Availability> OLD_PVP = new Game<>("old_pvp",
                ColumnType.TYPE());

        public static final Game<Boolean> CREATION_REQUESTABLE = new Game<>("creation_requestable",
                ColumnType.BOOLEAN);
        public static final Game<Boolean> OWNABLE = new Game<>("ownable", ColumnType.BOOLEAN);
        public static final Game<Boolean> ALLOW_NETHER_END = new Game<>("allow_nether_end",
                ColumnType.BOOLEAN);

        public static final Game<Integer> AUTO_START_PLAYER_NUMBER = new Game<>(
                "auto_start_player_number", Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MIN_PLAYER_NUMBER = new Game<>("min_player_number",
                Server.MAX_PLAYERS.getType());
        public static final Game<List<Integer>> TEAM_SIZES = new Game<>("team_sizes",
                ColumnType.integerList(255));
        public static final Game<Type.Availability> TEAM_MERGE = new Game<>("team_merge",
                ColumnType.TYPE());
        public static final Game<Boolean> EQUAL_TEAM_SIZE_REQUIRED = new Game<>(
                "equal_team_size_required", ColumnType.BOOLEAN);
        public static final Game<Boolean> SHOW_SELECTED_KITS = new Game<>("show_selected_kits",
                ColumnType.BOOLEAN);
        public static final Game<Boolean> HIDE_TEAMS = new Game<>("hide_teams", ColumnType.BOOLEAN);
        public static final Game<Type.Discord> DISCORD_TYPE = new Game<>("discord_type",
                ColumnType.TYPE());
        public static final Game<List<String>> DESCRIPTION = new Game<>("description",
                ColumnType.STRING_LIST(1000));

        // map
        public static final Game<String> MAP_NAME = new Game<>("name", ColumnType.VARCHAR(32));
        public static final Game<String> MAP_DISPLAY_NAME = new Game<>("display_name",
                ColumnType.VARCHAR(32));
        public static final Game<String> MAP_ITEM = new Game<>("item", ColumnType.VARCHAR(100));
        public static final Game<Integer> MAP_MIN_PLAYERS = new Game<>("min_players",
                Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MAP_MAX_PLAYERS = new Game<>("max_players",
                Server.MAX_PLAYERS.getType());
        public static final Game<List<String>> MAP_DESCRIPTION = new Game<>("description",
                ColumnType.STRING_LIST(1000));
        public static final Game<List<String>> MAP_INFO = new Game<>("info",
                ColumnType.STRING_LIST(500));
        public static final Game<Boolean> MAP_ENABLE = new Game<>("enable", ColumnType.BOOLEAN);
        public static final Game<UUID> MAP_AUTHOR_UUID = new Game<>("author_uuid",
                User.UUID.getType());
        // kit
        public static final Game<Integer> KIT_ID = new Game<>("id", ColumnType.INTEGER);
        public static final Game<String> KIT_NAME = new Game<>("name",
                ColumnType.VARCHAR(100).unique());
        public static final Game<String> KIT_ITEM = new Game<>("item_type", ColumnType.VARCHAR(50));
        public static final Game<List<String>> KIT_DESCRIPTION = new Game<>("description",
                ColumnType.STRING_LIST(2000));
        // lounge map
        public static final Game<String> LOUNGE_MAP_NAME = new Game<>("name",
                ColumnType.VARCHAR(100));
        public static final Game<String> LOUNGE_MAP_WORLD = new Game<>("world",
                ColumnType.VARCHAR(100));
        public static final Game<Float> LOUNGE_MAP_LOC_X = new Game<>("spawn_loc_x",
                ColumnType.FLOAT);
        public static final Game<Float> LOUNGE_MAP_LOC_Y = new Game<>("spawn_loc_y",
                ColumnType.FLOAT);
        public static final Game<Float> LOUNGE_MAP_LOC_Z = new Game<>("spawn_loc_z",
                ColumnType.FLOAT);
        public static final Game<Float> LOUNGE_MAP_LOC_YAW = new Game<>("spawn_loc_yaw",
                ColumnType.FLOAT);
        public static final Game<Float> LOUNGE_MAP_LOC_PITCH = new Game<>("spawn_loc_pitch",
                ColumnType.FLOAT);

        public static final Game<Integer> LOUNGE_MAP_DISPLAY_INDEX = new Game<>("display_index",
                ColumnType.INTEGER);
        public static final Game<Integer> LOUNGE_MAP_DISPLAY_X = new Game<>("display_x",
                ColumnType.INTEGER);
        public static final Game<Integer> LOUNGE_MAP_DISPLAY_Y = new Game<>("display_y",
                ColumnType.INTEGER);
        public static final Game<Integer> LOUNGE_MAP_DISPLAY_Z = new Game<>("display_z",
                ColumnType.INTEGER);
        public static final Game<BlockSide> LOUNGE_MAP_DISPLAY_FACING = new Game<>("display_facing",
                ColumnType.BLOCK_SIDE);
        public static final Game<BlockSide> LOUNGE_MAP_DISPLAY_ORIENTATION = new Game<>(
                "display_orientation", ColumnType.BLOCK_SIDE);
        public static final Game<Color> LOUNGE_MAP_DISPLAY_TITLE_COLOR = new Game<>(
                "display_title_color", ColumnType.COLOR);
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR = new Game<>(
                "display_stat_name_color", ColumnType.COLOR);
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR = new Game<>(
                "display_stat_first_color", ColumnType.COLOR);
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR = new Game<>(
                "display_stat_second_color", ColumnType.COLOR);
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR = new Game<>(
                "display_stat_third_color", ColumnType.COLOR);


        // statistics
        public static final Game<String> STAT_TYPE_NAME = new Game<>("name",
                ColumnType.VARCHAR(255));
        public static final Game<String> STAT_TYPE_DISPLAY_NAME = new Game<>("display_name",
                ColumnType.VARCHAR(255));
        public static final Game<String> STAT_TYPE_TYPE = new Game<>("type",
                ColumnType.VARCHAR(255));
        public static final Game<String> STAT_TYPE_DEFAULT_VALUE = new Game<>("default_value",
                ColumnType.VARCHAR(255));
        public static final Game<Integer> STAT_TYPE_DISPLAY_INDEX = new Game<>("display_index",
                ColumnType.INTEGER);
        public static final Game<Integer> STAT_TYPE_DISPLAY_LINE_INDEX = new Game<>(
                "display_line_index", ColumnType.INTEGER);
        public static final Game<Boolean> STAT_TYPE_GLOBAL_DISPLAY = new Game<>("global_display",
                ColumnType.BOOLEAN);
        public static final Game<Integer> STAT_TYPE_GLOBAL_DISPLAY_INDEX = new Game<>(
                "global_display_index", ColumnType.INTEGER);
        public static final Game<Integer> STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX = new Game<>(
                "global_display_line_index", ColumnType.INTEGER);

        public static final Game<UUID> STAT_USER_UUID = new Game<>("user_uuid",
                User.UUID.getType());
        public static final Game<String> STAT_USER_TYPE = new Game<>("type",
                ColumnType.VARCHAR(100));
        public static final Game<String> STAT_USER_VALUE_QUARTER = new Game<>("value_quarter",
                ColumnType.VARCHAR(255));
        public static final Game<String> STAT_USER_VALUE_ALL_TIME = new Game<>("value_all_time",
                ColumnType.VARCHAR(255));


        Game(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Location<T> extends Column<T> {

        public static final Location<Integer> NUMBER = new Location<>("number", ColumnType.INTEGER);
        public static final Location<String> WORLD = new Location<>("world",
                ColumnType.VARCHAR(32));
        public static final Location<Float> X = new Location<>("x", ColumnType.FLOAT);
        public static final Location<Float> Y = new Location<>("y", ColumnType.FLOAT);
        public static final Location<Float> Z = new Location<>("z", ColumnType.FLOAT);
        public static final Location<Float> YAW = new Location<>("yaw", ColumnType.FLOAT);
        public static final Location<Float> PITCH = new Location<>("pitch", ColumnType.FLOAT);

        Location(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class HungerGames<T> extends Column<T> {

        public static final HungerGames<Integer> ITEM_ID = new HungerGames<>("id",
                ColumnType.INTEGER(true));
        public static final HungerGames<String> ITEM_TYPE = new HungerGames<>("type",
                ColumnType.VARCHAR(100).notNullable());
        public static final HungerGames<Integer> ITEM_AMOUNT = new HungerGames<>("amount",
                ColumnType.INTEGER.notNullable());
        public static final HungerGames<Float> ITEM_CHANCE = new HungerGames<>("chance",
                ColumnType.FLOAT.notNullable());
        public static final HungerGames<Integer> ITEM_LEVEL = new HungerGames<>("level",
                ColumnType.INTEGER.notNullable());

        HungerGames(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Decoration<T> extends Column<T> {

        public static final Decoration<String> HEAD_NAME = new Decoration<>("name",
                ColumnType.VARCHAR(32));
        public static final Decoration<String> HEAD_SECTION = new Decoration<>("section",
                ColumnType.VARCHAR(32));
        public static final Decoration<String> HEAD_TAG = new Decoration<>("tag",
                ColumnType.VARCHAR(500));

        Decoration(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Story<T> extends Column<T> {

        public static final Story<UUID> USER_UUID = new Story<>("uuid", User.UUID.getType());
        public static final Story<Integer> BOOK_ID = new Story<>("book_id", ColumnType.INTEGER);
        public static final Story<String> CHAPTER_NAME = new Story<>("chapter_name",
                ColumnType.VARCHAR(100));
        public static final Story<String> QUEST_NAME = new Story<>("quest_name",
                ColumnType.VARCHAR(100));


        Story(String name, ColumnType<T> columnType) {
            super(name, columnType);
        }
    }

    public static class Network<T> extends Column<T> {

        public static final Network<String> FILE_NAME = new Network<>("name",
                ColumnType.VARCHAR(100));
        public static final Network<File> FILE_PATH = new Network<>("file_path", ColumnType.FILE);

        Network(String name, ColumnType<T> columnType) {
            super(name, columnType);
        }
    }


}
