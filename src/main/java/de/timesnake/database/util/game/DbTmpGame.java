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

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DbTmpGame extends DbGame, DbTmpGameInfo, DbCached<DbTmpGame> {

    @NotNull
    @Override
    DbTmpGameInfo getInfo();

    @NotCached
    void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio, String colorName);

    @NotCached
    void removeTeam(String name);

    @Nullable
    @NotCached
    Integer getHighestRank();

    @NotCached
    boolean containsTeam(String name);

    @NotCached
    @NotNull
    de.timesnake.database.core.game.team.DbTeam getTeam(String name);

    @NotCached
    @NotNull
    Collection<String> getTeamNames();

    @NotCached
    @NotNull
    Collection<Integer> getTeamRanks();

    @NotCached
    @NotNull
    Collection<de.timesnake.database.util.game.DbTeam> getTeams();

}
