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

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DbTmpGameInfo extends DbGameInfo {

    @NotNull
    Integer getAutoStartPlayerNumber();

    @NotCached
    void setAutoStartPlayerNumber(Integer number);

    @NotNull
    Integer getMinPlayerNumber();

    @NotCached
    void setMinPlayerNumber(Integer number);

    @Nullable
    List<Integer> getTeamSizes();

    @NotCached
    void setTeamSizes(List<Integer> sizes);

    @NotNull
    Type.Availability getTeamMergeAvailability();

    @NotCached
    void setTeamMergeAvailability(Type.Availability availability);

    boolean isEqualTeamSizeRequired();

    @NotCached
    void requireEqualTeamSize(boolean require);

    boolean hideTeams();

    @NotCached
    void setHideTeams(boolean hide);

    @NotNull
    Type.Discord getDiscordType();

    void setDiscordType(Type.Discord type);

    @Nullable
    List<String> getDescription();

    @NotCached
    void setDescription(List<String> description);

    @NotNull
    @Override
    DbTmpGameInfo toDatabase();

    @NotNull
    @Override
    DbTmpGameInfo toLocal();
}
