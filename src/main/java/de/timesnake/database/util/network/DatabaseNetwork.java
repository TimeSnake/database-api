package de.timesnake.database.util.network;

import de.timesnake.database.core.network.DbNetworkFile;

import java.util.List;

public interface DatabaseNetwork {
    DbNetworkFile getNetworkFile(String name);

    List<DbNetworkFile> getNetworkFiles();
}
