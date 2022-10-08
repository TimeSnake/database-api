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

package de.timesnake.database.core.server;

import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.server.DbServer;

import java.util.HashMap;

public class ServerMap extends HashMap<Type.Server<? extends DbServer>, ServerTable<? extends DbServer>> {

    private final HashMap<Type.Server<?>, ServerTable<? extends DbServer>> serverTables = new HashMap<>();

    public <S extends DbServer> ServerTable<S> put(Type.Server<S> key, ServerTable<S> value) {
        return (ServerTable<S>) super.put(key, value);
    }

    public <S extends DbServer, T extends Type.Server<S>> ServerTable<S> get(T type) {
        return (ServerTable<S>) super.get(type);
    }
}
