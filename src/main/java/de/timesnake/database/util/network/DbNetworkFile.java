/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.network;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface DbNetworkFile {

    boolean exists();

    @NotNull
    File getFile();
}
