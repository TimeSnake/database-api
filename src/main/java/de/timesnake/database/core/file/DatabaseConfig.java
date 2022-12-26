/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.file;

public interface DatabaseConfig {

    String getString(String path);

    String getDatabaseName(String name) throws DatabaseNotConfiguredException;

    String getDatabaseUrl(String name) throws DatabaseNotConfiguredException;

}
