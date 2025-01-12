/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core;

import de.timesnake.database.core.table.QueryTool;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.library.basic.util.Status;
import de.timesnake.library.basic.util.Type;
import de.timesnake.library.chat.ExTextColor;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static de.timesnake.database.core.table.QueryTool.ENTRY_ARRAY_DELIMITER;

public abstract class ColumnType<Value> {

  public static final ColumnType<Integer> INTEGER = new ColumnType<>("INTEGER", 11) {
    @Override
    public String getName() {
      return "INT";
    }

    @Override
    public Integer parseValueFromResultSet(ResultSet rs, Column<Integer> column)
        throws SQLException {
      int value = rs.getInt(column.getName());
      if (rs.wasNull()) {
        return null;
      }
      return value;
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Integer integer)
        throws SQLException {
      statement.setInt(index, integer);
    }
  };

  public static final ColumnType<Long> LONG = new ColumnType<>("BIGINT", 20) {
    @Override
    public String getName() {
      return "BIGINT(" + this.length + ")";
    }

    @Override
    public Long parseValueFromResultSet(ResultSet rs, Column<Long> column) throws SQLException {
      long value = rs.getLong(column.getName());
      if (rs.wasNull()) {
        return null;
      }
      return value;
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Long aLong)
        throws SQLException {
      statement.setLong(index, aLong);
    }
  };

  public static ColumnType<Integer> INTEGER(boolean autoIncrement) {
    return new ColumnType<>("INT", 1) {
      @Override
      public String getName() {
        return autoIncrement ? "INT AUTO_INCREMENT" : "INT";
      }

      @Override
      public Integer parseValueFromResultSet(ResultSet rs, Column<Integer> column)
          throws SQLException {
        int value = rs.getInt(column.getName());
        if (rs.wasNull()) {
          return null;
        }
        return value;
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Integer integer)
          throws SQLException {
        statement.setInt(index, integer);
      }
    };
  }

  public static ColumnType<String> VARCHAR(int length) {
    return new ColumnType<>("varchar", length) {
      @Override
      public String getName() {
        return "varchar(" + this.length + ")";
      }

      @Override
      public String parseValueFromResultSet(ResultSet rs, Column<String> column)
          throws SQLException {
        return rs.getString(column.getName());
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, String s)
          throws SQLException {
        statement.setString(index, s);
      }
    };
  }

  public static final ColumnType<Float> FLOAT = new ColumnType<>("float", 12) {
    @Override
    public String getName() {
      return "float(" + this.length + ")";
    }

    @Override
    public Float parseValueFromResultSet(ResultSet rs, Column<Float> column)
        throws SQLException {
      final float value = rs.getFloat(column.getName());
      if (rs.wasNull()) {
        return null;
      }
      return value;
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Float aFloat)
        throws SQLException {
      statement.setFloat(index, aFloat);
    }
  };

  public static final ColumnType<UUID> UUID = new ColumnType<>("binary", 16) {
    @Override
    public String getName() {
      return "binary(" + this.length + ")";
    }

    @Override
    public UUID parseValueFromResultSet(ResultSet rs, Column<UUID> column) throws SQLException {
      String uuid = rs.getString(column.getName());
      if (uuid != null) {
        return java.util.UUID.fromString(uuid);
      }
      return null;
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, UUID uuid)
        throws SQLException {
      statement.setString(index, uuid.toString());
    }

    @Override
    public String getSelectWrapper(String columnName) {
      return "BIN_TO_UUID(" + columnName + ") AS " + columnName;
    }

    @Override
    public String getValueWrapper() {
      return "UUID_TO_BIN(?)";
    }
  };

  public static final ColumnType<Boolean> BOOLEAN = new ColumnType<>("bit", 1) {
    @Override
    public String getName() {
      return "boolean";
    }

    @Override
    public Boolean parseValueFromResultSet(ResultSet rs, Column<Boolean> column)
        throws SQLException {
      return rs.getBoolean(column.getName());
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Boolean aBoolean)
        throws SQLException {
      statement.setBoolean(index, aBoolean);
    }
  };

  public static <T extends Status> ColumnType<T> STATUS() {
    return new ColumnType<>("varchar", 24) {
      @Override
      public String getName() {
        return "varchar(" + this.length + ")";
      }

      @Override
      public T parseValueFromResultSet(ResultSet rs, Column<T> column) throws SQLException {
        return Status.valueOf(rs.getString(column.getName()));
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Status status)
          throws SQLException {
        statement.setString(index, status.getName());
      }
    };
  }

  public static <T extends Type> ColumnType<T> TYPE() {
    return new ColumnType<>("varchar", 24) {
      @Override
      public String getName() {
        return "varchar(" + this.length + ")";
      }

      @Override
      public T parseValueFromResultSet(ResultSet rs, Column<T> column) throws SQLException {
        return Type.valueOf(rs.getString(column.getName()));
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Type type)
          throws SQLException {
        statement.setString(index, type.getName());
      }
    };
  }

