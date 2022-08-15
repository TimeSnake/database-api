package de.timesnake.database.core.group;

import de.timesnake.library.basic.util.chat.ExTextColor;

public class DbCachedGroup extends DbCachedGroupBasis implements de.timesnake.database.util.group.DbGroup {

    protected String prefix;
    protected ExTextColor color;

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
        return this.color.toString();
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        this.color = ExTextColor.NAMES.value(chatColorName);
        this.getDatabase().setChatColor(color);
    }

    @Override
    public ExTextColor getChatColor() {
        return this.color;
    }

    @Override
    public void setChatColor(ExTextColor color) {
        this.color = color;
        this.getDatabase().setChatColor(color);
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
