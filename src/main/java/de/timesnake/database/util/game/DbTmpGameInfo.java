package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;

import java.util.List;

public interface DbTmpGameInfo extends DbGameInfo {

    Integer getAutoStartPlayerNumber();

    @NotCached
    void setAutoStartPlayerNumber(Integer number);

    Integer getMinPlayerNumber();

    @NotCached
    void setMinPlayerNumber(Integer number);

    List<Integer> getTeamSizes();

    @NotCached
    void setTeamSizes(List<Integer> sizes);

    Type.Availability getTeamMergeAvailability();

    @NotCached
    void setTeamMergeAvailability(Type.Availability availability);

    boolean isEqualTeamSizeRequired();

    @NotCached
    void requireEqualTeamSize(boolean require);

    boolean hideTeams();

    @NotCached
    void setHideTeams(boolean hide);

    Type.Discord getDiscordType();

    void setDiscordType(Type.Discord type);

    List<String> getDescription();

    @NotCached
    void setDescription(List<String> description);

    @Override
    DbTmpGameInfo toDatabase();

    @Override
    DbTmpGameInfo toLocal();
}
