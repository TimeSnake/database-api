package de.timesnake.database.util.decoration;

import java.util.Collection;

public interface DatabaseDecoration {
    DbHead getHead(String tag);

    boolean containsHead(String tag);

    boolean addHead(String tag, String name, String section);

    boolean removeHead(String tag);

    Collection<String> getHeadTags();

    Collection<String> getHeadTags(String section);

    Collection<DbHead> getHeads();

    Collection<DbHead> getHeads(String section);

    Collection<String> getSections();
}
