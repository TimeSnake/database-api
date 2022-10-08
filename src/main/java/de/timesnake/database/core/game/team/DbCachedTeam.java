/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbCachedGroup;
import de.timesnake.database.util.object.ColumnMap;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DbCachedTeam extends DbCachedGroup implements de.timesnake.database.util.game.DbTeam {

    private float ratio;
    private String colorName;
    private Boolean privateChat;

    public DbCachedTeam(DbTeam team) {
        super(team);

        ColumnMap map = team.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.PREFIX,
                Column.Group.PREFIX_COLOR, Column.Team.RATIO, Column.Team.COLOR, Column.Team.PRIVATE_CHAT));

        this.name = map.get(Column.Group.NAME);
        this.prefix = map.get(Column.Group.PREFIX);
        this.color = map.get(Column.Group.PREFIX_COLOR);
        this.ratio = map.get(Column.Team.RATIO);
        this.colorName = map.get(Column.Team.COLOR);
        this.privateChat = map.get(Column.Team.PRIVATE_CHAT);
    }

    @Override
    public DbTeam getDatabase() {
        return (DbTeam) super.getDatabase();
    }

    @NotNull
    @Override
    public Float getRatio() {
        return this.ratio;
    }

    @Override
    public void setRatio(float ratio) {
        this.ratio = ratio;
        this.getDatabase().setRatio(ratio);
    }

    @Override
    public void setColor(String colorName) {
        this.colorName = colorName;
        this.getDatabase().setColor(colorName);
    }

    @NotNull
    @Override
    public String getColorName() {
        return this.colorName;
    }

    @Override
    public boolean hasPrivateChat() {
        return this.privateChat;
    }

    @Override
    public void setPrivateChat(Boolean privateChat) {
        this.privateChat = privateChat;
        this.getDatabase().setPrivateChat(privateChat);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTeam toLocal() {
        return this.getDatabase().toLocal();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTeam toDatabase() {
        return this.getDatabase();
    }
}
