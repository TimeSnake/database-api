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
