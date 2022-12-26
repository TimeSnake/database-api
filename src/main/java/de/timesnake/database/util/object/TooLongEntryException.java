/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.object;

import de.timesnake.database.core.ColumnType;

public class TooLongEntryException extends Exception {

    private final String entry;
    private final ColumnType type;

    public TooLongEntryException(String entry, ColumnType type) {
        this.entry = entry;
        this.type = type;
    }

    public String getEntry() {
        return entry;
    }

    public ColumnType getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return "The max length is limited to " + this.type.getLength() + ", current: " + this.entry.length();
    }
}
