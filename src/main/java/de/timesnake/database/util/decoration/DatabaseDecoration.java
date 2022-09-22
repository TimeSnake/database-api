package de.timesnake.database.util.decoration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DatabaseDecoration {

    @Nullable
    DbHead getHead(String tag);

    boolean containsHead(String tag);

    boolean addHead(String tag, String name, String section);

    boolean removeHead(String tag);

    @NotNull
    Collection<String> getHeadTags();

    @NotNull
    Collection<String> getHeadTags(String section);

    @NotNull
    Collection<DbHead> getHeads();

    @NotNull
    Collection<DbHead> getHeads(String section);

    @NotNull
    Collection<String> getSections();
}
