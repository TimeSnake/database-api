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

package de.timesnake.database.util.decoration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DatabaseDecoration {

    @Nullable
    DbHead getHead(String tag);

    boolean containsHead(String tag);

    boolean addHead(String tag, String name, String section);

    boolean removeHead(String tag);

    @NotNull
    Collection<String> getHeadTags();

    @NotNull
    Collection<String> getHeadTags(String section);

    @NotNull
    Collection<DbHead> getHeads();

    @NotNull
    Collection<DbHead> getHeads(String section);

    @NotNull
    Collection<String> getSections();
}
