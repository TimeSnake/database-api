package de.timesnake.database.util.game;

import de.timesnake.database.util.group.DbGroup;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;

public interface DbTeam extends DbGroup {

    @NotNull
    Float getRatio();

    @NotCached
    void setRatio(float ratio);

    @NotCached
    void setColor(String colorName);

    @NotNull
    String getColorName();

    boolean hasPrivateChat();

    @NotCached
    void setPrivateChat(Boolean privateChat);

    @NotNull
    @Override
    DbTeam toLocal();

    @NotNull
    @Override
    DbTeam toDatabase();

    default String parseColor(String colorName) {
        switch (colorName.toUpperCase()) {
            case "AQUA":
                return "AQUA";
            case "BLACK":
                return "BLACK";
            case "BLUE":
                return "BLUE";
            case "FUCHSIA":
                return "FUCHSIA";
            case "GRAY":
                return "GRAY";
            case "GREEN":
                return "GREEN";
            case "LIME":
                return "LIME";
            case "MAROON":
                return "MAROON";
            case "NAVY":
                return "NAVY";
            case "OLIVE":
                return "OLIVE";
            case "ORANGE":
                return "ORANGE";
            case "PURBLE":
                return "PURPLE";
            case "RED":
                return "RED";
            case "SILVER":
                return "SILVER";
            case "TEAL":
                return "TEAL";
            case "WHITE":
                return "WHITE";
            case "YELLOW":
                return "YELLOW";
            default:
        }
        return "WHITE";
    }
}
