package de.timesnake.database.util.permission;

import de.timesnake.library.basic.util.Status;

import java.util.Collection;

public interface DbPermission {

    boolean exists();

    Integer getId();

    String getName();

    void setName(String name);

    Status.Permission getMode();

    void setServers(Collection<String> servers);

    void setMode(Status.Permission mode);

    Collection<String> getServers();
}
