package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DbPunishment extends DbPlayer implements de.timesnake.database.util.user.DbPunishment {

    public DbPunishment(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
        super(databaseConnector, uuid, nameTable);
    }

    @Override
    public void delete() {
        super.setWithKey(null, Column.User.PUNISH_TYPE);
        super.setWithKey(null, Column.User.PUNISH_DATE);
        super.setWithKey(null, Column.User.PUNISH_CASTIGATOR);
        super.setWithKey(null, Column.User.PUNISH_REASON);
        super.setWithKey(null, Column.User.PUNISH_SERVER);
    }

    @Nullable
    @Override
    public Type.Punishment getType() {
        String type = super.getFirstWithKey(Column.User.PUNISH_TYPE);
        if (type != null) {
            return Type.Punishment.getByDatabaseValue(type);
        }
        return null;
    }

    @Override
    public void setType(Type.Punishment type) {
        super.setWithKey(type.getDatabaseValue(), Column.User.PUNISH_TYPE);
    }

    @Nullable
    @Override
    public Date getDate() {
        String date = super.getFirstWithKey(Column.User.PUNISH_DATE);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (date != null) {
            try {
                return df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void setDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        super.setWithKey(df.format(date), Column.User.PUNISH_DATE);
    }

    @Nullable
    @Override
    public String getCastigator() {
        return super.getFirstWithKey(Column.User.PUNISH_CASTIGATOR);
    }

    @Override
    public void setCastigator(String castigator) {
        super.setWithKey(castigator, Column.User.PUNISH_CASTIGATOR);
    }

    @Nullable
    @Override
    public String getReason() {
        return super.getFirstWithKey(Column.User.PUNISH_REASON);
    }

    @Override
    public void setReason(String reason) {
        super.setWithKey(reason, Column.User.PUNISH_REASON);
    }

    @Nullable
    @Override
    public String getServer() {
        return super.getFirstWithKey(Column.User.PUNISH_SERVER);
    }

    @Override
    public void setServer(String server) {
        super.setWithKey(server, Column.User.PUNISH_SERVER);
    }

}
