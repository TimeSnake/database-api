package de.timesnake.database.util.object;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.server.*;

import java.util.HashMap;

public class Type {

    public static <T extends Type> T getByDatabaseValue(Column<T> column, String s) {
        if (column.getValueClass().equals(Type.Server.class)) {
            return (T) Type.Server.getByDatabaseValue(s);
        } else if (column.getValueClass().equals(Type.Punishment.class)) {
            return (T) Type.Punishment.getByDatabaseValue(s);
        } else if (column.getValueClass().equals(Type.Availability.class)) {
            return (T) Type.Availability.getByDatabaseValue(s);
        }
        return null;
    }

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getDatabaseValue() {
        return this.type;
    }

    public static class Server<T extends de.timesnake.database.util.server.DbServer> extends Type {
        public static final Server<DbLobbyServer> LOBBY = new Server<>("lobby");
        public static final Server<DbNonTmpGameServer> GAME = new Server<>("game");
        public static final Server<DbTmpGameServer> TEMP_GAME = new Server<>("tempgame");
        public static final Server<DbBuildServer> BUILD = new Server<>("build");
        public static final Server<DbLoungeServer> LOUNGE = new Server<>("lounge");
        public static final HashMap<String, Server<?>> TYPES_BY_STRING = new HashMap<>();

        public static Server<?> getByDatabaseValue(String statusValue) {
            return TYPES_BY_STRING.get(statusValue);
        }

        static {
            TYPES_BY_STRING.put(LOBBY.getDatabaseValue(), LOBBY);
            TYPES_BY_STRING.put(GAME.getDatabaseValue(), GAME);
            TYPES_BY_STRING.put(TEMP_GAME.getDatabaseValue(), TEMP_GAME);
            TYPES_BY_STRING.put(BUILD.getDatabaseValue(), BUILD);
            TYPES_BY_STRING.put(LOUNGE.getDatabaseValue(), LOUNGE);
        }

        Server(String type) {
            super(type);
        }
    }

    public static class Punishment extends Type {
        public static final Punishment BAN = new Punishment("ban");
        public static final Punishment TEMP_BAN = new Punishment("temp_ban");
        public static final Punishment MUTE = new Punishment("mute");
        public static final HashMap<String, Punishment> TYPES_BY_STRING = new HashMap<>();

        public static Punishment getByDatabaseValue(String statusValue) {
            return TYPES_BY_STRING.get(statusValue);
        }

        static {
            TYPES_BY_STRING.put(BAN.getDatabaseValue(), BAN);
            TYPES_BY_STRING.put(TEMP_BAN.getDatabaseValue(), TEMP_BAN);
            TYPES_BY_STRING.put(MUTE.getDatabaseValue(), MUTE);
        }

        Punishment(String type) {
            super(type);
        }
    }

    public static class Availability extends Type {

        public static final Availability FORBIDDEN = new Availability("forbidden");
        public static final Availability ALLOWED = new Availability("allowed");
        public static final Availability REQUIRED = new Availability("required");
        public static final HashMap<String, Availability> TYPES_BY_STRING = new HashMap<>();

        public static Availability getByDatabaseValue(String statusValue) {
            return TYPES_BY_STRING.get(statusValue);
        }

        static {
            TYPES_BY_STRING.put(FORBIDDEN.getDatabaseValue(), FORBIDDEN);
            TYPES_BY_STRING.put(ALLOWED.getDatabaseValue(), ALLOWED);
            TYPES_BY_STRING.put(REQUIRED.getDatabaseValue(), REQUIRED);
        }

        Availability(String type) {
            super(type);
        }
    }

}
