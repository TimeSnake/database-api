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

package de.timesnake.database.core.file;

public class DatabaseNotConfiguredException extends Throwable {

    private final String databaseType;
    private final String missingValueType;

    public DatabaseNotConfiguredException(String databaseType, String missingValueType) {
        this.databaseType = databaseType;
        this.missingValueType = missingValueType;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public String getMissingValueType() {
        return missingValueType;
    }

    public String getMessage() {
        return "[Database] WARNING Database " + this.databaseType + ": Value " + this.missingValueType + "is not " +
                "configured";
    }
}
