package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;

public interface DbGameInfo {

    boolean exists();

    @NotCached
    void setDisplayName(String displayName);

    @NotCached
    void setChatColorName(String chatColorName);

    @NotCached
    void setAutoStart(int autoStart);

    @NotCached
    void setMinPlayers(int minPlayers);

    @NotCached
    void setMaxPlayers(int maxPlayers);

    @NotCached
    void setHeadLine(String description);

    @NotCached
    void setItem(String itemName);

    @NotCached
    void setSlot(int slot);

    @NotCached
    void setKitsAvailability(Type.Availability kits);

    @NotCached
    void setMapsAvailability(Type.Availability maps);

    @NotCached
    void setTemporary(boolean isTemporary);

    @NotCached
    void setTeamAmounts(Collection<Integer> amounts);

    @NotCached
    void setTeamMergeAvailability(Type.Availability teamMerging);

    @NotCached
    void setDescription(Collection<String> description);

    String getName();

    String getDisplayName();

    String getChatColorName();

    Integer getAutoStart();

    Integer getMinPlayers();

    Integer getMaxPlayers();

    String getHeadLine();

    String getItemName();

    Integer getSlot();

    boolean isTemporary();

    Type.Availability getKitAvailability();

    Type.Availability getMapAvailability();

    Collection<Integer> getTeamAmounts();

    Type.Availability getTeamMergeAvailability();

    List<String> getDescription();

    DbGameInfo toLocal();

    DbGameInfo toDatabase();
}
