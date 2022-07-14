package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbCachedTmpGameInfo;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.util.game.DbTmpGame;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;

public class DbCachedTmpGame extends DbCachedGame implements de.timesnake.database.util.game.DbTmpGame {

    protected DbCachedTmpGame(de.timesnake.database.core.game.DbTmpGame game) {
        super(game, new DbCachedTmpGameInfo(game.getInfo()));
    }

    @Override
    public DbCachedTmpGameInfo getInfo() {
        return (DbCachedTmpGameInfo) super.getInfo();
    }

    @Override
    protected de.timesnake.database.core.game.DbTmpGame getDatabase() {
        return (de.timesnake.database.core.game.DbTmpGame) super.getDatabase();
    }

    @Override
    public Integer getMaxPlayers() {return getInfo().getMaxPlayers();}

    @Override
    public void setMaxPlayers(int maxPlayers) {getInfo().setMaxPlayers(maxPlayers);}

    @Override
    public Type.Availability getMapAvailability() {return getInfo().getMapAvailability();}

    @Override
    public void setMapsAvailability(Type.Availability maps) {getInfo().setMapsAvailability(maps);}

    @Override
    public Type.Availability getKitAvailability() {return getInfo().getKitAvailability();}

    @Override
    public void setKitsAvailability(Type.Availability kits) {getInfo().setKitsAvailability(kits);}

    @Override
    public boolean hasStatistics() {return getInfo().hasStatistics();}

    @Override
    public void setStatistics(boolean statistics) {getInfo().setStatistics(statistics);}

    @Override
    public String getTexturePackLink() {return getInfo().getTexturePackLink();}

    @Override
    public void setTexturePackLink(String texturePack) {getInfo().setTexturePackLink(texturePack);}

    @Override
    public Boolean hasTexturePack() {return getInfo().hasTexturePack();}

    @Override
    public Integer getPlayerTrackingRange() {return getInfo().getPlayerTrackingRange();}

    @Override
    public void setPlayerTrackingRange(Integer playerTrackingRange) {getInfo().setPlayerTrackingRange(playerTrackingRange);}

    @Override
    public boolean exists() {return getInfo().exists();}

    @Override
    public String getName() {return getInfo().getName();}

    @Override
    public String getDisplayName() {return getInfo().getDisplayName();}

    @Override
    public void setDisplayName(String displayName) {getInfo().setDisplayName(displayName);}

    @Override
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Override
    public void setChatColorName(String chatColorName) {getInfo().setChatColorName(chatColorName);}

    @Override
    public String getItemName() {return getInfo().getItemName();}

    @Override
    public void setItem(String itemName) {getInfo().setItem(itemName);}

    @Override
    public String getHeadLine() {return getInfo().getHeadLine();}

    @Override
    public void setHeadLine(String headLine) {getInfo().setHeadLine(headLine);}

    @Override
    public Integer getSlot() {return getInfo().getSlot();}

    @Override
    public void setSlot(int slot) {getInfo().setSlot(slot);}

    @Override
    public Integer getAutoStartPlayerNumber() {return getInfo().getAutoStartPlayerNumber();}

    @Override
    public void setAutoStartPlayerNumber(Integer number) {getInfo().setAutoStartPlayerNumber(number);}

    @Override
    public Integer getMinPlayerNumber() {return getInfo().getMinPlayerNumber();}

    @Override
    public void setMinPlayerNumber(Integer number) {getInfo().setMinPlayerNumber(number);}

    @Override
    public List<Integer> getTeamSizes() {return getInfo().getTeamSizes();}

    @Override
    public void setTeamSizes(List<Integer> sizes) {getInfo().setTeamSizes(sizes);}

    @Override
    public Type.Availability getTeamMergeAvailability() {return getInfo().getTeamMergeAvailability();}

    @Override
    public void setTeamMergeAvailability(Type.Availability availability) {getInfo().setTeamMergeAvailability(availability);}

    @Override
    public boolean isEqualTeamSizeRequired() {return getInfo().isEqualTeamSizeRequired();}

    @Override
    public void requireEqualTeamSize(boolean require) {getInfo().requireEqualTeamSize(require);}

    @Override
    public List<String> getDescription() {return getInfo().getDescription();}

    @Override
    public void setDescription(List<String> description) {getInfo().setDescription(description);}

    @Override
    public void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName) {
        this.getDatabase().addTeam(name, rank, prefix, colorChatName, ratio, colorName);
    }

    @Override
    public void removeTeam(String name) {
        this.getDatabase().removeTeam(name);
    }

    @Override
    public void removeTeam(int rank) {
        this.getDatabase().removeTeam(rank);
    }

    @Override
    public Integer getHighestRank() {
        return this.getDatabase().getHighestRank();
    }

    @Override
    public boolean containsTeam(int rank) {
        return this.getDatabase().containsTeam(rank);
    }

    @Override
    public boolean containsTeam(String name) {
        return this.getDatabase().containsTeam(name);
    }

    @Override
    public DbTeam getTeam(String name) {
        return this.getDatabase().getTeam(name);
    }

    @Override
    public DbTeam getTeam(int rank) {
        return this.getDatabase().getTeam(rank);
    }

    @Override
    public Collection<String> getTeamNames() {
        return this.getDatabase().getTeamNames();
    }

    @Override
    public Collection<Integer> getTeamRanks() {
        return this.getDatabase().getTeamRanks();
    }

    @Override
    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        return this.getDatabase().getTeams();
    }

    @Override
    public DbTmpGame toDatabase() {
        return this.getDatabase();
    }

    public DbTmpGame toLocal() {
        return this.getDatabase().toLocal();
    }
}
