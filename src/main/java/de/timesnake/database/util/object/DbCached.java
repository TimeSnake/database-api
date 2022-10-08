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

package de.timesnake.database.util.object;

import org.jetbrains.annotations.NotNull;

public interface DbCached<T> {

    /**
     * Generates a local data copy
     * <\p>
     * This object shouldn't be used for a longer time, otherwise the object could contain invalid data.
     * It is recommended to use this for many data requests on the same data object in a short time.
     * Methods with the {@link NotCached}-Annotation are not working on the cached data, so these methods are ending
     * in database queries.
     * </\p>
     *
     * @return a local {@link T} copy
     */
    @NotNull
    T toLocal();

    @NotNull
    T toDatabase();
}
