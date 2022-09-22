package de.timesnake.database.util.permission;

import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DbPermission {

    boolean exists();

    @NotNull
    Integer getId();

    @NotNull
    String getName();

    void setName(String name);

    @NotNull
    Status.Permission getMode();

    void setMode(Status.Permission mode);

    @Nullable
    Collection<String> getServers();

    void setServers(Collection<String> servers);
}
