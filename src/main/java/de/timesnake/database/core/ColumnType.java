package de.timesnake.database.core;

public abstract class ColumnType {

    public static ColumnType integer(int length) {
        return new ColumnType("int", length) {
            @Override
            public String getName() {
                return "int(" + this.length + ")";
            }
        };
    }

    public static ColumnType integer(int length, boolean autoIncrement) {
        return new ColumnType("int", length) {
            @Override
            public String getName() {
                return autoIncrement ? "int(" + this.length + ") AUTO_INCREMENT" : "int(" + this.length + ")";
            }
        };
    }

    public static ColumnType varchar(int length) {
        return new ColumnType("varchar", length) {
            @Override
            public String getName() {
                return "varchar(" + this.length + ")";
            }
        };
    }

    public static ColumnType tinyint(int length) {
        return new ColumnType("tinyint", length) {
            @Override
            public String getName() {
                return "tinyint(" + this.length + ")";
            }
        };
    }

    protected final String name;
    protected final int length;

    ColumnType(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getSimpleName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public abstract String getName();
}
