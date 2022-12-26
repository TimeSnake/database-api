/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.network;

import de.timesnake.database.core.network.DbNetworkFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public interface DatabaseNetwork {

    void addNetworkFile(String name, File filePath);

    @Nullable
    DbNetworkFile getNetworkFile(String name);

    @NotNull
    List<DbNetworkFile> getNetworkFiles();
}
