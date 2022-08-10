package de.timesnake.database.core.group;

public class DbCachedGroup extends DbCachedGroupBasis implements de.timesnake.database.util.group.DbGroup {

    protected String prefix;
    protected String chatColorName;

    public DbCachedGroup(DbGroup database) {
        super(database);
    }

    @Override
    public DbGroup getDatabase() {
        return (DbGroup) super.getDatabase();
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.getDatabase().setPrefix(prefix);
    }

    @Override
    public String getChatColorName() {
        return this.chatColorName;
    }

    @Override
    public void setChatColorName(String chatColorName) {
        this.chatColorName = chatColorName;
        this.getDatabase().setChatColorName(chatColorName);
    }

    @Override
    public de.timesnake.database.util.group.DbGroup toLocal() {
        return this.getDatabase().toLocal();
    }

    @Override
    public de.timesnake.database.util.group.DbGroup toDatabase() {
        return this.getDatabase();
    }
}
