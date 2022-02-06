package de.timesnake.database.util.object;

import de.timesnake.database.core.Column;

import java.util.HashMap;

public class Status implements de.timesnake.channel.api.message.Status {

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String getDatabaseValue() {
        return status;
    }

    public static <T extends Status> T getByDatabaseValue(Column<T> column, String s) {
        if (column.getValueClass().equals(User.class)) {
            return (T) User.getByDatabaseValue(s);
        } else if (column.getValueClass().equals(Server.class)) {
            return (T) Server.getByDatabaseValue(s);
        } else if (column.getValueClass().equals(Permission.class)) {
            return (T) Permission.getByDatabaseValue(s);
        } else if (column.getValueClass().equals(Ticket.class)) {
            return (T) Ticket.getByDatabaseValue(s);
        }
        return null;
    }


    public static class User extends Status {
        public static final User OFFLINE = new User("offline");
        public static final User ONLINE = new User("online");
        public static final User IN_GAME = new User("ingame");
        public static final User OUT_GAME = new User("outgame");
        public static final User PRE_GAME = new User("pregame");
        public static final User SPECTATOR = new User("spectator");

        public static final HashMap<String, User> STATUS_BY_STRING = new HashMap<>();

        static {
            STATUS_BY_STRING.put(OFFLINE.getDatabaseValue(), OFFLINE);
            STATUS_BY_STRING.put(ONLINE.getDatabaseValue(), ONLINE);
            STATUS_BY_STRING.put(IN_GAME.getDatabaseValue(), IN_GAME);
            STATUS_BY_STRING.put(OUT_GAME.getDatabaseValue(), OUT_GAME);
            STATUS_BY_STRING.put(PRE_GAME.getDatabaseValue(), PRE_GAME);
            STATUS_BY_STRING.put(SPECTATOR.getDatabaseValue(), SPECTATOR);
        }

        User(String status) {
            super(status);
        }

        public static Status.User[] values() {
            return new Status.User[]{OFFLINE, ONLINE, IN_GAME, OUT_GAME, PRE_GAME, SPECTATOR};
        }

        public static User getByDatabaseValue(String statusValue) {
            return STATUS_BY_STRING.get(statusValue);
        }
    }

    public static class Server extends Status {
        public static final Server OFFLINE = new Server("offline");
        public static final Server STARTING = new Server("starting");
        public static final Server ONLINE = new Server("online");
        public static final Server SERVICE = new Server("service");
        public static final Server IN_GAME = new Server("ingame");
        public static final Server PRE_GAME = new Server("pregame");
        public static final Server POST_GAME = new Server("postgame");

        Server(String status) {
            super(status);
        }

        public static Status.Server[] values() {
            return new Status.Server[]{OFFLINE, STARTING, ONLINE, SERVICE, IN_GAME, PRE_GAME, POST_GAME};
        }

        public static final HashMap<String, Server> STATUS_BY_STRING = new HashMap<>();

        static {
            STATUS_BY_STRING.put(OFFLINE.getDatabaseValue(), OFFLINE);
            STATUS_BY_STRING.put(ONLINE.getDatabaseValue(), ONLINE);
            STATUS_BY_STRING.put(IN_GAME.getDatabaseValue(), IN_GAME);
            STATUS_BY_STRING.put(STARTING.getDatabaseValue(), STARTING);
            STATUS_BY_STRING.put(PRE_GAME.getDatabaseValue(), PRE_GAME);
            STATUS_BY_STRING.put(POST_GAME.getDatabaseValue(), POST_GAME);
            STATUS_BY_STRING.put(SERVICE.getDatabaseValue(), SERVICE);
        }

        public static Server getByDatabaseValue(String statusValue) {
            return STATUS_BY_STRING.get(statusValue);
        }
    }

    public static class Permission extends Status {
        public static final Permission ONLINE = new Permission("online");
        public static final Permission SERVICE = new Permission("service");
        public static final Permission IN_GAME = new Permission("ingame");

        Permission(String status) {
            super(status);
        }

        public static Status.Permission[] values() {
            return new Status.Permission[]{ONLINE, SERVICE, IN_GAME};
        }

        public static final HashMap<String, Permission> STATUS_BY_STRING = new HashMap<>();

        static {
            STATUS_BY_STRING.put(ONLINE.getDatabaseValue(), ONLINE);
            STATUS_BY_STRING.put(SERVICE.getDatabaseValue(), SERVICE);
            STATUS_BY_STRING.put(IN_GAME.getDatabaseValue(), IN_GAME);
        }

        public static Permission getByDatabaseValue(String statusValue) {
            return STATUS_BY_STRING.get(statusValue);
        }

    }


    public static class Ticket extends Status {
        public static final Ticket OPEN = new Ticket("open", "Open", "§2");
        public static final Ticket IN_PROCESS = new Ticket("in_process", "In Process", "§6");
        public static final Ticket SOLVED = new Ticket("solved", "Solved", "§c");
        public static final Ticket ADMIN = new Ticket("admin", "Admin", "§9");
        public static final Ticket DELETE = new Ticket("deleted", "Delete", "§4");


        private final String name;
        private final String chatColor;

        Ticket(String status, String name, String chatColor) {
            super(status);
            this.name = name;
            this.chatColor = chatColor;
        }

        public String getName() {
            return name;
        }

        public String getChatColor() {
            return chatColor;
        }

        public static Status.Ticket[] values() {
            return new Status.Ticket[]{OPEN, IN_PROCESS, SOLVED, ADMIN, DELETE};
        }

        public static final HashMap<String, Ticket> STATUS_BY_STRING = new HashMap<>();

        static {
            for (Status.Ticket status : values()) {
                STATUS_BY_STRING.put(status.getDatabaseValue(), status);
            }
        }

        public static Ticket getByDatabaseValue(String statusValue) {
            return STATUS_BY_STRING.get(statusValue);
        }
    }
}
