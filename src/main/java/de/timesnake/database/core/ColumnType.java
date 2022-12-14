/*
 * workspace.database-api.main
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

import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static de.timesnake.database.core.table.Table.ENTRY_ARRAY_DELIMITER;

public abstract class ColumnType<Value> {

    public static ColumnType<Integer> integer() {
        return new ColumnType<>("INT", 1, false) {
            @Override
            public String getName() {
                return "INT";
            }

            @Override
            public Integer parseValueFromSQL(ResultSet rs, Column<Integer> column) throws SQLException {
                return rs.getInt(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Integer integer) {
                return String.valueOf(integer);
            }
        };
    }

    public static ColumnType<Integer> integer(int length) {
        return new ColumnType<>("INT", length, false) {
            @Override
            public String getName() {
                return "INT(" + this.length + ")";
            }

            @Override
            public Integer parseValueFromSQL(ResultSet rs, Column<Integer> column) throws SQLException {
                return rs.getInt(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Integer integer) {
                return String.valueOf(integer);
            }
        };
    }

    public static ColumnType<Long> Long() {
        return new ColumnType<>("UNSIGNED BIGINT", 19, false) {
            @Override
            public String getName() {
                return "UNSIGNED BIGINT(" + this.length + ")";
            }

            @Override
            public Long parseValueFromSQL(ResultSet rs, Column<Long> column) throws SQLException {
                return rs.getLong(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Long integer) {
                return String.valueOf(integer);
            }
        };
    }

    public static ColumnType<Integer> integer(boolean autoIncrement) {
        return new ColumnType<>("INT", 1, false) {
            @Override
            public String getName() {
                return autoIncrement ? "INT AUTO_INCREMENT" : "INT";
            }

            @Override
            public Integer parseValueFromSQL(ResultSet rs, Column<Integer> column) throws SQLException {
                return rs.getInt(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Integer integer) {
                return String.valueOf(integer);
            }
        };
    }

    public static ColumnType<Integer> integer(int length, boolean autoIncrement) {
        return new ColumnType<>("INT", length, false) {
            @Override
            public String getName() {
                return autoIncrement ? "INT(" + this.length + ") AUTO_INCREMENT" : "INT(" + this.length + ")";
            }

            @Override
            public Integer parseValueFromSQL(ResultSet rs, Column<Integer> column) throws SQLException {
                return rs.getInt(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Integer integer) {
                return String.valueOf(integer);
            }
        };
    }

    public static ColumnType<String> varchar(int length) {
        return new ColumnType<>("varchar", length, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public String parseValueFromSQL(ResultSet rs, Column<String> column) throws SQLException {
                return rs.getString(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull String s) {
                return s;
            }
        };
    }

    public static ColumnType<Float> Float(int length) {
        return new ColumnType<>("float", length, false) {
            @Override
            public String getName() {
                return "float(" + this.length + ")";
            }

            @Override
            public Float parseValueFromSQL(ResultSet rs, Column<Float> column) throws SQLException {
                return rs.getFloat(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Float f) {
                return String.valueOf(f);
            }
        };
    }

    public static ColumnType<UUID> uuid() {
        return new ColumnType<>("binary", 16, false) {
            @Override
            public String getName() {
                return "binary(" + this.length + ")";
            }

            @Override
            public UUID parseValueFromSQL(ResultSet rs, Column<UUID> column) throws SQLException {
                return UUID.fromString(rs.getString(column.getName()));
            }

            @Override
            public String parseValueToSQL(@NotNull UUID uuid) {
                return "UUID_TO_BIN('" + uuid.toString() + "')";
            }

            @Override
            public String getSelectWrapper(String columnName) {
                return "BIN_TO_UUID(" + columnName + ")";
            }
        };
    }

    public static ColumnType<Integer> tinyint(int length) {
        return new ColumnType<>("tinyint", length, false) {
            @Override
            public String getName() {
                return "tinyint(" + this.length + ")";
            }

            @Override
            public Integer parseValueFromSQL(ResultSet rs, Column<Integer> column) throws SQLException {
                return rs.getInt(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Integer integer) {
                return String.valueOf(integer);
            }
        };
    }

    public static ColumnType<Boolean> Boolean() {
        return new ColumnType<>("boolean", 1, false) {
            @Override
            public String getName() {
                return "boolean";
            }

            @Override
            public Boolean parseValueFromSQL(ResultSet rs, Column<Boolean> column) throws SQLException {
                return rs.getBoolean(column.getName());
            }

            @Override
            public String parseValueToSQL(@NotNull Boolean b) {
                return String.valueOf(b);
            }
        };
    }

    public static ColumnType<? extends Status> status() {
        return new ColumnType<>("varchar", 16, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public Status parseValueFromSQL(ResultSet rs, Column<Status> column) throws SQLException {
                if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.User.class)) {
                    return de.timesnake.library.basic.util.Status.User.parseValue(rs.getString(column.getName()));
                } else if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.Server.class)) {
                    return de.timesnake.library.basic.util.Status.Server.parseValue(rs.getString(column.getName()));
                } else if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.Permission.class)) {
                    return de.timesnake.library.basic.util.Status.Permission.parseValue(rs.getString(column.getName()));
                } else if (column.getValueClass().equals(de.timesnake.library.basic.util.Status.Ticket.class)) {
                    return de.timesnake.library.basic.util.Status.Ticket.parseValue(rs.getString(column.getName()));
                }
                return null;
            }

            @Override
            public String parseValueToSQL(@NotNull Status status) {
                return status.getSimpleName();
            }
        };
    }

    public static ColumnType<? extends Type> type() {
        return new ColumnType<>("varchar", 16, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public Type parseValueFromSQL(ResultSet rs, Column<Type> column) throws SQLException {
                return Type.getByDatabaseValue(((Column<? extends Type>) column), rs.getString(column.getName()));
            }

            @Override
            public String parseValueToSQL(@NotNull Type type) {
                return type.getDatabaseValue();
            }
        };
    }

    public static ColumnType<Collection<String>> stringCollection(int length) {
        return new ColumnType<>("varchar", length, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public Collection<String> parseValueFromSQL(ResultSet rs, Column<Collection<String>> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return Collections.emptyList();
                }
                return new ArrayList<>(Arrays.asList(value.split(ENTRY_ARRAY_DELIMITER)));
            }

            @Override
            public String parseValueToSQL(@NotNull Collection<String> collection) {
                if (collection.size() > 0) {
                    return String.join(ENTRY_ARRAY_DELIMITER, collection);
                }
                return null;
            }
        };
    }

    public static ColumnType<Collection<Integer>> integerCollection(int length) {
        return new ColumnType<>("varchar", length, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public Collection<Integer> parseValueFromSQL(ResultSet rs, Column<Collection<Integer>> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return Collections.emptyList();
                }
                return Arrays.stream(value.split(ENTRY_ARRAY_DELIMITER))
                        .map(s -> {
                            try {
                                return Integer.parseInt(s);
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList();
            }

            @Override
            public String parseValueToSQL(@NotNull Collection<Integer> collection) {
                if (collection.size() > 0) {
                    return collection.stream().map(String::valueOf).collect(Collectors.joining(ENTRY_ARRAY_DELIMITER));
                }
                return null;
            }
        };
    }

    public static ColumnType<BlockSide> blockSide() {
        return new ColumnType<>("varchar", 5, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public BlockSide parseValueFromSQL(ResultSet rs, Column<BlockSide> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return null;
                }
                return BlockSide.valueOf(value.toUpperCase());
            }

            @Override
            public String parseValueToSQL(@NotNull BlockSide blockSide) {
                return blockSide.name().toLowerCase();
            }
        };
    }

    public static ColumnType<Color> color() {
        return new ColumnType<>("varchar", 5, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public Color parseValueFromSQL(ResultSet rs, Column<Color> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return null;
                }
                String[] rgba = value.split(ENTRY_ARRAY_DELIMITER);
                if (rgba.length < 4) {
                    return null;
                }
                return new Color(Integer.parseInt(rgba[0]), Integer.parseInt(rgba[1]), Integer.parseInt(rgba[2]),
                        Integer.parseInt(rgba[3]));
            }

            @Override
            public String parseValueToSQL(@NotNull Color color) {
                return color.getRed() + ENTRY_ARRAY_DELIMITER +
                        color.getGreen() + ENTRY_ARRAY_DELIMITER +
                        color.getBlue() + ENTRY_ARRAY_DELIMITER +
                        color.getAlpha();
            }
        };
    }

    public static ColumnType<File> file() {
        return new ColumnType<>("varchar", 500, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public File parseValueFromSQL(ResultSet rs, Column<File> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return null;
                }
                return new File(value);
            }

            @Override
            public String parseValueToSQL(@NotNull File file) {
                return file.getAbsolutePath();
            }
        };
    }

    public static ColumnType<Path> path() {
        return new ColumnType<>("varchar", 500, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public Path parseValueFromSQL(ResultSet rs, Column<Path> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return null;
                }
                return new File(value).toPath();
            }

            @Override
            public String parseValueToSQL(@NotNull Path path) {
                return path.toFile().getAbsolutePath();
            }
        };
    }

    public static ColumnType<ExTextColor> textColor() {
        return new ColumnType<>("varchar", 500, true) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }

            @Override
            public ExTextColor parseValueFromSQL(ResultSet rs, Column<ExTextColor> column) throws SQLException {
                String value = rs.getString(column.getName());
                if (value == null) {
                    return null;
                }
                return ExTextColor.NAMES.value(value);
            }

            @Override
            public String parseValueToSQL(@NotNull ExTextColor textColor) {
                return textColor.toString();
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

    public String getSelectWrapper(String columnName) {
        return Table.COLUMN_WRAPPER + columnName + Table.COLUMN_WRAPPER;
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

    public abstract Value parseValueFromSQL(ResultSet rs, Column<Value> column) throws SQLException;

    public abstract String parseValueToSQL(@NotNull Value value);

    public ColumnType<Value> unique() {
        return new ColumnType<>(this.getSimpleName(), this.getLength(), this.isWrapped(), true) {
            @Override
            public String getName() {
                return ColumnType.this.getName();
            }

            @Override
            public Value parseValueFromSQL(ResultSet rs, Column<Value> column) throws SQLException {
                return ColumnType.this.parseValueFromSQL(rs, column);
            }

            @Override
            public String parseValueToSQL(@NotNull Value v) {
                return ColumnType.this.parseValueToSQL(v);
            }
        };
    }

    public ColumnType<Value> nonUnique() {
        return new ColumnType<>(this.getSimpleName(), this.getLength(), this.isWrapped(), false) {
            @Override
            public String getName() {
                return ColumnType.this.getName();
            }

            @Override
            public Value parseValueFromSQL(ResultSet rs, Column<Value> column) throws SQLException {
                return ColumnType.this.parseValueFromSQL(rs, column);
            }

            @Override
            public String parseValueToSQL(@NotNull Value v) {
                return ColumnType.this.parseValueToSQL(v);
            }
        };
    }

    public ColumnType<Value> notNull() {
        return new ColumnType<>(this.getSimpleName(), this.getLength(), this.isWrapped()) {
            @Override
            public String getName() {
                return ColumnType.this.getName() + " NOT NULL";
            }

            @Override
            public Value parseValueFromSQL(ResultSet rs, Column<Value> column) throws SQLException {
                return ColumnType.this.parseValueFromSQL(rs, column);
            }

            @Override
            public String parseValueToSQL(@NotNull Value v) {
                return ColumnType.this.parseValueToSQL(v);
            }
        };
    }
}
