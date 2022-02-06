package de.timesnake.database.core.user;

import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;
import java.util.UUID;

public class DatabaseUsers extends DatabaseConnector implements de.timesnake.database.util.user.DatabaseUsers {

    private final InfosTable infosTable;
    private final PunishmentsTable punishmentsTable;
    private final MailsTable mailsTable;

    private final String infoTableName;
    private final String punishmentTableName;
    private final String mailsTableName;

    public DatabaseUsers(String name, String url, String user, String password, String infoTable, String punishmentTable, String mailsTable) {
        super(name, url, user, password);
        this.infoTableName = infoTable;
        this.punishmentTableName = punishmentTable;
        this.mailsTableName = mailsTable;

        this.infosTable = new InfosTable(this, this.infoTableName);
        this.punishmentsTable = new PunishmentsTable(this, this.punishmentTableName);
        this.mailsTable = new MailsTable(this, this.mailsTableName);
    }

    public void createTables() {
        this.infosTable.create();
        this.punishmentsTable.create();
        this.mailsTable.create();
    }

    public void backupTables() {
        this.infosTable.backup();
        this.punishmentsTable.backup();
        this.mailsTable.backup();
    }

    @Override
    public void addUser(UUID uuid, String name, String permGroup, String server) {
        if (!this.infosTable.containsPlayer(uuid)) {
            this.infosTable.addPlayer(uuid, name, permGroup, server);
        }
        if (!this.punishmentsTable.contains(uuid)) {
            this.punishmentsTable.addPlayer(uuid, name);
        }
    }

    @Override
    public DbUser getUser(UUID uuid) {
        if (uuid != null) {
            return new DbUser(this, uuid, this.infoTableName, this.punishmentTableName, this.mailsTableName, this.punishmentsTable, this.mailsTable);
        }
        return null;
    }

    @Override
    public DbUser getUser(String name) {
        return new DbUser(this, this.infosTable.getUniqueIdFromName(name), this.infoTableName, this.punishmentTableName, this.mailsTableName, this.punishmentsTable, this.mailsTable);
    }

    @Override
    public boolean containsUser(UUID uuid) {
        if (uuid != null) {
            return this.infosTable.containsPlayer(uuid);
        }
        return false;
    }

    @Override
    public boolean containsUser(String name) {
        return this.infosTable.containsPlayer(name);
    }

    @Override
    public Collection<UUID> getUsersUuid() {
        return this.infosTable.getPlayerUniqueIds();
    }

    @Override
    public Collection<String> getUsersName() {
        return this.infosTable.getPlayerNames();
    }


}
