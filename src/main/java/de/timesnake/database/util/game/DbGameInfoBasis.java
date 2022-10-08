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
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

public interface DbGameInfoBasis {

    boolean exists();

    @NotNull
    String getName();

    @NotNull
    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    @Deprecated
    @NotNull
    String getChatColorName();

    @NotCached
    @Deprecated
    void setChatColorName(String chatColorName);

    @NotNull
    ExTextColor getTextColor();

    @NotCached
    void setTextColor(ExTextColor color);

    @NotNull
    String getItemName();

    @NotCached
    void setItem(String itemName);

    @NotNull
    String getHeadLine();

    @NotCached
    void setHeadLine(String headLine);

    @NotNull
    Integer getSlot();

    @NotCached
    void setSlot(int slot);

    @NotNull
    DbGameInfoBasis toDatabase();

    @NotNull
    DbGameInfoBasis toLocal();
}
