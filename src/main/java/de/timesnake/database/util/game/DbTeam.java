package de.timesnake.database.util.game;

import de.timesnake.database.util.group.DbGroup;
import de.timesnake.database.util.object.NotLocal;

public interface DbTeam extends DbGroup {

    @NotLocal
    void setRatio(float ratio);

    Float getRatio();

    @NotLocal
    void setColor(String colorName);

    String getColorName();

    @Override
    DbTeam toLocal();

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
