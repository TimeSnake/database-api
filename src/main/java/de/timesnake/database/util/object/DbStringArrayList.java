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

package de.timesnake.database.util.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DbStringArrayList extends ArrayList<String> {

    public DbStringArrayList() {
        super();
    }

    public DbStringArrayList(Collection<String> collection) {
        if (collection != null) {
            super.addAll(collection);
        }
    }

    public DbStringArrayList(String[] array) {
        if (array != null) {
            super.addAll(Arrays.asList(array));
        }
    }
}
