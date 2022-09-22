package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DbTmpGameInfo extends DbGameInfo {

    @NotNull
    Integer getAutoStartPlayerNumber();

    @NotCached
    void setAutoStartPlayerNumber(Integer number);

    @NotNull
    Integer getMinPlayerNumber();

    @NotCached
    void setMinPlayerNumber(Integer number);

    @Nullable
    List<Integer> getTeamSizes();

    @NotCached
    void setTeamSizes(List<Integer> sizes);

    @NotNull
    Type.Availability getTeamMergeAvailability();

    @NotCached
    void setTeamMergeAvailability(Type.Availability availability);

    boolean isEqualTeamSizeRequired();

    @NotCached
    void requireEqualTeamSize(boolean require);

    boolean hideTeams();

    @NotCached
    void setHideTeams(boolean hide);

    @NotNull
    Type.Discord getDiscordType();

    void setDiscordType(Type.Discord type);

    @Nullable
    List<String> getDescription();

    @NotCached
    void setDescription(List<String> description);

    @NotNull
    @Override
    DbTmpGameInfo toDatabase();

    @NotNull
    @Override
    DbTmpGameInfo toLocal();
}
