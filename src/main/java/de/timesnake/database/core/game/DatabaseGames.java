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

package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.NonTmpGamesInfoTable;
import de.timesnake.database.core.game.info.TmpGamesInfoTable;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DatabaseGames extends DatabaseConnector implements de.timesnake.database.util.game.DatabaseGames {

    protected final NonTmpGamesInfoTable nonTmpGamesInfoTable;
    protected final TmpGamesInfoTable tmpGamesInfoTable;

    public DatabaseGames(String name, String url, String user, String password, String nonTmpGamesInfoTableName,
                         String tmpGamesInfoTableName) {
        super(name, url, user, password);
        this.nonTmpGamesInfoTable = new NonTmpGamesInfoTable(this, nonTmpGamesInfoTableName);
        this.tmpGamesInfoTable = new TmpGamesInfoTable(this, tmpGamesInfoTableName);
    }

    @Override
    public void createTables() {
        this.nonTmpGamesInfoTable.create();
        this.tmpGamesInfoTable.create();
        for (DbGame game : this.getGames()) {
            game.createTables();
        }
    }

    @Override
    public void backupTables() {
        for (DbGame game : this.getGames()) {
            game.backup();
        }
        this.nonTmpGamesInfoTable.backup();
        this.tmpGamesInfoTable.backup();
    }

    @Override
    public DbGame getGame(String gameName) {
        if (this.nonTmpGamesInfoTable.containsGame(gameName)) {
            return new DbNonTmpGame(this, gameName, this.nonTmpGamesInfoTable.getGame(gameName));
        } else if (this.tmpGamesInfoTable.containsGame(gameName)) {
            return new DbTmpGame(this, gameName, this.tmpGamesInfoTable.getGame(gameName));
        }
        return null;
    }

    @Override
    public boolean containsGame(String gameName) {
        if (this.nonTmpGamesInfoTable.containsGame(gameName)) {
            return true;
        } else if (this.tmpGamesInfoTable.containsGame(gameName)) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<String> getGamesName() {
        List<String> names = new ArrayList<>();
        names.addAll(this.nonTmpGamesInfoTable.getGamesName());
        names.addAll(this.tmpGamesInfoTable.getGamesName());
        return names;
    }

    @Override
    public Collection<DbGame> getGames() {
        List<DbGame> games = new LinkedList<>();
        for (String gameName : this.getGamesName()) {
            games.add(this.getGame(gameName));
        }
        return games;
    }

}
