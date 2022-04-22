package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;

public class DbGroup extends TableQuery implements de.timesnake.database.util.group.DbGroup {


    protected DbGroup(DatabaseConnector databaseConnector, int rank, String nameTable) {
        super(databaseConnector, nameTable, new TableEntry<>(rank, Column.Group.RANK));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Group.RANK) != null;
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Group.NAME);
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Group.NAME);
    }

    @Override
    public void setRank(int rank) {
        super.setWithKey(rank, Column.Group.RANK);
    }

    @Override
    public Integer getRank() {
        return (Integer) super.primaryEntries.get(0).getValue();
    }

    @Override
    public void setPrefix(String prefix) {
        super.setWithKey(prefix, Column.Group.PREFIX);
    }

    @Override
    public String getPrefix() {
        return super.getFirstWithKey(Column.Group.PREFIX);
    }

    @Override
    public void setChatColorName(String chatColorName) {
        super.setWithKey(chatColorName, Column.Group.CHAT_COLOR);
    }

    @Override
    public String getChatColorName() {
        return super.getFirstWithKey(Column.Group.CHAT_COLOR);
    }

    @Override
    public de.timesnake.database.util.group.DbGroup toLocal() {
        return new DbCachedGroup(this);
    }

    @Override
    public de.timesnake.database.util.group.DbGroup toDatabase() {
        return this;
    }

}
