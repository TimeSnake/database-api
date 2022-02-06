package de.timesnake.database.core.user;

import de.timesnake.channel.api.message.ChannelUserMessage;
import de.timesnake.channel.main.NetworkChannel;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.user.DbPunishment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PunishmentsTable extends PlayersTable {

    public PunishmentsTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.User.PUNISH_TYPE);
        super.addColumn(Column.User.PUNISH_DATE);
        super.addColumn(Column.User.PUNISH_CASTIGATOR);
        super.addColumn(Column.User.PUNISH_REASON);
        super.addColumn(Column.User.PUNISH_SERVER);
    }

    public void backup() {
        super.createBackup();
    }

    public void setPunishment(UUID uuid, String name, Type.Punishment type, Date date, String castigator, String reason, String server) {
        if (super.getFirst(Column.Support.NAME, new TableEntry<>(uuid, Column.User.UUID)) == null) {
            super.addPlayer(uuid, name);
        }
        super.set(type.getDatabaseValue(), Column.User.PUNISH_TYPE, new TableEntry<>(uuid, Column.User.UUID));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        super.set(df.format(date), Column.User.PUNISH_DATE, new TableEntry<>(uuid, Column.User.UUID));
        super.set(castigator, Column.User.PUNISH_CASTIGATOR, new TableEntry<>(uuid, Column.User.UUID));
        super.set(reason, Column.User.PUNISH_REASON, new TableEntry<>(uuid, Column.User.UUID));
        super.set(server, Column.User.PUNISH_SERVER, () -> NetworkChannel.getChannel().sendMessage(ChannelUserMessage.getPunishMessage(uuid)), new TableEntry<>(uuid, Column.User.UUID));
    }

    public void setPunishment(DbPunishment punishment) {
        this.setPunishment(punishment.getUniqueId(), punishment.getName(), punishment.getType(), punishment.getDate(), punishment.getCastigator(), punishment.getReason(), punishment.getServer());
    }

    public boolean contains(UUID uuid) {
        return super.getFirst(Column.User.PUNISH_TYPE, new TableEntry<>(uuid, Column.User.UUID)) != null;
    }

}
