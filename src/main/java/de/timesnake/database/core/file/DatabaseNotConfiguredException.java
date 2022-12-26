/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.file;

public class DatabaseNotConfiguredException extends Throwable {

    private final String databaseType;
    private final String missingValueType;

    public DatabaseNotConfiguredException(String databaseType, String missingValueType) {
        this.databaseType = databaseType;
        this.missingValueType = missingValueType;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public String getMissingValueType() {
        return missingValueType;
    }

    public String getMessage() {
        return "[Database] WARNING Database " + this.databaseType + ": Value " + this.missingValueType + "is not " +
                "configured";
    }
}
