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

package de.timesnake.database.core;

public abstract class ColumnType {

    public static ColumnType integer(int length) {
        return new ColumnType("int", length, false) {
            @Override
            public String getName() {
                return "int(" + this.length + ")";
            }
        };
    }

    public static ColumnType integer(int length, boolean autoIncrement) {
        return new ColumnType("int", length, false) {
            @Override
            public String getName() {
                return autoIncrement ? "int(" + this.length + ") AUTO_INCREMENT" : "int(" + this.length + ")";
            }
        };
    }

    public static ColumnType varchar(int length) {
        return new ColumnType("varchar", length, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }
        };
    }

    public static ColumnType tinyint(int length) {
        return new ColumnType("tinyint", length, false) {
            @Override
            public String getName() {
                return "tinyint(" + this.length + ")";
            }
        };
    }

    protected final String simpleName;
    protected final int length;
    protected final boolean wrapped;
    protected final boolean unique;

    ColumnType(String simpleName, int length, boolean wrapped) {
        this.simpleName = simpleName;
        this.length = length;
        this.wrapped = wrapped;
        this.unique = false;
    }

    ColumnType(String simpleName, int length, boolean wrapped, boolean unique) {
        this.simpleName = simpleName;
        this.length = length;
        this.wrapped = wrapped;
        this.unique = unique;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public int getLength() {
        return length;
    }

    public boolean isWrapped() {
        return wrapped;
    }

    public boolean isUnique() {
        return unique;
    }

    public abstract String getName();

    public ColumnType unique() {
        return new ColumnType(this.getSimpleName(), this.getLength(), this.isWrapped(), true) {
            @Override
            public String getName() {
                return ColumnType.this.getName();
            }
        };
    }

    public ColumnType nonUnique() {
        return new ColumnType(this.getSimpleName(), this.getLength(), this.isWrapped(), false) {
            @Override
            public String getName() {
                return ColumnType.this.getName();
            }
        };
    }

    public ColumnType notNull() {
        return new ColumnType(this.getSimpleName(), this.getLength(), this.isWrapped()) {
            @Override
            public String getName() {
                return ColumnType.this.getName() + " NOT NULL";
            }
        };
    }
}
