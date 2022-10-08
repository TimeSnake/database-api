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

package de.timesnake.database.util.group;

import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface DatabaseGroups {

    void addPermGroup(String name, int rank);

    boolean containsPermGroup(String name);

    @NotNull
    DbPermGroup getPermGroup(String name);

    void removePermGroup(String name);

    Collection<String> getPermGroupNames();

    Collection<Integer> getPermGroupRanks();

    Collection<? extends DbPermGroup> getPermGroups();

    void addDisplayGroup(String name, int rank, String prefix, ExTextColor color);

    boolean containsDisplayGroup(String name);

    @NotNull
    DbDisplayGroup getDisplayGroup(String name);

    void removeDisplayGroup(String name);

    Collection<String> getDisplayGroupNames();

    Collection<Integer> getDisplayGroupRanks();

    Collection<? extends DbDisplayGroup> getDisplayGroups();
}
