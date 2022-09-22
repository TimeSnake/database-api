package de.timesnake.database.util.group;

import org.jetbrains.annotations.NotNull;

public interface DbDisplayGroup extends DbGroup {

    @NotNull
    @Override
    DbDisplayGroup toDatabase();

    boolean showAlways();

    void setShowAlways(boolean showAlways);

    @NotNull
    @Override
    DbDisplayGroup toLocal();
}
