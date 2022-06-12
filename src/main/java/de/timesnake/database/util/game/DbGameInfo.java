package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;

public interface DbGameInfo {

    boolean exists();

    @NotCached
    void setItem(String itemName);

    @NotCached
    void setKitsAvailability(Type.Availability kits);

    @NotCached
    void setMapsAvailability(Type.Availability maps);

    @NotCached
    void setEqualTeamSize(Boolean equalSize);

    String getName();

    String getDisplayName();

    @NotCached
    void setDisplayName(String displayName);

    String getChatColorName();

    @NotCached
    void setChatColorName(String chatColorName);

    Integer getAutoStart();

    @NotCached
    void setAutoStart(int autoStart);

    Integer getMinPlayers();

    @NotCached
    void setMinPlayers(int minPlayers);

    Integer getMaxPlayers();

    @NotCached
    void setMaxPlayers(int maxPlayers);

    String getHeadLine();

    @NotCached
    void setHeadLine(String description);

    String getItemName();

    Integer getSlot();

    @NotCached
    void setSlot(int slot);

    boolean isTemporary();

    @NotCached
    void setTemporary(boolean isTemporary);

    Type.Availability getKitAvailability();

    Type.Availability getMapAvailability();

    Collection<Integer> getTeamAmounts();

    @NotCached
    void setTeamAmounts(Collection<Integer> amounts);

    Type.Availability getTeamMergeAvailability();

    @NotCached
    void setTeamMergeAvailability(Type.Availability teamMerging);

    Boolean isEqualTeamSize();

    String getTexturePackLink();

    @NotCached
    void setTexturePackLink(String texturePack);

    Boolean hasTexturePack();

    List<String> getDescription();

    @NotCached
    void setDescription(Collection<String> description);

    DbGameInfo toLocal();

    DbGameInfo toDatabase();
}
