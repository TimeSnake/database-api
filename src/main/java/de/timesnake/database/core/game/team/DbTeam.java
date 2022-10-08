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
import de.timesnake.database.core.group.DbGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbTeam extends DbGroup implements de.timesnake.database.util.game.DbTeam {

    public DbTeam(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @NotNull
    @Override
    public Float getRatio() {
        return this.getFirstWithKey(Column.Team.RATIO);
    }

    @Override
    public void setRatio(float ratio) {
        this.setWithKey(ratio, Column.Team.RATIO);
    }

    @Override
    public void setColor(String colorName) {
        this.setWithKey(this.parseColor(colorName), Column.Team.COLOR);
    }

    @NotNull
    @Override
    public String getColorName() {
        return this.parseColor(this.getFirstWithKey(Column.Team.COLOR));
    }

    @Override
    public boolean hasPrivateChat() {
        return this.getFirstWithKey(Column.Team.PRIVATE_CHAT);
    }

    @Override
    public void setPrivateChat(Boolean privateChat) {
        this.setWithKey(privateChat, Column.Team.PRIVATE_CHAT);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTeam toLocal() {
        return new DbCachedTeam(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbTeam toDatabase() {
        return this;
    }

}
