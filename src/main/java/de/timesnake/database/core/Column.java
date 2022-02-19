package de.timesnake.database.core;

import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;

import java.util.Date;
import java.util.UUID;

/**
 * Columns contains the database column name and type. The {@link Value} is mapping the value to a java object
 *
 * @param <Value> The object type, allowed classes are {@link Integer}, {@link String}, {@link Boolean}, {@link Float},
 *                {@link Double}, {@link UUID}, {@code String[]}, {@code Integer[]}, {@link DbStringArrayList},
 *                {@link DbIntegerArrayList}
 */
public class Column<Value> {

    private final String name;
    private final Class<Value> valueClass;
    private final ColumnType columnType;

    Column(String name, Class<Value> valueClass, ColumnType columnType) {
        this.name = name;
        this.valueClass = valueClass;
        this.columnType = columnType;
    }

    public String getName() {
        return this.name;
    }

    public Class<Value> getValueClass() {
        return valueClass;
    }

    public ColumnType getType() {
        return this.columnType;
    }


    public static class Server<T> extends Column<T> {
        public static final Server<Integer> PORT = new Server<>("port", Integer.class, ColumnType.integer(10));
        public static final Server<String> NAME = new Server<>("name", String.class, ColumnType.varchar(100));
        public static final Server<Status.Server> STATUS = new Server<>("status", Status.Server.class, ColumnType.varchar(30));
        public static final Server<Integer> ONLINE_PLAYERS = new Server<>("online_players", Integer.class, ColumnType.integer(6));
        public static final Server<Integer> MAX_PLAYERS = new Server<>("max_players", Integer.class, ColumnType.integer(6));
        public static final Server<Boolean> OLD_PVP = new Server<>("old_pvp", Boolean.class, ColumnType.tinyint(1));
        public static final Server<String> TASK = new Server<>("task", String.class, ColumnType.varchar(100));
        public static final Server<String> GAME_INFO = new Server<>("info", String.class, ColumnType.varchar(255));
        public static final Server<String> MAP_NAME = new Server<>("map_name", String.class, ColumnType.varchar(100));
        public static final Server<Boolean> MAPS = new Server<>("maps", Boolean.class, ColumnType.tinyint(1));
        public static final Server<Boolean> KITS = new Server<>("kits", Boolean.class, ColumnType.tinyint(1));
        public static final Server<Integer> TEAM_AMOUNT = new Server<>("team_amount", Integer.class, ColumnType.integer(3));
        public static final Server<Integer> TEAM_MAX_PLAYERS = new Server<>("team_max_players", Integer.class, ColumnType.integer(3));
        public static final Server<Boolean> TEAM_MERGING = new Server<>("team_merging", Boolean.class, ColumnType.tinyint(1));
        public static final Server<Integer> TWIN_SERVER = new Server<>("twin_server", PORT.getValueClass(), PORT.getType());
        public static final Server<String> PASSWORD = new Server<>("password", String.class, ColumnType.varchar(255));

        Server(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }
    }

    public static class User<T> extends Column<T> {
        public static final User<UUID> UUID = new User<>("uuid", UUID.class, ColumnType.varchar(50));
        public static final User<String> NAME = new User<>("name", String.class, ColumnType.varchar(50));
        public static final User<Status.User> STATUS = new User<>("status", Status.User.class, ColumnType.varchar(50));
        public static final User<Boolean> ANTI_CHEAT_MESSAGES = new User<>("anti_cheat_messages", Boolean.class, ColumnType.tinyint(1));
        public static final User<Boolean> SERVICE = new User<>("service", Boolean.class, ColumnType.tinyint(1));
        public static final User<Boolean> AIR_MODE = new User<>("air_mode", Boolean.class, ColumnType.tinyint(1));
        public static final User<String> TASK = new User<>("task", String.class, ColumnType.varchar(50));
        public static final User<String> TEAM = new User<>("team", String.class, ColumnType.varchar(50));
        public static final User<String> PERMGROUP = new User<>("permgroup", String.class, ColumnType.varchar(50));
        public static final User<String> SERVER = new User<>("server", Server.NAME.getValueClass(), Server.NAME.getType());
        public static final User<String> SERVER_LAST = new User<>("server_last", Server.NAME.getValueClass(), Server.NAME.getType());
        public static final User<String> SERVER_LOBBY = new User<>("server_lobby", Server.NAME.getValueClass(), Server.NAME.getType());
        public static final User<Integer> KIT = new User<>("kit", Integer.class, Game.KIT_ID.getType());

