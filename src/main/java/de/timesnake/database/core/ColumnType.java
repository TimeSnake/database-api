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

    protected final String name;
    protected final int length;
    protected final boolean wrapped;

    ColumnType(String name, int length, boolean wrapped) {
        this.name = name;
        this.length = length;
        this.wrapped = wrapped;
    }

    public String getSimpleName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public boolean isWrapped() {
        return wrapped;
    }

    public abstract String getName();
}
