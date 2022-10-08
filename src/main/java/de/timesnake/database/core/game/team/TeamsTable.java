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
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.group.GroupsTable;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class TeamsTable extends GroupsTable {

    public TeamsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName);
        super.addColumn(Column.Team.RATIO);
        super.addColumn(Column.Team.COLOR);
        super.addColumn(Column.Team.PRIVATE_CHAT);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio, String colorName) {
        super.addGroup(name, rank, prefix, color, () -> {
            super.set(ratio, Column.Team.RATIO, new TableEntry<>(rank, Column.Group.PRIORITY));
            super.set(colorName, Column.Team.COLOR, new TableEntry<>(rank, Column.Group.PRIORITY));
        });
    }

    public void removeTeam(String name) {
        super.removeGroup(name);
    }

    @Nullable
    public Integer getHighestRank() {
        return super.getHighestInteger(Column.Group.PRIORITY);
    }

    public boolean containsTeam(String name) {
        return super.containsGroup(name);
    }

    @NotNull
    public DbTeam getTeam(String name) {
        return new DbTeam(this.databaseConnector, name, this.tableName);
    }

    public Collection<String> getTeamNames() {
        return super.getGroupNames();
    }

    public Collection<Integer> getTeamRanks() {
        return super.getGroupRanks();
    }

    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        Collection<de.timesnake.database.util.game.DbTeam> teams = new ArrayList<>();
        for (String name : this.getTeamNames()) {
            teams.add(this.getTeam(name));
        }
        return teams;
    }

}
