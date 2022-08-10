package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class DbGroup extends DbGroupBasis implements de.timesnake.database.util.group.DbGroup {


    protected DbGroup(DatabaseConnector databaseConnector, String name, String tableName) {
        super(databaseConnector, name, tableName);
    }

    @Override
    public String getPrefix() {
        return super.getFirstWithKey(Column.Group.PREFIX);
    }

    @Override
    public void setPrefix(String prefix) {
        super.setWithKey(prefix, Column.Group.PREFIX);
    }

    @Override
    public String getChatColorName() {
        return super.getFirstWithKey(Column.Group.PREFIX_COLOR);
    }

    @Override
    public void setChatColorName(String chatColorName) {
        super.setWithKey(chatColorName, Column.Group.PREFIX_COLOR);
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
