package de.timesnake.database.core.group;

public class DbCachedGroupBasis implements de.timesnake.database.util.group.DbGroupBasis {

    protected final DbGroupBasis database;

    protected final int rank;
    protected String name;

    public DbCachedGroupBasis(DbGroupBasis database) {
        this.database = database;
        this.rank = this.database.getRank();
    }

    public de.timesnake.database.util.group.DbGroupBasis getDatabase() {
        return database;
    }

    public boolean exists() {
        return this.database.exists();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.database.setName(name);
    }

    public Integer getRank() {
        return this.rank;
    }

    public de.timesnake.database.util.group.DbGroupBasis toLocal() {
        return this.database.toLocal();
    }

    public de.timesnake.database.util.group.DbGroupBasis toDatabase() {
        return this.database;
    }
}
