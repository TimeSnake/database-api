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
