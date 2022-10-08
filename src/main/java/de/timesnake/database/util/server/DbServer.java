/*
 * database-api.main
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

package de.timesnake.database.util.server;

import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public interface DbServer {

    void delete();

    boolean exists();

    @NotNull
    String getName();

    void setName(String name);

    Integer getPort();

    @NotNull
    Status.Server getStatus();

    void setStatus(Status.Server status);

    @Nullable
    Integer getOnlinePlayers();

    void setOnlinePlayers(int playersOnline);

    @Nullable
    Integer getMaxPlayers();

    void setMaxPlayers(int playersMax);

    void setStatusSynchronized(Status.Server status);

    void setOnlinePlayersSynchronized(int playersOnline);

    @NotNull
    Type.Server<?> getType();

    @Nullable
    String getPassword();

    void setPassword(String password) throws TooLongEntryException;

    boolean hasPassword();

    @NotNull
    Path getFolderPath();
}
