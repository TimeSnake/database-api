package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbGroup;
import de.timesnake.database.util.object.DatabaseConnector;

public class DbTeam extends DbGroup implements de.timesnake.database.util.game.DbTeam {

    public DbTeam(DatabaseConnector databaseConnector, int rank, String nameTable) {
        super(databaseConnector, rank, nameTable);
    }

    @Override
    public void setRatio(float ratio) {
        this.setWithKey(ratio, Column.Team.RATIO);
    }

    @Override
    public Float getRatio() {
        return this.getFirstWithKey(Column.Team.RATIO);
    }

    @Override
    public void setColor(String colorName) {
        this.setWithKey(this.parseColor(colorName), Column.Team.COLOR);
    }

    @Override
    public String getColorName() {
        return this.parseColor(this.getFirstWithKey(Column.Team.COLOR));
    }

    @Override
    public de.timesnake.database.util.game.DbTeam toLocal() {
        return new DbCachedTeam(this);
    }

    @Override
    public de.timesnake.database.util.game.DbTeam toDatabase() {
        return this;
    }

}
