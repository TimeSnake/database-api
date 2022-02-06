package de.timesnake.database.util.decoration;

public interface DbHead {

    boolean exists();

    void delete();

    String getTag();

    String getName();

    String getSection();

    void setName(String name);

    void setSection(String section);
}
