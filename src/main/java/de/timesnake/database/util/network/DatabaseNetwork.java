package de.timesnake.database.util.network;

import de.timesnake.database.core.network.DbNetworkFile;

import java.io.File;
import java.util.List;

public interface DatabaseNetwork {
    void addNetworkFile(String name, File filePath);

    DbNetworkFile getNetworkFile(String name);

    List<DbNetworkFile> getNetworkFiles();
}
