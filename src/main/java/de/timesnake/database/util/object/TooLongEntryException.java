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

import de.timesnake.database.core.ColumnType;

public class TooLongEntryException extends Exception {

    private final String entry;
    private final ColumnType type;

    public TooLongEntryException(String entry, ColumnType type) {
        this.entry = entry;
        this.type = type;
    }

    public String getEntry() {
        return entry;
    }

    public ColumnType getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return "The max length is limited to " + this.type.getLength() + ", current: " + this.entry.length();
    }
}
