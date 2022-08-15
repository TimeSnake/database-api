package de.timesnake.database.core.game;

import de.timesnake.database.core.game.info.DbTmpGameInfo;
import de.timesnake.database.core.game.team.DbTeam;
import de.timesnake.database.core.game.team.TeamsTable;
import de.timesnake.database.core.main.DatabaseManager;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.chat.ExTextColor;

import java.util.ArrayList;
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
    public void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio, String colorName) {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().addTeam(name, rank, prefix, color, ratio, colorName);
        }
    }

    @Override
    public void removeTeam(String name) {
        if (this.loadTeamsTable()) {
            this.teamsTable.get().removeTeam(name);
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
    public Collection<String> getTeamNames() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeamNames();
        }
        return new ArrayList<>(0);
    }

    @Override
    public Collection<Integer> getTeamRanks() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeamRanks();
        }
        return new ArrayList<>(0);
    }

    @Override
    public Collection<de.timesnake.database.util.game.DbTeam> getTeams() {
        if (this.loadTeamsTable()) {
            return this.teamsTable.get().getTeams();
        }
        return new ArrayList<>(0);
    }

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
    @Deprecated
    public String getChatColorName() {return getInfo().getChatColorName();}

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {getInfo().setChatColorName(chatColorName);}

    @Override
    public ExTextColor getTextColor() {
        return getInfo().getTextColor();
    }

    @Override
    public void setTextColor(ExTextColor color) {
        getInfo().setTextColor(color);
    }

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
    public boolean hideTeams() {return getInfo().hideTeams();}

    @Override
    public void setHideTeams(boolean hide) {getInfo().setHideTeams(hide);}

    @Override
    public Type.Discord getDiscordType() {
        return getInfo().getDiscordType();
    }

    @Override
    public void setDiscordType(Type.Discord type) {
        getInfo().setDiscordType(type);
    }

    @Override
    public List<String> getDescription() {return getInfo().getDescription();}

    @Override
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