        public static final User<String> PREFIX = new User<>("prefix", String.class, ColumnType.varchar(255));
        public static final User<String> SUFFIX = new User<>("suffix", String.class, ColumnType.varchar(255));
        public static final User<String> NICK = new User<>("nick", String.class, ColumnType.varchar(255));

        public static final User<String> PUNISH_TYPE = new User<>("type", String.class, ColumnType.varchar(20));
        public static final User<String> PUNISH_DATE = new User<>("date", String.class, ColumnType.varchar(50));
        public static final User<String> PUNISH_CASTIGATOR = new User<>("castigator", Server.NAME.getValueClass(), User.NAME.getType());
        public static final User<String> PUNISH_REASON = new User<>("reason", String.class, ColumnType.varchar(1000));
        public static final User<String> PUNISH_SERVER = new User<>("server", String.class, ColumnType.varchar(1000));

        public static final User<Float> TIME_COINS = new User<>("coins", Float.class, ColumnType.varchar(255));

        public static final User<String> DATA_PROTECTION = new User<>("data_protection", String.class, ColumnType.varchar(255));

        public static final User<Long> DISCORD_ID = new User<>("discord_id", Long.class, ColumnType.varchar(20));

        public static final User<Integer> MAIL_ID = new User<>("id", Integer.class, ColumnType.integer(255));
        public static final User<UUID> MAIL_SENDER_UUID = new User<>("sender_uuid", UUID.getValueClass(), UUID.getType());
        public static final User<String> MAIL_SENDER_NAME = new User<>("sender_name", NAME.getValueClass(), NAME.getType());
        public static final User<String> MAIL_MESSAGE = new User<>("message", String.class, ColumnType.varchar(1000));

