/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.server.DbServer;
import java.util.HashMap;

public class ServerMap extends
    HashMap<Type.Server<? extends DbServer>, ServerTable<? extends DbServer>> {

  public <Table extends ServerTable<S>, S extends DbServer> Table put(Type.Server<S> key,
      Table value) {
    return (Table) super.put(key, value);
  }

  public <Table extends ServerTable<S>, S extends DbServer, T extends Type.Server<S>> Table get(
      T type) {
    return (Table) super.get(type);
  }
}
