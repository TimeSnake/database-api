package de.timesnake.database.util.group;

public interface DbDisplayGroup extends DbGroup {

    @Override
    DbDisplayGroup toDatabase();

    boolean showAlways();

    void setShowAlways(boolean showAlways);

    @Override
    DbDisplayGroup toLocal();
}
