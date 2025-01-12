/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.network;

import java.io.File;

public interface DbNetworkFile {

  boolean exists();

  File getFile();
}
