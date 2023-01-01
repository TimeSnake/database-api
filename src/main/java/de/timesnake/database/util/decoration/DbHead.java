/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.decoration;

import org.jetbrains.annotations.NotNull;

public interface DbHead {

    boolean exists();

    void delete();

    @NotNull
    String getTag();

    @NotNull
    String getName();

    void setName(String name);

    @NotNull
    String getSection();

    void setSection(String section);
}
