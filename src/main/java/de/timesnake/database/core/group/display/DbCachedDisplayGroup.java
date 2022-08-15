package de.timesnake.database.core.group.display;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbCachedGroup;
import de.timesnake.database.util.object.ColumnMap;

import java.util.Set;

public class DbCachedDisplayGroup extends DbCachedGroup implements de.timesnake.database.util.group.DbDisplayGroup {

    protected boolean showAlways;

    public DbCachedDisplayGroup(DbDisplayGroup database) {
        super(database);

        ColumnMap map = this.database.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.PREFIX, Column.Group.PREFIX_COLOR,
                Column.Group.SHOW_ALWAYS));

        this.name = map.get(Column.Group.NAME);
        this.prefix = map.get(Column.Group.PREFIX);
        this.color = map.get(Column.Group.PREFIX_COLOR);
        this.showAlways = map.get(Column.Group.SHOW_ALWAYS);
    }

    @Override
    public DbDisplayGroup getDatabase() {
        return (DbDisplayGroup) super.getDatabase();
    }

    @Override
    public boolean showAlways() {
        return this.showAlways;
    }

    @Override
    public void setShowAlways(boolean showAlways) {
        this.showAlways = showAlways;
        this.getDatabase().setShowAlways(showAlways);
    }

    @Override
    public de.timesnake.database.util.group.DbDisplayGroup toLocal() {
        return this.getDatabase().toLocal();
    }

    @Override
    public de.timesnake.database.util.group.DbDisplayGroup toDatabase() {
        return this.getDatabase();
    }
}