  public static final ColumnType<LocalDateTime> LOCAL_DATE_TIME = new ColumnType<>("TIMESTAMP",
      19) {
    @Override
    public String getName() {
      return "TIMESTAMP";
    }

    @Override
    public LocalDateTime parseValueFromResultSet(ResultSet rs, Column<LocalDateTime> column)
        throws SQLException {
      Timestamp timestamp = rs.getTimestamp(column.getName());
      if (timestamp != null) {
        return timestamp.toLocalDateTime();
      }
      return null;
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, LocalDateTime dateTime)
        throws SQLException {
      statement.setTimestamp(index, Timestamp.valueOf(dateTime));
    }
  };

  public static final ColumnType<Duration> DURATION = new ColumnType<>("BIGINT", 19) {
    @Override
    public String getName() {
      return "BIGINT(" + this.length + ")";
    }

    @Override
    public Duration parseValueFromResultSet(ResultSet rs, Column<Duration> column)
        throws SQLException {
      return Duration.ofSeconds(rs.getLong(column.getName()));
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Duration dateTime)
        throws SQLException {
      statement.setLong(index, dateTime.toSeconds());
    }
  };

  public static ColumnType<List<String>> STRING_LIST(int length) {
    return new ColumnType<>("varchar", length) {
      @Override
      public String getName() {
        return "varchar(" + this.length + ")";
      }

      @Override
      public List<String> parseValueFromResultSet(ResultSet rs, Column<List<String>> column)
          throws SQLException {
        String value = rs.getString(column.getName());
        if (value == null) {
          return Collections.emptyList();
        }
        return new ArrayList<>(Arrays.asList(value.split(ENTRY_ARRAY_DELIMITER)));
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index,
                                   List<String> strings) throws SQLException {
        if (!strings.isEmpty()) {
          statement.setString(index, String.join(ENTRY_ARRAY_DELIMITER, strings));
        } else {
          statement.setNull(index, Types.NULL);
        }
      }
    };
  }

