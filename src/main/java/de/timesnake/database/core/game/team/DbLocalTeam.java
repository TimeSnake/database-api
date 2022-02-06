package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbLocalGroup;
import de.timesnake.database.util.game.DbTeam;
import de.timesnake.database.util.object.ColumnMap;

import java.util.Set;

public class DbLocalTeam extends DbLocalGroup implements DbTeam {

    private float ratio;
    private String colorName;

    public DbLocalTeam(de.timesnake.database.core.game.team.DbTeam team) {
        super(team);

        ColumnMap columnMap = team.getFirstWithKey(Set.of(Column.Team.RATIO, Column.Team.COLOR));

        this.ratio = columnMap.get(Column.Team.RATIO);
        this.colorName = columnMap.get(Column.Team.COLOR);
    }

    @Override
    public void setRatio(float ratio) {
        this.ratio = ratio;
        ((de.timesnake.database.core.game.team.DbTeam) super.group).setRatio(ratio);
    }

    @Override
    public Float getRatio() {
        return this.ratio;
    }

    @Override
    public void setColor(String colorName) {
        this.colorName = colorName;
        ((de.timesnake.database.core.game.team.DbTeam) super.group).setColor(colorName);
    }

    @Override
    public String getColorName() {
        return this.colorName;
    }

    @Override
    public DbTeam toLocal() {
        return new DbLocalTeam(((de.timesnake.database.core.game.team.DbTeam) this.group));
    }

    @Override
    public DbTeam toDatabase() {
        return ((de.timesnake.database.core.game.team.DbTeam) this.group);
    }
}
