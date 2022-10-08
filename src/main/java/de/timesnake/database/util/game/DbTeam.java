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

import de.timesnake.database.util.group.DbGroup;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;

public interface DbTeam extends DbGroup {

    @NotNull
    Float getRatio();

    @NotCached
    void setRatio(float ratio);

    @NotCached
    void setColor(String colorName);

    @NotNull
    String getColorName();

    boolean hasPrivateChat();

    @NotCached
    void setPrivateChat(Boolean privateChat);

    @NotNull
    @Override
    DbTeam toLocal();

    @NotNull
    @Override
    DbTeam toDatabase();

    default String parseColor(String colorName) {
        switch (colorName.toUpperCase()) {
            case "AQUA":
                return "AQUA";
            case "BLACK":
                return "BLACK";
            case "BLUE":
                return "BLUE";
            case "FUCHSIA":
                return "FUCHSIA";
            case "GRAY":
                return "GRAY";
            case "GREEN":
                return "GREEN";
            case "LIME":
                return "LIME";
            case "MAROON":
                return "MAROON";
            case "NAVY":
                return "NAVY";
            case "OLIVE":
                return "OLIVE";
            case "ORANGE":
                return "ORANGE";
            case "PURBLE":
                return "PURPLE";
            case "RED":
                return "RED";
            case "SILVER":
                return "SILVER";
            case "TEAL":
                return "TEAL";
            case "WHITE":
                return "WHITE";
            case "YELLOW":
                return "YELLOW";
            default:
        }
        return "WHITE";
    }
}
