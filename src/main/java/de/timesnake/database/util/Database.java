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

package de.timesnake.database.util;

import de.timesnake.database.core.file.DatabaseConfig;
import de.timesnake.database.core.file.DatabaseNotConfiguredException;
import de.timesnake.database.core.game.DatabaseGames;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.decoration.DatabaseDecoration;
import de.timesnake.database.util.game.DatabaseLounges;
import de.timesnake.database.util.game.DbKit;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.game.DbTeam;
import de.timesnake.database.util.group.DatabaseGroups;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.hungergames.DatabaseHungerGames;
import de.timesnake.database.util.network.DatabaseNetwork;
import de.timesnake.database.util.permission.DatabasePermissions;
import de.timesnake.database.util.server.DatabaseServers;
import de.timesnake.database.util.story.DatabaseStory;
import de.timesnake.database.util.support.DatabaseSupport;
import de.timesnake.database.util.user.DatabaseUsers;
import de.timesnake.database.util.user.DbPunishment;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.basic.util.LogHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Database {

    Logger LOGGER = LogHelper.getLogger("database", Level.WARNING);

    /**
     * Gets the instance of the {@link DatabaseManager} class, read description before use
     * <p>
     * <\p>
     * Recommended only for initial (for the plugin, which starts and stops the database).
     * Recommended only for database-, table-creations
     * </\p>
     *
     * @return the instance of the {@link DatabaseManager} class
     */
    static Database getInstance() {
        return DatabaseManager.getInstance();
    }

    /**
     * Gets the server database
     * <p>
     * <\p>
     * The server database contains all infos about the bukkit-servers.
     * Infos of the servers can be edited here, like online players, max players, task, status, ...
     * </\p>
     *
     * @return the {@link DatabaseServers}
     */
    static DatabaseServers getServers() {
        return DatabaseManager.getInstance().getServers();
    }

    /**
     * Gets the group database
     * <p>
     * <\p>
     * The group database contains all infos about the groups.
     * The permission-groups can be found here.
     * </\p>
     *
     * @return the {@link DatabaseGroups}
     */
    static DatabaseGroups getGroups() {
        return DatabaseManager.getInstance().getGroups();
    }

    /**
     * Gets the permission database
     * <p>
     * <\p>
     * The permission database contains all permissions.
     * The user and group permissions can be found here.
     * The {@link DbPermGroup}s have a direct link to their permissions.
     * The {@link DbUser}s have a direct link to their permissions.
     * </\p>
     *
     * @return the {@link DatabasePermissions}
     */
    static DatabasePermissions getPermissions() {
        return DatabaseManager.getInstance().getPermissions();
    }


    /**
     * Gets the user database
     * <p>
     * <\p>
     * The user database contains all infos about the users.
     * Infos of the users can be edited here, like status, task, perm-group, kit, team, ...
     * The {@link DbUser} contains direct links to the permissions, perm-groups,
     * teams, ...
     * {@link DbPunishment}s,
     * </\p>
     *
     * @return the {@link DatabaseUsers}
     */
    static DatabaseUsers getUsers() {
        return DatabaseManager.getInstance().getUsers();
    }

    /**
     * Gets the game database
     * <p>
     * <\p>
     * The game database contains all infos about the games.
     * Infos of the games can be changed here, like description, max players, ...
     * The {@link DbMap}s, {@link DbKit}s,
     * {@link DbTeam}s can be edited here
     * </\p>
     *
     * @return the {@link DatabaseGames}
     */
    static DatabaseGames getGames() {
        return DatabaseManager.getInstance().getGames();
    }

    /**
     * Gets the game-lounges database
     * <p>
     * <\p>
     * The lounge database contains the lounge maps.
     * Infos of the maps can be changed here, like world, spawn, ...
     * </\p>
     *
     * @return the {@link DatabaseLounges}
     */
    static DatabaseLounges getLounges() {
        return DatabaseManager.getInstance().getLounges();
    }

    /**
     * Gets the support database
     * <p>
     * <\p>
     * The support database contains all infos about the tickets.
     * </\p>
     *
     * @return the {@link DatabaseSupport}
     */
    static DatabaseSupport getSupport() {
        return DatabaseManager.getInstance().getSupport();
    }

    /**
     * Gets the hungergames database
     * <p>
     * <\p>
     * The hungergames database contains the items.
     * The maps are managed over the {@link DatabaseGames}
     * </\p>
     *
     * @return the {@link DatabaseHungerGames}
     */
    static DatabaseHungerGames getHungerGames() {
        return DatabaseManager.getInstance().getHungerGames();
    }

    /**
     * Gets the decoration database
     * <p>
     * <\p>
     * The decoration database contains various decorations like custom heads.
     * </\p>
     *
     * @return the {@link DatabaseDecoration}
     */
    static DatabaseDecoration getDecorations() {
        return DatabaseManager.getInstance().getDecorations();
    }

    /**
     * Gets the story database
     * <p>
     * <\p>
     * The story database contains the progress of a user in a story chapter
     * </\p>
     *
     * @return the {@link DatabaseStory}
     */
    static DatabaseStory getStory() {
        return DatabaseManager.getInstance().getStory();
    }

    /**
     * Gets the network database
     * <p>
     * The network database contains information about the network, like file paths
     * </p>
     *
     * @return the {@link  DatabaseNetwork}
     */
    static DatabaseNetwork getNetwork() {
        return DatabaseManager.getInstance().getNetwork();
    }

    void connect(DatabaseConfig config) throws DatabaseNotConfiguredException;

    void createTables();

    void closeWithBackups();

    void close();
}
