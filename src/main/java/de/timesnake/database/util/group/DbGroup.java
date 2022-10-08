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

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DbGroup extends DbGroupBasis {

    boolean exists();

    @NotNull
    String getName();

    @NotCached
    void setName(String name);

    @NotNull
    Integer getRank();

    @Nullable
    String getPrefix();

    @NotCached
    void setPrefix(String prefix);

    @Deprecated
    @Nullable
    String getChatColorName();

    @NotCached
    @Deprecated
    void setChatColorName(String chatColorName);

    @Nullable
    ExTextColor getChatColor();

    void setChatColor(ExTextColor color);
}
