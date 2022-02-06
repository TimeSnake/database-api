package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotLocal;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;

public interface DbGameInfo {

    boolean exists();

    @NotLocal
    void setDisplayName(String displayName);

    @NotLocal
    void setChatColorName(String chatColorName);

    @NotLocal
    void setAutoStart(int autoStart);

    @NotLocal
    void setMinPlayers(int minPlayers);

    @NotLocal
    void setMaxPlayers(int maxPlayers);

    @NotLocal
    void setHeadLine(String description);

    @NotLocal
    void setItem(String itemName);

    @NotLocal
    void setSlot(int slot);

    @NotLocal
    void setKitsAvailability(Type.Availability kits);

    @NotLocal
    void setMapsAvailability(Type.Availability maps);

    @NotLocal
    void setTemporary(boolean isTemporary);

    @NotLocal
    void setTeamAmounts(Collection<Integer> amounts);

    @NotLocal
    void setTeamMergeAvailability(Type.Availability teamMerging);

    @NotLocal
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