        User(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Permission<T> extends Column<T> {
        public static final Permission<Integer> ID = new Permission<>("id", Integer.class, ColumnType.integer(255, true));
        public static final Permission<String> NAME = new Permission<>("name", String.class, ColumnType.varchar(50));
        public static final Permission<String> PERMISSION = new Permission<>("permission", String.class, ColumnType.varchar(255));
        public static final Permission<Status.Permission> MODE = new Permission<>("mode", Status.Permission.class, ColumnType.varchar(20));
        public static final Permission<DbStringArrayList> SERVER = new Permission<>("server", DbStringArrayList.class, ColumnType.varchar(255));

        Permission(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Support<T> extends Column<T> {
        public static final Support<Integer> ID = new Support<>("id", Integer.class, ColumnType.integer(255, true));
        public static final Support<String> UUID = new Support<>("uuid", String.class, ColumnType.varchar(50));
        public static final Support<String> NAME = new Support<>("name", User.NAME.getValueClass(), User.NAME.getType());
        public static final Support<String> MESSAGE = new Support<>("message", String.class, ColumnType.varchar(1000));
        public static final Support<Status.Ticket> STATUS = new Support<>("status", Status.Ticket.class, ColumnType.varchar(255));
        public static final Support<String> ANSWER = new Support<>("answer", String.class, ColumnType.varchar(1000));
        public static final Support<Date> DATE = new Support<>("date", Date.class, ColumnType.varchar(255));

        Support(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Survival<T> extends Column<T> {
        public static final Survival<Integer> PRIVATE_BLOCK_ID = new Survival<>("id", Integer.class, ColumnType.integer(255, true));
        public static final Survival<String> PRIVATE_BLOCK_WORLD = new Survival<>("world", String.class, ColumnType.varchar(100));
        public static final Survival<Integer> PRIVATE_BLOCK_X = new Survival<>("x", Integer.class, ColumnType.integer(255));
        public static final Survival<Integer> PRIVATE_BLOCK_Y = new Survival<>("y", Integer.class, ColumnType.integer(255));
        public static final Survival<Integer> PRIVATE_BLOCK_Z = new Survival<>("z", Integer.class, ColumnType.integer(255));
        public static final Survival<UUID> PRIVATE_BLOCK_OWNER = new Survival<>("owner", User.UUID.getValueClass(), User.UUID.getType());
        public static final Survival<String> PRIVATE_BLOCK_MEMBERS = new Survival<>("members", String.class, ColumnType.varchar(255));
        public static final Survival<String> PRIVATE_BLOCK_PASSWORD = new Survival<>("password", String.class, ColumnType.varchar(255));
        public static final Survival<Integer> MACHINE_ID = new Survival<>("id", Integer.class, ColumnType.integer(255, true));
        public static final Survival<String> MACHINE_WORLD = new Survival<>("world", String.class, ColumnType.varchar(100));
        public static final Survival<Integer> MACHINE_X = new Survival<>("x", Integer.class, ColumnType.integer(255));
        public static final Survival<Integer> MACHINE_Y = new Survival<>("y", Integer.class, ColumnType.integer(255));
        public static final Survival<Integer> MACHINE_Z = new Survival<>("z", Integer.class, ColumnType.integer(255));
        public static final Survival<String> MACHINE_TYPE = new Survival<>("type", String.class, ColumnType.varchar(255));

        Survival(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Group<T> extends Column<T> {
        public static final Group<Integer> RANK = new Group<>("rank", Integer.class, ColumnType.integer(20));
        public static final Group<String> NAME = new Group<>("name", String.class, ColumnType.varchar(50));
        public static final Group<String> PREFIX = new Group<>("prefix", String.class, ColumnType.varchar(50));
        public static final Group<String> CHAT_COLOR = new Group<>("chat_color", String.class, ColumnType.varchar(20));

        Group(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class PermGroup<T> extends Column<T> {
        //all from group
        public static final PermGroup<String> INHERITANCE = new PermGroup<>("inheritance", Group.NAME.getValueClass(), Group.NAME.getType());

        PermGroup(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Team<T> extends Column<T> {
        // all from group
        public static final Team<Float> RATIO = new Team<>("ratio", Float.class, ColumnType.varchar(20));
        public static final Team<String> COLOR = new Team<>("color", String.class, ColumnType.varchar(20));

        Team(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Game<T> extends Column<T> {
        // info
        public static final Game<String> NAME = new Game<>("name", String.class, ColumnType.varchar(50));
        public static final Game<String> DISPLAY_NAME = new Game<>("display_name", String.class, ColumnType.varchar(50));
        public static final Game<String> CHAT_COLOR = new Game<>("chat_color", String.class, ColumnType.varchar(20));
        public static final Game<Integer> AUTO_START = new Game<>("auto_start", Server.MAX_PLAYERS.getValueClass(), Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MIN_PLAYERS = new Game<>("min_players", Server.MAX_PLAYERS.getValueClass(), Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MAX_PLAYERS = new Game<>("max_players", Server.MAX_PLAYERS.getValueClass(), Server.MAX_PLAYERS.getType());
        public static final Game<String> HEAD_LINE = new Game<>("head_line", String.class, ColumnType.varchar(1000));
        public static final Game<String> ITEM = new Game<>("item", String.class, ColumnType.varchar(255));
        public static final Game<Integer> SLOT = new Game<>("slot", Integer.class, ColumnType.integer(4));
        public static final Game<Boolean> TEMPORARY = new Game<>("temporary", Boolean.class, ColumnType.tinyint(1));
        public static final Game<Type.Availability> KITS = new Game<>("kits", Type.Availability.class, ColumnType.varchar(50));
        public static final Game<Type.Availability> MAPS = new Game<>("maps", Type.Availability.class, ColumnType.varchar(50));
        public static final Game<DbIntegerArrayList> TEAM_AMOUNTS = new Game<>("team_amounts", DbIntegerArrayList.class, ColumnType.varchar(255));
        public static final Game<Type.Availability> TEAM_MERGE = new Game<>("team_merge", Type.Availability.class, ColumnType.varchar(50));
        public static final Game<DbStringArrayList> DESCRIPTION = new Game<>("description", DbStringArrayList.class, ColumnType.varchar(1000));

        // map
        public static final Game<String> MAP_NAME = new Game<>("name", String.class, ColumnType.varchar(100));
        public static final Game<String> MAP_DISPLAY_NAME = new Game<>("display_name", String.class, ColumnType.varchar(100));
        public static final Game<String> MAP_ITEM = new Game<>("item", String.class, ColumnType.varchar(100));
        public static final Game<Integer> MAP_MIN_PLAYERS = new Game<>("min_players", Server.MAX_PLAYERS.getValueClass(), Server.MAX_PLAYERS.getType());
        public static final Game<Integer> MAP_MAX_PLAYERS = new Game<>("max_players", Server.MAX_PLAYERS.getValueClass(), Server.MAX_PLAYERS.getType());
        public static final Game<DbStringArrayList> MAP_DESCRIPTION = new Game<>("description", DbStringArrayList.class, ColumnType.varchar(1000));
        public static final Game<DbStringArrayList> MAP_INFO = new Game<>("info", DbStringArrayList.class, ColumnType.varchar(255));
        public static final Game<Boolean> MAP_ENABLE = new Game<>("enable", Boolean.class, ColumnType.tinyint(1));
        public static final Game<UUID> MAP_AUTHOR_UUID = new Game<>("author_uuid", UUID.class, User.UUID.getType());
        // kit
        public static final Game<Integer> KIT_ID = new Game<>("id", Integer.class, ColumnType.integer(255));
        public static final Game<String> KIT_NAME = new Game<>("name", String.class, ColumnType.varchar(100));
        public static final Game<String> KIT_ITEM = new Game<>("item_type", String.class, ColumnType.varchar(50));
        public static final Game<DbStringArrayList> KIT_DESCRIPTION = new Game<>("description", DbStringArrayList.class, ColumnType.varchar(2000));
        // lounge map
        public static final Game<String> LOUNGE_MAP_NAME = new Game<>("name", String.class, ColumnType.varchar(100));
        public static final Game<String> LOUNGE_MAP_LOC_WORLD = new Game<>("loc_world", String.class, ColumnType.varchar(100));
        public static final Game<String> LOUNGE_MAP_LOC_NAME = new Game<>("loc_name", String.class, ColumnType.varchar(255));
        public static final Game<Double> LOUNGE_MAP_LOC_X = new Game<>("loc_x", Double.class, ColumnType.varchar(20));
        public static final Game<Double> LOUNGE_MAP_LOC_Y = new Game<>("loc_y", Double.class, ColumnType.varchar(20));
        public static final Game<Double> LOUNGE_MAP_LOC_Z = new Game<>("loc_z", Double.class, ColumnType.varchar(20));
        public static final Game<Float> LOUNGE_MAP_LOC_YAW = new Game<>("loc_yaw", Float.class, ColumnType.varchar(20));
        public static final Game<Float> LOUNGE_MAP_LOC_PITCH = new Game<>("loc_pitch", Float.class, ColumnType.varchar(20));

        // statistics
        public static final Game<String> STAT_TYPE_NAME = new Game<>("name", String.class, ColumnType.varchar(255));
        public static final Game<String> STAT_TYPE_DISPLAY_NAME = new Game<>("display_name", String.class, ColumnType.varchar(255));
        public static final Game<String> STAT_TYPE_TYPE = new Game<>("type", String.class, ColumnType.varchar(255));
        public static final Game<String> STAT_TYPE_DEFAULT_VALUE = new Game<>("default_value", String.class, ColumnType.varchar(255));
        public static final Game<Integer> STAT_TYPE_DISPLAY_INDEX = new Game<>("display_index", Integer.class, ColumnType.integer(10));
        public static final Game<Integer> STAT_TYPE_DISPLAY_LINE_INDEX = new Game<>("display_line_index", Integer.class, ColumnType.integer(10));

        public static final Game<UUID> STAT_USER_UUID = new Game<>("user_uuid", UUID.class, User.UUID.getType());
        public static final Game<String> STAT_USER_TYPE = new Game<>("type", String.class, ColumnType.varchar(100));
        public static final Game<String> STAT_USER_VALUE = new Game<>("value", String.class, ColumnType.varchar(255));


        Game(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Location<T> extends Column<T> {
        public static final Location<Integer> NUMBER = new Location<>("number", Integer.class, ColumnType.integer(255));
        public static final Location<String> WORLD = new Location<>("world", String.class, ColumnType.varchar(100));
        public static final Location<Double> X = new Location<>("x", Double.class, ColumnType.varchar(20));
        public static final Location<Double> Y = new Location<>("y", Double.class, ColumnType.varchar(20));
        public static final Location<Double> Z = new Location<>("z", Double.class, ColumnType.varchar(20));
        public static final Location<Float> YAW = new Location<>("yaw", Float.class, ColumnType.varchar(20));
        public static final Location<Float> PITCH = new Location<>("pitch", Float.class, ColumnType.varchar(20));

        Location(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class HungerGames<T> extends Column<T> {
        public static final HungerGames<Integer> ITEM_ID = new HungerGames<>("id", Integer.class, ColumnType.integer(255, true));
        public static final HungerGames<String> ITEM_TYPE = new HungerGames<>("type", String.class, ColumnType.varchar(100));
        public static final HungerGames<Integer> ITEM_AMOUNT = new HungerGames<>("amount", Integer.class, ColumnType.integer(2));
        public static final HungerGames<Float> ITEM_CHANCE = new HungerGames<>("chance", Float.class, ColumnType.varchar(10));
        public static final HungerGames<Integer> ITEM_LEVEL = new HungerGames<>("level", Integer.class, ColumnType.integer(3));

        HungerGames(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class EndGame<T> extends Column<T> {
        public static final EndGame<String> SERVER = new EndGame<>("server", Server.NAME.getValueClass(), Server.NAME.getType());
        public static final EndGame<UUID> PLAYER_UUID = new EndGame<>("player_uuid", User.UUID.getValueClass(), User.UUID.getType());
        public static final EndGame<String> PLAYER_NAME = new EndGame<>("player_name", User.NAME.getValueClass(), User.NAME.getType());

        EndGame(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Decoration<T> extends Column<T> {
        public static final Decoration<String> HEAD_NAME = new Decoration<>("name", String.class, ColumnType.varchar(500));
        public static final Decoration<String> HEAD_SECTION = new Decoration<>("section", String.class, ColumnType.varchar(500));
        public static final Decoration<String> HEAD_TAG = new Decoration<>("tag", String.class, ColumnType.varchar(500));

        Decoration(String name, Class<T> valueType, ColumnType type) {
            super(name, valueType, type);
        }

    }

    public static class Story<T> extends Column<T> {
        public static final Story<UUID> USER_UUID = new Story<>("uuid", UUID.class, User.UUID.getType());
        public static final Story<Integer> CHAPTER_ID = new Story<>("chapter_id", Integer.class, ColumnType.integer(2));
        public static final Story<Integer> PART_ID = new Story<>("part_id", Integer.class, ColumnType.integer(2));
        public static final Story<Integer> SECTION_ID = new Story<>("section_id", Integer.class, ColumnType.integer(2));

        Story(String name, Class<T> tClass, ColumnType columnType) {
            super(name, tClass, columnType);
        }
    }


}
