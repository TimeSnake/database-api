package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbTmpGameInfo;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.core.game.team.TeamsTable;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DbTmpGame extends DbGame implements de.timesnake.database.util.game.DbTmpGame {

    private Optional<TeamsTable> teamsTable;

    protected DbTmpGame(DatabaseConnector databaseConnector, String gameName, DbTmpGameInfo info) {
        super(databaseConnector, gameName, info);
    }

    @Override
    public de.timesnake.database.core.game.info.DbTmpGameInfo getInfo() {
        return (de.timesnake.database.core.game.info.DbTmpGameInfo) super.getInfo();
    }

    private boolean loadTeamsTable() {
        if (this.teamsTable != null) {
            if (this.teamsTable.isEmpty()) {
                return false;
            }

            if (this.teamsTable.get() != null) {
                return true;
            }
        }

        List<Integer> teamSizes = this.getInfo().getTeamSizes();
        if (teamSizes.size() > 0 && !(teamSizes.size() == 1 && teamSizes.get(0).equals(0))) {
            this.teamsTable = Optional.of(DatabaseManager.getInstance().getGameTeams().getGameTeams(gameName));
            return true;
        } else {
            this.teamsTable = Optional.empty();
            return false;
        }
    }

    @Override
    public void createTables() {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().create();
        }
        super.createTables();
    }

    @Override
    public void backup() {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().backup();
        }
        super.backup();
    }

    @Override
    public void delete() {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().delete();
        }
        super.delete();
    }


    @Override
    public void addTeam(String name, int rank, String prefix, String colorChatName, float ratio, String colorName) {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().addTeam(name, rank, prefix, colorChatName, ratio, colorName);
        }
    }

    @Override
    public void removeTeam(String name) {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().removeTeam(name);
        }
    }

    @Override
    public void removeTeam(int rank) {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().removeTeam(rank);
        }
    }

    @Override
    public Integer getHighestRank() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getHighestRank();
        }
        return null;
    }

    @Override
    public boolean containsTeam(int rank) {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().containsTeam(rank);
        }
        return false;
    }

    @Override
    public boolean containsTeam(String name) {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().containsTeam(name);
        }
        return false;
    }

    @Override
    public DbTeam getTeam(String name) {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeam(name);
        }
        return null;
    }

    @Override
    public DbTeam getTeam(int rank) {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeam(rank);
        }
        return null;
    }

    @Override
    public Collection<String> getTeamNames() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeamNames();
        }
        return null;
    }

    @Override
    public Collection<Integer> getTeamRanks() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeamRanks();
        }
        return null;
    }

    @Override
    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeams();
        }
        return null;
    }

    public Integer getMaxPlayers() {return getInfo().getMaxPlayers();}

    public void setMaxPlayers(int maxPlayers) {getInfo().setMaxPlayers(maxPlayers);}

    public Type.Availability getMapAvailability() {return getInfo().getMapAvailability();}

    public void setMapsAvailability(Type.Availability maps) {getInfo().setMapsAvailability(maps);}

    public Type.Availability getKitAvailability() {return getInfo().getKitAvailability();}

    public void setKitsAvailability(Type.Availability kits) {getInfo().setKitsAvailability(kits);}

    public boolean hasStatistics() {return getInfo().hasStatistics();}

    public void setStatistics(boolean statistics) {getInfo().setStatistics(statistics);}

    public String getTexturePackLink() {return getInfo().getTexturePackLink();}

    public void setTexturePackLink(String texturePack) {getInfo().setTexturePackLink(texturePack);}

    public Boolean hasTexturePack() {return getInfo().hasTexturePack();}

    public Integer getPlayerTrackingRange() {return getInfo().getPlayerTrackingRange();}

    public void setPlayerTrackingRange(Integer playerTrackingRange) {getInfo().setPlayerTrackingRange(playerTrackingRange);}

    public boolean exists() {return getInfo().exists();}

    public String getName() {return getInfo().getName();}

    public String getDisplayName() {return getInfo().getDisplayName();}

    public void setDisplayName(String displayName) {getInfo().setDisplayName(displayName);}

    public String getChatColorName() {return getInfo().getChatColorName();}

    public void setChatColorName(String chatColorName) {getInfo().setChatColorName(chatColorName);}

    public String getItemName() {return getInfo().getItemName();}

    public void setItem(String itemName) {getInfo().setItem(itemName);}

    public String getHeadLine() {return getInfo().getHeadLine();}

    public void setHeadLine(String headLine) {getInfo().setHeadLine(headLine);}

    public Integer getSlot() {return getInfo().getSlot();}

    public void setSlot(int slot) {getInfo().setSlot(slot);}

    public Integer getAutoStartPlayerNumber() {return getInfo().getAutoStartPlayerNumber();}

    public void setAutoStartPlayerNumber(Integer number) {getInfo().setAutoStartPlayerNumber(number);}

    public Integer getMinPlayerNumber() {return getInfo().getMinPlayerNumber();}

    public void setMinPlayerNumber(Integer number) {getInfo().setMinPlayerNumber(number);}

    public List<Integer> getTeamSizes() {return getInfo().getTeamSizes();}

    public void setTeamSizes(List<Integer> sizes) {getInfo().setTeamSizes(sizes);}

    public Type.Availability getTeamMergeAvailability() {return getInfo().getTeamMergeAvailability();}

    public void setTeamMergeAvailability(Type.Availability availability) {getInfo().setTeamMergeAvailability(availability);}

    public boolean isEqualTeamSizeRequired() {return getInfo().isEqualTeamSizeRequired();}

    public void requireEqualTeamSize(boolean require) {getInfo().requireEqualTeamSize(require);}

    public List<String> getDescription() {return getInfo().getDescription();}

    public void setDescription(List<String> description) {getInfo().setDescription(description);}

    @Override
    public de.timesnake.database.util.game.DbTmpGame toLocal() {
        return new DbCachedTmpGame(this);
    }

    @Override
    public de.timesnake.database.util.game.DbTmpGame toDatabase() {
        return this;
    }

}
