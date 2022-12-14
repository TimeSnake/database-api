/*
 * workspace.database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core;

import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;
import de.timesnake.library.basic.util.chat.ExTextColor;

import java.awt.*;
import java.io.File;
import java.lang.Boolean;
import java.lang.Float;
import java.lang.Long;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import static de.timesnake.database.core.ColumnType.Boolean;
import static de.timesnake.database.core.ColumnType.Float;
import static de.timesnake.database.core.ColumnType.Long;
import static de.timesnake.database.core.ColumnType.*;

public class Column<Value> {

    private final String name;
    private final ColumnType<Value> columnType;

    Column(String name, ColumnType<Value> columnType) {
        this.name = name;
        this.columnType = columnType;
    }

    public String getName() {
        return this.name;
    }

    public ColumnType<Value> getType() {
        return this.columnType;
    }


    public static class Server<T> extends Column<T> {
        public static final Server<Integer> PORT = new Server<>("port", integer(6));
        public static final Server<String> NAME = new Server<>("name", varchar(16).notNull().unique());
        public static final Server<Status.Server> STATUS = new Server<>("status", ((ColumnType<Status.Server>) status()));
        public static final Server<Integer> MAX_PLAYERS = new Server<>("max_players", integer(3));
        public static final Server<Integer> ONLINE_PLAYERS = new Server<>("online_players", MAX_PLAYERS.getType());
        public static final Server<Boolean> OLD_PVP = new Server<>("old_pvp", Boolean());
        public static final Server<String> TASK = new Server<>("task", varchar(32));

        public static final Server<UUID> OWNER = new Server<>("owner_uuid", User.UUID.getType());

        public static final Server<String> GAME_INFO = new Server<>("info", varchar(255));
        public static final Server<String> MAP_NAME = new Server<>("map_name", varchar(32));
        public static final Server<Boolean> MAPS = new Server<>("maps", Boolean());
        public static final Server<Boolean> KITS = new Server<>("kits", Boolean());
        public static final Server<Integer> TEAM_AMOUNT = new Server<>("team_amount", integer(3));
        public static final Server<Integer> TEAM_MAX_PLAYERS = new Server<>("team_max_players", MAX_PLAYERS.getType());
        public static final Server<Boolean> TEAM_MERGING = new Server<>("team_merging", Boolean());
        public static final Server<Integer> TWIN_SERVER = new Server<>("twin_server", PORT.getType());
        public static final Server<String> PASSWORD = new Server<>("password", varchar(255));
        public static final Server<Path> FOLDER_PATH = new Server<>("folder_path", path());
        public static final Server<Boolean> DISCORD = new Server<>("discord", Boolean());

        public static final Server<String> BUILD_WORLD = new Server<>("world", varchar(64));


        Server(String name, ColumnType<T> type) {
            super(name, type);
        }
    }

    public static class User<T> extends Column<T> {
        public static final User<UUID> UUID = new User<>("uuid", uuid());
        public static final User<String> NAME = new User<>("name", varchar(16).notNull());
        public static final User<Status.User> STATUS = new User<>("status", ((ColumnType<Status.User>) status()));
        public static final User<Boolean> ANTI_CHEAT_MESSAGES = new User<>("anti_cheat_messages", Boolean());
        public static final User<Boolean> SERVICE = new User<>("service", Boolean());
        public static final User<Boolean> AIR_MODE = new User<>("air_mode", Boolean());
        public static final User<String> TASK = new User<>("task", varchar(32));
        public static final User<String> TEAM = new User<>("team", varchar(16));
        public static final User<String> PERM_GROUP = new User<>("perm_group", Group.NAME.getType());
        public static final User<String> SERVER = new User<>("server", Server.NAME.getType().nonUnique());
        public static final User<String> SERVER_LAST = new User<>("server_last", Server.NAME.getType().nonUnique());
        public static final User<String> SERVER_LOBBY = new User<>("server_lobby", Server.NAME.getType().nonUnique());
        public static final User<Integer> KIT = new User<>("kit", Game.KIT_ID.getType());

        public static final User<String> PREFIX = new User<>("prefix", varchar(16));
        public static final User<String> SUFFIX = new User<>("suffix", varchar(16));
        public static final User<String> NICK = new User<>("nick", varchar(16));

        public static final User<String> PUNISH_TYPE = new User<>("type", varchar(20));
        public static final User<String> PUNISH_DATE = new User<>("date", varchar(50));
        public static final User<String> PUNISH_CASTIGATOR = new User<>("castigator", User.NAME.getType());
        public static final User<String> PUNISH_REASON = new User<>("reason", varchar(255));
        public static final User<String> PUNISH_SERVER = new User<>("server", varchar(255));

        public static final User<Float> TIME_COINS = new User<>("coins", Float(8));

        public static final User<String> DATA_PROTECTION = new User<>("data_protection", varchar(255));

        public static final User<Long> DISCORD_ID = new User<>("discord_id", Long());

        public static final User<Integer> MAIL_ID = new User<>("id", integer(8));
        public static final User<UUID> MAIL_SENDER_UUID = new User<>("sender_uuid", UUID.getType());
        public static final User<String> MAIL_SENDER_NAME = new User<>("sender_name", NAME.getType());
        public static final User<String> MAIL_MESSAGE = new User<>("message", varchar(255));

        public static final User<String> DISPLAY_GROUP = new User<>("display_group", Group.NAME.getType());

        User(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Permission<T> extends Column<T> {
        public static final Permission<Integer> ID = new Permission<>("id", integer(true));
        public static final Permission<String> NAME = new Permission<>("name", varchar(42));
        public static final Permission<String> PERMISSION = new Permission<>("permission", varchar(255));
        public static final Permission<Status.Permission> MODE = new Permission<>("mode", ((ColumnType<Status.Permission>) status()));
        public static final Permission<Collection<String>> SERVER = new Permission<>("server", stringCollection(255));

        Permission(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Support<T> extends Column<T> {
        public static final Support<Integer> ID = new Support<>("id", integer(true));
        public static final Support<String> UUID = new Support<>("uuid", varchar(36));
        public static final Support<String> NAME = new Support<>("name", User.NAME.getType());
        public static final Support<String> MESSAGE = new Support<>("message", varchar(255));
        public static final Support<Status.Ticket> STATUS = new Support<>("status", ((ColumnType<Status.Ticket>) status()));
        public static final Support<String> ANSWER = new Support<>("answer", varchar(255));
        // TODO date
        public static final Support<Date> DATE = new Support<>("date", null);

        Support(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Group<T> extends Column<T> {
        public static final Group<String> NAME = new Group<>("name", varchar(16));
        public static final Group<Integer> PRIORITY = new Group<>("priority", integer(2).notNull().unique());
        public static final Group<String> PREFIX = new Group<>("prefix", varchar(16));
        public static final Group<ExTextColor> PREFIX_COLOR = new Group<>("prefix_color", textColor());

        public static final Group<String> INHERITANCE = new Group<>("inheritance", Group.NAME.getType());

        public static final Group<Boolean> SHOW_ALWAYS = new Group<>("show_always", Boolean());

        Group(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Team<T> extends Column<T> {
        // all from group
        public static final Team<Float> RATIO = new Team<>("ratio", Float(6));
        public static final Team<String> COLOR = new Team<>("color", varchar(20));
        public static final Team<Boolean> PRIVATE_CHAT = new Team<>("private_chat", Boolean());

        Team(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Game<T> extends Column<T> {
        // info
        public static final Game<String> NAME = new Game<>("name", varchar(32));
        public static final Game<String> DISPLAY_NAME = new Game<>("display_name", varchar(32));
        public static final Game<ExTextColor> TEXT_COLOR = new Game<>("text_color", textColor());
        public static final Game<String> HEAD_LINE = new Game<>("head_line", varchar(255));
        public static final Game<String> ITEM = new Game<>("item", varchar(128));
        public static final Game<Integer> SLOT = new Game<>("slot", integer(2).unique());

        public static final Game<Integer> MAX_PLAYERS = new Game<>("max_players", Server.MAX_PLAYERS.getType());
        public static final Game<Type.Availability> MAPS = new Game<>("maps", ((ColumnType<Type.Availability>) type()));
        public static final Game<Type.Availability> KITS = new Game<>("kits", ((ColumnType<Type.Availability>) type()));
        public static final Game<Boolean> STATISTICS = new Game<>("statistics", Boolean());
        public static final Game<String> TEXTURE_PACK_LINK = new Game<>("texture_pack_link", varchar(255));
        public static final Game<Integer> PLAYER_TRACKING_RANGE = new Game<>("player_tracking_range", integer(3));
        public static final Game<Integer> MAX_HEALTH = new Game<>("max_health", integer(5));
        public static final Game<Integer> VIEW_DISTANCE = new Game<>("view_distance", integer(3));

        public static final Game<Boolean> CREATION_REQUESTABLE = new Game<>("creation_requestable", Boolean());
        public static final Game<Boolean> OWNABLE = new Game<>("ownable", Boolean());
        public static final Game<Boolean> ALLOW_NETHER_END = new Game<>("allow_nether_end", Boolean());

        public static final Game<Integer> AUTO_START_PLAYER_NUMBER = new Game<>("auto_start_player_number", Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MIN_PLAYER_NUMBER = new Game<>("min_player_number", Server.MAX_PLAYERS.getType());
        public static final Game<Collection<Integer>> TEAM_SIZES = new Game<>("team_sizes", integerCollection(255));
        public static final Game<Type.Availability> TEAM_MERGE = new Game<>("team_merge", ((ColumnType<Type.Availability>) type()));
        public static final Game<Boolean> EQUAL_TEAM_SIZE_REQUIRED = new Game<>("equal_team_size_required", Boolean());
        public static final Game<Boolean> SHOW_SELECTED_KITS = new Game<>("show_selected_kits", Boolean());
        public static final Game<Boolean> HIDE_TEAMS = new Game<>("hide_teams", Boolean());
        public static final Game<Type.Discord> DISCORD_TYPE = new Game<>("discord_type", ((ColumnType<Type.Discord>) type()));
        public static final Game<Collection<String>> DESCRIPTION = new Game<>("description", stringCollection(1000));

        // map
        public static final Game<String> MAP_NAME = new Game<>("name", varchar(32));
        public static final Game<String> MAP_DISPLAY_NAME = new Game<>("display_name", varchar(32));
        public static final Game<String> MAP_ITEM = new Game<>("item", varchar(100));
        public static final Game<Integer> MAP_MIN_PLAYERS = new Game<>("min_players", Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MAP_MAX_PLAYERS = new Game<>("max_players", Server.MAX_PLAYERS.getType());
        public static final Game<Collection<String>> MAP_DESCRIPTION = new Game<>("description", stringCollection(1000));
        public static final Game<Collection<String>> MAP_INFO = new Game<>("info", stringCollection(500));
        public static final Game<Boolean> MAP_ENABLE = new Game<>("enable", Boolean());
        public static final Game<UUID> MAP_AUTHOR_UUID = new Game<>("author_uuid", User.UUID.getType());
        // kit
        public static final Game<Integer> KIT_ID = new Game<>("id", integer(255));
        public static final Game<String> KIT_NAME = new Game<>("name", varchar(100).unique());
        public static final Game<String> KIT_ITEM = new Game<>("item_type", varchar(50));
        public static final Game<Collection<String>> KIT_DESCRIPTION = new Game<>("description", stringCollection(2000));
        // lounge map
        public static final Game<String> LOUNGE_MAP_NAME = new Game<>("name", varchar(100));
        public static final Game<String> LOUNGE_MAP_WORLD = new Game<>("world", varchar(100));
        public static final Game<Float> LOUNGE_MAP_LOC_X = new Game<>("spawn_loc_x", Float(20));
        public static final Game<Float> LOUNGE_MAP_LOC_Y = new Game<>("spawn_loc_y", Float(20));
        public static final Game<Float> LOUNGE_MAP_LOC_Z = new Game<>("spawn_loc_z", Float(20));
        public static final Game<Float> LOUNGE_MAP_LOC_YAW = new Game<>("spawn_loc_yaw", Float(6));
        public static final Game<Float> LOUNGE_MAP_LOC_PITCH = new Game<>("spawn_loc_pitch", Float(6));

        public static final Game<Integer> LOUNGE_MAP_DISPLAY_INDEX = new Game<>("display_index", integer(2));
        public static final Game<Integer> LOUNGE_MAP_DISPLAY_X = new Game<>("display_x", integer(10));
        public static final Game<Integer> LOUNGE_MAP_DISPLAY_Y = new Game<>("display_y", integer(10));
        public static final Game<Integer> LOUNGE_MAP_DISPLAY_Z = new Game<>("display_z", integer(10));
        public static final Game<BlockSide> LOUNGE_MAP_DISPLAY_FACING = new Game<>("display_facing", blockSide());
        public static final Game<BlockSide> LOUNGE_MAP_DISPLAY_ORIENTATION = new Game<>("display_orientation", blockSide());
        public static final Game<Color> LOUNGE_MAP_DISPLAY_TITLE_COLOR = new Game<>("display_title_color", color());
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR = new Game<>("display_stat_name_color", color());
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR = new Game<>("display_stat_first_color", color());
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR = new Game<>("display_stat_second_color", color());
        public static final Game<Color> LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR = new Game<>("display_stat_third_color", color());


        // statistics
        public static final Game<String> STAT_TYPE_NAME = new Game<>("name", varchar(255));
        public static final Game<String> STAT_TYPE_DISPLAY_NAME = new Game<>("display_name", varchar(255));
        public static final Game<String> STAT_TYPE_TYPE = new Game<>("type", varchar(255));
        public static final Game<String> STAT_TYPE_DEFAULT_VALUE = new Game<>("default_value", varchar(255));
        public static final Game<Integer> STAT_TYPE_DISPLAY_INDEX = new Game<>("display_index", integer(2));
        public static final Game<Integer> STAT_TYPE_DISPLAY_LINE_INDEX = new Game<>("display_line_index", integer(2));
        public static final Game<Boolean> STAT_TYPE_GLOBAL_DISPLAY = new Game<>("global_display", Boolean());
        public static final Game<Integer> STAT_TYPE_GLOBAL_DISPLAY_INDEX = new Game<>("global_display_index", integer(2));
        public static final Game<Integer> STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX = new Game<>("global_display_line_index", integer(2));

        public static final Game<UUID> STAT_USER_UUID = new Game<>("user_uuid", User.UUID.getType());
        public static final Game<String> STAT_USER_TYPE = new Game<>("type", varchar(100));
        public static final Game<String> STAT_USER_VALUE_QUARTER = new Game<>("value_quarter", varchar(255));
        public static final Game<String> STAT_USER_VALUE_ALL_TIME = new Game<>("value_all_time", varchar(255));


        Game(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Location<T> extends Column<T> {
        public static final Location<Integer> NUMBER = new Location<>("number", integer(4));
        public static final Location<String> WORLD = new Location<>("world", varchar(32));
        public static final Location<Float> X = new Location<>("x", Float(20));
        public static final Location<Float> Y = new Location<>("y", Float(20));
        public static final Location<Float> Z = new Location<>("z", Float(20));
        public static final Location<Float> YAW = new Location<>("yaw", Float(6));
        public static final Location<Float> PITCH = new Location<>("pitch", Float(6));

        Location(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class HungerGames<T> extends Column<T> {
        public static final HungerGames<Integer> ITEM_ID = new HungerGames<>("id", integer(4, true));
        public static final HungerGames<String> ITEM_TYPE = new HungerGames<>("type", varchar(100).notNull());
        public static final HungerGames<Integer> ITEM_AMOUNT = new HungerGames<>("amount", integer(2).notNull());
        public static final HungerGames<Float> ITEM_CHANCE = new HungerGames<>("chance", Float(8).notNull());
        public static final HungerGames<Integer> ITEM_LEVEL = new HungerGames<>("level", integer(2).notNull());

        HungerGames(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Decoration<T> extends Column<T> {
        public static final Decoration<String> HEAD_NAME = new Decoration<>("name", varchar(32));
        public static final Decoration<String> HEAD_SECTION = new Decoration<>("section", varchar(32));
        public static final Decoration<String> HEAD_TAG = new Decoration<>("tag", varchar(500));

        Decoration(String name, ColumnType<T> type) {
            super(name, type);
        }

    }

    public static class Story<T> extends Column<T> {
        public static final Story<UUID> USER_UUID = new Story<>("uuid", User.UUID.getType());
        public static final Story<Integer> BOOK_ID = new Story<>("book_id", integer(2));
        public static final Story<String> CHAPTER_NAME = new Story<>("chapter_name", varchar(100));
        public static final Story<String> QUEST_NAME = new Story<>("quest_name", varchar(100));


        Story(String name, ColumnType<T> columnType) {
            super(name, columnType);
        }
    }

    public static class Network<T> extends Column<T> {
        public static final Network<String> FILE_NAME = new Network<>("name", varchar(100));
        public static final Network<File> FILE_PATH = new Network<>("file_path", file());

        Network(String name, ColumnType<T> columnType) {
            super(name, columnType);
        }
    }


}
