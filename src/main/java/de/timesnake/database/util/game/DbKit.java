package de.timesnake.database.util.game;

import de.timesnake.database.util.object.UnsupportedStringException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DbKit {

    boolean exists();

    @NotNull
    Integer getId();

    @NotNull
    String getName();

    void setName(String name);

    @NotNull
    String getItemType();

    void setItemType(String itemType);

    @Nullable
    Collection<String> getDescription();

    void setDescription(Collection<String> description) throws UnsupportedStringException;
}
