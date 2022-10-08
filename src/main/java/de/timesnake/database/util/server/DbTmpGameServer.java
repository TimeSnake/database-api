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

import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbTmpGameServer extends DbPvPServer {

    boolean areKitsEnabled();

    void setKitsEnabled(boolean kitsEnabled);

    @Nullable
    String getMapName();

    void setMapName(String mapName);

    @Nullable
    Integer getTwinServerPort();

    void setTwinServerPort(Integer port);

    @Nullable
    DbLoungeServer getTwinServer();

    @NotNull
    @Override
    Type.Server<DbTmpGameServer> getType();

    boolean areMapsEnabled();

    void setMapsEnabled(boolean mapsEnabled);

    @Nullable
    Integer getTeamAmount();

    void setTeamAmount(Integer integer);

    @Nullable
    Integer getMaxPlayersPerTeam();

    void setMaxPlayersPerTeam(Integer maxPlayersPerTeam);

    boolean isTeamMerging();

    void setTeamMerging(boolean teamMerging);

    boolean isDiscordEnabled();

    void setDiscord(boolean discordEnabled);
}
