/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.object;

import de.timesnake.database.util.server.*;
import net.kyori.adventure.util.Index;

public abstract class Type {

  public static final String DIVIDER = "_";

  public static <T extends Type> T valueOf(String name) {
    if (name == null) {
      return null;
    }

    String[] names = name.split(DIVIDER);

    if (names.length < 2) {
      return null;
    }

    return switch (names[0]) {
      case Server.PREFIX -> (T) Server.valueOf(names[1]);
      case Punishment.PREFIX -> (T) Punishment.valueOf(names[1]);
      case Availability.PREFIX -> (T) Availability.valueOf(names[1]);
      case Discord.PREFIX -> (T) Discord.valueOf(names[1]);
      default -> null;
    };
  }

  private final String name;

  Type(String name) {
    this.name = name;
  }

  /**
   * Gets name
   * <p>Must not be longer than 20 characters.</p>
   *
   * @return
   */
  public String getShortName() {
    return this.name;
  }

  public String getName() {
    return this.getType() + DIVIDER + this.getShortName();
  }

  protected abstract String getType();

  public static class Server<T extends de.timesnake.database.util.server.DbServer> extends Type {

    public static final Server<DbLobbyServer> LOBBY = new Server<>("lobby");
    public static final Server<DbNonTmpGameServer> GAME = new Server<>("game");
    public static final Server<DbTmpGameServer> TEMP_GAME = new Server<>("tempgame");
    public static final Server<DbBuildServer> BUILD = new Server<>("build");
    public static final Server<DbLoungeServer> LOUNGE = new Server<>("lounge");

    public static Server<?> valueOf(String name) {
      return TYPES_BY_STRING.value(name.replace(PREFIX + DIVIDER, ""));
    }

    public static Server<?>[] values() {
      return VALUES;
    }

    private static final Server<?>[] VALUES = {LOBBY, GAME, TEMP_GAME, BUILD, LOUNGE};
    private static final Index<String, Server<?>> TYPES_BY_STRING = Index.create(
        Type::getShortName, VALUES);

    private static final String PREFIX = "server";

    Server(String type) {
      super(type);
    }

    @Override
    protected String getType() {
      return PREFIX;
    }
  }

  public static class Punishment extends Type {

    public static final Punishment BAN = new Punishment("ban");
    public static final Punishment TEMP_BAN = new Punishment("tempban");
    public static final Punishment JAIL = new Punishment("jail");
    public static final Punishment MUTE = new Punishment("mute");
    public static final Punishment TEMP_MUTE = new Punishment("tempmute");

    public static Punishment valueOf(String name) {
      return TYPES_BY_STRING.value(name.replace(PREFIX + DIVIDER, ""));
    }

    public static Punishment[] values() {
      return VALUES;
    }

    private static final Punishment[] VALUES = {BAN, TEMP_BAN, JAIL, MUTE, TEMP_MUTE};
    private static final Index<String, Punishment> TYPES_BY_STRING = Index.create(
        Type::getShortName, VALUES);

    private static final String PREFIX = "punish";

    Punishment(String type) {
      super(type);
    }

    @Override
    protected String getType() {
      return PREFIX;
    }
  }

  public static class Availability extends Type {

    public static final Availability FORBIDDEN = new Availability("forbidden");
    public static final Availability ALLOWED = new Availability("allowed");
    public static final Availability REQUIRED = new Availability("required");

    public static Availability valueOf(String name) {
      return TYPES_BY_STRING.value(name.replace(PREFIX + DIVIDER, ""));
    }

    public static Availability[] values() {
      return VALUES;
    }

    private static final Availability[] VALUES = {FORBIDDEN, ALLOWED, REQUIRED};
    private static final Index<String, Availability> TYPES_BY_STRING = Index.create(
        Type::getShortName, VALUES);

    private static final String PREFIX = "avail";

    Availability(String type) {
      super(type);
    }

    @Override
    protected String getType() {
      return PREFIX;
    }
  }

  public static class Discord extends Type {

    public static final Discord FORBIDDEN = new Discord("forbidden");
    public static final Discord TEAMS = new Discord("teams");
    public static final Discord DISTANCE = new Discord("distance");

    public static Discord valueOf(String name) {
      return TYPES_BY_STRING.value(name.replace(PREFIX + DIVIDER, ""));
    }

    public static Discord[] values() {
      return VALUES;
    }

    private static final Discord[] VALUES = {FORBIDDEN, TEAMS, DISTANCE};
    private static final Index<String, Discord> TYPES_BY_STRING = Index.create(
        Type::getShortName, VALUES);

    private static final String PREFIX = "discord";

    Discord(String type) {
      super(type);
    }

    @Override
    protected String getType() {
      return PREFIX;
    }
  }

}
