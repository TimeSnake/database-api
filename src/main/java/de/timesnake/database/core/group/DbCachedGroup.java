package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.group.DbGroup;
import de.timesnake.database.util.object.ColumnMap;

import java.util.Set;

public class DbCachedGroup implements DbGroup {

    protected final de.timesnake.database.core.group.DbGroup group;

    private int rank;
    private String name;
    private String prefix;
    private String chatColorName;

    public DbCachedGroup(de.timesnake.database.core.group.DbGroup group) {
        this.group = group;

        ColumnMap columnMap = this.group.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.PREFIX,
                Column.Group.CHAT_COLOR));

        this.rank = group.getRank();
        this.name = columnMap.get(Column.Group.NAME);
        this.prefix = columnMap.get(Column.Group.PREFIX);
        this.chatColorName = columnMap.get(Column.Group.CHAT_COLOR);
    }

    @Override
    public boolean exists() {
        return this.group.exists();
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.group.setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
        this.group.setRank(rank);
    }

    @Override
    public Integer getRank() {
        return this.rank;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.group.setPrefix(prefix);
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setChatColorName(String chatColorName) {
        this.chatColorName = chatColorName;
        this.group.setChatColorName(chatColorName);
    }

    @Override
    public String getChatColorName() {
        return this.chatColorName;
    }

    @Override
    public DbGroup toLocal() {
        return new DbCachedGroup(this.group);
    }

    @Override
    public DbGroup toDatabase() {
        return this.group;
    }
}
