package de.timesnake.database.util.game;

import de.timesnake.database.util.object.UnsupportedStringException;

import java.util.Collection;

public interface DbKit {

    boolean exists();

    void setName(String name);

    void setItemType(String itemType);

    void setDescription(Collection<String> description) throws UnsupportedStringException;

    Integer getId();

    String getName();

    String getItemType();

    Collection<String> getDescription();
}
