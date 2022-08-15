package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.chat.ExTextColor;

public class DbGameInfoBasis extends TableQuery implements de.timesnake.database.util.game.DbGameInfoBasis {

    public DbGameInfoBasis(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, new TableEntry<>(gameName, Column.Game.NAME));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Game.NAME) != null;
    }

    @Override
    public String getName() {
        return (String) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getDisplayName() {
        return super.getFirstWithKey(Column.Game.DISPLAY_NAME);
    }

    @Override
    public void setDisplayName(String displayName) {
        super.setWithKey(displayName, Column.Game.DISPLAY_NAME);
    }

    @Override
    @Deprecated
    public String getChatColorName() {
        return this.getTextColor().toString();
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        this.setTextColor(ExTextColor.NAMES.value(chatColorName));
    }

    @Override
    public ExTextColor getTextColor() {
        return super.getFirstWithKey(Column.Game.TEXT_COLOR);
    }

    @Override
    public void setTextColor(ExTextColor color) {
        super.setWithKey(color, Column.Game.TEXT_COLOR);
    }

    @Override
    public String getItemName() {
        return super.getFirstWithKey(Column.Game.ITEM);
    }

    @Override
    public void setItem(String itemName) {
        super.setWithKey(itemName, Column.Game.ITEM);
    }

    @Override
    public String getHeadLine() {
        return super.getFirstWithKey(Column.Game.HEAD_LINE);
    }

    @Override
    public void setHeadLine(String headLine) {
        super.setWithKey(headLine, Column.Game.HEAD_LINE);
    }

    @Override
    public Integer getSlot() {
        return super.getFirstWithKey(Column.Game.SLOT);
    }

    @Override
    public void setSlot(int slot) {
        super.setWithKey(slot, Column.Game.SLOT);
    }

    @Override
    public de.timesnake.database.util.game.DbGameInfoBasis toDatabase() {
        return this;
    }

    @Override
    public de.timesnake.database.util.game.DbGameInfoBasis toLocal() {
        return new DbCachedGameInfoBasis(this);
    }

}