  public static ColumnType<List<Integer>> integerList(int length) {
    return new ColumnType<>("varchar", length) {
      @Override
      public String getName() {
        return "varchar(" + this.length + ")";
      }

      @Override
      public List<Integer> parseValueFromResultSet(ResultSet rs, Column<List<Integer>> column)
          throws SQLException {
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
      public void applyOnStatement(PreparedStatement statement, int index,
                                   List<Integer> integers) throws SQLException {
        if (!integers.isEmpty()) {
          statement.setString(index, integers.stream()
              .map(String::valueOf)
              .collect(Collectors.joining(ENTRY_ARRAY_DELIMITER)));
        } else {
          statement.setNull(index, Types.NULL);
        }
      }
    };
  }

  public static final ColumnType<BlockSide> BLOCK_SIDE = new ColumnType<>("varchar", 5) {
    @Override
    public String getName() {
      return "varchar(" + this.length + ")";
    }

    @Override
    public BlockSide parseValueFromResultSet(ResultSet rs, Column<BlockSide> column)
        throws SQLException {
      String value = rs.getString(column.getName());
      if (value == null) {
        return null;
      }
      return BlockSide.valueOf(value.toUpperCase());
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, BlockSide blockSide)
        throws SQLException {
      statement.setString(index, blockSide.name().toLowerCase());
    }
  };

  public static final ColumnType<Color> COLOR = new ColumnType<>("varchar", 15) {
    @Override
    public String getName() {
      return "varchar(" + this.length + ")";
    }

    @Override
    public Color parseValueFromResultSet(ResultSet rs, Column<Color> column)
        throws SQLException {
      String value = rs.getString(column.getName());
      if (value == null) {
        return null;
      }
      String[] rgba = value.split(ENTRY_ARRAY_DELIMITER);
      if (rgba.length < 4) {
        return null;
      }
      return new Color(Integer.parseInt(rgba[0]), Integer.parseInt(rgba[1]),
          Integer.parseInt(rgba[2]),
          Integer.parseInt(rgba[3]));
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Color color)
        throws SQLException {
      statement.setString(index,
          color.getRed() + ENTRY_ARRAY_DELIMITER +
              color.getGreen() + ENTRY_ARRAY_DELIMITER +
              color.getBlue() + ENTRY_ARRAY_DELIMITER +
              color.getAlpha());
    }
  };

  public static final ColumnType<File> FILE = new ColumnType<>("varchar", 500) {
    @Override
    public String getName() {
      return "varchar(" + this.length + ")";
    }

    @Override
    public File parseValueFromResultSet(ResultSet rs, Column<File> column) throws SQLException {
      String value = rs.getString(column.getName());
      if (value == null) {
        return null;
      }
      return new File(value);
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, File file)
        throws SQLException {
      statement.setString(index, file.getAbsolutePath());
    }
  };

  public static final ColumnType<Path> PATH = new ColumnType<>("varchar", 500) {
    @Override
    public String getName() {
      return "varchar(" + this.length + ")";
    }

    @Override
    public Path parseValueFromResultSet(ResultSet rs, Column<Path> column) throws SQLException {
      String value = rs.getString(column.getName());
      if (value == null) {
        return null;
      }
      return new File(value).toPath();
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index, Path path)
        throws SQLException {
      statement.setString(index, path.toFile().getAbsolutePath());
    }
  };

  public static final ColumnType<ExTextColor> TEXT_COLOR = new ColumnType<>("varchar", 16) {
    @Override
    public String getName() {
      return "varchar(" + this.length + ")";
    }

    @Override
    public ExTextColor parseValueFromResultSet(ResultSet rs, Column<ExTextColor> column)
        throws SQLException {
      String value = rs.getString(column.getName());
      if (value == null) {
        return null;
      }
      return ExTextColor.NAMES.value(value);
    }

    @Override
    public void applyOnStatement(PreparedStatement statement, int index,
                                 ExTextColor exTextColor) throws SQLException {
      statement.setString(index, exTextColor.getName());
    }
  };

  protected final String simpleName;
  protected final int length;
  protected final boolean unique;
  protected final boolean nullable;

  ColumnType(String simpleName, int length) {
    this.simpleName = simpleName;
    this.length = length;
    this.unique = false;
    this.nullable = true;
  }

  ColumnType(String simpleName, int length, boolean unique) {
    this.simpleName = simpleName;
    this.length = length;
    this.unique = unique;
    this.nullable = true;
  }

  ColumnType(String simpleName, int length, boolean unique, boolean nullable) {
    this.simpleName = simpleName;
    this.length = length;
    this.unique = unique;
    this.nullable = nullable;
  }

  public String getSimpleName() {
    return simpleName;
  }

  public String getSelectWrapper(String columnName) {
    return QueryTool.COLUMN_WRAPPER + columnName + QueryTool.COLUMN_WRAPPER;
  }

  public int getLength() {
    return length;
  }

  public boolean isUnique() {
    return unique;
  }

  public boolean isNullable() {
    return nullable;
  }

  public abstract String getName();

  public String getEnhancedName() {
    return this.getName() +
        (this.isNullable() ? "" : " NOT NULL") +
        (this.isUnique() ? " UNIQUE" : "");
  }

  public String getValueWrapper() {
    return "?";
  }

  public abstract Value parseValueFromResultSet(ResultSet rs, Column<Value> column)
      throws SQLException;

  public abstract void applyOnStatement(PreparedStatement statement, int index, Value value)
      throws SQLException;

  public ColumnType<Value> unique() {
    return new ColumnType<>(this.getSimpleName(), this.getLength(), true, this.isNullable()) {
      @Override
      public String getName() {
        return ColumnType.this.getName();
      }

      @Override
      public Value parseValueFromResultSet(ResultSet rs, Column<Value> column)
          throws SQLException {
        return ColumnType.this.parseValueFromResultSet(rs, column);
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Value value)
          throws SQLException {
        ColumnType.this.applyOnStatement(statement, index, value);
      }
    };
  }

  public ColumnType<Value> notUnique() {
    return new ColumnType<>(this.getSimpleName(), this.getLength(), false, this.isNullable()) {
      @Override
      public String getName() {
        return ColumnType.this.getName();
      }

      @Override
      public Value parseValueFromResultSet(ResultSet rs, Column<Value> column)
          throws SQLException {
        return ColumnType.this.parseValueFromResultSet(rs, column);
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Value value)
          throws SQLException {
        ColumnType.this.applyOnStatement(statement, index, value);
      }
    };
  }

  public ColumnType<Value> notNullable() {
    return new ColumnType<>(this.getSimpleName(), this.getLength(), this.isUnique(), false) {
      @Override
      public String getName() {
        return ColumnType.this.getName();
      }

      @Override
      public Value parseValueFromResultSet(ResultSet rs, Column<Value> column)
          throws SQLException {
        return ColumnType.this.parseValueFromResultSet(rs, column);
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Value value)
          throws SQLException {
        ColumnType.this.applyOnStatement(statement, index, value);
      }

    };
  }

  public ColumnType<Value> nullable() {
    return new ColumnType<>(this.getSimpleName(), this.getLength(), this.isUnique(), true) {
      @Override
      public String getName() {
        return ColumnType.this.getName();
      }

      @Override
      public Value parseValueFromResultSet(ResultSet rs, Column<Value> column)
          throws SQLException {
        return ColumnType.this.parseValueFromResultSet(rs, column);
      }

      @Override
      public void applyOnStatement(PreparedStatement statement, int index, Value value)
          throws SQLException {
        ColumnType.this.applyOnStatement(statement, index, value);
      }

    };
  }
}
